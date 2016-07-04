import java.util.Date;

public class Client implements Cloneable
{
  
  //...
  
  public Object clone() throws CloneNotSupportedException
  {
    // Obtain a default (shallow) copy
    Client copy = (Client)super.clone();
    
    // Set up aggregate and component objects
    // 'name' : No need to clone component, as String is immutable
    // 'joinDate' : Clone component
    copy.joinDate = (Date)joinDate.clone();
    
    // 'account' : Don't clone, but set up state observation (if
    // used), etc. For example:
    //copy.account.addObserver( copy );
    
    return copy;
  }
  
  //...

  private String name;         // Composition
  private Date joinDate;       // Composition
  private Account account;     // Aggregation
}