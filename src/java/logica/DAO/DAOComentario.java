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
import java.util.Date;
import java.util.List;
import logica.modelo.Comentario;
import logica.modelo.Conexion;
import logica.DAO.ConvertidorFechaSQL;
import logica.modelo.Usuario;
/**
 *
 * @author Andrés
 */
public class DAOComentario extends Conexion{
    public boolean RegistrarComentarioTour(Comentario comentario)
    {
        ConvertidorFechaSQL convert = new ConvertidorFechaSQL();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Comentario(Descripcion,Estrellas,Tour,Usuario,Fecha) values(?,?,?,?,?) ");
            pst.clearParameters();
            pst.setString(1, comentario.getDescripción());
            pst.setInt(2, comentario.getEstrellas());
            pst.setInt(3, comentario.getTour().getCodigo());
            pst.setString(4, comentario.getUsuario().getEmail());
            pst.setDate(5, convert.Convertidor(comentario.getFecha()));
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public List<Comentario> ObtenerComentariosPorTour(int codigo)
    {
        List<Comentario> comentarios =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select u.Email,u.Nombre,u.Apellidos,c.Codigo,c.Descripcion,c.Estrellas,c.Fecha from cr_trips.Comentario as c inner join cr_trips.Usuario as u on c.Usuario = u.Email where c.Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                comentarios.add(DibujarComentario(rs.getString("Email"),rs.getString("Nombre"),rs.getString("Apellidos"),rs.getInt("Coidgo"),rs.getString("Descripción"),rs.getInt("Estrellas"),rs.getDate("Fecha")));

            }
            return comentarios;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Comentario DibujarComentario(String email, String nombre, String Apellidos, int codigo, String descripcion, int estrellas, Date fecha)
    {   
        Comentario comentario = new Comentario(codigo,descripcion,estrellas,fecha);
        Usuario user = new Usuario ();
        user.setEmail(email);
        user.setNombre(nombre);
        user.setApellidos(Apellidos);
        comentario.setUsuario(user);
        return comentario;
    }
}
