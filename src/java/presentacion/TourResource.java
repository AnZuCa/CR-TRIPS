/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.Incluye;
import logica.modelo.Model;
import logica.modelo.Tour;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Tour")
public class TourResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TourResource
     */
    public TourResource() {
    }
    @GET
    @Path("/Todos")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getTours() {
        String json = new Gson().toJson(Model.instance().ObtenerTours());
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Categoria")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getToursPorCategoria(@QueryParam("codigo") Integer codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerToursPorCategoria(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Canton")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getToursPorCanton(@QueryParam("canton") String canton) {
        String json = new Gson().toJson(Model.instance().ObtenerToursPorCanton(canton));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/FechaSalida")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getToursPorFechaSalida(@QueryParam("fecha1") String fecha1,@QueryParam("fecha2") String fecha2) {
        String json = new Gson().toJson(Model.instance().ObtenerToursPorFechaSalida(fecha1,fecha2));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Provincia")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getToursPorProvincia(@QueryParam("provincia") String provincia) {
        String json = new Gson().toJson(Model.instance().ObtenerToursPorProvincia(provincia));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Empresa")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getToursPorEmpresa(@QueryParam("empresa") String empresa) {
        String json = new Gson().toJson(Model.instance().ObtenerToursPorEmpresa(empresa));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
        @GET
        @Path("/Codigo")
        @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        public Response getTour(@QueryParam("codigoTour") int codigo) {
            String json = new Gson().toJson(Model.instance().ObtenerTour(codigo));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarTour(Tour tour) {
        //Lleva usuario el objeto
        Tour ultimoTour = Model.instance().RegistrarTour(tour);
        if (ultimoTour != null)
        {
            String json = new Gson().toJson(ultimoTour);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el tour").build();
    
    }
    
    @GET
    @Path("/BuscarTours")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getBuscarTours(@QueryParam("nombreTour") String nombreTour,@QueryParam("fechaSalida") String fechaSalida,@QueryParam("fechaLlegada") String fechaLlegada,@QueryParam("CantidadTickes") int CantidadTickes) {
        String prueba = "";
        String json = new Gson().toJson(Model.instance().ObtenerToursReservaFiltro(nombreTour,fechaSalida,fechaLlegada,CantidadTickes));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
}
