package za.co.solms.elections.server;

import java.util.Map;
import java.util.TreeMap;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import za.co.solms.elections.NoSuchParty;
import za.co.solms.elections.DuplicateParty;

/**
 * A persistent election server implementation using the CORBA POA.
 */
public class ElectionServant extends za.co.solms.elections._ElectionServerImplBase
{
  public ElectionServant() {}
  
  public void reset()
  {
	  votes.clear();
  }
  
  public void addParty(String party) throws DuplicateParty
  {
	  if (votes.keySet().contains(party))
		  throw new DuplicateParty();
	  else
		  votes.put(party, new Counter());
  }
  
  public void addVotes(String party, int numVotes) throws NoSuchParty 
  {
    Counter counter = votes.get(party);
    
    if (counter == null)
      throw new NoSuchParty();
    else
      counter.add(numVotes);
  }
  
  public int getVotes(String party, IntHolder totalVotes)
                    throws NoSuchParty 
  {
    Counter counter = votes.get(party);
    
    totalVotes.value = getTotalVotes();
    
    if (counter == null)
      throw new NoSuchParty();
    else
      return counter.getCount();
  }
  
  private int getTotalVotes()
  {
    int sum = 0;
    for (Counter counter : votes.values())
      sum += counter.getCount();
    return sum;
  }
  
  private static class Counter
  {
    public void add(int count)
    {
      this.count += count;
    }
    
    public int getCount()
    {
      return count;
    }
    
    public void reset()
    {
      count = 0;
    }
    
    private int count = 0;
  }
  
  private Map<String,Counter> votes = new TreeMap<String,Counter>();
}