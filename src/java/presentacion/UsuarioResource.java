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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.modelo.Model;
import logica.modelo.Usuario;



/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Usuario")
public class UsuarioResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }
    
    @POST
    @Path("/Login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(Usuario user) {
        //TODO return proper representation object
      
        try {
            
           HttpSession session = request.getSession(true);
           Usuario u = Model.instance().Login(user);
         
            if (u != null) {
                session.setAttribute("usuario", u);
                String json = new Gson().toJson(u);
               
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
                     
            } else {
                throw new NotFoundException();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error" + e.toString()).build();

        }
    }

}
