public class MyThreads
{
  public static void main (String args[])
  {
    for (int nThread=1; nThread<=3; nThread++)
    {
      // Create a new thread which will perform the instructions
      // specified by the given Runnable
      Thread t = new Thread( new MyRunnable() );
      
      // Start it - this call return immediately
      t.start();
    }

    System.out.println("\nAll threads started. Main thread terminates.");
  }
}