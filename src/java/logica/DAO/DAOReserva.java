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
import logica.modelo.GananciaPorTour;
import logica.modelo.MailSender;
import logica.modelo.Reserva;
import logica.modelo.ReservasPorFecha;
import logica.modelo.ReservasPorTour;
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
                pst = getConexion().prepareStatement("insert into cr_trips.Reserva(Total, Cantidad_tickets, Tipo_pago,Usuario, Fecha,Tour_reserva_salida, Tour_reserva) values(?,?,?,?,?,?,?) ");
                pst.clearParameters();
                pst.setDouble(1, reserva.getTotal());
                pst.setInt(2, reserva.getCantidadtickets());
                pst.setInt(3, reserva.getTipopago());
                pst.setString(4, reserva.getUsuario().getEmail());
                pst.setDate(5, java.sql.Date.valueOf(timeStamp));
                pst.setInt(6,reserva.getTour_reserva_salida().getSalida().getCodigo());
                pst.setInt(7, reserva.getTourreserva().getCodigo());
                if (pst.executeUpdate() != 1) {
                    return false;

                }
                ActualizarCantidadAsientosTour(reserva.getTourreserva().getCodigo(),asientosdisponibles-reserva.getCantidadtickets());
                MailSender ms = new MailSender();
                ms.sendEmailReserva(reserva.getUsuario(), ObtenerUltimaReserva(reserva.getUsuario().getEmail()));
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
            while (rs.next()) {
                reservas.add(DibujarReserva(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva_salida"),rs.getInt("Tour_reserva")));

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
            while (rs.next()) {
                reservas.add(DibujarReserva2(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva_salida"),rs.getInt("Tour_reserva")));

            }
            return reservas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    //Función para dashboard de reporteria
    public List<ReservasPorFecha> ReservasPorFecha(String empresa)
    {
        List<ReservasPorFecha> reservas=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select tr.Fecha_salida,count(*) as Cantidad_reservas from cr_trips.reserva as r inner join cr_trips.usuario as u on r.Usuario= u.Email inner join cr_trips.Tour_reserva_salida as trs on " +
            "r.Tour_reserva_salida = trs.Salida and r.Tour_reserva = trs.Tour_reserva inner join cr_trips.Tour_reserva as tr on trs.Tour_reserva=tr.Codigo inner join cr_trips.Tour as t on tr.Tour=t.Codigo where t.Empresa=? group by tr.Fecha_salida order by tr.Fecha_salida ASC");
            pst.clearParameters();
            pst.setString(1, empresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                reservas.add(DibujarReserva3(rs.getDate("Fecha_salida"),rs.getLong("Cantidad_reservas")));

            }
            return reservas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
     //Función para dashboard de reporteria
    public List<ReservasPorTour> ReservasPorTour(String empresa)
    {
        List<ReservasPorTour> reservas=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Nombre,count(*) as Cantidad_reservas from cr_trips.reserva as r inner join cr_trips.usuario as u on r.Usuario= u.Email inner join cr_trips.Tour_reserva_salida as trs on " +
            "r.Tour_reserva_salida = trs.Salida and r.Tour_reserva = trs.Tour_reserva inner join cr_trips.Tour_reserva as tr on trs.Tour_reserva=tr.Codigo inner join cr_trips.Tour as t on tr.Tour=t.Codigo where t.Empresa=?  group by t.Nombre order by t.Nombre ASC");
            pst.clearParameters();
            pst.setString(1, empresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                reservas.add(DibujarReserva4(rs.getString("nombre"),rs.getLong("Cantidad_reservas")));

            }
            return reservas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public List<GananciaPorTour> GananciasPorTour(String empresa)
    {
        List<GananciaPorTour> ganancias=  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select t.Nombre,sum(r.Total) as Ganancia from cr_trips.reserva as r inner join cr_trips.usuario as u on r.Usuario= u.Email inner join cr_trips.Tour_reserva_salida as trs on " +
            "r.Tour_reserva_salida = trs.Salida and r.Tour_reserva = trs.Tour_reserva inner join cr_trips.Tour_reserva as tr on trs.Tour_reserva=tr.Codigo inner join cr_trips.Tour as t on tr.Tour=t.Codigo where t.Empresa=? group by t.Nombre order by Ganancia ASC");
            pst.clearParameters();
            pst.setString(1, empresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                ganancias.add(DibujarReserva5(rs.getString("nombre"),rs.getDouble("Ganancia")));

            }
            return ganancias;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Double GananciaDeTour(int codigo)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select sum(r.Total) as Ganancia from cr_trips.reserva where Tour_reserva_salida=? ");
            pst.clearParameters();
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Ganancia");

            }

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
                return DibujarReserva(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva_salida"),rs.getInt("Tour_reserva"));

            }

        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Reserva ObtenerUltimaReserva(String correo)
    {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Reserva where Codigo = (select MAX(Codigo) from cr_trips.Reserva where Usuario = ?) ");
            pst.clearParameters();
            pst.setString(1, correo);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarReserva(rs.getInt("Codigo"), rs.getDouble("Total"),rs.getInt("Cantidad_tickets"),rs.getInt("Tipo_pago"),rs.getString("Usuario"), rs.getDate("Fecha"),rs.getInt("Tour_reserva_salida"),rs.getInt("Tour_reserva"));

            }

        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Reserva DibujarReserva2(int codigo, double total, int cantidad_tickets, int tipo_pago,String usuario, Date fecha,int tour_reserva_salida, int tour_reserva)
    {
    
        DAOTourReserva tr=new DAOTourReserva();
        DAOTourReservaSalida trs = new DAOTourReservaSalida();
        DAOUsuario u=new DAOUsuario();
        Reserva r = new Reserva(codigo,total,cantidad_tickets, tipo_pago,fecha);
        r.setTourreserva(tr.ObtenerTourReservaPorCodigo(tour_reserva));
        r.setUsuario(u.ObtenerUsuarioReserva(usuario));
        r.setTour_reserva_salida(trs.ObtenerTourReservaSalida(tour_reserva, tour_reserva_salida));
        return r;
    }
    public Reserva DibujarReserva(int codigo, double total, int cantidad_tickets, int tipo_pago,String usuario, Date fecha,int tour_reserva_salida, int tour_reserva)
    {
    
        DAOTourReserva tr=new DAOTourReserva();
        DAOTourReservaSalida trs = new DAOTourReservaSalida();
        Reserva r = new Reserva(codigo,total,cantidad_tickets, tipo_pago,fecha);
        r.setTourreserva(tr.ObtenerTourReservaPorCodigo(tour_reserva));
        r.setTour_reserva_salida(trs.ObtenerTourReservaSalida(tour_reserva, tour_reserva_salida));
        return r;
    }
    
    public ReservasPorFecha DibujarReserva3(Date fechasalida,long cantidad)
    {
        return new ReservasPorFecha(fechasalida,cantidad);
    }
    public ReservasPorTour DibujarReserva4(String nombre,long cantidad)
    {
        return new ReservasPorTour(nombre,cantidad);
    }
    public GananciaPorTour DibujarReserva5(String nombre,double ganancia)
    {
        return new GananciaPorTour(nombre,ganancia);
    }
}
