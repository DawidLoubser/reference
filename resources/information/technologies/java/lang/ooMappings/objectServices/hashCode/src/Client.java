public class Client
{
  
  //...
  
  public int hashCode()
  {
    // Pick one of the attributes used in the
    // logical equality test
    return name.hashCode();
  }
  
  public boolean equals(Object o)
  {
    try
    {
      Client other = (Client)o;
      
      return
        // Composition: logical equality test
        name.equals( other.name ) &&
        // Aggregation: instance equality test
        account == other.account;
    }
    catch (RuntimeException e)
    {
      return false;
    }
  }
  
  //...

  private String name;         // Composition
  private Account account;     // Aggregation
}
