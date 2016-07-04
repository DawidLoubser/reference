package za.co.solms.ejb3.messaging;

/**
 * @author fritz@solms.co.za
 */
import javax.jms.*;
import javax.naming.*;
import javax.swing.*;
import java.awt.event.*;

public class MessageSender extends JFrame
{
  public MessageSender()
  {
    createGUI();
    
    addSubmitListener();
    
    try
    {
      createMessageSender();
    }
    catch (Exception e)
    {
      e.printStackTrace();  
      System.exit(0);
    }  
  }  

  private void createGUI()
  {
    setTitle("MessageSender");
    JPanel messagePanel = new JPanel();
    messagePanel.setLayout(new java.awt.GridLayout(2,1));
    messagePanel.add(messageField);
    messagePanel.add(sendButton);
    getContentPane().add(messagePanel);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }  
   
  private void addSubmitListener()
  {  
    sendButton.addActionListener( new ActionListener()
      {
        public void actionPerformed(ActionEvent event)
        {
          try
          {
            TextMessage message 
              = queueSession.createTextMessage();
            message.setText(messageField.getText());
            messageSender.send(messageQueue, message);
            System.out.println("Message successfully sent");
          }
          catch (javax.jms.JMSException e)  
          {
            JOptionPane.showMessageDialog(MessageSender.this,
              e.getMessage(), "JMS Exception",
                JOptionPane.ERROR_MESSAGE);
          }     
        }
      });
  }      
  
  private void createMessageSender() throws NamingException,
                                                 JMSException
  {
    Context context = new InitialContext();

    QueueConnectionFactory queueFactory
      = (QueueConnectionFactory)context.lookup
                ("ConnectionFactory");

    System.out.println("Connected to Queue connection factory.");
    
    queueSession
      = queueFactory.createQueueConnection().createQueueSession
          (false /*not transacted*/, Session.AUTO_ACKNOWLEDGE);
    
    messageQueue = (Queue)context.lookup("queue/HiThere");                
    
    messageSender
      = queueSession.createSender(messageQueue);
  }
  
  public static void main(String[] args)
  {
    new MessageSender().setVisible(true);
  }
  
  private JButton sendButton = new JButton("send message");
  private JTextField messageField = new JTextField("", 40);  
  private QueueSender messageSender;
  private QueueSession queueSession;
  private Queue messageQueue;
  private static final long serialVersionUID = 23983298976543L;
}

