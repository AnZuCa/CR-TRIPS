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
     public List<Tour> ObtenerListaDeseo(Usuario user)
    {
        List<Tour> tours =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Codigo,t.Nombre,t.Descripcion,t.foto,t.provincia,t.canton,t.categoria,c.descripcion,u.email,u.nombre from cr_trips.tour as t inner join cr_trips.categoria as c on t.Categoria = c.Codigo inner join cr_trips.Usuario as u on t.Empresa = u.email inner join cr_trips.Lista_deseo as ls on t.Codigo = ls.Tour  where ls.Usuario = ? ");
            pst.clearParameters();
            pst.setString(1, user.getEmail());
            rs = pst.executeQuery();
            if (rs.next()) {
                tours.add(DibujarTour(rs.getInt("Codigo"), rs.getString("Nombre"),rs.getString("Descripcion"),rs.getString("foto"),rs.getString("provincia"),rs.getString("canton"),rs.getInt("categoria"),rs.getString("des"),rs.getString("email"),rs.getString("nom")));

            }
            return tours;
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
        return tour;
    }
}
