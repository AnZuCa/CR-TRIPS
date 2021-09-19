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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByFecNacimiento", query = "SELECT u FROM Usuario u WHERE u.fecNacimiento = :fecNacimiento"),
    @NamedQuery(name = "Usuario.findByTipousuario", query = "SELECT u FROM Usuario u WHERE u.tipousuario = :tipousuario"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "FecNacimiento")
    private String fecNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo_usuario")
    private int tipousuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Password")
    private String password;
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Subscripcion")
    private String subscripcion;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Tour> tourList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Comentario> comentarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<Tour> tourList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Pregunta> preguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<Recomendacion> recomendacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<Incluye> incluyeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Reserva> reservaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Tarjeta> tarjetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Telefono> telefonoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<TipoTicket> tipoTicketList;

    public Usuario() {
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String email, String nombre, String apellidos, String fecNacimiento, int tipousuario, String password, String subscripcion) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecNacimiento = fecNacimiento;
        this.tipousuario = tipousuario;
        this.password = password;
        this.subscripcion = subscripcion;
    }

    public String getSubscripcion() {
        return subscripcion;
    }

    public void setSubscripcion(String subscripcion) {
        this.subscripcion = subscripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(String fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public int getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(int tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @XmlTransient
    public List<Tour> getTourList1() {
        return tourList1;
    }

    public void setTourList1(List<Tour> tourList1) {
        this.tourList1 = tourList1;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @XmlTransient
    public List<Recomendacion> getRecomendacionList() {
        return recomendacionList;
    }

    public void setRecomendacionList(List<Recomendacion> recomendacionList) {
        this.recomendacionList = recomendacionList;
    }

    @XmlTransient
    public List<Incluye> getIncluyeList() {
        return incluyeList;
    }

    public void setIncluyeList(List<Incluye> incluyeList) {
        this.incluyeList = incluyeList;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @XmlTransient
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    @XmlTransient
    public List<TipoTicket> getTipoTicketList() {
        return tipoTicketList;
    }

    public void setTipoTicketList(List<TipoTicket> tipoTicketList) {
        this.tipoTicketList = tipoTicketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.modelo.Usuario[ email=" + email + " ]";
    }
    
}
