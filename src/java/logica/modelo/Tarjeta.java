/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t"),
    @NamedQuery(name = "Tarjeta.findByCodigo", query = "SELECT t FROM Tarjeta t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tarjeta.findByNumero", query = "SELECT t FROM Tarjeta t WHERE t.numero = :numero"),
    @NamedQuery(name = "Tarjeta.findByMmyyy", query = "SELECT t FROM Tarjeta t WHERE t.mmyyy = :mmyyy"),
    @NamedQuery(name = "Tarjeta.findByCvc", query = "SELECT t FROM Tarjeta t WHERE t.cvc = :cvc")})
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "Numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "MMYYY")
    private String mmyyy;
    @Basic(optional = false)
    @Column(name = "CVC")
    private int cvc;
    @JoinColumn(name = "Usuario", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Tarjeta() {
    }

    public Tarjeta(Integer codigo) {
        this.codigo = codigo;
    }

    public Tarjeta(Integer codigo, String numero, String mmyyy, int cvc) {
        this.codigo = codigo;
        this.numero = numero;
        this.mmyyy = mmyyy;
        this.cvc = cvc;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMmyyy() {
        return mmyyy;
    }

    public void setMmyyy(String mmyyy) {
        this.mmyyy = mmyyy;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
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
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.Tarjeta[ codigo=" + codigo + " ]";
    }
    
}
