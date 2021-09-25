/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByCodigo", query = "SELECT r FROM Reserva r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Reserva.findByTotal", query = "SELECT r FROM Reserva r WHERE r.total = :total"),
    @NamedQuery(name = "Reserva.findByCantidadtickets", query = "SELECT r FROM Reserva r WHERE r.cantidadtickets = :cantidadtickets"),
    @NamedQuery(name = "Reserva.findByTipopago", query = "SELECT r FROM Reserva r WHERE r.tipopago = :tipopago"),
    @NamedQuery(name = "Reserva.findByFecha", query = "SELECT r FROM Reserva r WHERE r.fecha = :fecha")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cantidad_tickets")
    private int cantidadtickets;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_pago")
    private int tipopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Tour_reserva", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private TourReserva tourreserva;
    @JoinColumn(name = "Usuario", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario usuario;

    
    
    public Reserva() {
    }

    public Reserva(Integer codigo) {
        this.codigo = codigo;
    }

    public Reserva(Integer codigo, double total, int cantidadtickets, int tipopago, Date fecha) {
        this.codigo = codigo;
        this.total = total;
        this.cantidadtickets = cantidadtickets;
        this.tipopago = tipopago;
        this.fecha = fecha;
    }



    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidadtickets() {
        return cantidadtickets;
    }

    public void setCantidadtickets(int cantidadtickets) {
        this.cantidadtickets = cantidadtickets;
    }

    public int getTipopago() {
        return tipopago;
    }

    public void setTipopago(int tipopago) {
        this.tipopago = tipopago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TourReserva getTourreserva() {
        return tourreserva;
    }

    public void setTourreserva(TourReserva tourreserva) {
        this.tourreserva = tourreserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.Reserva[ codigo=" + codigo + " ]";
    }
    
}
