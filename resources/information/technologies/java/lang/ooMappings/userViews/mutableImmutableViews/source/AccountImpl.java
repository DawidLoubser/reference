/** A simple (non-observable) implementation of a mutable account */
public class AccountImpl implements Account.Mutable, Cloneable
{
  public AccountImpl( String accountNumber )
  {
    setAccountNumber( accountNumber );
  }
  
  public String getAccountNumber()
  {
    // Immutable component, safe to return directly
    return accNo;
  }
  
  private void setAccountNumber( String accountNumber )
  {
    // Immutable component, safe to set directly (no need to clone)
    this.accNo = accountNumber;
  }
  
  public double getBalance()
  {
    // Primitive component, automatically cloned
    return bal;
  }
  
  public void credit( double amt ) throws Exception
  {
    if (amt > 0)
    {
      bal += amt;
    }
    else
    {
      throw new Exception("Invalid amount: " + amt);
    }
  }
  
  public void debit( double amt ) throws Exception
  {
    if (amt > 0)
    {
      bal -= amt;
    }
    else
    {
      throw new Exception("Invalid amount: " + amt);
    }
  }
  
  public String toString()
  {
    return "Account: " + accNo + " (balance=" + bal + ")";
  }
  
  public boolean equals(Object o)
  {
    try
    {
      // Note: Cast to same class (not interface) - testing for logical
      // equality implies that both the state (attributes) and behaviour
      // (the class) should be the same
      AccountImpl other = (AccountImpl)o;
      
      return
        // Component: test logical equality
        accNo.equals( other.accNo ) &&
        // Component (primitive): test value equality
        bal == other.bal;
    }
    catch (RuntimeException e)
    {
      return false;
    }
  }
  
  public int hashCode()
  {
    return accNo.hashCode();
  }
  
  public Account.Mutable clone()
  {
    try
    {
      // Perform shallow copy
      AccountImpl copy = (AccountImpl)super.clone();
      
      // Transfer components
      // (in this case, no need, because String is immutable, and
      // double is primitive (copy already made)
      return copy;
    }
    catch (CloneNotSupportedException e)
    {
      // Never happens, but this removes the precondition
      throw new RuntimeException(e);
    }
  }
  
  
  private String accNo;   // Composition
  private double bal;     // Composition
}
