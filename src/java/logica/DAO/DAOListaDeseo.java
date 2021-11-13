/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.modelo.CantidadUsuariosListaDeseo;
import logica.modelo.Categoria;
import logica.modelo.Conexion;
import logica.modelo.ListaDeseo;
import logica.modelo.Tour;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOListaDeseo extends Conexion{
    public boolean RegistrarTourDeseo(ListaDeseo listadeseo)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Lista_deseo values(?,?) ");
            pst.clearParameters();
            pst.setString(1, listadeseo.getUsuario().getEmail());
            pst.setInt(2, listadeseo.getTour().getCodigo());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public boolean EliminarTourDeseo(ListaDeseo listadeseo)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("delete from cr_trips.Lista_deseo where Usuario=? and Tour=? ");
            pst.clearParameters();
            pst.setString(1, listadeseo.getUsuario().getEmail());
            pst.setInt(2, listadeseo.getTour().getCodigo());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    
     public boolean eliminarTodoListaDeseo(String correo)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("delete from cr_trips.Lista_deseo where Usuario=?");
            pst.clearParameters();
            pst.setString(1, correo);
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
     public List<Tour> ObtenerListaDeseo(Usuario user)
    {
        List<Tour> tours =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre as nom from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email inner join cr_trips.Lista_deseo as ls on t.Codigo = ls.Tour  where ls.Usuario = ? ");
            pst.clearParameters();
            pst.setString(1, user.getEmail());
            rs = pst.executeQuery();
            while (rs.next()) {
                tours.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("descripcion"),rs.getString("email"),rs.getString("nom")));

            }
            return tours;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      public List<ListaDeseo> ObtenerListaDeseoPorTour(int codigo)
    {
        List<ListaDeseo> usuarios =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.nombre,u.email,u.nombre as nom,u.apellidos from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email inner join cr_trips.Lista_deseo as ls on t.Codigo = ls.Tour  where t.Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            while (rs.next()) {
                usuarios.add(DibujarUsuariosListaDeseo(rs.getString("email"),rs.getString("nom"),rs.getString("Apellidos"),rs.getString("Nombre")));

            }
            return usuarios;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      //Función dashboard
    public List<CantidadUsuariosListaDeseo> ObtenerCantidadUsuariosListaDeseo(String empresa)
    {
        List<CantidadUsuariosListaDeseo> cantidadusuarios =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Nombre,count(*) as Cantidad_Clientes from cr_trips.lista_deseo as ld inner join cr_trips.tour as t on ld.Tour = t.Codigo where t.Empresa=? and not exists(select * from cr_trips.reserva as r inner join cr_trips.tour_reserva_salida as trs on r.Tour_reserva_salida=trs.Salida and r.Tour_reserva=trs.Tour_reserva inner join cr_trips.tour_reserva as tr on tr.Codigo=trs.Tour_reserva where r.Usuario=ld.Usuario and tr.Tour=t.Codigo) group by t.nombre order by Cantidad_Clientes asc");
            pst.clearParameters();
            pst.setString(1, empresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                cantidadusuarios.add(DibujarCantidadUsuarios(rs.getString("Nombre"),rs.getLong("Cantidad_Clientes")));

            }
            return cantidadusuarios;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
     public Tour DibujarTour(Integer codigo, String nombre,String descripcion, String foto,String provincia,String canton,Integer categoria,String des, String email, String nom )
    {   
        Tour tour = new Tour(codigo,nombre,descripcion,foto,provincia,canton);
        tour.setCategoria(new Categoria(categoria,des));
        Usuario user = new Usuario();
        user.setEmail(email);
        user.setNombre(nom);
        tour.setUsuario(user);
        System.out.println(tour.toString());
        return tour;
    }
      public ListaDeseo DibujarUsuariosListaDeseo(String email, String nom,String apellidos,String tour )
    {   

        Usuario user = new Usuario();
        ListaDeseo ld = new ListaDeseo();
        user.setEmail(email);
        user.setNombre(nom);
        user.setApellidos(apellidos);
        Tour tour1 =new Tour();
        tour1.setNombre(tour);
        ld.setTour(tour1);
        ld.setUsuario(user);
        return ld;
    }
      public CantidadUsuariosListaDeseo DibujarCantidadUsuarios(String nombre, Long cantidadclientes)
      {
          return new CantidadUsuariosListaDeseo(nombre,cantidadclientes);
      }
}
