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
    public Response getMisReservas() {
        HttpSession session = request.getSession(true);
        Usuario user = (Usuario) session.getAttribute("usuario");
        String json = new Gson().toJson(Model.instance().ObtenerMisReservas(user));
        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
    }
    @GET
    @Path("/Reservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getReservasPorTour(@QueryParam("codigo") int codigotourreserva) {
        String json = new Gson().toJson(Model.instance().ObtenerReservasPorTour(codigotourreserva));
        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
    }
    @GET
    @Path("/MiReserva")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getMiReserva(@QueryParam("codigo") int codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerMiReserva(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarReserva(Reserva r) {
        HttpSession session = request.getSession(true);
        Usuario user = (Usuario) session.getAttribute("usuario");
        r.setUsuario(user);
        boolean flag = Model.instance().RegistrarReserva(r) ;
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto");
            return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar la reserva").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
    }
}
