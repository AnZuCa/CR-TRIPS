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
import logica.modelo.Conexion;
import logica.modelo.Salida;
import logica.modelo.TicketTour;
import logica.modelo.TipoTicket;
import logica.modelo.TourReservaSalida;

/**
 *
 * @author hp
 */
public class DAOTourReservaSalida extends Conexion{
    public boolean RegistrarTourRservaSalida(TourReservaSalida tourreservasalida)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Tour_reserva_salida values(?,?) ");
            pst.clearParameters();
            pst.setInt(1, tourreservasalida.getSalida().getCodigo());
            pst.setInt(2, tourreservasalida.getTourreserva().getCodigo());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public List<TourReservaSalida> ObtenerTourReservaSalidas(int tour_reserva)
    {
        List<TourReservaSalida> tourreservasalida=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select s.Codigo, s.Lugar, s.Fecha_hora from cr_trips.Tour_reserva_salida as trs inner join cr_trips.Salida as s on trs.Salida = s.Codigo where trs.Tour_reserva = ? ");
            pst.clearParameters();
            pst.setInt(1, tour_reserva);
            rs = pst.executeQuery();
            if (rs.next()) {
                tourreservasalida.add(DibujarTourReservaSalida(rs.getInt("Codigo"), rs.getString("Lugar"), rs.getDate("Fecha_hora")));

            }
            return tourreservasalida;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public TourReservaSalida ObtenerTourReservaSalida(int tour_reserva,int codigo_salida)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select s.Codigo, s.Lugar, s.Fecha_hora from cr_trips.Tour_reserva_salida as trs inner join cr_trips.Salida as s on trs.Salida = s.Codigo where trs.Tour_reserva = ? and trs.Salida = ? ");
            pst.clearParameters();
            pst.setInt(1, tour_reserva);
            pst.setInt(2, codigo_salida);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarTourReservaSalida(rs.getInt("Codigo"), rs.getString("Lugar"), rs.getDate("Fecha_hora"));

            }
            
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public TourReservaSalida DibujarTourReservaSalida(int codigo, String lugar,Date fecha)
    {
        Salida s = new Salida(codigo,lugar,fecha);
        TourReservaSalida trs = new TourReservaSalida();
        trs.setSalida(s);
        return trs;
    }
}
