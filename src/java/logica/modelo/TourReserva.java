/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "Fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Basic(optional = false)
    @Column(name = "Fecha_llegada")
    @Temporal(TemporalType.DATE)
    private Date fechallegada;
    @Basic(optional = false)
    @Column(name = "Cantidad_tickets")
    private int cantidadtickets;
    @ManyToMany(mappedBy = "tourReservaCollection")
    private Collection<Incluye> incluyeCollection;
    @JoinTable(name = "recomendacion_tour", joinColumns = {
        @JoinColumn(name = "Tour_reserva_Codigo", referencedColumnName = "Codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "Recomendacion", referencedColumnName = "Codigo")})
    @ManyToMany
    private Collection<Recomendacion> recomendacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourReserva")
    private Collection<TicketTour> ticketTourCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourReserva")
    private Collection<Salida> salidaCollection;
    @JoinColumn(name = "Tour", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private Tour tour;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourreserva")
    private Collection<Reserva> reservaCollection;

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
    public Collection<Incluye> getIncluyeCollection() {
        return incluyeCollection;
    }

    public void setIncluyeCollection(Collection<Incluye> incluyeCollection) {
        this.incluyeCollection = incluyeCollection;
    }

    @XmlTransient
    public Collection<Recomendacion> getRecomendacionCollection() {
        return recomendacionCollection;
    }

    public void setRecomendacionCollection(Collection<Recomendacion> recomendacionCollection) {
        this.recomendacionCollection = recomendacionCollection;
    }

    @XmlTransient
    public Collection<TicketTour> getTicketTourCollection() {
        return ticketTourCollection;
    }

    public void setTicketTourCollection(Collection<TicketTour> ticketTourCollection) {
        this.ticketTourCollection = ticketTourCollection;
    }

    @XmlTransient
    public Collection<Salida> getSalidaCollection() {
        return salidaCollection;
    }

    public void setSalidaCollection(Collection<Salida> salidaCollection) {
        this.salidaCollection = salidaCollection;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
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
        return "presentacion.modelo.TourReserva[ codigo=" + codigo + " ]";
    }
    
}
