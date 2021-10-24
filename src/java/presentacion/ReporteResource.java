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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logica.DAO.DAOReporte;

/**
 * REST Web Service
 *
 * @author hp
 */
@Path("Reporte")
public class ReporteResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReporteResource
     */
    public ReporteResource() {
    }

    /**
     * Retrieves representation of an instance of presentacion.ReporteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/ReporteUsuariosReserva")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getUsuariosReserva(@QueryParam("codigo") int codigo,@QueryParam("nombre") String nombre) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteUsuariosReserva(codigo);
         String nomeRelatorio= "Reservas "+nombre + ".pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
    @GET
    @Path("/ReporteExcursionesMenosReservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getExcursionesMenosReservas(@QueryParam("empresa") String empresa) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteExcursionesMenosReservas(empresa);
         String nomeRelatorio= "ExcursionesMenosReservas.pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
        @GET
    @Path("/ReporteExcursionesMasReservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getExcursionesMasReservas(@QueryParam("empresa") String empresa) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteExcursionesMasReservas(empresa);
         String nomeRelatorio= "ExcursionesMasReservas.pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
    @GET
    @Path("/ReporteFechasConMasReservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getFechasConMasReservas(@QueryParam("empresa") String empresa) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteFechasMasReservas(empresa);
         String nomeRelatorio= "FechasConMasReservas.pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
    @GET
    @Path("/ReporteFechasConMenosReservas")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getFechasConMenosReservas(@QueryParam("empresa") String empresa) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteFechasMenosReservas(empresa);
         String nomeRelatorio= "FechasConMenosReservas.pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
    @GET
    @Path("/ReporteGananciasPorExcursiones")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getGananciasPorExcursiones(@QueryParam("empresa") String empresa) {
        DAOReporte daore = new DAOReporte();
        byte[] bytes=daore.ReporteGananciasPorExcursiones(empresa);
         String nomeRelatorio= "GananciasPorExcursiones.pdf";
        return Response.ok(bytes).type("application/pdf").header("Content-Disposition", "filename=\"" + nomeRelatorio + "\"").build();
    }
}
