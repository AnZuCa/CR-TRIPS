/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "ticket_tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketTour.findAll", query = "SELECT t FROM TicketTour t"),
    @NamedQuery(name = "TicketTour.findByPrecio", query = "SELECT t FROM TicketTour t WHERE t.precio = :precio"),
    @NamedQuery(name = "TicketTour.findByTourreserva", query = "SELECT t FROM TicketTour t WHERE t.ticketTourPK.tourreserva = :tourreserva"),
    @NamedQuery(name = "TicketTour.findByTipoTicket", query = "SELECT t FROM TicketTour t WHERE t.ticketTourPK.tipoTicket = :tipoTicket")})
public class TicketTour implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketTourPK ticketTourPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Precio")
    private double precio;
    @JoinColumn(name = "Tipo_Ticket", referencedColumnName = "Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoTicket tipoTicket;
    @JoinColumn(name = "Tour", referencedColumnName = "Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tour tour;

    public TicketTour() {
    }

    public TicketTour(TicketTourPK ticketTourPK) {
        this.ticketTourPK = ticketTourPK;
    }

    public TicketTour(TicketTourPK ticketTourPK, double precio) {
        this.ticketTourPK = ticketTourPK;
        this.precio = precio;
    }

    public TicketTour(int tourreserva, int tipoTicket) {
        this.ticketTourPK = new TicketTourPK(tourreserva, tipoTicket);
    }

    public TicketTourPK getTicketTourPK() {
        return ticketTourPK;
    }

    public void setTicketTourPK(TicketTourPK ticketTourPK) {
        this.ticketTourPK = ticketTourPK;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoTicket getTipoTicket() {
        return tipoTicket;
    }

    public void setTipoTicket(TipoTicket tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketTourPK != null ? ticketTourPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketTour)) {
            return false;
        }
        TicketTour other = (TicketTour) object;
        if ((this.ticketTourPK == null && other.ticketTourPK != null) || (this.ticketTourPK != null && !this.ticketTourPK.equals(other.ticketTourPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.TicketTour[ ticketTourPK=" + ticketTourPK + " ]";
    }
    
}
