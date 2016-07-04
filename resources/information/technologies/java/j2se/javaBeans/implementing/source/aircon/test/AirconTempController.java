package aircon.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import aircon.*;

/** A small Swing GUI window that allows a user to control the 
 * airconditioning by changing the state of an 'AirconditionSettings'
 * JavaBean handed to it. For simplicity, this is deliberately a one-way
 * GUI (does not display current temperature).
 */
public class AirconTempController extends JFrame
{
	/** Construct with the given airconditoner settings, which will be 
	 * adjusted according to the user's interaction with this GUI.
	 */
	public AirconTempController( AirconditionSettings aircon )
	{
		this.aircon = aircon;
		
		// Setup window
		setTitle("Aircon");
		setLayout( new GridLayout(2, 1) );
		
		// Attach actionlistener: increase temp, or beep if limit reached
		upBtn.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					AirconTempController.this.aircon.increaseRequestedTemp();
				}
				catch (TemperatureOutOfBoundsException ex)
				{
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		
	  // Attach actionlistener: decrease temp, or beep if limit reached
		downBtn.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					AirconTempController.this.aircon.decreaseRequestedTemp();
				}
				catch (TemperatureOutOfBoundsException ex)
				{
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		
		// Add buttons
		getContentPane().add( upBtn );
		getContentPane().add( downBtn );
		
		// Exit Java VM if closed
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		// Resize to smallest useful size based on the contained components
		pack();
		
		// Make visible
		setVisible(true);
	}
	
	private AirconditionSettings aircon;
	private JButton upBtn = new JButton("+");
	private JButton downBtn = new JButton("-");
}