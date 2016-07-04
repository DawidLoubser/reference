
import java.io.*;
import java.util.*;

public class WordCounter implements Runnable
{
  public WordCounter(BufferedReader in, long streamLength, 
                                SortedMap wordCounts)
  {
    this.in = in;
    this.wordCounts = wordCounts;
    this.streamLength = streamLength;
  }	
  
  public void stop()
  {
    stopRequested = true;
  }
  
	
  public void run()
  {
    stopRequested = false;
    try
    {
      while (in.ready() && (!stopRequested))
      {
        addWords(in.readLine());
        Thread.currentThread().yield();
      }  
    }
    catch (EOFException e)
      {}
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
    if (progressListeners.size() != 0)
    {
      Iterator iter = progressListeners.iterator();
      ProgressListener progressListener = (ProgressListener)iter.next();
      progressListener.completed();
    }
  }
    
  private void addWords(String line)
  {
    if (line != null)
    {
      StringTokenizer tokenizer = new StringTokenizer
        (line, " ,.;?()+-*/&^$_=0123456789><[]{}|");
      while (tokenizer.hasMoreTokens())
      {
        String word = tokenizer.nextToken().toLowerCase();
        Counter counter = (Counter)wordCounts.get(word);
        synchronized (wordCounts)
        {
          if (counter == null)
            wordCounts.put(word.toLowerCase(), new Counter(1));
          else
            counter.increment();
        }    
      }
      streamPosition += line.length();
      if (progressListeners.size() != 0)
      {
        Iterator iter = progressListeners.iterator();
        ProgressListener progressListener = (ProgressListener)iter.next();
        progressListener.completedFraction
               ((double)streamPosition/streamLength);
      }
    }
  } 
  
  public static interface ProgressListener
  {
    public void completedFraction(double fraction);
    
    public void completed();
  }
  
  public static class Counter
  {
    public Counter() 
    {
      this(0);
    }
    
    public Counter(int initialCount)
    {
      value = initialCount;
    }
    
    public int getValue()
    {
      return value;
    }
    
    public void reset() 
    {
      value = 0;
    }
    
    public void increment() 
    {
      ++value;
    }
    
    public String toString()
    {
      return String.valueOf(value);
    }
    
    private int value = 0;
  }
  
  public void addProgressListener(ProgressListener progressListener)
  {
    progressListeners.add(progressListener);
  }
  
  public void removeProgressListener(ProgressListener progressListener)
  {
    progressListeners.remove(progressListener);
  }
  
  public static void main(String[] args) throws IOException
  {
    File file = new File("temp.txt");
    FileInputStream fin = new FileInputStream(file);
    BufferedReader in 
      = new BufferedReader(new InputStreamReader(fin));
    
    SortedMap wordCounts = new TreeMap();
    new WordCounter(in, file.length(), wordCounts).run();
    System.out.println(wordCounts);
  }  
  
  private boolean stopRequested;
  private BufferedReader in;
  private SortedMap wordCounts;
  private Set progressListeners = new HashSet();
  private long streamLength, streamPosition;
}
