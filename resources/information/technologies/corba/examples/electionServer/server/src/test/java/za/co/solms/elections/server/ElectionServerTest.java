package za.co.solms.elections.server;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sun.corba.se.impl.naming.cosnaming.TransientNameService;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import za.co.solms.elections.ElectionServerHelper;
import za.co.solms.elections.ElectionServer;
import za.co.solms.elections.DuplicateParty;
import za.co.solms.elections.NoSuchParty;

public class ElectionServerTest
{
	@BeforeClass
	public static void startServer()
	{
		logger.info("Creating and initializing instance of an Object Request Broker");
		orb = ORB.init(args,null);

		logger.info("Creating a transient name service");
		nameService = new TransientNameService((com.sun.corba.se.spi.orb.ORB) orb);
		
		logger.info("Starting election server.");
		new Thread() {public void run() {new ElectionServerImpl().run(orb);}}.start();
		
		logger.info("Waiting for a while till things are started.");
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {}
		
      logger.info("Get a handle to the naming context");
      try
      {
	      namingContext = NamingContextExtHelper.narrow
	            (orb.resolve_initial_references("NameService"));
      }
      catch (Exception e)
      {
      	fail("Could not connect to naming service: " + e.toString());
      }
      
      try
      {
			server = ElectionServerHelper.narrow
   			(namingContext.resolve_str("ElectionManager"));
      }
      catch (Exception e)
      {
      	fail("Could not connect to election server: " + e.toString());
      }
     
		
		logger.info("Completed once-off initialization, now ready to run tests ...");
	}
	
	@Before
	public void cleanup()
	{
		server.reset();
	}
	
	@Test
	public void testServer() throws NotFound, CannotProceed, InvalidName
	{
		String[] parties = {"Workers Party","Establishment Party"};
		
		for (String party:parties)
		{
	      try
	      {
	      	logger.info("Creating parties");
	      	server.addParty(party);
	      }
	      catch (DuplicateParty e)
	      {
	      	fail("Could not create party - claimed to be duplicate.");
	      }
	      
         
	      IntHolder totalVotes = new IntHolder();
	      try
	      {
		      int numVotes = server.getVotes(party,totalVotes);
		      assertEquals(0,numVotes);
		      assertEquals(0,totalVotes.value);
	      }
	      catch (NoSuchParty e)
	      {
	      	fail("Party should have existed: " + e.toString());
	      }
		}
		
		DecimalFormat percentageFormatter = new DecimalFormat("#0.00%");
		Random randomNumberGenerator = new Random();
		for (int i=0; i<6; ++i)
		{
			String party = parties[randomNumberGenerator.nextInt(2)];
			int numVotes = randomNumberGenerator.nextInt(500);
	      try
	      {
				server.addVotes(party, numVotes);
		      IntHolder totalVotes = new IntHolder();
		      numVotes = server.getVotes(party,totalVotes);
		      String percentage = percentageFormatter.format(((double)numVotes)/totalVotes.value);
				System.out.println("Added " + numVotes + " votes for " + party + " = " + numVotes);
				System.out.println("Total number of votes cast = " + totalVotes.value);
				System.out.println("The " + party + " has now " + percentage + " of the votes.");
				System.out.println();
	      }
	      catch (NoSuchParty e)
	      {
	      	fail("Party should have existed: " + e.toString());
	      }
      }
		
      
		System.out.println("FINAL RESULTS:");
		System.out.println("==============");
      try
      {
	      IntHolder totalVotes = new IntHolder();
      	for (String party:parties)
      	{
		      int numVotes = server.getVotes(party,totalVotes);
		      String percentage = percentageFormatter.format(((double)numVotes)/totalVotes.value);
		      System.out.println(party + ": " + percentage + " (num votes: " + numVotes + ")");
      	}   
      	System.out.println("Total votes cast: " + totalVotes.value);
      }
      catch (NoSuchParty e)
      {
      	fail("Party should have existed: " + e.toString());
      }
	}
	
   private static ORB orb;
	private static TransientNameService nameService;
   private static NamingContextExt namingContext; 
   private static ElectionServer server;
   
	private static	String[] args = {"-ORBInitialPort","2000"};

	private static Logger logger = Logger.getLogger(ElectionServerTest.class.getName());
}
