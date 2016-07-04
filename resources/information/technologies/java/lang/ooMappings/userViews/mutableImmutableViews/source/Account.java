/** Contract for a simplistic account (read-only view) that also
 * enforces that all Accounts must be cloneable. */
public interface Account extends Cloneable
{
  /** Gets this account's account number.
   * @return The account number (a Component, required)
   */
  public String getAccountNumber();
  
  /** Gets the current balance of the account.
   * @return The balance (a Component, required)
   */
  public double getBalance();
  
  /** Override the specification of the clone service,
   * indicating that the return type is Account
   * (eliminates casting from client code).
   */
  public Account clone();

  
  /** Contract for an account of which the state can be changed
   * through user interaction.
   */
  public interface Mutable extends Account
  {
    /** Credit the account with the specified amount */
    public void credit( double amt ) throws Exception;
    
    /** Debit the account with the specified amount */
    public void debit( double amt ) throws Exception;
    
    /** Override the specification of the clone service,
     * indicating that the return type is Account.Mutable 
     * (eliminates casting from client code).
     */
    public Account.Mutable clone();
  }
}
