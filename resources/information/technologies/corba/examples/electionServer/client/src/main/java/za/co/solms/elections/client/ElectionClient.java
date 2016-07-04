package za.co.solms.elections.client;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import za.co.solms.elections.ElectionServer;
import za.co.solms.elections.ElectionServerHelper;

/** A simple client making use of the CORBA election server */
public class ElectionClient 
{

  public static void main(String[] args) 
  {
    try
    {
      // Initialise the Object Request Broker
      ORB orb = ORB.init(args, null);
      
      // Get a handle to the naming context
      NamingContextExt namingContext 
        = NamingContextExtHelper.narrow
            (orb.resolve_initial_references("NameService"));
      
      // Look up the server in the naming service, and cast the CORBA
      // object reference to a Java object reference ('narrow')
      ElectionServer server = ElectionServerHelper.narrow
        (namingContext.resolve_str("ElectionManager"));
      
      String party = "Workers Party";
      
      server.addParty(party);
      
      server.addVotes(party, 10230);
      server.addVotes(party, 11540);
      
      IntHolder totalVotes = new IntHolder();
      
      int numVotes = server.getVotes(party,totalVotes);
      
      System.out.println("Votes for " + party + ": " + numVotes);
      
      System.out.println("This represents a percentage of " 
          + numVotes / (double)totalVotes.value * 100d + "%");
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }
  }
}