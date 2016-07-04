import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestTextField extends JFrame 
                           implements ActionListener, FocusListener
{
  public TestTextField()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("Testing Text Fields");                      

    JPanel panel = new JPanel();    
    panel.setLayout(new GridLayout(3,2));
    panel.add(new JLabel("Numerator: "));
    panel.add(numeratorField);
    panel.add(new JLabel("Denominator: "));
    panel.add(denominatorField);
    panel.add(new JLabel("Decimal approximation:"));
    panel.add(resultField);
    
    getContentPane().add(new JScrollPane(panel));
    
    ActionListener actionListener = new ActionListener()
      {
        public void actionPerformed (ActionEvent event) {calculate();}
      };  
      

    numeratorField.addActionListener(actionListener);
    numeratorField.addFocusListener(this);
    numeratorField.setToolTipText("The thing on top of the line.");
    denominatorField.addActionListener(actionListener);
    denominatorField.addFocusListener(this);
    denominatorField.setToolTipText("The thing below the line.");
    resultField.setEditable(false);
    resultField.setToolTipText("The result of the division.");

    pack();
    setVisible(true);

    numerator = Double.parseDouble(numeratorField.getText());
    denominator = Double.parseDouble(denominatorField.getText());
  }
  
  private void calculate()
  {
    try
    {
       numerator = new Double(numeratorField.getText()).doubleValue();
    }
    catch (NumberFormatException e)
    {
      JOptionPane.showMessageDialog(this, "Invalid number you %$^%$^%:",
                                          "ERROR", JOptionPane.ERROR_MESSAGE);
    }                                               
    finally
    {
      numeratorField.setText(String.valueOf(numerator));                                          
    }      
    double denominator = new Double(denominatorField.getText()).doubleValue();
    double result = numerator / denominator;
    resultField.setText(decimalFormat.format(result));
  }    
  
  public void actionPerformed (ActionEvent event) {calculate();}
  public void focusLost       (FocusEvent  event) {calculate();}
  public void focusGained     (FocusEvent  event) {}
  
  private JTextField numeratorField   = new JTextField("1",20);
  private JTextField denominatorField = new JTextField("1",20);
  private JTextField resultField      = new JTextField("1",20);
  double numerator, denominator;
  private java.text.DecimalFormat decimalFormat 
    = new java.text.DecimalFormat("0.######E0");  
    
  public static void main(String[] args) {new TestTextField();}  
}
