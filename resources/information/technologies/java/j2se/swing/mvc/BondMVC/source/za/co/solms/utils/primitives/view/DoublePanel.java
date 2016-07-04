/**
 * 
 */
package za.co.solms.utils.primitives.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.solms.utils.primitives.ObservableDouble;
import za.co.solms.utils.primitives.ObservableDouble.Mutable;

/**
 * @author fritz@solms.co.za
 *
 */
public class DoublePanel extends JPanel
{
  public DoublePanel(ObservableDouble.Mutable obsDouble, String role, DecimalFormat format)
  {
    // TODO add exceptions for null inputs
    this.format = format;
    this.role = role;
    this.data = obsDouble;
    
    setup();
  }
  
  private void setup()
  {
     add(new JLabel(role));
     add(doubleField);
     doubleField.setToolTipText(format.toPattern());

     new ViewAdapter();
     new Controller();
  }
  
  private class ViewAdapter implements PropertyChangeListener
  {
     private ViewAdapter()
     {
        data.addObserver(this);
        updateView();
     }
     
     public void propertyChange(PropertyChangeEvent event)
     {
        updateView();
     }
  }
  
  private class Controller implements ActionListener, FocusListener
  {
     public Controller()
     {
        doubleField.addActionListener(this);
        doubleField.addFocusListener(this);
     }
     
    private void updateModel()
    {
      try
      {
        double newData= format.parse(doubleField.getText()).doubleValue();
        data.setValue(newData);
      }
      catch (ParseException e)
      {
        JOptionPane.showMessageDialog(DoublePanel.this, 
            ("Invalid data format. Use " + format.toPattern()),
            "Input error", JOptionPane.ERROR_MESSAGE);
      }
    }

      public void focusGained(FocusEvent arg0) {}

      public void focusLost(FocusEvent arg0)
      {
         updateModel();
      }

      public void actionPerformed(ActionEvent arg0)
      {
         updateModel();
      }
  }
  
  private void updateView()
  {
    doubleField.setText(format.format(data.getValue()));
  }
  
  private JTextField doubleField = new JTextField("",10);
  private ObservableDouble.Mutable data;
  private DecimalFormat format;
  private String role;
  
  private static final long serialVersionUID = 200608180854L;
}
