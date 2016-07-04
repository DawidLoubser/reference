package aircon;

/** An exception to indicate that a requested temperature setting
 * was outside of the allowable range of temperatures.
 */
public class TemperatureOutOfBoundsException extends Exception
{
	public TemperatureOutOfBoundsException( int reqTemp )
	{
		super( String.valueOf(reqTemp) );
	}
}