import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestLayout extends JFrame
{
  public TestLayout()
  {
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });    

    //**********************************************************
    setTitle("Testing FlowLayout");                      
    getContentPane().setLayout(new FlowLayout());
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout()); 
    buttonPanel.add(new JButton("button 1"));
    buttonPanel.add(new JButton("button 2"));
    buttonPanel.add(new JButton("button 3"));
    buttonPanel.add(new JButton("button 4"));
    buttonPanel.add(new JButton("button 5"));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    buttonPanel.setBackground(Color.white);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    mainPanel.add(buttonPanel);
    getContentPane().add(new JScrollPane(mainPanel)); 

    //**********************************************************

    setSize(400,200);
    setVisible(true);
  }

  public static void main(String[] args) throws Exception 
  {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    new TestLayout();
  }
}
