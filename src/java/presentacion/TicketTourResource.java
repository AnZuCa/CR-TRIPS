/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.Model;
import logica.modelo.RecomendacionTour;
import logica.modelo.TicketTour;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("TicketTour")
public class TicketTourResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TicketTourResource
     */
    public TicketTourResource() {
    }

    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarTicketTour(TicketTour tt) {
        //Lleva usuario dentro del objeto
        boolean flag = Model.instance().RegistrarTicketTour(tt);
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto de Ticket Tour");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el ticket tour").build();
    
    }
}
