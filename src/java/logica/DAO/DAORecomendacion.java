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
import logica.modelo.Recomendacion;

/**
 *
 * @author hp
 */
public class DAORecomendacion extends Conexion {
     public List<Recomendacion> ObtenerRecomendacionesPorTour(Integer codigotour)
    {
        List<Recomendacion> categorias =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select r.Codigo, r.Descripcion from cr_trips.recomendacion as r inner join cr_trips.recomendacion_tour as rt on r.Codigo = rt.Recomendacion where rt.Tour_reserva_Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, codigotour);
            rs = pst.executeQuery();
            if (rs.next()) {
                categorias.add(DibujarRecomendacion(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return categorias;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      public List<Recomendacion> ObtenerRecomendacionesPorEmpresa(String emailempresa)
    {
        List<Recomendacion> recomendaciones =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Recomendacion where Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, emailempresa);
            rs = pst.executeQuery();
            if (rs.next()) {
                recomendaciones.add(DibujarRecomendacion(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return recomendaciones;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Recomendacion DibujarRecomendacion(Integer codigo, String descripcion)
    {   
        return new Recomendacion(codigo,descripcion);
    }
}
