\begin{verbatim}
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ClickCounter extends javax.swing.JApplet
{
  public ClickCounter() {init();}
  
  public void init()
  {
    count = 0;    
    getContentPane().add(createMainPanel());
  }
  
  public JPanel createMainPanel()
  {
    counter = new JTextField(String.valueOf(count), 3);
    //counter.setEditable(false);
    
     
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
  
  public static void main(String[] args)
  {
    new DefaultFrame(new ClickCounter(), "Click Counter", "STC.jpg");
  }
  
  private int count;
  private JTextField counter;
}  

\end{verbatim}
