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
import logica.modelo.Categoria;

/**
 *
 * @author hp
 */
public class DAOCategoria extends Conexion{
    
    public Categoria ObtenerCategoriaPorId(int id)
    {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("Select * from cr_trips.Categoria where Codigo = ? ");
            pst.clearParameters();
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return DibujarCategoria(rs.getInt("Codigo"),rs.getString("Descripcion"));

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    
    public List<Categoria> ObtenerCategorias()
    {
        List<Categoria> categorias =  new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("Select * from cr_trips.Categoria");
            pst.clearParameters();
            rs = pst.executeQuery();
            if (rs.next()) {
                categorias.add(DibujarCategoria(rs.getInt("Codigo"),rs.getString("Descripcion")));

            }
            return categorias;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return null;
    }
    public boolean RegistrarCategoria(Categoria categoria)
    {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = getConexion().prepareStatement("insert into cr_trips.Categoria(Descripcion) values(?) ");
            pst.clearParameters();
            pst.setString(1, categoria.getDescripcion());
            if (pst.executeUpdate() != 1) {
                return false;

            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        return false;
    }
    public Categoria DibujarCategoria(Integer codigo, String descripcion)
    {   
        return new Categoria(codigo,descripcion);
    }
     
    
    
}
