package za.co.solms.utils.resource.pooling;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class BasicResourcePool<R extends Prototype<R>> 
            implements ResourcePool<R>
{
  public R getResource()
  {
    if (prototype == null)
      throw new IllegalStateException
                          ("Pool not yet initialised with prototype.");

    boolean resourceAvailable = false;
    synchronized (availableResources)
    {
      // Spin lock for extracting resource
      while (!resourceAvailable)
      {
        resourceAvailable = true;
        if (availableResources.isEmpty())
        {
          try
          {
            availableResources.wait();
          }
          catch (InterruptedException e)
          {
            if (availableResources.isEmpty())
              resourceAvailable = false;
            else
              resourceAvailable = true;
          }
        }
      }
      Iterator<R> iter = availableResources.iterator();
      R resource = iter.next();
      availableResources.remove(resource);
      return resource;
    }
  }

  public int getSize() {return resources.size();}

  public int getNumFreeResources() 
  {
    return availableResources.size();
  }

  public void releaseResource(R resource)
  {
    if (resources.contains(resource))
    {
      synchronized(availableResources)
      {
        availableResources.add(resource);
        availableResources.notify();
      }
    }
    else
      throw new IllegalArgumentException
                        ("Resource instance not in pool.");
  }

  public void resize(int numResourcesInPool)
  {
    if (numResourcesInPool < 1)
      throw new IllegalArgumentException
                         ("Pool size may not be less than 1");

    int instancesToAdd = numResourcesInPool - resources.size();
    if (numResourcesInPool > 0)
    {
      for (int i=0; i<instancesToAdd; ++i)
      {
        R resource = (R)((R)prototype).clone();
        add(resource);
      }
    }
    else if (numResourcesInPool < 0)
    {
      synchronized (availableResources)
      {
        synchronized (resources)
        {
          for (int i=instancesToAdd; i<0; ++i)
          {
            // Spin lock for removing resource from pool
            
            while (availableResources.isEmpty())
              try
              {
                availableResources.wait();
              }
              catch (InterruptedException e) {}

            R resource =  getResource();
            resources.remove(resource);
          }
        }
      }
    }
  }

  private void add(R resource)
  {
    synchronized(resources)
    {
      synchronized(availableResources)
      {
        resources.add(resource);
        availableResources.add(resource);
        availableResources.notify();
      }
    }
  }

  public void init(R prototype, int initialPoolSize)
  {
    this.prototype = prototype;
    add(prototype);
    resize(initialPoolSize);
  }

  private Set<R> resources = new HashSet<R>();
  private Set<R> availableResources = new HashSet<R>();
  private R prototype;
}