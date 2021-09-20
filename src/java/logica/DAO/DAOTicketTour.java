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
import logica.modelo.ListaDeseo;
import logica.modelo.TicketTour;

/**
 *
 * @author hp
 */
public class DAOTicketTour extends Conexion{
    public boolean RegistrarTicketTour(TicketTour tickettour)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Ticket_tour values(?,?,?) ");
            pst.clearParameters();
            pst.setDouble(1, tickettour.getPrecio());
            pst.setInt(2, tickettour.getTourReserva().getCodigo());
            pst.setInt(3, tickettour.getTipoTicket1().getCodigo());
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
