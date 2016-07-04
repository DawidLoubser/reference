
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class WordCounterApp extends JFrame
{
  public WordCounterApp()
  {
    JPanel mainPanel = new JPanel();
    mainPanel.add(createLeftPanel());
    mainPanel.add(createRightPanel());
    
    getContentPane().add(mainPanel);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setTitle(coreTitle);
    
    pack();
  } 
  
  private JPanel createRightPanel()
  {
    JPanel panel = new JPanel();
    
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    panel.add(aCountPanel);
    panel.add(anCountPanel);
    panel.add(theCountPanel);
    
    JPanel wordCountPanel = new JPanel();
    wordCountPanel.add(new JLabel("word:"));
    wordCountPanel.add(wordField);
    wordField.setToolTipText
      ("Enter any word to obtain number of occurances of that word in file.");
    wordCountPanel.add(wordCountField);
    wordCountField.setEditable(false);
    panel.add(wordCountPanel);
    
    wordField.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          String word = wordField.getText().trim().toLowerCase();
          if (wordCounts.get(word) == null)
            wordCountField.setText("0");
          else
            wordCountField.setText(wordCounts.get(word).toString());  
        }
      });
        
    panel.setBorder(BorderFactory.createTitledBorder
      ("Individual word counts"));
    JPanel outerPanel = new JPanel();
    outerPanel.add(panel);      
    outerPanel.setBorder(BorderFactory.createRaisedBevelBorder());    
    return outerPanel;
  }
  
  private JPanel createLeftPanel()
  {
    JPanel panel = new JPanel();
    
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JButton selectFileButton = new JButton("select file");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(selectFileButton);
    panel.add(buttonPanel);
    
    buttonPanel = new JPanel();
    JButton startButton = new JButton("start");
    JButton stopButton = new JButton("stop");
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    panel.add(buttonPanel);
    
    progressBar.setBorder(BorderFactory.createTitledBorder("Progress"));
    progressBar.setToolTipText("% of file completed.");
    panel.add(progressBar);
    progressBar.setValue(0);
    progressBar.setStringPainted(true);    
    
    panel.add(totalCountPanel); 
    
    selectFileButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          JFileChooser fileChooser = new JFileChooser(".");
          if (fileChooser.showOpenDialog(WordCounterApp.this) 
                == JFileChooser.APPROVE_OPTION)
          {
            file = fileChooser.getSelectedFile();
            WordCounterApp.this.setTitle(coreTitle + ": " + file.getName()
               + " (" + (file.length()/1000) + "kB)");
          }
        }
      });
    
    startButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          if (file == null)
          {
            JOptionPane.showMessageDialog(WordCounterApp.this,
                "No file selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
          }
          
          BufferedReader in = null;
          try
          {
            FileReader fin = new FileReader(file);
            in = new BufferedReader(fin);  
          }
          catch (IOException ex)
          {
            JOptionPane.showMessageDialog(null, "Could not open file",
               "File Error", JOptionPane.ERROR_MESSAGE);
          }
          if (in != null)
          {
            start = new Date();
            wordCounts.clear();
            
            wordCounter = new WordCounter(in, file.length(), wordCounts);
            
            wordCounter.addProgressListener(new WordCounter.ProgressListener()
              {
                public void completedFraction(double fraction)
                {
                  progressBar.setValue((int)(fraction*100));
                }
                
                public void completed()
                {
                  progressBar.setValue(100);
                  wordCounter.removeProgressListener(this);
                  totalCountPanel.stop();
                  aCountPanel.stop();
                  anCountPanel.stop();
                  theCountPanel.stop();
                  
                  Date end = new Date();
                  double timeInSeconds 
                    = ((double)(end.getTime() - start.getTime()))/1000.0;
                  JOptionPane.showMessageDialog(WordCounterApp.this,
                    ("Required " + String.valueOf(timeInSeconds) 
                      + " seconds " + " to count all words in file."), 
                      "Time taken",
                        JOptionPane.INFORMATION_MESSAGE);
                  
                }
              });
            
            Thread counterThread = new Thread(wordCounter);
            counterThread.setPriority(Thread.MIN_PRIORITY);
            counterThread.start();
            totalCountPanel.start();
            aCountPanel.start();
            anCountPanel.start();
            theCountPanel.start();
          }  
        }
      });    
      
    stopButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          if (wordCounter != null)
            wordCounter.stop();
        }  
      });  
      
    return panel;    
  }
  
  public static void main(String[] args)
  {
    new WordCounterApp().setVisible(true);
  }  
  
  private SortedMap wordCounts = new TreeMap();

  private TotalCountPanel totalCountPanel = new TotalCountPanel(wordCounts);
  private WordCountPanel aCountPanel = new WordCountPanel(wordCounts, "a");
  private WordCountPanel anCountPanel = new WordCountPanel(wordCounts, "an");
  private WordCountPanel theCountPanel = new WordCountPanel(wordCounts, "the");
  
  private JTextField wordField = new JTextField("",6);
  private JTextField wordCountField = new JTextField("",6);
  
  private File file;
  
  private static final String coreTitle = "STC Word Counter";
    
  private JProgressBar progressBar 
    = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
  private WordCounter wordCounter;    
  private Date start;
}  
