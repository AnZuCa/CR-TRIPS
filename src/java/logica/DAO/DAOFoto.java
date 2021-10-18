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
import logica.modelo.Foto;
import logica.modelo.Incluye;
import logica.modelo.Usuario;

/**
 *
 * @author hp
 */
public class DAOFoto extends Conexion{
    public boolean RegistrarFoto(Foto foto)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Foto(Url, Tour) values(?,?) ");
            pst.clearParameters();
            pst.setString(1, foto.getUrl());
            pst.setInt(2, foto.getTour().getCodigo());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
      public List<Foto> ObtenerFotosPorTour(int tour)
    {
        List<Foto> fotos =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Foto where Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, tour);
            rs = pst.executeQuery();
            while (rs.next()) {
                fotos.add(DibujarFoto(rs.getInt("Codigo"),rs.getString("Url")));

            }
            return fotos;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public Foto DibujarFoto(int codigo, String url)
    {   
        return new Foto(codigo,url);
    }
}
