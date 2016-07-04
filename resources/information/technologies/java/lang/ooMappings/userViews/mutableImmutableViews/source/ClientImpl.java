/** A simplistic (non-observable) implementation of a Client */
public class ClientImpl implements Client.Mutable, Cloneable
{
  public ClientImpl( String name )
  {
    setName(name);
  }
  
  public ClientImpl( String name, Account.Mutable account )
  {
    this( name );
    setAccount( account );
  }
  
  public String getName()
  {
    // Immutable component: Safe to return directly
    return name;
  }
  
  private void setName( String name ) // Read-only property (private setter)
  {
    // Immutable component: safe to set directly
    this.name = name;
  }
  
  public Account.Mutable getAccount()
  {
    // Component with immutable view: Safe to return directly
    // If something has an immutable handle to this instance, it
    // will only 'see' the returned account as an instance of
    // the immutable interface 'Account', and not be able to
    // invoke any methods that change the account's state 
    // without casting (which peer review should eliminate)
    return account;
  }
  
  public void setAccount(Account.Mutable account)
  {
    // We need to clone the account to ensure we can
    // take full ownership of it from now on
    // (not that account is optional, hence the check for null)
    this.account  =  account != null ? account.clone() : null;
  }
  
  public String toString()
  {
    return "Client " + name + " with account: " + account;
  }
  
  public boolean equals(Object o)
  {
    try
    {
      ClientImpl other = (ClientImpl)o;
      
      return
        // Component: Logical equality test
        name.equals( other.name ) &&
        // Optional component: Logical equality test (catering for null)
        account == other.account || account.equals( other.account );
    }
    catch (RuntimeException e)
    {
      return false;
    }
  }
  
  public int hashCode()
  {
    return name.hashCode();
  }
  
  public Client.Mutable clone()
  {
    try
    {
      ClientImpl copy = (ClientImpl)super.clone();
      
      // Clone components
      copy.account = account.clone();
      
      return copy;
    }
    catch (CloneNotSupportedException e)
    {
      // Never happens, but this removes the precondition
      throw new RuntimeException(e);
    }
  }
  
  
  private String name;              // Composition
  private Account.Mutable account;  // Composition
}