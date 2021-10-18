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
import logica.modelo.Model;
import logica.modelo.Reserva;
import logica.modelo.TourReserva;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Reserva")
public class ReservaResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservaResource
     */
    public ReservaResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.ReservaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/MisReservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getMisReservas(@QueryParam("correo") String correo) {
        Usuario user = new Usuario();
        user.setEmail(correo);
        String json = new Gson().toJson(Model.instance().ObtenerMisReservas(user));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Reservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getReservasPorTour(@QueryParam("codigo") int codigotourreserva) {
        String json = new Gson().toJson(Model.instance().ObtenerReservasPorTour(codigotourreserva));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/MiReserva")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getMiReserva(@QueryParam("codigo") int codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerMiReserva(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarReserva(Reserva r) {
        //Lleva usuario el objeto
        boolean flag = Model.instance().RegistrarReserva(r) ;
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar la reserva").build();
    
    }
}
