import java.util.*;
import java.io.*;
import java.text.*;

public class TestPipes
{
  public static void main(String[] args) throws IOException
  {
    PipedOutputStream pipeInput  = new PipedOutputStream();
    PipedInputStream  pipeOutput = new PipedInputStream();
    pipeInput.connect(pipeOutput);
    
    new Thread(new DataSource(pipeInput)).start();
    new Thread(new DataProcessor(pipeOutput)).start();
  }
}

class DataSource implements Runnable
{
  public DataSource (PipedOutputStream pipe) 
  {
    this.pipe = pipe;
    try {out = new ObjectOutputStream(pipe);} 
      catch (IOException e) {e.printStackTrace(); System.exit(0);}
  }
    
  public void run() 
  {
    for (int i=0; i<10; ++i)
    {
      try
      {
        Date dateTime = new Date();
        Double data = randomNumberGenerator.nextDouble();
        out.writeObject(dateTime);
        out.writeObject(data);  
      }
      catch (IOException event) {event.printStackTrace();}  
    }  
  }    
  private PipedOutputStream pipe;
  private Random randomNumberGenerator = new Random();
  private ObjectOutputStream out;
}


class DataProcessor implements Runnable
{
  public DataProcessor (PipedInputStream pipe) {this.pipe = pipe;}
    
  public void run() 
  {
    try
    {
      ObjectInputStream in = new ObjectInputStream(pipe);
      while(true)
        try
        {
          Date dateTime = (Date)in.readObject();
          Double data   = (Double)in.readObject();
          System.out.println("received data: " 
                             + dateFormat.format(dateTime) + " => " 
                             + nFormat.format(data.doubleValue()));
        }
        catch (ClassNotFoundException e) {e.printStackTrace();}                     
    }
    catch (IOException e) {System.out.println(e.getMessage());}  
  }
  
  private PipedInputStream pipe;
  private DateFormat dateFormat 
    = new SimpleDateFormat("HH:mm:ss:SS 'on' dd/MM/yy");
  private DecimalFormat nFormat = new DecimalFormat("#.####");
}

