package TestJMS;

import javax.jms.*;
import javax.naming.*;
import javax.swing.*;
import java.awt.event.*;

public class GoldPricePublisher extends JFrame
{
  public static void main(String[] args)
  {
    new GoldPricePublisher().show();
  }

  public GoldPricePublisher()
  {
    createGUI();

    addSubmitListener();

    try
    {
      createGoldPricePublisher();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
  }

  private void createGUI()
  {
    setTitle("Gold Price Publisher");
    JPanel submitPanel = new JPanel();
    submitPanel.add(submitButton);
    submitPanel.add(goldPriceField);
    getContentPane().add(submitPanel);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }

  private void addSubmitListener()
  {
    submitButton.addActionListener( new ActionListener()
      {
        public void actionPerformed(ActionEvent event)
        {
          try
          {
            TextMessage message
              = topicSession.createTextMessage();
            message.setText(goldPriceField.getText());
            goldPricePublisher.publish(goldPriceTopic, message);
          }
          catch (javax.jms.JMSException e)
          {
            JOptionPane.showMessageDialog(GoldPricePublisher.this,
              e.getMessage(), "JMS Exception",
                JOptionPane.ERROR_MESSAGE);
          }
        }
      });
  }

  private void createGoldPricePublisher() throws NamingException,
                                                 JMSException
  {
    Context context = new InitialContext();

    TopicConnectionFactory topicFactory
      = (TopicConnectionFactory)context.lookup
                ("ConnectionFactory");

    System.out.println("Connected to topic connection factory.");

    topicSession
      = topicFactory.createTopicConnection().createTopicSession
          (false /*not transacted*/, Session.AUTO_ACKNOWLEDGE);

    goldPriceTopic = (Topic)context.lookup("topic/GoldPrice");

    goldPricePublisher
      = topicSession.createPublisher(goldPriceTopic);
  }

  private JButton submitButton = new JButton("submit");
  private JTextField goldPriceField = new JTextField("", 8);
  private TopicPublisher goldPricePublisher;
  private TopicSession topicSession;
  private Topic goldPriceTopic;
}

