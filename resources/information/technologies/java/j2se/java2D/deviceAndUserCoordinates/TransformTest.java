
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class TransformTest extends JFrame
{
  public TransformTest()
  {
    setTitle("Testing Java 2D Transformations");
    
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());                      
                      
    JPanel buttonPane = new JPanel();
    buttonPane.add(scaleButton);
    buttonPane.add(translateButton);
    buttonPane.add(rotateButton);
    buttonPane.add(shearButton);
  
    mainPanel.add(buttonPane, BorderLayout.NORTH);
    mainPanel.add(drawPane, BorderLayout.CENTER);
    
    getContentPane().add(mainPanel);
    
    scaleButton.addActionListener(drawPane);
    translateButton.addActionListener(drawPane);
    rotateButton.addActionListener(drawPane);
    shearButton.addActionListener(drawPane);
    
    pack();
    setVisible(true);
  }
  
  private JButton scaleButton     = new JButton("Scale");
  private JButton translateButton = new JButton("Translate");
  private JButton rotateButton    = new JButton("Rotate");
  private JButton shearButton     = new JButton("Shear");
  
  private DrawPane drawPane = new DrawPane();
  
  public static void main(String[] args) {new TransformTest();}
}    

class DrawPane extends JPanel implements ActionListener
{
  public DrawPane() {setPreferredSize(new Dimension(600,400));}
  
  public void paint(Graphics gc)
  {
    super.paint(gc);
    
    Graphics2D gc2D = (Graphics2D)gc;
    
    Dimension dim = getSize();
    double centerX = dim.getWidth()/2;
    double centerY = dim.getHeight()/2;
    
    AffineTransform affineTransform = new AffineTransform();
    
    // Put origin in center
    affineTransform.setToTranslation(centerX,centerY);  
    gc2D.transform(affineTransform);

    // Now perform user-requested transformations
    affineTransform.setToScale(scale,scale);
    gc2D.transform(affineTransform);

    affineTransform.setToRotation(angle);
    gc2D.transform(affineTransform);
    
    affineTransform.setToTranslation(distance,distance);
    gc2D.transform(affineTransform);

    affineTransform.setToShear(shear,0);
    gc2D.transform(affineTransform);
    
    Shape shape = new Rectangle2D.Double(-25,-20,50,40);
    
    gc2D.setPaint(Color.black);
    gc2D.fill(shape);
  }
  
  public void actionPerformed(ActionEvent event)
  {
      
    if (event.getActionCommand().equals("Scale"))
      scale *= 1.1;
      
    else if (event.getActionCommand().equals("Rotate"))
      angle += Math.PI/3;
      
    else if (event.getActionCommand().equals("Translate"))
      distance += 20;
    else if (event.getActionCommand().equals("Shear"))
      shear += 0.1;
      
    repaint();  
  }
  
  private double scale    = 1.0;
  private double angle    = 0;
  private double distance = 0;
  private double shear    = 0;
}    

