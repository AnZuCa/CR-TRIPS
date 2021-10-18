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
import logica.modelo.Incluye;
import logica.modelo.TicketTour;
import logica.modelo.TipoTicket;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOTipoTicket extends Conexion{
    public boolean RegistrarTipo_ticket(TipoTicket tt)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Tipo_Ticket(Empresa,Descripcion) values(?,?) ");
            pst.clearParameters();
            pst.setString(1, tt.getEmpresa().getEmail());
            pst.setString(2, tt.getDescripcion());

            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
      public List<TipoTicket> ObtenerTipoTicketPorEmpresa(String emailempresa)
    {
        List<TipoTicket> tipotickets =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Tipo_Ticket where Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, emailempresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipotickets.add(DibujarTipoTicket(rs.getInt("Codigo"),rs.getString("Empresa"),rs.getString("Descripcion")));

            }
            return tipotickets;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      
      public TipoTicket DibujarTipoTicket(int codigo, String empresa,String descripcion)
    {   
        return new TipoTicket(codigo,descripcion);
    }
}
