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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.Model;
import logica.modelo.Telefono;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Telefono")
public class TelefonoResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TelefonoResource
     */
    public TelefonoResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.TelefonoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getTelefonosPorUsuario(@QueryParam("correo") String correo) {
        Usuario user = new Usuario();
        user.setEmail(correo);
        String json = new Gson().toJson(Model.instance().ObtenerTelefonos(user));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarTelefono(Telefono telefono) {
        //Lleva usuario el objeto
        boolean flag = Model.instance().RegistrarTelefono(telefono);
        if (flag == true)
        {
            String json = new Gson().toJson(Model.instance().ObtenerTelefonos(telefono.getUsuario()));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el telefono").build();
    
    }
}
