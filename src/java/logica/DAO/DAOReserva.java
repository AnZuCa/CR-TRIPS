/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logica.modelo.Conexion;
import logica.modelo.Reserva;
import logica.modelo.TourReserva;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOReserva extends Conexion{
    public boolean RegistrarReserva(Reserva reserva)
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            int asientosdisponibles=ObtenerCantidadAsientosDisponiblesTour(reserva.getTourreserva().getCodigo());
            if (reserva.getCantidadtickets()<=asientosdisponibles)
            {
                pst = getConexion().prepareStatement("insert into cr_trips.Reserva(Total, Cantidad_tickets, Tipo_pago,Usuario, Fecha, Tour_reserva) values(?,?,?,?,?,?) ");
                pst.clearParameters();
                pst.setDouble(1, reserva.getTotal());
                pst.setInt(2, reserva.getCantidadtickets());
                pst.setInt(3, reserva.getTipopago());
                pst.setString(4, reserva.getUsuario().getEmail());
                pst.setDate(5, java.sql.Date.valueOf(timeStamp));
                pst.setInt(6, reserva.getTourreserva().getCodigo());
                if (pst.executeUpdate() != 1) {
                    return false;

                }
                ActualizarCantidadAsientosTour(reserva.getTourreserva().getCodigo(),asientosdisponibles-reserva.getCantidadtickets());
                return true;
            }
            
            
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public boolean ActualizarCantidadAsientosTour(int codigo, int asientos)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("update cr_trips.Tour_reserva set Cantidad_tickets = ? where Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1,codigo);
            pst.setInt(2, asientos);
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public int ObtenerCantidadAsientosDisponiblesTour(int codigo)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("SELECT Cantidad_tickets FROM cr_trips.Tour_reserva where Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("Cantidad_tickets");

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return 0;
    }
    public List<Reserva> ObtenerMisReservas(Usuario user)
    {
        List<Reserva> reservas=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Reserva where Usuario = ? ");
            pst.clearParameters();
            pst.setString(1, user.getEmail());
            rs = pst.executeQuery();
            if (rs.next()) {
                reservas.add(DibujarReserva(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva")));

            }
            return reservas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public List<Reserva> ObtenerReservas(int codigo)
    {
        List<Reserva> reservas=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Reserva where Tour_reserva = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                reservas.add(DibujarReserva2(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva")));

            }
            return reservas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Reserva ObtenerMiReserva(int codigo)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Reserva where Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarReserva(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva"));

            }

        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Reserva DibujarReserva2(int codigo, double total, int cantidad_tickets, int tipo_pago,String usuario, Date fecha, int tour_reserva)
    {
    
        DAOTourReserva tr=new DAOTourReserva();
        DAOUsuario u=new DAOUsuario();
        Reserva r = new Reserva(codigo,total,cantidad_tickets, tipo_pago,fecha);
        r.setTourreserva(tr.ObtenerTourReservaPorCodigo(tour_reserva));
        r.setUsuario(u.ObtenerUsuarioReserva(usuario));
        return r;
    }
    public Reserva DibujarReserva(int codigo, double total, int cantidad_tickets, int tipo_pago,String usuario, Date fecha, int tour_reserva)
    {
    
        DAOTourReserva tr=new DAOTourReserva();
        Reserva r = new Reserva(codigo,total,cantidad_tickets, tipo_pago,fecha);
        r.setTourreserva(tr.ObtenerTourReservaPorCodigo(tour_reserva));
        return r;
    }
}
