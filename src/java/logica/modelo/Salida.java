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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "salida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salida.findAll", query = "SELECT s FROM Salida s"),
    @NamedQuery(name = "Salida.findByCodigo", query = "SELECT s FROM Salida s WHERE s.salidaPK.codigo = :codigo"),
    @NamedQuery(name = "Salida.findByLugar", query = "SELECT s FROM Salida s WHERE s.lugar = :lugar"),
    @NamedQuery(name = "Salida.findByFechahora", query = "SELECT s FROM Salida s WHERE s.fechahora = :fechahora"),
    @NamedQuery(name = "Salida.findByTourreserva", query = "SELECT s FROM Salida s WHERE s.salidaPK.tourreserva = :tourreserva")})
public class Salida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalidaPK salidaPK;
    @Basic(optional = false)
    @Column(name = "Lugar")
    private String lugar;
    @Basic(optional = false)
    @Column(name = "Fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @JoinColumn(name = "Tour_reserva", referencedColumnName = "Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TourReserva tourReserva;

    public Salida() {
    }

    public Salida(SalidaPK salidaPK) {
        this.salidaPK = salidaPK;
    }

    public Salida(SalidaPK salidaPK, String lugar, Date fechahora) {
        this.salidaPK = salidaPK;
        this.lugar = lugar;
        this.fechahora = fechahora;
    }

    public Salida(int codigo, int tourreserva) {
        this.salidaPK = new SalidaPK(codigo, tourreserva);
    }

    public SalidaPK getSalidaPK() {
        return salidaPK;
    }

    public void setSalidaPK(SalidaPK salidaPK) {
        this.salidaPK = salidaPK;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public TourReserva getTourReserva() {
        return tourReserva;
    }

    public void setTourReserva(TourReserva tourReserva) {
        this.tourReserva = tourReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salidaPK != null ? salidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salida)) {
            return false;
        }
        Salida other = (Salida) object;
        if ((this.salidaPK == null && other.salidaPK != null) || (this.salidaPK != null && !this.salidaPK.equals(other.salidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.Salida[ salidaPK=" + salidaPK + " ]";
    }
    
}
