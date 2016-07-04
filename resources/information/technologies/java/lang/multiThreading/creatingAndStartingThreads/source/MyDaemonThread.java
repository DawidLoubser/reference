public class Daemon implements Runnable
{
  public void run()
  { 
    int i = 1;
    while(true)
    {
      System.out.print('x'); 
      if (i%50 == 0)
      {
        Thread.yield();
        i = 0;
      }  
      ++i;
    }    
  }
}

public class MyDaemonThread
{
  public static void main (String args[])
  {
    Thread daemon = new Thread(new Daemon());
    daemon.setDaemon(true);
    daemon.start();
    
    for (int nThread=1; nThread<=3; nThread++)
    {
      new MyCooperativeThread().start();
    }
    System.out.println("\nAll threads started. Main thread terminates."); 
  }
}
