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
import logica.modelo.TicketTour;
import logica.modelo.TipoTicket;


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
            pst.setInt(2, tickettour.getTour().getCodigo());
            pst.setInt(3, tickettour.getTipoTicket().getCodigo());

            if (pst.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public List<TicketTour> ObtenerTicketsTour(int tour)
    {
        List<TicketTour> ticketstour =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select ttt.Codigo, ttt.Descripcion, tt.Precio from cr_trips.Ticket_tour as tt inner join cr_trips.Tipo_Ticket as ttt on tt.Tipo_Ticket = ttt.Codigo  where tt.Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, tour);
            rs = pst.executeQuery();
            while (rs.next()) {
                ticketstour.add(DibujarTicketTour(rs.getInt("Codigo"), rs.getString("Descripcion"), rs.getDouble("Precio")));

            }
            return ticketstour;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public TicketTour DibujarTicketTour(int codigo, String descripcion,double precio)
    {
        TipoTicket tt = new TipoTicket(codigo,descripcion);
        TicketTour tit = new TicketTour();
        tit.setPrecio(precio);
        tit.setTipoTicket(tt);
        return tit;
    }
}
