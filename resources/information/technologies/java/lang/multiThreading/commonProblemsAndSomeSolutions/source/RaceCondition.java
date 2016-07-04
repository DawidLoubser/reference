import java.util.*;
import java.io.*;
import java.text.*;

public class RaceCondition
{
  public static void main(String[] args) throws IOException
  {
    List list = new LinkedList();
    DataSource dataSource = new DataSource(list);
    
    for (int i=0; i<2; ++i)
    {
      DataProcessor dataProcessor = new DataProcessor(list,dataSource,i);
      dataSource.addDataProcessor(dataProcessor);
      new Thread(dataProcessor).start();
    }  
    new Thread(dataSource).start();
  }
}

class DataSource implements Runnable
{
  public DataSource (List list) {this.list = list;}
    
  public void run() 
  {
    for (int i=0; i<10; ++i)
    {
      synchronized(this)
      {
      Date dateTime = new Date();
      Double data = new Double(randomNumberGenerator.nextDouble());
      list.add(dateTime);
      list.add(data);
      notifyAll();
      }
      try {Thread.sleep(1000);} catch (InterruptedException e) {}
    }  
    stopDataProcessorThreads();
  }    
  
  public void addDataProcessor(DataProcessor dataProcessor)
    {dataProcessors.add(dataProcessor);}

  public void stopDataProcessorThreads()
  {
    while(dataProcessors.size() != 0)
      ((DataProcessor)dataProcessors.remove(0)).stop();
  }    
    
  private List list;
  private List dataProcessors = new LinkedList();
  private Random randomNumberGenerator = new Random();
  private DataProcessor dataProcessor;
}


class DataProcessor implements Runnable
{
  public DataProcessor (LinkedList list, DataSource dataSource, int no) 
  {
    this.list = list;
    this.dataSource = dataSource;  
    this.no = no;
  }
    
  public void run() 
  {
    stopRequested = false;
    
    while(!stopRequested)
    {
      synchronized(dataSource)
      {
        if ((list.size() == 0) && (!stopRequested))
        {
          try { dataSource.wait(); } catch (InterruptedException e) {}
        }
        
// The code above will be prone to race-conditions, and should rather
// be replaced with a spin-lock such as:
//      while ((list.size() == 0) && (!stopRequested))
//      {
//        ...
      
        if (!stopRequested)
        {
          Date dateTime = (Date)list.removeFirst();
          System.out.print("#" + no + " received data: " 
                           + dateFormat.format(dateTime) + " => ");
          Double data   = (Double)list.removeFirst();  
          System.out.println(dataFormat.format(data.doubleValue()));
        }  
      }    
    }
  }
  
  public void stop() 
  {
    System.out.println("data processor " + no + " stopped.");
    stopRequested = true;
  }
  
  private LinkedList list;
  private DateFormat dateFormat 
    = new SimpleDateFormat("HH:mm:ss:SS 'on' dd/MM/yy");
  private DecimalFormat dataFormat = new DecimalFormat("#.####");
  private DataSource dataSource;
  private boolean stopRequested;
  private int no;
}
