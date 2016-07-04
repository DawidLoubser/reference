package za.co.solms.ejb3.messaging;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="testQueue")
public class MessageProcessor implements MessageListener
{
  public void onMessage(Message message)
  {
    try
    {
      // Cast message to expected type, in order to access contents
      TextMessage textMessage = (TextMessage)message;
      
      System.out.println("----------------");
      System.out.println("Received message: ");
      System.out.println("Message text: " + textMessage.getText());
      System.out.println("Priority: " + message.getJMSPriority());
      System.out.println("Time stamp: " 
	       + new java.util.Date(message.getJMSTimestamp()));
      System.out.println("----------------");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
