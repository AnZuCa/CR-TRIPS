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
import logica.modelo.Usuario;

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

            pst = getConexion().prepareStatement("select r.Codigo, r.Descripcion from cr_trips.recomendacion as r inner join cr_trips.recomendacion_tour as rt on r.Codigo = rt.Recomendacion where rt.Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, codigotour);
            rs = pst.executeQuery();
            while (rs.next()) {
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
            while (rs.next()) {
                recomendaciones.add(DibujarRecomendacion(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return recomendaciones;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      
    public Recomendacion ObtenerUltimaRecomendacion()
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Recomendacion order by 1 desc limit 1 ");
            pst.clearParameters();
            rs = pst.executeQuery();
            while (rs.next()) {
                return DibujarRecomendacion(rs.getInt("Codigo"),rs.getString("Descripcion"));

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      
    public Recomendacion RegistrarRecomendacion(Recomendacion recomendacion,Usuario usuario)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Recomendacion(Descripcion, Empresa) values(?,?) ");
            pst.clearParameters();
            pst.setString(1, recomendacion.getDescripcion());
            pst.setString(2, usuario.getEmail());
            if (pst.executeUpdate() != 1) {
                return null;

            }
            return ObtenerUltimaRecomendacion();
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarRecomendacionTour(int recomendacion,int tour)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Recomendacion_tour values(?,?) ");
            pst.clearParameters();
            pst.setInt(1, recomendacion);
            pst.setInt(2, tour);
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Recomendacion DibujarRecomendacion(Integer codigo, String descripcion)
    {   
        return new Recomendacion(codigo,descripcion);
    }
}
