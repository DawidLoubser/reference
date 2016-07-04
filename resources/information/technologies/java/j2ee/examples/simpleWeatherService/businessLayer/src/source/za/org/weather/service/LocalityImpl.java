package za.org.weather.service;

/** A simple implementation of a mutable locality
 * (data object) */
public class LocalityImpl implements Locality.Mutable
{
  /** Construct the locality with the given name.
   * @throws NullPointerException If the name is null. 
   */
  public LocalityImpl( String name )
  throws NullPointerException
  {
    setName( name );
  }
  
  public void setName(String name)
  throws NullPointerException
  {
    if (name == null)
      throw new NullPointerException("name");
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean equals(Object obj)
  {
    try
    {
      LocalityImpl other = (LocalityImpl)obj;
      return this.name.equals( other.name );
    }
    catch (ClassCastException e)
    {
      return false;
    }
  }
  
  public String toString()
  {
    return "Locality: " + this.name;
  }
  
  private String name;
}