/*
 * ElectionClient.java
 *
 * Created on December 5, 2005, 2:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package za.co.solms.election.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RMISecurityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import za.co.solms.election.sessionBean.contract.ElectionRemote;

/**
 *
 * @author fritz
 */
public class ElectionClient extends JFrame
{
  /** Creates a new instance of ElectionClient */
  public ElectionClient()
  {
    setTitle("Election Client");
    getContentPane().add(new JScrollPane(new PartyVotesPanel()));
    pack();
    
    //logger.setLevel(Level.INFO);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private class PartyVotesPanel extends JPanel
  {
    public PartyVotesPanel()
    {
      JPanel panel = new JPanel();
      
      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout(new GridLayout(0,2));
      fieldsPanel.add(new JLabel("Party: ",JLabel.RIGHT));
      fieldsPanel.add(partyField);
      fieldsPanel.add(new JLabel("Votes: ",JLabel.RIGHT));
      fieldsPanel.add(votesField);
      
      logger.info("Connecting to session bean");
      System.out.println("Conencting to session bean");
      
      connectToSessionBean();
      
      getVotesButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent actionEvent)
        {
          try
          {
            logger.info("Retrieving votes");
            System.out.println("Retrieving votes");
            votesField.setText(
              Integer.toString(electionBean.getVotes(partyField.getText().trim())));
          }
          catch (Exception e)
          {
            JOptionPane.showMessageDialog(ElectionClient.this, e, "Exception caught", 
                JOptionPane.ERROR_MESSAGE);
          }
        }
      });
      
      addVotesButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent actionEvent)
        {
          try
          {
            logger.info("Adding votes");
            System.out.println("Adding votes");
            electionBean.addVotes(partyField.getText(), 
                Integer.parseInt(votesField.getText()));
            votesField.setText(
              Integer.toString(electionBean.getVotes(partyField.getText().trim())));
          }
          catch (Exception e)
          {
            JOptionPane.showMessageDialog(ElectionClient.this, e, "Exception caught", 
                JOptionPane.ERROR_MESSAGE);
          }
        }
      });
      
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.add(addVotesButton);
      buttonsPanel.add(getVotesButton);
      
      panel.add(fieldsPanel);
      panel.add(buttonsPanel);
      add(panel);
    }
    
    private void connectToSessionBean()
    {
      try
      {    
        InitialContext context = new InitialContext();
        System.out.println("Connecting to session bean: jndi name = " + ElectionRemote.class.getName());
        electionBean = (ElectionRemote)context.lookup(ElectionRemote.class.getName());
        System.out.println("Received handle to bean.");
      }
      catch (NamingException e)
      {
        e.printStackTrace();
        System.exit(0);
      }
    }
    
    private JButton getVotesButton = new JButton("Get votes");
    private JButton addVotesButton = new JButton("Add votes");
    private JTextField partyField = new JTextField("",10);
    private JTextField votesField = new JTextField("",10);
  }
  
  public static void main(String[] args)
  {
    System.out.println("Initial security manager: " + System.getSecurityManager());
    if (System.getSecurityManager() == null)
    {  
     System.setSecurityManager(new RMISecurityManager());
     System.out.println("Changed security manager to: " + System.getSecurityManager());
    } 

    new ElectionClient().setVisible(true);
  }
  
  private ElectionRemote electionBean;
  private Logger logger = Logger.getLogger(ElectionClient.class.getName());
}
