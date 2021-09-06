/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "tipo_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTicket.findAll", query = "SELECT t FROM TipoTicket t"),
    @NamedQuery(name = "TipoTicket.findByCodigo", query = "SELECT t FROM TipoTicket t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoTicket.findByDescripcion", query = "SELECT t FROM TipoTicket t WHERE t.descripcion = :descripcion")})
public class TipoTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTicket1")
    private Collection<TicketTour> ticketTourCollection;
    @JoinColumn(name = "Empresa", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario empresa;

    public TipoTicket() {
    }

    public TipoTicket(Integer codigo) {
        this.codigo = codigo;
    }

    public TipoTicket(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TicketTour> getTicketTourCollection() {
        return ticketTourCollection;
    }

    public void setTicketTourCollection(Collection<TicketTour> ticketTourCollection) {
        this.ticketTourCollection = ticketTourCollection;
    }

    public Usuario getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
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
        if (!(object instanceof TipoTicket)) {
            return false;
        }
        TipoTicket other = (TipoTicket) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.TipoTicket[ codigo=" + codigo + " ]";
    }
    
}
