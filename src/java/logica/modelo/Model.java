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
    private  DAOUsuario daousuario = new DAOUsuario();
    private DAOTelefono daotelefono = new DAOTelefono();
    private DAOTarjeta daotarjeta = new DAOTarjeta();
    private DAOTour daotour = new DAOTour();
    private DAOIncluye daoincluye = new DAOIncluye();
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
    public boolean RegistrarRecomendacion(Recomendacion recomendacion, Usuario user)
    {
        return daorecomendacion.RegistrarRecomendacion(recomendacion, user);
    }
    public boolean RegistrarCategoria(Categoria categoria)
    {
        return daocategoria.RegistrarCategoria(categoria);
    }
    public boolean RegistrarUsuario(Usuario user)
    {
        return daousuario.RegistrarUsuario(user);
    }
    public boolean RegistrarTelefono(Telefono telefono)
    {
        return daotelefono.RegistrarTelefono(telefono);
    }
    public boolean RegistrarTarjeta(Tarjeta tarjeta)
    {
        return daotarjeta.RegistrarTarjeta(tarjeta);
    }
    public List<Recomendacion> ObtenerRecomendacionesPorEmpresa(String emailempresa)
    {
        return daorecomendacion.ObtenerRecomendacionesPorEmpresa(emailempresa);
    }
    public List<Tarjeta> ObtenerTarjetasPorUsuario(Usuario user)
    {
         return daotarjeta.ObtenerTarjetas(user);
    }
    public List<Tour> ObtenerToursPorEmpresa(String email)
    {
         return daotour.ObtenerTourPorEmpresa(email);
    }
    public List<Tour> ObtenerToursPorCategoria(int categoria)
    {
         return daotour.ObtenerTourPorCategoria(categoria);
    }
    public List<Tour> ObtenerToursPorProvincia(String provincia)
    {
         return daotour.ObtenerTourPorProvincia(provincia);
    }
    public List<Tour> ObtenerToursPorCanton(String canton)
    {
         return daotour.ObtenerTourPorCanton(canton);
    }
    public List<Telefono> ObtenerTelefonos(Usuario user)
    {
        return daotelefono.ObtenerTelefonos(user);
    }
    public boolean RegistrarTour(Tour tour)
    {
        boolean flag = daotour.RegistrarTour(tour);
        if (flag ==true)
        {
            int codigo = daotour.ObtenerUltimoTourRegistrado(tour.getUsuario().getEmail());
            for(Recomendacion r: tour.getRecomendacionList()){
                daorecomendacion.RegistrarRecomendacionTour(codigo, tour.getUsuario().getEmail());
            }
            for(Incluye i: tour.getIncluyeList()){
                daoincluye.RegistrarIncluyeTour(codigo, tour.getUsuario().getEmail());
            }
        
       return true;
        }
      return false;
    }
    
   public Tour ObtenerTour(int codigo)
   {
       Tour tour = daotour.ObtenerTour(codigo);
       tour.setRecomendacionList(daorecomendacion.ObtenerRecomendacionesPorTour(codigo));
       tour.setIncluyeList(daoincluye.ObtenerIncluyesPorTour(codigo));
       return tour;
       
   }
    public List<Incluye> ObtenerIncluyesPorTour(int codigotour)
    {
        return daoincluye.ObtenerIncluyesPorTour(codigotour);
    }
    public boolean RegistrarIncluye(Incluye incluye, Usuario user)
    {
        return daoincluye.RegistrarIncluye(incluye, user);
    }
    public List<Incluye> ObtenerIncluyesPorEmpresa(String emailempresa)
    {
        return daoincluye.ObtenerIncluyesPorEmpresa(emailempresa);
    }
    public Usuario Login(Usuario user)
    {
        return daousuario.ObtenerUsuario(user);
    }
}
