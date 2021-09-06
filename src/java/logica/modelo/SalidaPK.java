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

/**
 *
 * @author hp
 */
@Embeddable
public class SalidaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Codigo")
    private int codigo;
    @Basic(optional = false)
    @Column(name = "Tour_reserva")
    private int tourreserva;

    public SalidaPK() {
    }

    public SalidaPK(int codigo, int tourreserva) {
        this.codigo = codigo;
        this.tourreserva = tourreserva;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTourreserva() {
        return tourreserva;
    }

    public void setTourreserva(int tourreserva) {
        this.tourreserva = tourreserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigo;
        hash += (int) tourreserva;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaPK)) {
            return false;
        }
        SalidaPK other = (SalidaPK) object;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (this.tourreserva != other.tourreserva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.SalidaPK[ codigo=" + codigo + ", tourreserva=" + tourreserva + " ]";
    }
    
}
