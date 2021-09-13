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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "salida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salida.findAll", query = "SELECT s FROM Salida s"),
    @NamedQuery(name = "Salida.findByCodigo", query = "SELECT s FROM Salida s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Salida.findByLugar", query = "SELECT s FROM Salida s WHERE s.lugar = :lugar"),
    @NamedQuery(name = "Salida.findByFechahora", query = "SELECT s FROM Salida s WHERE s.fechahora = :fechahora")})
public class Salida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @JoinTable(name = "tour_reserva_salida", joinColumns = {
        @JoinColumn(name = "Salida", referencedColumnName = "Codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "Tour_reserva", referencedColumnName = "Codigo")})
    @ManyToMany
    private List<TourReserva> tourReservaList;

    public Salida() {
    }

    public Salida(Integer codigo) {
        this.codigo = codigo;
    }

    public Salida(Integer codigo, String lugar, Date fechahora) {
        this.codigo = codigo;
        this.lugar = lugar;
        this.fechahora = fechahora;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    @XmlTransient
    public List<TourReserva> getTourReservaList() {
        return tourReservaList;
    }

    public void setTourReservaList(List<TourReserva> tourReservaList) {
        this.tourReservaList = tourReservaList;
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
        if (!(object instanceof Salida)) {
            return false;
        }
        Salida other = (Salida) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.Salida[ codigo=" + codigo + " ]";
    }
    
}
