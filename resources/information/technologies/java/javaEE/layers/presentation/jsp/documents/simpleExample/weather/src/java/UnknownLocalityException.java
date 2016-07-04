package za.org.example;

public class UnknownLocalityException extends Exception
{
  public UnknownLocalityException( String localityName )
  {
    super( localityName );
  }
}