/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import logica.modelo.Categoria;
import logica.modelo.Model;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Categoria")
public class CategoriaResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    /**
     * Creates a new instance of CategoriaResource
     */
    public CategoriaResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.CategoriaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getCategoria(@QueryParam("codigo") Integer codigo) {
        String json = new Gson().toJson(Model.instance().ObtenerCategoriaPorId(codigo));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    @GET
    @Path("Categorias")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        String json = new Gson().toJson(Model.instance().ObtenerCategorias());
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    
    }
    /**
     * PUT method for updating or creating an instance of CategoriaResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
