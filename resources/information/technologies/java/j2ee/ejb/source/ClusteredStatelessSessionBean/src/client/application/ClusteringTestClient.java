package za.co.solms.clustering.test;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class ClusteringTestClient
{
  public static void main(String[] args) {new ClusteringTestClient().run();}
  public void run()
  {
    connect();
    
		class CallThread extends Thread
		{
		  public void run()
			{
			  try
				{
          long milliSecs = clusteringTest.run(500000);

          System.out.println("Time taken = " + (milliSecs/1000.0) + " seconds.");
			  }
				catch (Exception e) {e.printStackTrace();}
			}
		}
		
		for (int i=0; i<20; ++i)
		  new CallThread().start();
  }
      
  public void connect()
  {
    try
    {
      InitialContext jndiContext = new InitialContext();

      System.out.println("Now looking up session bean " + jndiName + " ...");

      Object beanHomeRef
        = jndiContext.lookup(jndiName);

      System.out.println("got it");

      ClusteringTestHome home =
        (ClusteringTestHome)PortableRemoteObject.narrow
          (beanHomeRef, ClusteringTestHome.class);

      clusteringTest = home.create();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void destroy()
  {
    try
    {
      clusteringTest.remove();
      System.out.println("Removed session bean.");
    }
    catch (javax.ejb.RemoveException e)
    {
      System.out.println("Could not remove session bean.");
    }
    catch (java.rmi.RemoteException e)
    {
      System.out.println("Could not remove session bean.");
    }
  }

  private static final String jndiName = "ejb/ClusteringTest";
  private ClusteringTest clusteringTest;
}
