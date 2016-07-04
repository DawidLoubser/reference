
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

public class ShapeTest extends JFrame
{
  public ShapeTest()
  {
    setTitle("Testing Java 2D Shapes");
    
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });
            
    JPanel mainPanel = new JPanel();                      
    mainPanel.setLayout(new GridLayout(3,3,10,10));
    mainPanel.add(new ArcDemo(Arc2D.OPEN));
    mainPanel.add(new ArcDemo(Arc2D.CHORD));
    mainPanel.add(new ArcDemo(Arc2D.PIE));
    mainPanel.add(new GeneralShapeDemo(GeneralShapeDemo.SOLID));
    mainPanel.add(new GeneralShapeDemo(GeneralShapeDemo.GRADIENT));
    mainPanel.add(new GeneralShapeDemo(GeneralShapeDemo.TEXTURE));
    mainPanel.setPreferredSize(new Dimension(400,400));
    mainPanel.setBackground(Color.white);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    
    getContentPane().add(mainPanel);
    pack();
    setVisible(true);
  }
  
  public static void main(String[] args) {new ShapeTest();}
}

class ArcDemo extends JLabel
{
  public ArcDemo(int type) {this.type = type;}
  
  public void paint(Graphics gc)
  {
    super.paint(gc);
    Graphics2D gc2D = (Graphics2D)gc;
    Dimension dim = getSize();
    double top = 0;
    double left = 0;
    double width = dim.getWidth();
    double height = dim.getHeight();
    double startingAngle = 0;
    double angularExtend = 270;
    gc2D.setStroke(new BasicStroke(3));
    gc2D.draw(new Arc2D.Double(top, left, width, height, startingAngle,
                               angularExtend, type));
  }                  
  private int type;           
}  
    

class GeneralShapeDemo extends JLabel
{
  public GeneralShapeDemo(int type) {this.type = type;}
  
  public void paint(Graphics gc)
  {
    super.paint(gc);
    Graphics2D gc2D = (Graphics2D)gc;
    Dimension dim = getSize();
    gc2D.setStroke(new BasicStroke(3));
    
    GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
    path.append(new Line2D.Double(0, dim.getHeight()/2,
                                  dim.getWidth()/2, dim.getHeight()), true);
    path.append(new Line2D.Double(dim.getWidth()/2, dim.getHeight(),
                                  dim.getWidth(), dim.getHeight()/2), true);
    path.append(new QuadCurve2D.Double(dim.getWidth(), dim.getHeight()/2,
                                       dim.getWidth()/2,-dim.getHeight()/2, 
                                       0, dim.getHeight()/2), true);

    switch (type)
    {
      case SOLID: break;
      
      case GRADIENT:  GradientPaint blueToYellow 
                        = new GradientPaint(0, (float)dim.getHeight()/2, 
                                            Color.blue, (float)dim.getWidth(), 
                                            (float)dim.getHeight()/2, 
                                            Color.yellow);
                      gc2D.setPaint(blueToYellow);
                      break;

      case TEXTURE: int blockSize = 5;                                       
                    BufferedImage bufferedImage
                      = new BufferedImage(blockSize, blockSize, 
                                          BufferedImage.TYPE_INT_RGB);
                    Graphics2D bufferedImageGC = bufferedImage.createGraphics();
		                bufferedImageGC.setColor(Color.blue);
		                bufferedImageGC.fillRect(0, 0, blockSize, blockSize);
		                bufferedImageGC.setColor(Color.lightGray);
		                bufferedImageGC.fillOval(0, 0, blockSize, blockSize);
		                Rectangle rectangle = new Rectangle(0, 0, blockSize, 
		                                                    blockSize);
		                gc2D.setPaint(new TexturePaint(bufferedImage, rectangle));
		                break;
	  }	                
    gc2D.fill(path);
  }                  
  
  public static final int SOLID    = 0;
  public static final int GRADIENT = 1;
  public static final int TEXTURE  = 2;
  
  private int type;
}  
