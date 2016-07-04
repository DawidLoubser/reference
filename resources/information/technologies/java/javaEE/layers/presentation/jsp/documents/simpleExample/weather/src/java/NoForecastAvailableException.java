package za.org.example;

public class NoForecastAvailableException extends Exception
{
  public NoForecastAvailableException( String localityName )
  {
    super( localityName );
  }
}