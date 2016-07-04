public class MyCooperativeThread extends Thread
{
  public MyCooperativeThread() 
  { 
    ++nInstances; 
    instanceNo = nInstances; 
  }

  public void run()
    { 
      for(int i=0; i<600; i++) 
      {
        System.out.print(instanceNo);
        // Every 50th iteration, yield control
        if (i%50 == 0)
        {
         yield();
        }
      }    
      System.out.println("\nThread" + instanceNo + " terminates.");
    }

  static int nInstances = 0;
  int instanceNo;
}


public class MyCooperativeThreads
{
  public static void main (String args[])
  {
    for (int nThread=1; nThread<=3; nThread++) 
      new MyCooperativeThread().start();
    System.out.println("\nAll threads started. Main thread terminates."); 
  }   
}