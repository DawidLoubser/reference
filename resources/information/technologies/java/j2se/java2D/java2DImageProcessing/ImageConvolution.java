
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;

public class ImageConvolution extends JFrame
{
  public ImageConvolution() 
  {
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });    

    setTitle("Image Convolution");
    
    setJMenuBar(createMenuBar());

    JScrollPane scrollPane = new JScrollPane(imagePanel);
    scrollPane.setPreferredSize(new Dimension(400,400));
    getContentPane().add(scrollPane);

    openMenuItem.addActionListener(new FileLoader(this));
    
    OperatorSelector operatorSelector = new OperatorSelector(this);
    identityMenuItem.addActionListener(operatorSelector);
    sharpeningMenuItem.addActionListener(operatorSelector);
    blurringMenuItem.addActionListener(operatorSelector);
    inversionMenuItem.addActionListener(operatorSelector);
    thresholdMenuItem.addActionListener(operatorSelector);
    posterizationMenuItem.addActionListener(operatorSelector);
    edgeDetectionMenuItem.addActionListener(operatorSelector);
    
    
    pack();
    setVisible(true);
  }
  
  private JMenuBar createMenuBar()
  {
    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    menuBar.add(fileMenu);

    openMenuItem = new JMenuItem("Open");
    openMenuItem.setMnemonic('O');
    fileMenu.add(openMenuItem);
    
    JMenu transformMenu = new JMenu("Transformation");
    transformMenu.setMnemonic('T');
    menuBar.add(transformMenu);
    
    identityMenuItem      = new JMenuItem("Identity");
    blurringMenuItem      = new JMenuItem("Blurring");
    sharpeningMenuItem    = new JMenuItem("Sharpening");
    inversionMenuItem     = new JMenuItem("Inversion");
    posterizationMenuItem = new JMenuItem("Posterization");
    thresholdMenuItem     = new JMenuItem("Threshold");
    edgeDetectionMenuItem = new JMenuItem("Edge Detection");
    
    transformMenu.add(identityMenuItem);
    transformMenu.add(sharpeningMenuItem);
    transformMenu.add(blurringMenuItem);
    transformMenu.add(inversionMenuItem);
    transformMenu.add(posterizationMenuItem);
    transformMenu.add(thresholdMenuItem);
    transformMenu.add(edgeDetectionMenuItem);
    
    return menuBar;
  }
    
  class FileLoader implements ActionListener
  {
    FileLoader (ImageConvolution frame) {this.frame = frame;}
    
    public void actionPerformed(ActionEvent event)
    {
      try
      {
        JFileChooser fileChooser = new JFileChooser(".");
        FileExtensionFilter filter = new FileExtensionFilter();
        filter.add("gif"); 
        filter.add("jpg"); 
        filter.setDescription("JPG & GIFImages"); 
        fileChooser.setFileFilter(filter); 
        int returnOption = fileChooser.showOpenDialog(frame);

        if (returnOption == JFileChooser.APPROVE_OPTION) 
        {
          String fileName = fileChooser.getSelectedFile().getName();
          imagePanel.setImage(getToolkit().getImage(fileName));
          repaint();
        }  
      }
      catch (Exception e) {e.printStackTrace();}
    }
    private ImageConvolution frame;
  }    
    
  class OperatorSelector implements ActionListener
  {
    OperatorSelector (ImageConvolution frame) {this.frame = frame;}
    
    public void actionPerformed(ActionEvent event)
    {
      if (event.getSource() == thresholdMenuItem)
        imagePanel.setThresholdOp(128);
      else if (event.getSource() == identityMenuItem)
        imagePanel.setIdentityOp();
      else if (event.getSource() == blurringMenuItem)
        imagePanel.setBlurringOp();
      else if (event.getSource() == sharpeningMenuItem)
        imagePanel.setSharpeningOp();
      else if (event.getSource() == inversionMenuItem)
        imagePanel.setInversionOp();
      else if (event.getSource() == edgeDetectionMenuItem)
        imagePanel.setEdgeDetectionOp();
      else if (event.getSource() == posterizationMenuItem)
        imagePanel.setPosterizationOp();
        
      imagePanel.repaint();  
    }
    private ImageConvolution frame;
  }    

  public static void main(String[] args) {new ImageConvolution();}
  
  private ImagePanel imagePanel = new ImagePanel();
  private JMenuItem openMenuItem;  
  private JMenuItem identityMenuItem, blurringMenuItem, sharpeningMenuItem,
                    inversionMenuItem, edgeDetectionMenuItem,
                    posterizationMenuItem, thresholdMenuItem;
} 

class ImagePanel extends JLabel
{
  public void setImage(Image image) 
  {
    if (image != null)
    {
      setIdentityOp();
      try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            tracker.waitForID(0);
          }
          catch ( Exception e ) {}
      // Populate buffered image
      imageWidth = image.getWidth(this);
      imageHeight = image.getHeight(this);
      setMinimumSize(new Dimension(imageWidth,imageHeight));
      setPreferredSize(new Dimension(imageWidth,imageHeight));
      bufferedImage = new BufferedImage(imageWidth, imageHeight, 
                                        BufferedImage.TYPE_INT_RGB);
      Graphics2D bufferedImageGC = bufferedImage.createGraphics();
        
      bufferedImageGC.drawImage(image, 0, 0, this);
    }  
  }
  
  public void paint(Graphics gc)
  {
    super.paint(gc);
      
    if (bufferedImage != null)
    {
      Graphics2D gc2D = (Graphics2D)gc;

      AffineTransform at = new AffineTransform();

      BufferedImage destinationBufferedImage 
        = new BufferedImage(imageWidth, imageHeight, 
                            BufferedImage.TYPE_INT_RGB);      
      operator.filter(bufferedImage, destinationBufferedImage);
      operator = new AffineTransformOp(at, 
                           AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      
      gc2D.drawImage(destinationBufferedImage, operator, 0, 0); 
    }  
  }  
  
  public void setIdentityOp()
  {
    float[] op = {0.0f, 0.0f, 0.0f,   
                  0.0f, 1.0f, 0.0f,
                  0.0f, 0.0f, 0.0f};
    operator = new ConvolveOp
                    (new Kernel(3, 3, op), ConvolveOp.EDGE_NO_OP, null);
  }                    
  
  public void setBlurringOp()
  {
    float x = 1.0f/9.0f;
    float[] op = {x, x, x,   
                  x, x, x,
                  x, x, x};
    operator = new ConvolveOp
                     (new Kernel(3, 3, op), ConvolveOp.EDGE_NO_OP, null);
  }                    
  
  public void setSharpeningOp()
  {
    float[] op = { 0.0f, -1.0f,  0.0f,   
                  -1.0f,  5.0f, -1.0f,
                   0.0f, -1.0f,  0.0f};
    operator = new ConvolveOp
                     (new Kernel(3, 3, op), ConvolveOp.EDGE_NO_OP, null);
  }                    
  
  public void setEdgeDetectionOp()
  {
    float[] op = { 0.0f, -1.0f,  0.0f,   
                  -1.0f,  4.0f, -1.0f,
                   0.0f, -1.0f,  0.0f};
    operator = new ConvolveOp
                     (new Kernel(3, 3, op), ConvolveOp.EDGE_NO_OP, null);
  }    

  public void setThresholdOp(int threshold) 
  {
    short[] filter = new short[256];
    for (int i=0; i<256; ++i)
    {
      if (i<threshold)
        filter[i] = (short)0;
      else
        filter[i] = (short)255;  
    }
    operator = new LookupOp(new ShortLookupTable(0, filter), null);
  }

  public void setInversionOp() 
  {
    short[] filter = new short[256];
    for (int i=0; i<256; ++i)
        filter[i] = (short)(255-i);

    operator = new LookupOp(new ShortLookupTable(0, filter), null);
  }

  public void setPosterizationOp() 
  {
    short[] filter = new short[256];
    for (int i=0; i<256; ++i)
        filter[i] = (short)(i - (i % 64));

    operator = new LookupOp(new ShortLookupTable(0, filter), null);
  }
    
  private BufferedImage bufferedImage = null;
  private BufferedImageOp operator;
  private Image image = null;
  private int imageWidth, imageHeight;
  private int mode = 0;
  private static long count = 0;
}  


class FileExtensionFilter extends javax.swing.filechooser.FileFilter
{
  public FileExtensionFilter() {}

  public FileExtensionFilter(String extension)
    {add(extension);}
    
  public void add(String extension) {extensions.add(extension);}  
  
  public boolean check(String extension, String name)
  {
    int length = name.length();
    
    if (length < extension.length())
      return false;
      
    String ext = name.substring(length - extension.length(), length);
    
    return ext.equals(extension);
  }
  
  public boolean accept (File file)
  {
    Iterator iter = extensions.iterator();
    boolean accept = false;
    
    while ((iter.hasNext()))
      accept = accept | check((String)iter.next(),file.getName());
      
    return accept;  
  }  
  
  public void setDescription(String description) 
    {this.description = description;}
    
  public String getDescription() {return description;}
    
  private LinkedList extensions = new LinkedList();
  private String description;
}


