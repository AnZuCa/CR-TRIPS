/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "incluye")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incluye.findAll", query = "SELECT i FROM Incluye i"),
    @NamedQuery(name = "Incluye.findByCodigo", query = "SELECT i FROM Incluye i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "Incluye.findByDescripcion", query = "SELECT i FROM Incluye i WHERE i.descripcion = :descripcion")})
public class Incluye implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;

    @JoinColumn(name = "Empresa", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario empresa;

    public Incluye() {
    }

    public Incluye(Integer codigo) {
        this.codigo = codigo;
    }

    public Incluye(Integer codigo, String descripcion) {
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
        if (!(object instanceof Incluye)) {
            return false;
        }
        Incluye other = (Incluye) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.Incluye[ codigo=" + codigo + " ]";
    }
    
}
