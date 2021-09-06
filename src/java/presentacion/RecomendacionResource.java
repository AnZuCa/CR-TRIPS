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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.Model;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Recomendacion")
public class RecomendacionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RecomendacionResource
     */
    public RecomendacionResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.RecomendacionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getRecomendacionPorEmpresa(@QueryParam("email") String emailempresa) {
        String json = new Gson().toJson(Model.instance().ObtenerRecomendacionesPorEmpresa(emailempresa));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }

    /**
     * PUT method for updating or creating an instance of RecomendacionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
