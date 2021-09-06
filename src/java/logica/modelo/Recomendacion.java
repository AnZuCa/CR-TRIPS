/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "recomendacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recomendacion.findAll", query = "SELECT r FROM Recomendacion r"),
    @NamedQuery(name = "Recomendacion.findByCodigo", query = "SELECT r FROM Recomendacion r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Recomendacion.findByDescripcion", query = "SELECT r FROM Recomendacion r WHERE r.descripcion = :descripcion")})
public class Recomendacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @ManyToMany(mappedBy = "recomendacionCollection")
    private Collection<TourReserva> tourReservaCollection;
    @JoinColumn(name = "Empresa", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario empresa;

    public Recomendacion() {
    }

    public Usuario getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
    }

    public Usuario getempresa() {
        return empresa;
    }

    public void setempresa(Usuario empresa) {
        this.empresa = empresa;
    }

    public Recomendacion(Integer codigo) {
        this.codigo = codigo;
    }

    public Recomendacion(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Recomendacion(Integer codigo, String descripcion, Collection<TourReserva> tourReservaCollection, Usuario usuario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tourReservaCollection = tourReservaCollection;
        this.empresa = usuario;
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
    public Collection<TourReserva> getTourReservaCollection() {
        return tourReservaCollection;
    }

    public void setTourReservaCollection(Collection<TourReserva> tourReservaCollection) {
        this.tourReservaCollection = tourReservaCollection;
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
        if (!(object instanceof Recomendacion)) {
            return false;
        }
        Recomendacion other = (Recomendacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.Recomendacion[ codigo=" + codigo + " ]";
    }
    
}
