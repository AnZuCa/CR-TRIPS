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
import java.util.Set;
import logica.modelo.Categoria;
import logica.modelo.Conexion;
import logica.modelo.Tarjeta;
import logica.modelo.Tour;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOTour extends Conexion{
    public List<Tour> ObtenerTourPorProvincia(String provincia)
    {
        List<Tour> tour =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email  where t.provincia = ? ");
            pst.clearParameters();
            pst.setString(1, provincia);
            rs = pst.executeQuery();
            if (rs.next()) {
                tour.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom")));

            }
            return tour;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public List<Tour> ObtenerTourPorCanton(String canton)
    {
        List<Tour> tour =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email  where t.Canton = ? ");
            pst.clearParameters();
            pst.setString(1, canton);
            rs = pst.executeQuery();
            if (rs.next()) {
                tour.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom")));

            }
            return tour;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public List<Tour> ObtenerTourPorCategoria(int categoria)
    {
        List<Tour> tour =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email  where t.Categoria = ? ");
            pst.clearParameters();
            pst.setInt(1, categoria);
            rs = pst.executeQuery();
            if (rs.next()) {
                tour.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom")));

            }
            return tour;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public int ObtenerUltimoTourRegistrado(String email)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("SELECT MAX(Codigo) AS Codigo FROM cr_trips.Tour where Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("Codigo");

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return 0;
    }
    public List<Tour> ObtenerTourPorEmpresa(String email_empresa)
    {
        List<Tour> tour =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email  where t.Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, email_empresa);
            rs = pst.executeQuery();
            if (rs.next()) {
                tour.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom")));

            }
            return tour;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Tour ObtenerTour(int codigo)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email  where t.codigo = ?");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom"));

            }

        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarTour(Tour tour)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Tour(Nombre,Descripcion,Foto,Provincia,Canton,Categoria,Empresa) values(?,?,?,?,?,?,?) ");
            pst.clearParameters();
            pst.setString(1, tour.getNombre());
            pst.setString(2, tour.getDescripcion());
            pst.setString(3, tour.getFoto());
            pst.setString(4, tour.getProvincia());
            pst.setString(5, tour.getCanton());
            pst.setInt(6, tour.getCategoria().getCodigo());
            pst.setString(7, tour.getUsuario().getEmail());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Tour DibujarTour(Integer codigo, String nombre,String descripcion, String foto,String provincia,String canton,Integer categoria,String des, String email, String nom )
    {   
        Tour tour = new Tour(codigo,nombre,descripcion,foto,provincia,canton);
        tour.setCategoria(new Categoria(categoria,des));
        Usuario user = new Usuario();
        user.setEmail(email);
        user.setNombre(nom);
        tour.setUsuario(user);
        return tour;
    }
    
    public Tour DibujarTour(Integer codigo, String nombre,String descripcion, String foto,String provincia,String canton)
    {   
        return new Tour(codigo,nombre,descripcion,foto,provincia,canton);
    }
}
