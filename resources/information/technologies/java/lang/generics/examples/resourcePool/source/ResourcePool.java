package za.co.solms.utils.resource.pooling;

/**
  * Contract for resource pools (e.g. connection or thread pools).
  * The pool must be initialized with a prototype and an initial 
  * pool size before the pool can be used.
  */
public interface ResourcePool<R extends Prototype<R>>
{
  /**
   * Provides and locks a handle to a particular resource.
   */
  public R getResource();

  /**
   * Returns the size of the pool.
   */
  public int getSize();

  /**
   * Returns the current number of available resources.
   */
  public int getNumFreeResources();

  /**
   * Releases the provided resource back to the pool It is the 
   * user's responsibility to drop all references to that instance 
   * directly after having released it.
   */
  public void releaseResource(R resource);

  /**
   * Resizes the pool to contain the specified number of instances. 
   * Pool sizes are increased by cloning the prototype.
   */
  public void resize(int numResourceInstances);

  /**
   * Initializes the pool for use by providing the prototype which 
   * is cloned when increasng the pool size. 
   * The pool is initialized to the specified pool size.
   */
  public void init(R resource, int initialPoolSize);
}