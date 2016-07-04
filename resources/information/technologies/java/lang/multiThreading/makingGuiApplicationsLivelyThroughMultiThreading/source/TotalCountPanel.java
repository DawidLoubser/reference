
import java.util.*;
import javax.swing.*;

public class TotalCountPanel extends JPanel implements Runnable
{
  public TotalCountPanel(SortedMap wordCounts)
  {
    this.wordCounts = wordCounts;
    add(new JLabel("Total no of words:"));
    add(countField);
  }
  
  public void start()
  {
    if (thread == null)
    {
      thread = new Thread(this);
      thread.start();
    }
  }
  
  public void stop()
  {
    stopRequested = true;
  }  
  
  public void run()
  {
    stopRequested = false;
    while (!stopRequested)
    {
      int count = 0;
      synchronized (wordCounts)
      {
        Iterator iter = wordCounts.values().iterator();
        while (iter.hasNext())
          count += ((WordCounter.Counter)iter.next()).getValue();
      }
      countField.setText(String.valueOf(count));
      try
      {
        Thread.currentThread().sleep(200);
      }
      catch (InterruptedException e) 
      {}
    }
    thread = null;
  }      
        
  private Thread thread;
  
  private boolean stopRequested;      
        
  private SortedMap wordCounts;    
  
  private JTextField countField = new JTextField(8);
}    
