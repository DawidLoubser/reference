import java.util.Date;

public class Person 
{
  //...
  
  public boolean equals(Object o)
  {
    try
    {
      // Attempt to cast other object to this class
      Person other = (Person)o;
      
      // Compare relevant attributes
      return name.equals( other.name ) &&
        birthDate.equals( other.birthDate );
    }
    catch (RuntimeException e)
    {
      // If the cast could not be performed, or e.g.
      // null-pointer exception occurs, we are assumed
      // not logically equal to the given object
      return false;
    }
  }
  
  //...
  
  private String name;
  private Date birthDate;
}
