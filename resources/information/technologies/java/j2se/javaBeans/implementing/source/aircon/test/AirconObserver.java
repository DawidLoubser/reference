package aircon.test;

import java.beans.*;
import aircon.AirconditionSettings;

/** A simple observer that observer temperature changes */
public class AirconObserver implements PropertyChangeListener
{
	public void propertyChange(PropertyChangeEvent evt)
	{
		if ( evt.getPropertyName().equals(
				AirconditionSettings.REQUESTED_TEMP_PROPERTY ))
		{
		  // Here, we could make the necessary adjustments to the airconditioning
		  // harware to attain the new requested temperature
			System.out.println("Adjusting temperature to: " + evt.getNewValue());
			
			// If we needed acces to the source of the event, we could do so via
			(AirconditionSettings) a = (AirconditionSettings)evt.getSource();
		}
	}
}