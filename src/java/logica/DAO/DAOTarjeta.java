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
import logica.modelo.Tarjeta;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOTarjeta extends Conexion{
    public List<Tarjeta> ObtenerTarjetas(Usuario user)
    {
        List<Tarjeta> tarjetas =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Tarjeta where Usuario = ? ");
            pst.clearParameters();
            pst.setString(1, user.getEmail());
            rs = pst.executeQuery();
            while (rs.next()) {
                tarjetas.add(DibujarTarjeta(rs.getInt("Codigo"), rs.getString("Numero"),rs.getString("MMYYY"),rs.getInt("CVC"),rs.getString("Titular")));

            }
            return tarjetas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarTarjeta(Tarjeta tarjeta)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Tarjeta(Numero, MMYYY, CVC, Usuario,Titular) values(?,?,?,?,?) ");
            pst.clearParameters();
            pst.setString(1,tarjeta.getNumero());
            pst.setString(2, tarjeta.getMmyyy());
            pst.setInt(3, tarjeta.getCvc());
            pst.setString(4, tarjeta.getUsuario().getEmail());
            pst.setString(5, tarjeta.getTitular());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Tarjeta DibujarTarjeta(Integer codigo, String numero, String mmyy, Integer cvc, String titular)
    {   
        return new Tarjeta(codigo,numero,mmyy,cvc, titular);
    }
}
