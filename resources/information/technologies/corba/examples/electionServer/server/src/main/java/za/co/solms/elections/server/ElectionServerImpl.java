package za.co.solms.elections.server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

/** A server that publishes the election servant to the CORBA ORB
 */
public class ElectionServerImpl
{
  public void run(ORB orb)
  {
	 try
	 {
		 // Create a new Election server ...
		 ElectionServant servant = new ElectionServant();

		 // ... and connect it to our orb
		 orb.connect(servant);

		 // Object Request Broker Initialised
		 System.out.println ("Election server ORB initialised");

		 // Obtain reference for our nameservice
		 org.omg.CORBA.Object object = 
		 orb.resolve_initial_references("NameService");

		 // Since we have only an object reference, we must
		 // cast it to a NamingContext. We use a helper
		 // class for this purpose
		 NamingContext namingContext = 
		 NamingContextHelper.narrow(object);
		 
		 // Add a new naming component for our interface
		 NameComponent list[] = 
		 { new NameComponent("ElectionManager", "") };

		 // Now notify naming service of our new interface
		 namingContext.rebind(list, servant);

		 orb.run();
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
  }
  
  public static void main(String[] args) 
  {
	 // Create a new object request broker
    ORB orb = ORB.init(args, null);
    
    new ElectionServerImpl().run(orb);
  }
}
