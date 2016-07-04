package za.co.solms.messaging;

import javax.ejb.*;
import javax.jms.*;

public class MessageProcessor implements MessageDrivenBean,
                                         MessageListener
{
  public void onMessage(Message message)
  {
    try
    {
      String msg = ((TextMessage)message).getText();
      
      System.out.println(msg);
    }  
    catch (ClassCastException e)
    {
      System.out.println("ERROR: Received non-text message.");
    }
    catch (JMSException eJMS)
    {
      System.out.println("JMS ERROR: " + eJMS.getMessage());  
    }  
  }
   
  public void setMessageDrivenContext
                (MessageDrivenContext context)
                  {this.context = context;}
                  
  public void ejbCreate() {System.out.println("MDB created.");}
                  
  public void ejbRemove() {System.out.println("MDB removed.");}
  
  private MessageDrivenContext context;
}                                         
