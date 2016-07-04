public class MyRunnable implements Runnable
{
  publi MyRunnable() 
  { 
    ++nInstances; 
    instanceNo = nInstances; 
  }

  public void run()
  {
    for(int i=0; i<600; i++)
    {
      System.out.print(instanceNo);
    }
    System.out.println("\nThread" + instanceNo + " terminates.");
  }

  static int nInstances = 0;
  int instanceNo;
}