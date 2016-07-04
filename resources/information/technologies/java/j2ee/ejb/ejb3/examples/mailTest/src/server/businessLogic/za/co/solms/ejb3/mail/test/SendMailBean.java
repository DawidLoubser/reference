package za.co.solms.ejb3.mail.test;

import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.AroundInvoke;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

/**
 * @author fritz@solms.co.za
 *
 */
@Stateless public class SendMailBean implements SendMailRemote 
{
    public void sendMail(String recipient, String subject, String msg) 
    {
        try
        {
            Session session = (Session)PortableRemoteObject.narrow(new InitialContext().lookup("java:Mail"), Session.class);
            
            MimeMessage message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(msg, "text/plain");
            message.setSentDate(new Date());
            Transport.send(message);
        }
        catch (NamingException e) {logger.error("Could not connect to mail service.", e);}
        catch (AddressException e) {logger.error("Invalid recipient.", e);}
        catch (MessagingException e) {logger.error("Messaging exception", e);}
    }

    private static final Logger logger = Logger.getLogger(SendMailBean.class);
}
