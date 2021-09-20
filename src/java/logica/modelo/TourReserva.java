/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "tour_reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TourReserva.findAll", query = "SELECT t FROM TourReserva t"),
    @NamedQuery(name = "TourReserva.findByCodigo", query = "SELECT t FROM TourReserva t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TourReserva.findByFechasalida", query = "SELECT t FROM TourReserva t WHERE t.fechasalida = :fechasalida"),
    @NamedQuery(name = "TourReserva.findByFechallegada", query = "SELECT t FROM TourReserva t WHERE t.fechallegada = :fechallegada"),
    @NamedQuery(name = "TourReserva.findByCantidadtickets", query = "SELECT t FROM TourReserva t WHERE t.cantidadtickets = :cantidadtickets")})
public class TourReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_llegada")
    @Temporal(TemporalType.DATE)
    private Date fechallegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad_tickets")
    private int cantidadtickets;
    @ManyToMany(mappedBy = "tourReservaList")
    private List<Salida> salidaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourReserva")
    private List<TicketTour> ticketTourList;
    @JoinColumn(name = "Tour", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private Tour tour;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourreserva")
    private List<Reserva> reservaList;
    private List<TourReservaSalida> tourreservasalidalist;
            
            
    public TourReserva() {
    }

    public TourReserva(Integer codigo) {
        this.codigo = codigo;
    }

    public TourReserva(Integer codigo, Date fechasalida, Date fechallegada, int cantidadtickets) {
        this.codigo = codigo;
        this.fechasalida = fechasalida;
        this.fechallegada = fechallegada;
        this.cantidadtickets = cantidadtickets;
    }

    public List<TourReservaSalida> getTourreservasalidalist() {
        return tourreservasalidalist;
    }

    public void setTourreservasalidalist(List<TourReservaSalida> tourreservasalidalist) {
        this.tourreservasalidalist = tourreservasalidalist;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Date getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(Date fechallegada) {
        this.fechallegada = fechallegada;
    }

    public int getCantidadtickets() {
        return cantidadtickets;
    }

    public void setCantidadtickets(int cantidadtickets) {
        this.cantidadtickets = cantidadtickets;
    }

    @XmlTransient
    public List<Salida> getSalidaList() {
        return salidaList;
    }

    public void setSalidaList(List<Salida> salidaList) {
        this.salidaList = salidaList;
    }

    @XmlTransient
    public List<TicketTour> getTicketTourList() {
        return ticketTourList;
    }

    public void setTicketTourList(List<TicketTour> ticketTourList) {
        this.ticketTourList = ticketTourList;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TourReserva)) {
            return false;
        }
        TourReserva other = (TourReserva) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.TourReserva[ codigo=" + codigo + " ]";
    }
    
}
