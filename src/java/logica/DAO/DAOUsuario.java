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
import logica.modelo.Conexion;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOUsuario extends Conexion{
    public Usuario ObtenerUsuario(Usuario user)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Usuario where Email = ? and Password = ? ");
            pst.clearParameters();
            pst.setString(1,user.getEmail());
            pst.setString(2, user.getPassword());
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarUsuario(rs.getString("Email"),rs.getString("Nombre"),rs.getString("Apellidos"),rs.getString("FecNacimiento"),rs.getInt("Tipo_usuario"),rs.getString("Password"),rs.getString("Subscripcion"));

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Usuario ObtenerUsuarioReserva(String correo)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Usuario where Email = ? ");
            pst.clearParameters();
            pst.setString(1,correo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarUsuario(rs.getString("Email"),rs.getString("Nombre"),rs.getString("Apellidos"),rs.getString("FecNacimiento"),rs.getInt("Tipo_usuario"),"",rs.getString("Subscripcion"));

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarUsuario(Usuario usuario)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.usuario values(?,?,?,?,?,?,?) ");
            pst.clearParameters();
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getNombre());
            pst.setString(3, usuario.getApellidos());
            pst.setString(4, usuario.getFecNacimiento());
            pst.setInt(5, usuario.getTipousuario());
            pst.setString(6, usuario.getPassword());
            pst.setString(7, usuario.getSubscripcion());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Usuario DibujarUsuario(String email, String nombre, String apellidos, String nacimiento, Integer tipo, String password, String subscripcion)
    {   
        return new Usuario(email,nombre,apellidos,nacimiento,tipo,password,subscripcion);
    }
}
