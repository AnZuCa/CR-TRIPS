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
import logica.modelo.Comentario;
import logica.modelo.Incluye;
import logica.modelo.Model;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Comentario")
public class ComentarioResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ComentarioResource
     */
    public ComentarioResource() {
    }

    @GET
    @Path("/getAllComments")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getCometarioPorTour(@QueryParam("codigo") int codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerComentariosPorTour(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarComentario(Comentario comentario) {
        //Llevan usuario dentro del objeto
        boolean flag = Model.instance().RegistrarComentario(comentario);
        if (flag == true)
        {
            String json = new Gson().toJson(Model.instance().ObtenerComentariosPorTour(comentario.getTour().getCodigo()));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el comentario").build();
    
    }
}
