package za.co.solms.ejb3.comms.email;

import java.util.Date;

import javax.ejb.AroundInvoke;
import javax.ejb.InvocationContext;
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
 * @author fritz
 *
 */
public class EMailInterceptor 
{

   @AroundInvoke
   public Object intercept(InvocationContext ctx) throws Exception
   {
       if (ctx.getMethod().getName().equals("buyCart"))
       {    
	       try
	       {
	           Session session = (Session)PortableRemoteObject.narrow(new InitialContext().lookup("java:Mail"), Session.class);
	           
	           MimeMessage message = new MimeMessage(session);
	           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	           message.setSubject("Order placed");
	           message.setContent("A new order has been placed.", "text/plain");
	           message.setSentDate(new Date());
	           Transport.send(message);
	       }
	       catch (NamingException e) {logger.error("Could not connect to mail service.", e);}
	       catch (AddressException e) {logger.error("Invalid recipient.", e);}
	       catch (MessagingException e) {logger.error("Messaging exception", e);}
       }       
       return ctx.proceed();
  }
       
  private static final String recipient = "fritz@solms.co.za";     
  private static final Logger logger = Logger.getLogger(EMailInterceptor.class);
}
