public class Client
{
  
  //...
  
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
