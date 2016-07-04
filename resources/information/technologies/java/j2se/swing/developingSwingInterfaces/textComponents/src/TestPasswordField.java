import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestPasswordField extends JFrame implements ActionListener
{
  public TestPasswordField()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("Testing Password Fields");                      
    
    getContentPane().setLayout(new GridLayout(2,2));
    getContentPane().add(new JLabel("User name: "));
    getContentPane().add(userNameField);
    getContentPane().add(new JLabel("Password: "));
    getContentPane().add(passwordField);
    
    passwordField.addActionListener(this);

    pack();
    setVisible(true);
  }
  
  public void actionPerformed (ActionEvent event)
  {
    if (new String(passwordField.getPassword()).equals("007"))
      JOptionPane.showMessageDialog(this, "Password correct.",
                                    "Enter our dungeons", 
                                    JOptionPane.INFORMATION_MESSAGE);
    else  
      JOptionPane.showMessageDialog(this, "Password incorrect.",
                                    "Intruder Detection", 
                                    JOptionPane.ERROR_MESSAGE);
  }

  private JTextField userNameField     = new JTextField("Administrator",10);
  private JPasswordField passwordField = new JPasswordField("",10);
    
  public static void main(String[] args) {new TestPasswordField();}  
}
