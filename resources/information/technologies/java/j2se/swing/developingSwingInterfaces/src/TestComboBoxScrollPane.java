import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.Vector;

public class TestComboBoxScrollPane extends JFrame 
                       implements ActionListener
{
  public TestComboBoxScrollPane()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("Combo Boxes and Scroll Panes");
    
    getContentPane().setLayout(new BorderLayout());
    
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));                      
    
    comboBoxModel = new DefaultComboBoxModel(loadImages());
    comboBox = new JComboBox(comboBoxModel);   
    comboBox.addActionListener(this); 
    
    mainPanel.add(comboBox, BorderLayout.NORTH);
    mainPanel.add(new JScrollPane(imageLabel),BorderLayout.CENTER);

    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);

    showImageFile((File)comboBox.getSelectedItem());
    
    getContentPane().add(mainPanel);
    
    setSize(500,500);
    setVisible(true);
  }
  
  public Vector loadImages()
  {
    Vector imageFiles = new Vector();
    File dir = new File(".");

    File[] jPegFiles = dir.listFiles(new FileExtensionFilter("jpg"));
    for (int i=0; i<jPegFiles.length; ++i)
      imageFiles.addElement(jPegFiles[i]);

    File[] gifFiles = dir.listFiles(new FileExtensionFilter("gif"));
    for (int i=0; i<jPegFiles.length; ++i)
      imageFiles.addElement(gifFiles[i]);

    return imageFiles;  
  }  

  public void showImageFile(File file)
  {
    imageLabel.setIcon (new ImageIcon(file.getName()));
  }
  
  public void actionPerformed(ActionEvent event)
    {showImageFile((File)comboBox.getSelectedItem());}

  private JLabel imageLabel = new JLabel();
  private JPanel mainPanel = new JPanel();
  private JComboBox comboBox;
  private DefaultComboBoxModel comboBoxModel;
  
  public static void main(String[] args) {new TestComboBoxScrollPane();}
}


class FileExtensionFilter implements FilenameFilter
{
  public FileExtensionFilter(String extension)
    {this.extension = "." + extension;}

  public boolean accept (File file, String name)
  {
    int length = name.length();

    if (length < extension.length())
      return false;

    String ext = name.substring(length - extension.length(), length);

    return ext.equals(extension);
  }
  private String extension;
}
