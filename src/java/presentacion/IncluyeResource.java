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
import logica.modelo.Incluye;
import logica.modelo.IncluyeTour;
import logica.modelo.Model;
import logica.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Incluye")
public class IncluyeResource {
    @Context
    private HttpServletRequest request;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of IncluyeResource
     */
    public IncluyeResource() {
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getIncluyesPorEmpresa(@QueryParam("correo") String correo) {
        String json = new Gson().toJson(Model.instance().ObtenerIncluyesPorEmpresa(correo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
     @GET
    @Path("/getIncluye")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getIncluyePorTour(@QueryParam("codigo") int codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerIncluyesPorTour(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("/Registrar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarIncluye(Incluye incluye) {
        //Lleva usuario dentro del objeto
        boolean flag = Model.instance().RegistrarIncluye(incluye, incluye.getEmpresa());
        if (flag == true)
        {
            String json = new Gson().toJson(Model.instance().ObtenerIncluyesPorEmpresa(incluye.getEmpresa().getEmail()));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el incluye").build();
    
    }
    
    @POST
    @Path("/RegistrarIncluyeTour")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response RegistrarIncluyeTour(IncluyeTour it) {
        
        boolean flag = Model.instance().RegistrarIncluyeTour(it);
        if (flag == true)
        {
            String json = new Gson().toJson("Registro correcto de Incluye Tour");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.SEE_OTHER).entity("Error al registrar el incluye tour").build();
    
    }
}
