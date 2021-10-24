/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import logica.modelo.Conexion;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;



/**
 *
 * @author hp
 */
public class DAOReporte extends Conexion {
    public byte[] ReporteUsuariosReserva(int Codigo)
    {
     JRPdfExporter exporter = new JRPdfExporter();
     JasperReport reporte = null;
     String path="C:\\Users\\hp\\Desktop\\CR-TRIPS\\src\\java\\Reportes\\Reservas_tour.jasper";
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("Codigo",1);
           reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,map,getConexion());
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
         exporter.exportReport();
        
         } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Error in generate Report..."+e);
            } finally {
            }
        return outputStream.toByteArray();
         
         
    }
    public byte[] ReporteExcursionesMenosReservas(String empresa)
    {
     JRPdfExporter exporter = new JRPdfExporter();
     JasperReport reporte = null;
     String path="C:\\Users\\hp\\Desktop\\CR-TRIPS\\src\\java\\Reportes\\Excursiones_menos_reservas.jasper";
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("Empresa",empresa);
           reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,map,getConexion());
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
         exporter.exportReport();
        
         } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Error in generate Report..."+e);
            } finally {
            }
        return outputStream.toByteArray();   
    }
    public byte[] ReporteExcursionesMasReservas(String empresa)
    {
     JRPdfExporter exporter = new JRPdfExporter();
     JasperReport reporte = null;
     String path="C:\\Users\\hp\\Desktop\\CR-TRIPS\\src\\java\\Reportes\\Excursiones_mas_reservas.jasper";
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("Empresa",empresa);
           reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,map,getConexion());
           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
         exporter.exportReport();
        
         } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Error in generate Report..."+e);
            } finally {
            }
        return outputStream.toByteArray();   
    }


}
