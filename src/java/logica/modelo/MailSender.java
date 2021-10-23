/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.List;
import javax.mail.Session;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import org.jboss.logging.Logger;

/**
 *
 * @author hp
 */
public class MailSender {
    private String correo ="";
    private String password ="";
    
    public Session GetSession()
    {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return Session.getDefaultInstance(properties);
    }
    public  void sendEmailReserva(Usuario user, Reserva reserva) {
        String asunto="Reserva CR-TRIPS";
        String mensaje="<h1>Buenas"+user.getNombre()+" "+user.getApellidos()+".</h1><br>"
                +"<p>Le informamos que la reservación con código "+reserva.getCodigo()+" fue exitosa.</p><br><br>"+
                "Detalles de la reservación:</p><br>Nombre del tour:"+reserva.getTourreserva().getTour().getNombre()+"<br>"
                +"<p>Punto de salida:"+reserva.getTour_reserva_salida().getSalida().getLugar()+"</p><br>"+
                "<p>Hora de salida:"+reserva.getTour_reserva_salida().getSalida().getFechahora().toString()+"</p><br>"+
                "<p>Cantidad de Asientos:"+reserva.getCantidadtickets()+"</p><br>"+
                "<p>Hora de salida:"+reserva.getTotal()+"</p><br><br>"+
                "Muchas gracias por confiar en nosotros para sus vacaciones!<br>En caso de alguna duda escribir a:"+reserva.getTourreserva().getTour().getEmpresa().getEmail();
        
        MimeMessage mail = new MimeMessage(GetSession());
        try{
            mail.setFrom(new InternetAddress(correo));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");
            Transport transporte = GetSession().getTransport("smtp");
            transporte.connect(correo,password);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
        
        }
        catch (AddressException ex)
        {
          System.out.println(ex.toString());
        }
        catch (MessagingException ex){
            System.out.println(ex.toString());
        }
    }
    public  void sendEmailUsuariosListaDeseo(List<ListaDeseo> users, Date salida) {
        for(ListaDeseo ls: users)
        {
                    String asunto="";
        String mensaje="<h1>Buenas"+ls.getUsuario().getNombre()+" "+ls.getUsuario().getApellidos()+".</h1><br>"
                +"<p>Le informamos que tenemos una nueva excursión a "+ls.getTour().getNombre()+" el día"+salida.toString()+"</p><br><br>"+
                "No deje ir esta oportunidad, esperamos verlos en este nuevo viaje por los rincones de Costa Rica!";
        
        MimeMessage mail = new MimeMessage(GetSession());
        try{
            mail.setFrom(new InternetAddress(correo));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(ls.getUsuario().getEmail()));
            mail.setSubject(asunto);
            mail.setContent(mensaje,"text/html");
            Transport transporte = GetSession().getTransport("smtp");
            transporte.connect(correo,password);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
        
        }
        catch (AddressException ex)
        {
          System.out.println(ex.toString());
        }
        catch (MessagingException ex){
            System.out.println(ex.toString());
        }
        
        
        }

    }


    
}
