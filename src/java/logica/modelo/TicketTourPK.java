/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hp
 */
@Embeddable
public class TicketTourPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Tour_reserva")
    private int tourreserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_Ticket")
    private int tipoTicket;

    public TicketTourPK() {
    }

    public TicketTourPK(int tourreserva, int tipoTicket) {
        this.tourreserva = tourreserva;
        this.tipoTicket = tipoTicket;
    }

    public int getTourreserva() {
        return tourreserva;
    }

    public void setTourreserva(int tourreserva) {
        this.tourreserva = tourreserva;
    }

    public int getTipoTicket() {
        return tipoTicket;
    }

    public void setTipoTicket(int tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tourreserva;
        hash += (int) tipoTicket;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketTourPK)) {
            return false;
        }
        TicketTourPK other = (TicketTourPK) object;
        if (this.tourreserva != other.tourreserva) {
            return false;
        }
        if (this.tipoTicket != other.tipoTicket) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.TicketTourPK[ tourreserva=" + tourreserva + ", tipoTicket=" + tipoTicket + " ]";
    }
    
}
