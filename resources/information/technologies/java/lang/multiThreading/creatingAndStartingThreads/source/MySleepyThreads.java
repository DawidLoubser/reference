public class MySleepyThread extends Thread
{
  public MySleepyThread() 
  { 
    ++nInstances; 
    instanceNo = nInstances; 
  }

  public void run()
    {
      for(int i=0; i<600; i++)
      {
        System.out.print(instanceNo);
        // Every 50th iteration, sleep for 5ms
        if (i%50 == 0)
        {
         try{ sleep(5); } catch (InterruptedException e) { /* Do nothing */ }
        }
      }
      System.out.println("\nThread" + instanceNo + " terminates.");
    }

  static int nInstances = 0;
  int instanceNo;
}


public class MySleepyThreads
{
  public static void main (String args[])
  {
     for (int nThread=1; nThread<=3; nThread++)
       new MySleepyThread().start();
     System.out.println("\nAll threads started. Main thread terminates.");
  }
}