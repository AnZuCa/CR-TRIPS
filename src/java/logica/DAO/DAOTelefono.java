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
import logica.modelo.Telefono;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOTelefono extends Conexion{
    public List<Telefono> ObtenerTelefonos(Usuario user)
    {
        List<Telefono> telefonos =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Telefono where Usuario = ? ");
            pst.clearParameters();
            pst.setString(1, user.getEmail());
            rs = pst.executeQuery();
            if (rs.next()) {
                telefonos.add(DibujarTelefono(rs.getInt("Codigo"),rs.getInt("Numero")));

            }
            return telefonos;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarTelefono(Telefono telefono)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Telefono(Numero, Usuario) values(?,?) ");
            pst.clearParameters();
            pst.setInt(1,telefono.getCodigo());
            pst.setString(2, telefono.getUsuario().getEmail());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Telefono DibujarTelefono(Integer codigo, Integer numero)
    {   
        return new Telefono(codigo,numero);
    }
}
