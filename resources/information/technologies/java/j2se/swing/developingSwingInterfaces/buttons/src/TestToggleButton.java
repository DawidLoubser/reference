import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TestToggleButton extends JFrame 
                              implements ActionListener, ItemListener
{
  public TestToggleButton()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("Toggle Buttons and Borders");
    
    getContentPane().setLayout(new BorderLayout());
    
    JPanel mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));                      
    
    radioButtonPanel.add(lineBorderButton);
    radioButtonPanel.add(etchedBorderButton);
    radioButtonPanel.add(raisedBorderButton);
    radioButtonPanel.add(loweredBorderButton);
    
    mainPanel.add(radioButtonPanel);
    
    ButtonGroup radioButtons = new ButtonGroup();
    radioButtons.add(lineBorderButton);
    radioButtons.add(etchedBorderButton);
    radioButtons.add(raisedBorderButton);
    radioButtons.add(loweredBorderButton);
    
    lineBorderButton.setMnemonic('L');
    etchedBorderButton.setMnemonic('E');
    raisedBorderButton.setMnemonic('R');
    loweredBorderButton.setMnemonic('o');
    
    lineBorderButton.addActionListener(this);
    etchedBorderButton.addActionListener(this);
    raisedBorderButton.addActionListener(this);
    loweredBorderButton.addActionListener(this);
    
    raisedBorderButton.setSelected(true);
    
    allowLineBorder.addItemListener(this);
    allowEtchedBorder.addItemListener(this);
    
    JPanel panel = new JPanel();
    panel.add(allowLineBorder);
    panel.add(allowEtchedBorder);
    panel.setBorder(BorderFactory.createTitledBorder("Border restrictions"));
  
    mainPanel.add(panel);
    getContentPane().add(mainPanel);
    
    pack();
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event)
  {
    if (event.getSource() == lineBorderButton)
      radioButtonPanel.setBorder(lineBorder);  
    else if (event.getSource() == etchedBorderButton)
      radioButtonPanel.setBorder(etchedBorder);  
    else if (event.getSource() == raisedBorderButton)
      radioButtonPanel.setBorder(raisedBorder);  
    else if (event.getSource() == loweredBorderButton)
      radioButtonPanel.setBorder(loweredBorder);  
  }
  
  public void itemStateChanged(ItemEvent event)
  {
    if (event.getSource() == allowLineBorder)
      {
        if (!allowLineBorder.isSelected())
          if (lineBorderButton.isSelected())
            {
              raisedBorderButton.setSelected(true);
              radioButtonPanel.setBorder(raisedBorder);  
            }  
        lineBorderButton.setEnabled(allowLineBorder.isSelected());
      }  

    if (event.getSource() == allowEtchedBorder)
      {
        if (!allowEtchedBorder.isSelected())
          if (etchedBorderButton.isSelected())
            {
              raisedBorderButton.setSelected(true);
              radioButtonPanel.setBorder(raisedBorder);  
            }  
        etchedBorderButton.setEnabled(allowEtchedBorder.isSelected());
      }  
  }    
  
  private JRadioButton lineBorderButton    
    = new JRadioButton("Line Border");
  private JRadioButton etchedBorderButton  
    = new JRadioButton("Etched Border");
  private JRadioButton raisedBorderButton  
    = new JRadioButton("Raised Bevel Border");
  private JRadioButton loweredBorderButton 
    = new JRadioButton("Lowered Bevel Border");
  
  private Border lineBorder = BorderFactory.createLineBorder(Color.black);
  private Border etchedBorder = BorderFactory.createEtchedBorder();
  private Border raisedBorder = BorderFactory.createRaisedBevelBorder();
  private Border loweredBorder = BorderFactory.createLoweredBevelBorder();

  private JCheckBox allowLineBorder 
    = new JCheckBox("Allow line border", true);
  private JCheckBox allowEtchedBorder 
    = new JCheckBox("Allow etched border", true);
  
  private JPanel radioButtonPanel    = new JPanel();
  
  public static void main(String[] args) {new TestToggleButton();}
}

