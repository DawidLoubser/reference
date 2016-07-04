import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ClickCounterApplet extends javax.swing.JApplet
{
  public void init()
  {
    count = 0;
    
    getContentPane().add(createMainPanel());
  }
  
  public JPanel createMainPanel()
  {
    counter = new JTextField(String.valueOf(count), 3);
    counter.setEditable(false);
    
     
    JButton button = new JButton("click");
    
    JPanel panel = new JPanel();
    panel.add(button);
    panel.add(counter);
    
    button.setToolTipText("Please press me!");
    button.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          counter.setText(String.valueOf(++count));
        }
      });  
    panel.setBorder(BorderFactory.createTitledBorder("Click counter"));  
    
    return panel;
  }   

  private int count;
  private JTextField counter;
}  
