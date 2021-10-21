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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.modelo.Conexion;
import logica.modelo.MailSender;
import logica.modelo.Salida;
import logica.modelo.Tour;
import logica.modelo.TourReserva;
import logica.modelo.TourReservaSalida;

/**
 *
 * @author hp
 */
public class DAOTourReserva extends Conexion{
    public boolean RegistrarTourReserva(TourReserva tourreserva)
    {
        MailSender mailsender = new MailSender();
        ConvertidorFechaSQL convert = new ConvertidorFechaSQL();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Tour_reserva(Tour,Fecha_salida,Fecha_llegada,Cantidad_Tickets) values(?,?,?,?) ");
            pst.clearParameters();
            pst.setInt(1, tourreserva.getTour().getCodigo());
            pst.setDate(2, convert.Convertidor(tourreserva.getFechasalida()));
            pst.setDate(3, convert.Convertidor(tourreserva.getFechallegada()));
            pst.setInt(4, tourreserva.getCantidadtickets());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            DAOListaDeseo ls = new DAOListaDeseo();
            mailsender.sendEmailUsuariosListaDeseo(ls.ObtenerListaDeseoPorTour(tourreserva.getTour().getCodigo()), tourreserva.getFechasalida());
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public int ObtenerUltimoTourReservaRegistrado(int codigo)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("SELECT MAX(Codigo) AS Codigo FROM cr_trips.Tour_reserva where Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("Codigo");

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return 0;
    }
    public List<TourReserva> ObtenerTourReserva(int tour)
    {
        List<TourReserva> tourreserva=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Tour_reserva where Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, tour);
            rs = pst.executeQuery();
            while (rs.next()) {
                tourreserva.add(DibujarTourReserva(rs.getInt("Codigo"), rs.getInt("Tour"), rs.getDate("Fecha_salida"),rs.getDate("Fecha_llegada"),rs.getInt("Cantidad_tickets")));

            }
            return tourreserva;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    
    public List<TourReserva> BuscarToursFiltro(String NombreTour,int canTickes,String FechaSalida,String FechaLlegada)
    {
        List<TourReserva> tourreserva=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement(
                    "select tr.Codigo,tr.Tour,tr.Fecha_salida, tr.Fecha_llegada,tr.Cantidad_tickets from cr_trips.Tour_reserva as tr inner join cr_trips.Tour as t on tr.Tour=t.Codigo where tr.Fecha_salida = ? and tr.Fecha_llegada = ? and tr.Cantidad_tickets = ? and t.Nombre = ?");
            pst.clearParameters();
            pst.setString(1, FechaSalida);
            pst.setString(2, FechaLlegada);
            pst.setInt(3, canTickes);
            pst.setString(4, NombreTour);
            rs = pst.executeQuery();
            while (rs.next()) {
                tourreserva.add(DibujarTourReserva(rs.getInt("Codigo"), rs.getInt("Tour"), rs.getDate("Fecha_salida"),rs.getDate("Fecha_llegada"),rs.getInt("Cantidad_tickets")));
            }
            return tourreserva;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    
    
    public List<TourReserva> ObtenerTourReservaPorEmpresa(String empresa)
    {
        List<TourReserva> tourreserva=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select tr.Codigo,tr.Tour,tr.Fecha_salida, tr.Fecha_llegada,tr.Cantidad_tickets from cr_trips.Tour_reserva as tr inner join cr_trips.Tour as t on tr.Tour=t.Codigo where t.Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, empresa);
            rs = pst.executeQuery();
            while(rs.next()) {
                tourreserva.add(DibujarTourReserva(rs.getInt("Codigo"), rs.getInt("Tour"), rs.getDate("Fecha_salida"),rs.getDate("Fecha_llegada"),rs.getInt("Cantidad_tickets")));

            }
            return tourreserva;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public TourReserva ObtenerTourReservaPorCodigo(int Codigo)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Tour_reserva where Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, Codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarTourReserva(rs.getInt("Codigo"), rs.getInt("Tour"), rs.getDate("Fecha_salida"),rs.getDate("Fecha_llegada"),rs.getInt("Cantidad_tickets"));

            }
     
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public TourReserva DibujarTourReserva(int codigo, int tour,Date fecha_salida,Date fecha_llegada,int cantidad)
    {
        DAOTourReservaSalida trs = new DAOTourReservaSalida();

        DAOTour daotour = new DAOTour();
        DAOFoto daofoto = new DAOFoto();
        TourReserva tr = new TourReserva(codigo,fecha_salida,fecha_llegada,cantidad);
        Tour tour1 = daotour.ObtenerTour(tour);
        tour1.setFotoList(daofoto.ObtenerFotosPorTour(tour));
        tr.setTour(tour1);

        tr.setTourreservasalidalist(trs.ObtenerTourReservaSalidas(tour));
        return tr;
    }
}
