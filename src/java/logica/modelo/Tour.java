/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Provincia")
    private String provincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Canton")
    private String canton;
    @ManyToMany(mappedBy = "tourList")
    private List<Incluye> incluyeList;
    @JoinTable(name = "lista_deseo", joinColumns = {
        @JoinColumn(name = "Tour", referencedColumnName = "Codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario", referencedColumnName = "Email")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinTable(name = "recomendacion_tour", joinColumns = {
        @JoinColumn(name = "Tour", referencedColumnName = "Codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "Recomendacion", referencedColumnName = "Codigo")})
    @ManyToMany
    private List<Recomendacion> recomendacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Comentario> comentarioList;
    @JoinColumn(name = "Categoria", referencedColumnName = "Codigo")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "Empresa", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Usuario empresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Pregunta> preguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<Foto> fotoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tour")
    private List<TourReserva> tourReservaList;
    private Usuario usuario;
    private List<TicketTour> ticketTourList;

    
    
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

    public List<TicketTour> getTicketTourList() {
        return ticketTourList;
    }

    public void setTicketTourList(List<TicketTour> ticketTourList) {
        this.ticketTourList = ticketTourList;
    }



    public Integer getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public List<Incluye> getIncluyeList() {
        return incluyeList;
    }

    public void setIncluyeList(List<Incluye> incluyeList) {
        this.incluyeList = incluyeList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Recomendacion> getRecomendacionList() {
        return recomendacionList;
    }

    public void setRecomendacionList(List<Recomendacion> recomendacionList) {
        this.recomendacionList = recomendacionList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
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
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @XmlTransient
    public List<Foto> getFotoList() {
        return fotoList;
    }

    public void setFotoList(List<Foto> fotoList) {
        this.fotoList = fotoList;
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
        return "logica.modelo.Tour[ codigo=" + codigo + " ]";
    }
    
}
