/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.modelo.Conexion;
import logica.modelo.TicketTour;
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
}
