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
import logica.modelo.Pregunta;

/**
 *
 * @author hp
 */
public class DAOPregunta extends Conexion{
    public boolean RegistrarPregunta(Pregunta pregunta)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Pregunta(Usuario,Tour,Descripcion) values(?,?,?) ");
            pst.clearParameters();
            pst.setString(1, pregunta.getUsuario().getEmail());
            pst.setInt(2, pregunta.getTour().getCodigo());
            pst.setString(3, pregunta.getDescripcion());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
      public List<Pregunta> ObtenerPreguntasPorTour(int tour)
    {
        List<Pregunta> preguntas =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("select * from cr_trips.Pregunta where Tour = ? ");
            pst.clearParameters();
            pst.setInt(1, tour);
            rs = pst.executeQuery();
            while (rs.next()) {
                preguntas.add(DibujarPregunta(rs.getInt("Codigo"),rs.getString("Usuario"),rs.getInt("Tour"),rs.getString("Descripcion")));

            }
            return preguntas;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
      
    public Pregunta DibujarPregunta(int codigo, String usuario, int tour, String descripcion)
    {
        DAOUsuario daousuario = new DAOUsuario();
        DAORespuesta daorespuesta = new DAORespuesta();
        Pregunta pregunta = new Pregunta();
        pregunta.setUsuario(daousuario.ObtenerUsuarioReserva(usuario));
        pregunta.setRespuestaList(daorespuesta.ObtenerRespuestasPorPregunta(codigo));
        pregunta.setCodigo(codigo);
        pregunta.setDescripcion(descripcion);
        return pregunta;
    
    }
}
