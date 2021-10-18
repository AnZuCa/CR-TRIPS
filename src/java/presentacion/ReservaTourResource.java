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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.ListaDeseo;
import logica.modelo.Model;
import logica.modelo.TourReserva;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("ReservaTour")
public class ReservaTourResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservaTourResource
     */
    public ReservaTourResource() {
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarReservaTour(TourReserva rt) {
        boolean flag = Model.instance().RegistrarRservaTour(rt) ;
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar").build();
    
    }
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getReservaTours(@QueryParam("tour") Integer tour) {
        String json = new Gson().toJson(Model.instance().ObtenerToursReserva(tour));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Empresa")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getReservaToursPorEmpresa(@QueryParam("correo") String correo) {
        String json = new Gson().toJson(Model.instance().ObtenerToursReservaPorEmpresa(correo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("/Codigo")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getReservaTour(@QueryParam("codigo") int codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerTourReservaPorCodigo(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
}
