/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "tour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t"),
    @NamedQuery(name = "Tour.findByCodigo", query = "SELECT t FROM Tour t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tour.findByNombre", query = "SELECT t FROM Tour t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tour.findByDescripcion", query = "SELECT t FROM Tour t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tour.findByFoto", query = "SELECT t FROM Tour t WHERE t.foto = :foto"),
    @NamedQuery(name = "Tour.findByProvincia", query = "SELECT t FROM Tour t WHERE t.provincia = :provincia"),
    @NamedQuery(name = "Tour.findByCanton", query = "SELECT t FROM Tour t WHERE t.canton = :canton")})
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "Foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "Provincia")
    private String provincia;
    @Basic(optional = false)
    @Column(name = "Canton")
    private String canton;
    @JoinTable(name = "lista_deseo", joinColumns = {
        @JoinColumn(name = "Tour", referencedColumnName = "Codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario", referencedColumnName = "Email")})
    @ManyToMany
    private List<Usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Comentario> comentarioCollection;
    @JoinColumn(name = "Categoria", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "Empresa", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario empresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Pregunta> preguntaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private Collection<Foto> fotoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<TourReserva> tourReservaCollection;

    public Tour() {
    }

    public Tour(Integer codigo) {
        this.codigo = codigo;
    }

    public Tour(Integer codigo, String nombre, String descripcion, String foto, String provincia, String canton) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.provincia = provincia;
        this.canton = canton;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @XmlTransient
    public List<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(List<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(List<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(List<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @XmlTransient
    public Collection<Foto> getFotoCollection() {
        return fotoCollection;
    }

    public void setFotoCollection(List<Foto> fotoCollection) {
        this.fotoCollection = fotoCollection;
    }

    @XmlTransient
    public List<TourReserva> getTourReservaCollection() {
        return tourReservaCollection;
    }

    public void setTourReservaCollection(List<TourReserva> tourReservaCollection) {
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
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presentacion.modelo.Tour[ codigo=" + codigo + " ]";
    }
    
}
