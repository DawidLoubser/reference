
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

/**
 * A utility class tyically used by applications to notify a number
 * of people about an error encountered by the application in the
 * hope that one of them might just attend to the problem.
 *
 * The e-mail contains 
 * <ul>
 *  <li>The application name that encountered the problem.</li>
 *  <li>The name of the machine on which the problem was encountered.</li>
 *  <li>The problem encountered.</li>
 *  <li>A stack trace for the exception.</li>
 * </ul>
 *
 * <b>Usage:</b>
 * <code>
 * String[] notificationGroup = {"Fritz@SolmsTraining.co.za",
 *                               "Pieter@SolmsTraining.co.za"};
 *   
 *  new EMailNotifier().notify
 *           (this.getClass().getName(),"dummy error", notificationGroup);
 * </code>
 *
 * @author Fritz Solms
 */
public class EMailNotifier
{ 
  public void notify(Exception e, String applicationName, 
                     String[] notificationGroup)
  {
    try
    {
      String description = createExceptionDescription(e);
      
      String smtpServer = "mail.netlab.co.za";
      
      // create some properties and get the default Session
      Properties props = new Properties();
      props.put("mail.smtp.host", smtpServer);
    
      Session session = Session.getDefaultInstance(props, null);
      session.setDebug(true);
      
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("ErrorReporter@SolmsTraining.co.za"));
  
      InternetAddress[] recipients 
        = new InternetAddress[notificationGroup.length];
      
      for (int i=0; i<notificationGroup.length; ++i)
        recipients[i] = new InternetAddress(notificationGroup[i]);
                                  
      String machineName = "unknown";
      try
      {
        machineName = InetAddress.getLocalHost().getHostName();                                  
      } 
      catch (UnknownHostException ex) {}
      
      msg.setRecipients(Message.RecipientType.TO, recipients);
      msg.setSubject("Error in " + applicationName);
      msg.setSentDate(new Date());
      
      String msgText = "The following application encountered a problem and " +
        "needs YOUR attention:\n\n" +
        "Application: " + applicationName + "\n" +
        "Machine: " + machineName + "\n" +
        "Error: " + description + "\n";
      
      msg.setContent(msgText, "text/plain"); 
                  
      Transport.send(msg);
    } 
    catch (MessagingException mex) 
    {
      handleMessagingException(mex);
		}
	}

  private void handleMessagingException(MessagingException mex)
  {  
     MessagingException ex = mex;
     do 
     {
       ex.printStackTrace();
       if (ex instanceof SendFailedException) 
       {
          SendFailedException sfex = (SendFailedException)ex;
          Address[] invalid = sfex.getInvalidAddresses();
          if (invalid != null) 
          {
            System.out.println("    ** Invalid Addresses");
            if (invalid != null) 
            {
              for (int i = 0; i < invalid.length; i++) 
                System.out.println("         " + invalid[i]);
             }
          }
          Address[] validUnsent = sfex.getValidUnsentAddresses();
          if (validUnsent != null) 
          {
            System.out.println("    ** ValidUnsent Addresses");
            if (validUnsent != null) 
            {
              for (int i = 0; i < validUnsent.length; i++) 
                System.out.println("         "+validUnsent[i]);
            }
          }
          Address[] validSent = sfex.getValidSentAddresses();
          if (validSent != null) 
          {
            System.out.println("    ** ValidSent Addresses");
            if (validSent != null) 
            {
              for (int i = 0; i < validSent.length; i++) 
                System.out.println("         "+validSent[i]);
            }
          }
      }
      System.out.println("");
    } 
    while ((ex = (MessagingException)ex.getNextException()) != null);
  }
  
  private String createExceptionDescription(Exception e)
  {
    java.io.StringWriter stringWriter = new java.io.StringWriter();
    
    stringWriter.write(e.getMessage() + "\n\nStack trace:\n");

    e.printStackTrace(new java.io.PrintWriter(stringWriter));
    
    return stringWriter.toString();       
  }    
}
