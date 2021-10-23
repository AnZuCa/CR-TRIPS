/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.DAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import logica.modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author hp
 */
public class DAOReporte extends Conexion {
    public byte[] ReporteUsuarios()
    {
    JRPdfExporter exporter = new JRPdfExporter();
     JasperReport reporte = null;
     String path="C:\\Users\\hp\\JaspersoftWorkspace\\MyReports\\Simple_Blue.jasper";
     String path1="C:\\Users\\hp\\JaspersoftWorkspace\\MyReports\\Simple_Blue.jrxml";
     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
     try
     {
         
        //File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(path));

        //System.out.println("Report compiled");
//InputStream jrxmlInput = new FileInputStream(new File(path1));
//         JasperDesign design = JRXmlLoader.load(jrxmlInput);
//        JasperReport jasperReport = JasperCompileManager.compileReport(design);
//        //JasperReport jasperReport = JasperCompileManager.compileReport(reportLocation);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConexion()); // datasource Service
         Map<String,Object> map = new HashMap<String,Object>();
         map.put("nom","Andres");
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
