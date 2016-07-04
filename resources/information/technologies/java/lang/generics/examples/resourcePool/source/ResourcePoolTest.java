package za.co.solms.utils.resource.pooling;

import java.util.*;

public class ResourcePoolTest
{
  class A implements Prototype<A>
  {
    public A clone() 
    {
      A copy = null;
      try
      {
        copy = (A)super.clone();
      }
      catch (CloneNotSupportedException e) {/* never thrown */}
      return copy;
    }
  }

  public void run()
  {
    System.out.println("Creating pool");
    resourcePool = new BasicResourcePool<A>();
    System.out.println("Pool state: " + resourcePool.getNumFreeResources() 
        + " available from a total of " + resourcePool.getSize());
    System.out.println("Initialising pool");
    resourcePool.init(new A(), 5);
    System.out.println("Pool state: " + resourcePool.getNumFreeResources() 
        + " available from a total of " + resourcePool.getSize());

    new Thread()
     {
       public void run()
       {
         try
         {
           Thread.sleep(4000);
         }
         catch (InterruptedException e) {}
         System.out.println("Now resizing");
         ResourcePoolTest.this.resourcePool.resize(7);
         System.out.println("Pool state: " 
            + ResourcePoolTest.this.resourcePool.getNumFreeResources() 
            + " available from a total of " 
            + ResourcePoolTest.this.resourcePool.getSize());
         try
         {
           Thread.sleep(4000);
         }
         catch (InterruptedException e) {}
         Iterator<A> iter = as.iterator();
         A a = iter.next();
         as.remove(a);
         System.out.println("Releasing 1 resource");
         resourcePool.releaseResource(a);
       }
     }.start();


    as = new HashSet<A>();
    for (int i=0; i<8; ++i)
    {
      System.out.print((i+1) + ": Retrieving resource: ");
      A a = resourcePool.getResource();
      as.add(a);
      System.out.println(a);
    }
  }

  public static void main(String[] args)
  {
    new ResourcePoolTest().run();
  }

  private ResourcePool<A> resourcePool;
  private Collection<A> as;
}