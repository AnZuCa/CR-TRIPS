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
    private DAOComentario daocomentario = new DAOComentario();
    private DAOFoto daofoto = new DAOFoto();
    private DAOListaDeseo daolistadeseo = new DAOListaDeseo();
    private DAOTourReserva daotourreserva = new DAOTourReserva();
    private DAOTourReservaSalida daotourreservasalida = new DAOTourReservaSalida();
    private DAOTicketTour daotickettour = new DAOTicketTour();
    private DAOTipoTicket daotipoticket = new DAOTipoTicket();
    private DAOReserva daoreserva = new DAOReserva();
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
    public List<Tour> ObtenerTours()
    {
         return daotour.ObtenerTours();
    }
    public List<Tour> ObtenerToursPorFechaSalida(String fecha)
    {
         return daotour.ObtenerTourPorFechaSalida(fecha);
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
            tour.setCodigo(codigo);
            for(TicketTour t: tour.getTicketTourList()){
                t.setTour(tour);
                daotickettour.RegistrarTicketTour(t);
            }
            for(Incluye i: tour.getIncluyeList()){
                daoincluye.RegistrarIncluyeTour(codigo, tour.getCodigo());
            }
            for(Foto f: tour.getFotoList()){
                f.setTour(tour);
                daofoto.RegistrarFoto(f);
            }
            return true;
        }
        return false;
   
    }
     public boolean RegistrarRservaTour(TourReserva tourreserva)
    {
        boolean flag = daotourreserva.RegistrarTourReserva(tourreserva);
        if (flag ==true)
        {
            int codigo = daotourreserva.ObtenerUltimoTourReservaRegistrado(tourreserva.getTour().getCodigo());
            TourReserva tr = new TourReserva();
            tr.setCodigo(codigo);
            
            for(TourReservaSalida trs: tourreserva.getTourreservasalidalist()){
                trs.setTourreserva(tr);
                daotourreservasalida.RegistrarTourRservaSalida(trs);
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
       tour.setFotoList(daofoto.ObtenerFotosPorTour(codigo));
       return tour;
       
   }
   public List<TourReserva> ObtenerToursReservaFiltro(String nombreTour,String fechaSalida,String fechaLlegada,int CantidadTickes)
    {
        return daotourreserva.BuscarToursFiltro(nombreTour,CantidadTickes,fechaSalida,fechaLlegada);
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
    public boolean RegistrarComentario(Comentario comentario)
    {
        return daocomentario.RegistrarComentarioTour(comentario);
    }
    public List<Comentario> ObtenerComentariosPorTour(int tour)
    {
        return daocomentario.ObtenerComentariosPorTour(tour);
    }
    public boolean RegistrarFoto(Foto foto)
    {
        return daofoto.RegistrarFoto(foto);
    }
    public List<Foto> ObtenerFotosPorTour(int tour)
    {
        return daofoto.ObtenerFotosPorTour(tour);
    }
    public boolean RegistrarTourListaDeseo(ListaDeseo listadeseo)
    {
        return daolistadeseo.RegistrarTourDeseo(listadeseo);
    }
    public List<Tour> ObtenerListaDeseo(Usuario user)
    {
        return daolistadeseo.ObtenerListaDeseo(user);
    }
    public boolean RegistrarTipoTicket(TipoTicket tipoticket)
    {
        return daotipoticket.RegistrarTipo_ticket(tipoticket);
    }
    public boolean RegistrarReserva(Reserva reserva)
    {
        return daoreserva.RegistrarReserva(reserva);
    }
    public List<TipoTicket> ObtenerTipoTicket(Usuario user)
    {
        return daotipoticket.ObtenerTipoTicketPorEmpresa(user.getEmail());
    }
    public List<Reserva> ObtenerMisReservas(Usuario user)
    {
        return daoreserva.ObtenerMisReservas(user);
    }
    public List<Reserva> ObtenerReservasPorTour(int codigo)
    {
        return daoreserva.ObtenerReservas(codigo);
    }
    public Reserva ObtenerMiReserva(int codigo)
    {
        return daoreserva.ObtenerMiReserva(codigo);
    }
    public List<TourReserva> ObtenerToursReserva(int tour)
    {
        return daotourreserva.ObtenerTourReserva(tour);
    }
    public List<TourReserva> ObtenerToursReservaPorEmpresa(String empresa)
    {
        return daotourreserva.ObtenerTourReservaPorEmpresa(empresa);
    }
    public TourReserva ObtenerTourReservaPorCodigo(int Codigo)
    {
        return daotourreserva.ObtenerTourReservaPorCodigo(Codigo);
    }
    public Usuario Login(Usuario user)
    {
        return daousuario.ObtenerUsuario(user);
    }
    
    
     public static void main(String[] args){
        Conexion con= new Conexion();
        
        Model modelo = new Model();
        
        
         System.out.println(modelo.ObtenerToursReservaFiltro("Manuel Antonio", "2021-01-01", "2021-02-01", 4));
        
 
    }
}
