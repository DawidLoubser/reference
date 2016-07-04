package TestJMS;

import javax.jms.*;
import javax.naming.*;
import javax.swing.*;
import java.awt.event.*;

public class MessageRecipient extends JFrame
{
  public static void main(String[] args)
  {
    new MessageRecipient().show();
  }
  
  public MessageRecipient()
  {
    createGUI();
    
    try
    {
      createMessageRecipient();
    }
    catch (Exception e)
    {
      e.printStackTrace();  
      System.exit(0);
    }  
  }  
  
  private void createGUI()
  {
    setTitle("Gold Price Subscriber");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(new java.awt.Dimension(150,150));
  }  
  
  private void createMessageRecipient() 
                   throws NamingException, JMSException
  {
    Context context = new InitialContext();
    
    QueueConnectionFactory queueFactory
      = (QueueConnectionFactory)context.lookup
                ("ConnectionFactory");
    
    System.out.println("Connected to queue connection factory.");
    
    QueueConnection queueConnection
      = queueFactory.createQueueConnection();
    
    QueueSession queueSession 
      = queueConnection.createQueueSession
          (false /*not transacted*/, Session.AUTO_ACKNOWLEDGE);
    
    Queue messageQueue = (Queue)context.lookup("queue/HiThere");                
    
    QueueReceiver messageRecipient 
      = queueSession.createReceiver(messageQueue);

    messageRecipient.setMessageListener(new MessageListener()
      {
        public void onMessage(Message msg)
        {
          JOptionPane.showMessageDialog
            (MessageRecipient.this, msg, "Received message",
              JOptionPane.INFORMATION_MESSAGE);
        }
      });

    queueConnection.start();
  }
}

