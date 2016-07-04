package za.co.solms.ejb3.friends;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** A small Swing client to manage one's friends */

public class FriendManager
{
  private class FriendPanel extends JPanel
  {
    public FriendPanel()
    {
      setupGUI();
      addListeners();
    }
    
    private void addListeners()
    {
      lookup.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent event)
          {
            try
            {
	          long id = Integer.parseInt(idField.getText());
	          friend = dao.find(id);
	          updateFields(friend);
            }
            catch (Exception e)
            {
              e.printStackTrace();
              JOptionPane.showMessageDialog(FriendPanel.this,
                e.toString(), "Exception caught", 
                  JOptionPane.ERROR_MESSAGE);
            }
          }
        });
      
      add.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent event)
            {
              try
              {
                 Address address = new Address(streetField.getText(),
                    suburbField.getText());
                 long id = dao.create(nameField.getText(),
                                      emailField.getText(),
			                          telField.getText(), address);
                 friend = dao.find(id);
                 friend.updateAddress(address);
                 updateFields(friend);
              }
              catch (Exception e)
              {
                e.printStackTrace();
                JOptionPane.showMessageDialog(FriendPanel.this,
                  e.toString(), "Exception caught", 
                    JOptionPane.ERROR_MESSAGE);
              }
            }
          });
      
      merge.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent event)
            {
              try
              {
                if (friend == null)
                  JOptionPane.showMessageDialog(FriendPanel.this,
                      "Must first lookup or create friend",
                      "Friend still null",
                      JOptionPane.ERROR_MESSAGE);
                else
                {
                  friend.setName(nameField.getText().trim());
                  friend.setEmailAddress(emailField.getText().trim());
                  friend.setTelephoneNo(telField.getText().trim());
                  Address address = new Address(streetField.getText(),
                      suburbField.getText());
                  friend.updateAddress(address);
                  dao.merge(friend);
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
                JOptionPane.showMessageDialog(FriendPanel.this,
                  e.toString(), "Exception caught", 
                    JOptionPane.ERROR_MESSAGE);
              }
            }
          });
      
      remove.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent event)
            {
              try
              {
                if (friend == null)
                  JOptionPane.showMessageDialog(FriendPanel.this,
                      "Must first lookup or create friend",
                      "Friend still null",
                      JOptionPane.ERROR_MESSAGE);
                else
                {
                  dao.remove(friend);
                }
              }
              catch (Exception e)
              {
                e.printStackTrace();
                JOptionPane.showMessageDialog(FriendPanel.this,
                  e.toString(), "Exception caught", 
                    JOptionPane.ERROR_MESSAGE);
              }
            }
          });
    }
    
    private void updateFields(Friend friend)
    {
      idField.setText(Long.toString(friend.getId()));
      nameField.setText(friend.getName());
      emailField.setText(friend.getEmailAddress());
      telField.setText(friend.getTelephoneNo());
      Address address = friend.retrieveAddress();
      streetField.setText(address.getStreet());
      suburbField.setText(address.getSuburb());
    }
    
    private void setupGUI()
    {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout(new GridLayout(0,2));
      fieldsPanel.add(new JLabel("Id (auto-generated):"));
      fieldsPanel.add(idField);
      fieldsPanel.add(new JLabel("Name:"));
      fieldsPanel.add(nameField);
      fieldsPanel.add(new JLabel("Email address:"));
      fieldsPanel.add(emailField);
      fieldsPanel.add(new JLabel("Telephone:"));
      fieldsPanel.add(telField);
      JPanel outer = new JPanel();
      outer.add(fieldsPanel);
      add(outer);
      
      JPanel addressPanel = new JPanel();
      addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));
      addressPanel.setLayout(new GridLayout(0,2));
      addressPanel.add(new JLabel("Street:"));
      addressPanel.add(streetField);
      addressPanel.add(new JLabel("Suburb:"));
      addressPanel.add(suburbField);
      outer = new JPanel();
      outer.add(addressPanel);
      add(outer);

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(lookup);
      buttonPanel.add(add);
      buttonPanel.add(merge);
      buttonPanel.add(remove);
      add(buttonPanel);
    }
    
    private JTextField idField = new JTextField("", 20);
    private JTextField nameField = new JTextField("", 20);
    private JTextField emailField = new JTextField("", 20);
    private JTextField telField = new JTextField("", 20);
    private JTextField streetField = new JTextField("", 20);
    private JTextField suburbField = new JTextField("", 20);
    private JButton lookup = new JButton("Lookup");
    private JButton add = new JButton("Add");
    private JButton merge = new JButton("Merge");
    private JButton remove = new JButton("Remove");
    
    private static final  long serialVersionUID = 98543797853L;
  }
  
  public static void main(String[] args)
  {
    new FriendManager().run();
  }  
   
  public void run()
  {
    try
    {
  	  InitialContext ctx = new InitialContext();
  	  System.out.println("Got Naming Service");
  	  dao = (FriendDAO) ctx.lookup("FriendDAOBean/remote");
    }
    catch (NamingException e)
    {
      System.err.println("Unable to connect to FriendDAO: " + e);
      System.exit(1);
    }
    
    JFrame frame = new JFrame("Friends manager");
    frame.getContentPane().add(new FriendPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  private FriendDAO dao;
  private Friend friend = null;
}
