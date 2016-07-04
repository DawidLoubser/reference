package za.co.solms.election.connector;

/**
 * @author fritz@solms.co.za
 *
 * The interface publishng the client services available 
 * via a connection to the election server.
 */
public interface ElectionServerConnection
{
  /**
   * Add a number of votes for a party.
   */
  public void addVotes(String party, int numVotes);
  
  /** 
   * Query the number of votes a particular party has.
   */
  public int getVotes(String party);
}
