package za.co.solms.comms.email;

import java.util.Date;
import javax.interceptor.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;
import za.co.solms.astrology.AstrologyFeeder.PersonInfo;

/**
 * This interceptor is used to add the responsibility of send an email to
 * somebody who can extract value from being notified of a person having
 * requested an astrological forecast:
 */
public class NotifyForecastRequestInterceptor
{

  @AroundInvoke
  public Object notifyOfForecastRequest( InvocationContext ctx)
      throws Exception
  {
    logger.info("Intercepted " + ctx.getMethod().getName());

    try
    {

      // Use the JavaMail API to hard-code a message
      // (this should ideally not be performed in the
      // same thread as here, but rather by another EJB
      // in response to a JMS message dispatched from this
      // interceptor)
      MimeMessage message = new MimeMessage(session);
      message.setRecipient(
          Message.RecipientType.TO,
          new InternetAddress(recipient));
      message.setSubject("Astrological forecast requested.");
      String eMailAddress = ((PersonInfo) ctx
          .getParameters()[0])
          .getEMailAddress();
      message.setContent("Requester: "
          + eMailAddress, "text/plain");
      message.setSentDate(new Date());
      Transport.send(message);
      
    }
    catch (AddressException e)
    {
      logger.error("Invalid mail recipient", e);
    }
    catch (MessagingException e)
    {
      logger.error("Mail sending failed", e);
    }

    // Continue
    return ctx.proceed();
  }


  // A JavaMail session (injected)
  @Resource(mappedName="Mail")
  private Session session;

  // Who should receive the mail
  private static final String recipient = "fritz@solms.co.za";
  
  // Logging, using Log4J
  private static final Logger logger = Logger
      .getLogger(NotifyForecastRequestInterceptor.class);
}