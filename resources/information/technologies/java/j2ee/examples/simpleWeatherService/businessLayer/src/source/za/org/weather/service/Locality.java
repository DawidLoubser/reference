package za.org.weather.service;

/** A locality represents a place for which, for example,
 * a weather forecast is targeted.
 */
public interface Locality
{
  /** The standard place-name of the locality,
   * e.g. "Krugersdorp". */
  public String getName();
  
  
  /** A mutable handle to a locality */
  public interface Mutable extends Locality
  {
    /** Sets the locality name
     * @throws NullPointerException If the name is null */
    public void setName( String name )
    throws NullPointerException;
  }
}