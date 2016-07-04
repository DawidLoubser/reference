public class DBConnectionPool
{
  private DBConnectionPool() {...}

  private static synchronized createInstance()
  {
    if (theInstance == null)
      theInstance = new DBConnectionPool();
  }

  public static DBConnectionPool getInstance()
  {
    if (theInstance == null)
      DBConnectionPool.createInstance();

    return theInstance;
  }

  /* ... other services ... */

  private static DBConnectionPool theInstance;
}
