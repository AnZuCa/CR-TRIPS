/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.google.gson.Gson;
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

import javax.servlet.http.HttpServletRequest;
import logica.modelo.Recomendacion;
import logica.modelo.Usuario;


/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Recomendacion")
public class RecomendacionResource {
    @Context
    private HttpServletRequest request;
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
    public Response getRecomendacionesPorEmpresa() {
        HttpSession session = request.getSession(true);
        Usuario user = (Usuario) session.getAttribute("usuario");
        String json = new Gson().toJson(Model.instance().ObtenerRecomendacionesPorEmpresa(user.getEmail()));
        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarRecomendacion(Recomendacion recomendacion) {
        HttpSession session = request.getSession(true);
        Usuario user = (Usuario) session.getAttribute("usuario");
        boolean flag = Model.instance().RegistrarRecomendacion(recomendacion, user);
        if (flag == true)
        {
            String json = new Gson().toJson(Model.instance().ObtenerRecomendacionesPorEmpresa(user.getEmail()));
            return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar la recomendación").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                        .header("Access-Control-Allow-Credentials", "true") 
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600").build();
    
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
