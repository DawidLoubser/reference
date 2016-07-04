
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class WordCountPanel extends JPanel implements Runnable
{
  public WordCountPanel(SortedMap wordCounts, String word)
  {
    this.wordCounts = wordCounts;
    this.word = word;
    
    setLayout(new FlowLayout(FlowLayout.RIGHT));
    
    add(new JLabel(word));
    add(wordField);
    wordField.setEditable(false);
  }
  
  public void start()
  {
    stopRequested = false;
    new Thread(this).start();
  }
  
  public void stop()
  {
    stopRequested = true;
  }
    
  public void run()
  {
    while (!stopRequested)
    {
      if (wordCounts.get(word) != null)
      {
        WordCounter.Counter counter 
          = (WordCounter.Counter)wordCounts.get(word);
        if (counter.getValue() != lastCount)
        {
          lastCount = counter.getValue();
          wordField.setText(counter.toString());
        }
      }
      synchronized(Thread.currentThread())
      {
        try
        {
          Thread.currentThread().sleep(200);
        }
        catch (InterruptedException e) {}  
      }  
    }  
  }
  
  private String word;
  private JTextField wordField = new JTextField(6);
  private int lastCount = 0;
            
  private SortedMap wordCounts;     
  
  private boolean stopRequested = false;
}
