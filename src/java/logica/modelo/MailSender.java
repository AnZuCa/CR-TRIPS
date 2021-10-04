/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.modelo;

import java.net.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;

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
    public  void sendEmailReserva(Usuario user) {
        String destinario="";
        String asunto="";
        String mensaje="";
        
        MimeMessage mail = new MimeMessage(GetSession());
        try{
            mail.setFrom(new InternetAddress(correo));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinario));
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

    public static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservacion en GetYourTour");
            String htmlCode="<h1>Reservacion realizada</h1> <br/> <h2>Esperamos que disfrute sus tours</h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    
}
