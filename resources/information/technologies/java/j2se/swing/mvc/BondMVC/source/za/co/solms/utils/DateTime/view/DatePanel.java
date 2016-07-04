/**
 * 
 */
package za.co.solms.utils.DateTime.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.solms.utils.DateTime.ObservableDate;

/**
 * A simple entry field for dates.
 * 
 * @author fritz@solms.co.za
 *
 */
public class DatePanel extends JPanel
{
	public DatePanel(ObservableDate.Mutable obsDate, String role, DateFormat dateFormat)
	{
		// TODO add exceptions for null inputs
		this.dateFormat = dateFormat;
		this.role = role;
    this.date = obsDate;
		
		setup();
	}
	
	private void setup()
	{
		add(new JLabel(role));
		add(dateField);

    new ViewAdapter();
    new Controller();
	}

  private class ViewAdapter implements PropertyChangeListener
  {
      public ViewAdapter()
      {
         date.addObserver(this);
         updateView();
      }
      
      public void propertyChange(PropertyChangeEvent event)
      {
         updateView();
      }
  }
	
	private class Controller implements ActionListener, FocusListener
	{
     private Controller()
     {
        dateField.addActionListener(this);
        dateField.addFocusListener(this);
     }
     
    private void updateModel()
    {
			try
			{
				Date newDate= dateFormat.parse(dateField.getText());
				date.setDate(newDate);
			}
			catch (ParseException e)
			{
				JOptionPane.showMessageDialog(DatePanel.this, 
						"Invalid date format.",
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
		dateField.setText(dateFormat.format(date.getDate()));
	}
	
	private JTextField dateField = new JTextField();
	private ObservableDate.Mutable date;
	private DateFormat dateFormat;
	private String role;
	
	private static final long serialVersionUID = 200608180854L;
}
