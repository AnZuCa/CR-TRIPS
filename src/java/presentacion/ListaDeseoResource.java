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
import javax.ws.rs.DELETE;
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
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("ListaDeseo")
public class ListaDeseoResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ListaDeseoResource
     */
    public ListaDeseoResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.ListaDeseoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaDeseo(@QueryParam("correo") String correo) {
        Usuario user = new Usuario();
        user.setEmail(correo);
        String json = new Gson().toJson(Model.instance().ObtenerListaDeseo(user));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/CantidadUsuariosListaDeseo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCantidadUsuariosListaDeseo(@QueryParam("empresa") String empresa) {
        String json = new Gson().toJson(Model.instance().ObtenerCantidadUsuariosListaDeseo(empresa));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarTourDeseo(ListaDeseo tourdeseo) {
        //Llleva usuario el objeto
        boolean flag = Model.instance().RegistrarTourListaDeseo(tourdeseo) ;
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto de tour en lista de deseo");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el tour en lista de deseo").build();
    
    }
    @DELETE
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response EliminarTourDeseo(ListaDeseo tourdeseo) {
        //Llleva usuario el objeto
        boolean flag = Model.instance().EliminarTourListaDeseo(tourdeseo) ;
        if (flag == true)
        {
            String json = new Gson().toJson("Se eliminó corrrectamente el tour en lista de deseo");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al eliminar el tour en lista de deseo").build();
    
    }
}
