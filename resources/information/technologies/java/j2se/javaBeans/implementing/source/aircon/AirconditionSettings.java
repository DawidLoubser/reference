package aircon;

import java.beans.*;
import java.io.*;

/** An object that represents the desired settings for an
 * air conditioning system. Implemented as an observable
 * JavaBean.
 */
public class AirconditionSettings implements Serializable
{
	/** Creates a new (default) aircondition settings instance */
	public AirconditionSettings()
	{
		this.requestedTemp = 22;   // Default temperature
	}
	
	/** The temperature which the airconditioning system needs to
	 * try to maintain (in celcius).
	 */
	public int getRequestedTemp()
	{
		return requestedTemp;
	}
	
	
	/** Sets the temperature for the airconditioner to try to maintain.
	 * @throws TemperatureOutOfBoundsException If the temperature violates
	 * the minimum or maxim selectable temperatures.
	 */
	public void setRequestedTemp( int requestedTemp )
	throws TemperatureOutOfBoundsException
	{
		if (requestedTemp < MIN_TEMP || requestedTemp > MAX_TEMP )
		{
			throw new TemperatureOutOfBoundsException( requestedTemp );
		}
		
		// Change property
		int old = this.requestedTemp;
		this.requestedTemp = requestedTemp;
		
		// Notify observers
		pcs.firePropertyChange( REQUESTED_TEMP_PROPERTY, old, requestedTemp );
	}
	
	
	/** Increases the requested temperature by one notch
	 * @throws TemperatureOutOfBoundsException If the maximum temp is reached 
	 */
	public void increaseRequestedTemp()
	throws TemperatureOutOfBoundsException
	{
		setRequestedTemp( requestedTemp + 1 );
	}
	
	
	/** Decreases the requested temperature by one notch 
	 * @throws TemperatureOutOfBoundsException If the minimum temp is reached
	 */
	public void decreaseRequestedTemp()
	throws TemperatureOutOfBoundsException
	{
		setRequestedTemp( requestedTemp - 1 );
	}
	
	/** Adds a new observer */
	public void addObserver( PropertyChangeListener observer )
	{
		pcs.addPropertyChangeListener( observer );
	}
	
	/** Removes the given observer */
	public void removeObserver( PropertyChangeListener observer )
	{
		pcs.removePropertyChangeListener( observer );
	}
	
	
	// The current requested temperature
	private int requestedTemp;
	
	/** JavaBean property name for the requested temperature */
	public static final String REQUESTED_TEMP_PROPERTY = "requestedTemp";
	
	/** The minimum requestable temperature */
	public static final int MIN_TEMP = 17;
	
	/** The maximum requestable temperature */
	public static final int MAX_TEMP = 30;
	
	// JavaBeans event notification helper
	private PropertyChangeSupport pcs = new PropertyChangeSupport( this );
}