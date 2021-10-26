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
import logica.modelo.Pregunta;
import logica.modelo.Respuesta;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Respuesta")
public class RespuestaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RespuestaResource
     */
    public RespuestaResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.RespuestaResource
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        public Response RegistrarRespuesta(Respuesta respuesta) {
    
        boolean flag = Model.instance().RegistrarRespuesta(respuesta);
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto de Pregunta Tour");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar la Pregunta tour").build();
    
    }
}
