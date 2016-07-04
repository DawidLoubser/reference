/** Contract for a simplistic client (read-only view). A client
 * has a name, and optionally an account. Enforces that all clients
 * are cloneable.
 */
public interface Client extends Cloneable
{
  /** Returns the full name of a client. A client's name
   * cannot change after it has been set (an immutable property).
   * @return The client's name (a Component, required)
   */
  public String getName();
  
  /** Returns the account that belongs to this client
   * @return The client's account (an Aggregate, optional)
   */
  public Account getAccount();
  
  /** Override the specification of the clone service,
   * indicating that the return type is Client
   * (eliminates casting from client code).
   */
  public Client clone();
  
  
  /** Contract for a mutable client that enforces the correct
   * UML-based behaviour for the attributes.
   */
  public interface Mutable extends Client
  {
    /** Sets the client's current account component to 
     * the given account (which may be cloned to guarantee
     * encapsulation) */
    public void setAccount( Account.Mutable account );
    
    /** Returns the account that belongs to this client
     * (through a mutable view) - this implies that having
     * a mutable handle to a client means that one also has
     * a mutable handle to its account.
     */
    public Account.Mutable getAccount();
    
    /** Override the specification of the clone service,
     * indicating that the return type is Client.Mutable
     * (eliminates casting from client code).
     */
    public Client.Mutable clone();
  }
}