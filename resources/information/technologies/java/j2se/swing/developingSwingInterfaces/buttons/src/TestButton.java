import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestButton extends JFrame 
                        implements ActionListener, KeyListener
{
  public TestButton()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("Testing Password Fields");                      
    
    getContentPane().setLayout(new GridLayout(3,1));
    
    JPanel panel = new JPanel();
    panel.add(new JLabel("User name: "));
    panel.add(userNameField);
    getContentPane().add(panel);
    
    panel = new JPanel();
    panel.add(new JLabel("Password: "));
    panel.add(passwordField);
    getContentPane().add(panel);
    
    panel = new JPanel();
    panel.add(okButton);
    panel.add(cancelButton);
    panel.add(helpButton);
    getContentPane().add(panel);
    getRootPane().setDefaultButton(okButton);
    
    okButton.setEnabled(false);
    okButton.setMnemonic('k');
    okButton.setToolTipText("Press to enter the dungeons");
    cancelButton.setMnemonic('C');
    cancelButton.setToolTipText("Press if you dont feel macho today");
    helpButton.setToolTipText("Help");
    
    passwordField.addActionListener(this);
    passwordField.addKeyListener(this);

    pack();
    setVisible(true);
  }
  
  public void keyReleased(KeyEvent event)
  {
    okButton.setEnabled(true);
  }  
  public void keyPressed(KeyEvent event) {}
  public void keyTyped(KeyEvent event) {}
  
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
  
  private ImageIcon okIcon = new ImageIcon("ok.gif");
  private ImageIcon cancelIcon = new ImageIcon("cancel.gif");
  private ImageIcon helpIcon = new ImageIcon("help.gif");
  
  private JButton okButton = new JButton("Ok", okIcon);
  private JButton cancelButton = new JButton("Cancel", cancelIcon);
  private JButton helpButton = new JButton(helpIcon);
    // image on button
  public static void main(String[] args) {new TestButton();}  
}
