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
import logica.modelo.Pregunta;
import logica.modelo.Respuesta;

/**
 *
 * @author hp
 */
public class DAORespuesta extends Conexion {
        public boolean RegistrarRespuesta(Respuesta respuesta)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Respuesta(Pregunta, Descripcion) values(?,?) ");
            pst.clearParameters();
            pst.setInt(1, respuesta.getPregunta().getCodigo());
            pst.setString(2, respuesta.getDescripcion());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
      public List<Respuesta> ObtenerRespuestasPorPregunta(int pregunta)
    {
        List<Respuesta> respuestas =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Pregunta where Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, pregunta);
            rs = pst.executeQuery();
            while (rs.next()) {
                respuestas.add(DibujarRespuesta(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return respuestas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
     public Respuesta DibujarRespuesta(int codigo, String descripcion)
     {
         return new Respuesta(codigo,descripcion);
     }
}
