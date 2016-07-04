package za.co.solms.ejb3.mail.test;

/**
 * 
 * @author fritz@solms.co.za
 *
 */
public interface SendMail 
{
  public void sendMail(String recipient, String subject, String msg);
}
