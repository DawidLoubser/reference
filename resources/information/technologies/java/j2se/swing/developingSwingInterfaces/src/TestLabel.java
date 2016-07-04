import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestLabel extends JFrame
{
  public TestLabel()
  {
    super("JLabel");                      
    
    /*
        Sets the windows default operation when closed to exit
        the application
    */
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    ImageIcon icon = new ImageIcon("face.gif");
    
    getContentPane().setLayout(new BoxLayout(getContentPane(), 
                               BoxLayout.Y_AXIS));
    getContentPane().setBackground(Color.white);
    
    JLabel label = new JLabel("A normal text label");
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    getContentPane().add(label); 
    
    label = new JLabel(icon);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    getContentPane().add(label); 
    
    label = new JLabel("Text & icon", icon, JLabel.CENTER);
    label.setAlignmentX(Component.RIGHT_ALIGNMENT);                                    
    getContentPane().add(label);

    label = new JLabel("text below icon", icon, JLabel.CENTER);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);                                    
    label.setVerticalTextPosition(JLabel.BOTTOM);                                               
    label.setHorizontalTextPosition(JLabel.CENTER);
    getContentPane().add(label);
    
    pack();
    setVisible(true);
  }

  public static void main(String[] args) throws Exception 
  {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    new TestLabel();
  }
}
