/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logica.modelo.Conexion;
import logica.modelo.Salida;
import logica.modelo.TourReservaSalida;

/**
 *
 * @author hp
 */
public class DAOSalida extends Conexion{
    public boolean RegistrarSalida(Salida salida)
    {
        ConvertidorFechaSQL conver = new ConvertidorFechaSQL();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Salida(Lugar,Fecha_hora) values(?,?) ");
            pst.clearParameters();
            pst.setString(1, salida.getLugar());
            pst.setTimestamp(2, conver.ConvertidorFechaHora(salida.getFechahora()));
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    
        public List<Salida> ObtenerSalidas()
    {
        List<Salida> salidas=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Salida");
            pst.clearParameters();
            rs = pst.executeQuery();
            while (rs.next()) {
                salidas.add(DibujarSalida(rs.getInt("Codigo"), rs.getString("Lugar"), rs.getDate("Fecha_hora")));

            }
            return salidas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Salida DibujarSalida(int codigo, String lugar, Date fecha)
    {
        return new Salida(codigo,lugar,fecha);
    }
}
