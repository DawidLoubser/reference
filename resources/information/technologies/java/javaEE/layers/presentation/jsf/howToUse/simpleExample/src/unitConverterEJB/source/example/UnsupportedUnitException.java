package example;

/**
 * Exception to indicate that the precondition (that of a given unit
 * being supported or understood) was not met.
 */
public class UnsupportedUnitException extends Exception
{
  public UnsupportedUnitException(String unsupportedUnit)
  {
    super(unsupportedUnit);
  }
}