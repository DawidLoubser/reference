import java.util.*;

public class RunningAverage
{
  public static void main(String[] args)
  {
    List<Double> list = new LinkedList<Double>();
    new DataProducer(list).start();
    new DataConsumer(list).start();
  }
}


class DataProducer extends Thread
{
  public DataProducer(List<Double> list)
  {
    this.list = list;
  }
  
  public void run()
  {
    while (true)
    {
      Double data = new Double(randomNumberGenerator.nextDouble()*1000.0);
      synchronized(list)
      {
        list.add(data);
        // Notify all threads waiting for state change in list
        list.notifyAll();
      }
      try
      {
        sleep(5);
      }
      catch (InterruptedException e){}
    }
  }
  
  private List<Double> list;
  private Random randomNumberGenerator = new Random();
}


class DataConsumer extends Thread
{
  public DataConsumer(List<Double> list)
  {
    this.list = list;
  }
  
  public void run()
  {
    while (true)
    {
      synchronized(list)
      {
        while (list.isEmpty())
        {
          try
          {
            // wait until somebody notifies that list modified.
            list.wait();
          } 
          catch (InterruptedException e){}  
        }
        if (!list.isEmpty())
        {
          double data = list.remove(0);
          ++dataCount;
          average = (average*(dataCount-1) + data) / dataCount;
          System.out.println(data + " => " + average);
        }
      }
    }
  }
  
  private LinkedList list;
  private long dataCount = 0;
  private double average = 0;
}
