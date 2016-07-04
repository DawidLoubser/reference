package za.org.weather.service;

/** Exception to indicate that a particular locality 
 * is not known */
public class UnknownLocalityException extends Exception
{
	public UnknownLocalityException( Locality locality )
	{
		super( String.valueOf( locality ) );
	}
}