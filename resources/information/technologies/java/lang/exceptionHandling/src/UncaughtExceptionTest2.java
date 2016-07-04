package za.co.solms.exceptions;

public class UncaughtExceptionTest2
{
  public static class MyThread extends Thread
  {
    public MyThread(String name) {super(name);}
    
    public void run()
    {
      System.out.println(getName() + " is starting ...");
      try
      {
        sleep(3000);
      }
      catch (InterruptedException e)
      {}

      throw new RuntimeException
                          ("^%!!??!^%%^??!##");
    }
  }

  public static void main(String[] args)
  {
    Thread thread 
      = new MyThread("MySpecialThread1");
    thread.setUncaughtExceptionHandler
                   (new UncaughtExceptionLogger());

    new MyThread("MySpecialThread2").start();
    new MyThread("MySpecialThread3").start();

    Thread.setDefaultUncaughtExceptionHandler
                     (new UncaughtExceptionLogger("[Default]"));
    
    thread.start();

    for (int i=0; i<10; ++i)
    {	    
      try
      {
        Thread.currentThread().sleep(500);	      
      }
      catch (InterruptedException e) {}
      System.out.println("Yawn ...");
    }
  }  
}
	      
	  
