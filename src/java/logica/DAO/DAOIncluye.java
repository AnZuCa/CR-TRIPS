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
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOIncluye extends Conexion{
     public List<Incluye> ObtenerIncluyesPorTour(Integer codigotour)
    {
        List<Incluye> incluyes =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select r.Codigo, r.Descripcion from cr_trips.incluye as r inner join cr_trips.Incluye_tour as rt on r.Codigo = rt.Incluye where rt.Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, codigotour);
            rs = pst.executeQuery();
            while (rs.next()) {
                incluyes.add(DibujarIncluye(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return incluyes;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      public List<Incluye> ObtenerIncluyesPorEmpresa(String emailempresa)
    {
        List<Incluye> incluyes =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Incluye where Empresa = ? ");
            pst.clearParameters();
            pst.setString(1, emailempresa);
            rs = pst.executeQuery();
            while (rs.next()) {
                incluyes.add(DibujarIncluye(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return incluyes;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarIncluye(Incluye incluye,Usuario usuario)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Incluye(Descripcion, Empresa) values(?,?) ");
            pst.clearParameters();
            pst.setString(1, incluye.getDescripcion());
            pst.setString(2, usuario.getEmail());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public boolean RegistrarIncluyeTour(int incluye,String tour)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Incluye_tour values(?,?) ");
            pst.clearParameters();
            pst.setInt(1, incluye);
            pst.setString(2, tour);
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Incluye DibujarIncluye(Integer codigo, String descripcion)
    {   
        return new Incluye(codigo,descripcion);
    }
}
