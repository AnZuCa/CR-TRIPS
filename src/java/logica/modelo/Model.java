/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;
import java.util.List;
import logica.DAO.*;
/**
 *
 * @author hp
 */
public class Model {
    private  DAOCategoria daocategoria = new DAOCategoria();
    private  DAORecomendacion daorecomendacion = new DAORecomendacion();
    private static Model uniqueInstance;
    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance; 
    }
    
    public Categoria ObtenerCategoriaPorId(int codigo)
    {
        return daocategoria.ObtenerCategoriaPorId(codigo);
    }
    public List<Categoria> ObtenerCategorias()
    {
        return daocategoria.ObtenerCategorias();
    }
    public List<Recomendacion> ObtenerRecomendacionesPorTour(int codigotour)
    {
        return daorecomendacion.ObtenerRecomendacionesPorTour(codigotour);
    }
    public List<Recomendacion> ObtenerRecomendacionesPorEmpresa(String emailempresa)
    {
        return daorecomendacion.ObtenerRecomendacionesPorEmpresa(emailempresa);
    }
}
