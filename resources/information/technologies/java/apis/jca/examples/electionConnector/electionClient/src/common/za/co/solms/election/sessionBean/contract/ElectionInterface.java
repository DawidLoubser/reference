package za.co.solms.election.sessionBean.contract;

/**
 *
 * @author fritz
 */
public interface ElectionInterface
{
  public void addVotes(String party, int numVotes) throws Exception;
  
  public int getVotes(String party) throws Exception;
}
