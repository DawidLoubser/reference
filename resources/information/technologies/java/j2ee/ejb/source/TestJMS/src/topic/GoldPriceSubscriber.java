package TestJMS;

import javax.jms.*;
import javax.naming.*;
import javax.swing.*;
import java.awt.event.*;

public class GoldPriceSubscriber extends JFrame
{
  public static void main(String[] args)
  {
    new GoldPriceSubscriber().show();
  }

  public GoldPriceSubscriber()
  {
    createGUI();

    try
    {
      createGoldPriceSubscriber();
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

  private void createGoldPriceSubscriber()
                   throws NamingException, JMSException
  {
    Context context = new InitialContext();

    TopicConnectionFactory topicFactory
      = (TopicConnectionFactory)context.lookup
                ("ConnectionFactory");

    System.out.println("Connected to topic connection factory.");

    TopicConnection topicConnection
      = topicFactory.createTopicConnection();

    TopicSession topicSession
      = topicConnection.createTopicSession
          (false /*not transacted*/, Session.AUTO_ACKNOWLEDGE);

    Topic goldPriceTopic
      = (Topic)context.lookup("topic/GoldPrice");

    TopicSubscriber goldPriceSubscriber
      = topicSession.createSubscriber(goldPriceTopic);

    goldPriceSubscriber.setMessageListener(
      new MessageListener()
        {
          public void onMessage(Message msg)
          {
            JOptionPane.showMessageDialog
                  (GoldPriceSubscriber.this, msg,
                   "Received message",
                   JOptionPane.INFORMATION_MESSAGE);
          }
        });

    topicConnection.start();
  }
}
