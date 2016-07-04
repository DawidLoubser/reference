package za.co.solms.ejb3.mail.test;

import javax.naming.InitialContext;

/**
 * @author fritz@solms.co.za
 *
 */
public class ConsoleClient 
{
  public void run()
  {
    try
    {
      System.out.println("Connecting to naming service ...");
      InitialContext context = new InitialContext();
      System.out.println("Looking up SendMailRemote ...");
      SendMailRemote mailSender = (SendMailRemote)context.lookup(SendMailRemote.class.getName());
      System.out.println("Sending mail ...");
      mailSender.sendMail("fritz@solms.co.za", "test", "Yippiiee");
      System.out.println("Mail sent.");
    }
    catch (Exception e) {e.printStackTrace();}
  }
    
  public static void main(String[] args) {new ConsoleClient().run();}
}
