
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.print.PrinterJob;

public class PrintTest extends JFrame implements ActionListener
{
  public PrintTest()
  {
    addWindowListener(new WindowAdapter()
                      {
                        public void windowClosing(WindowEvent event)
                          {System.exit(0);}
                      });    

    setTitle("Print Test");
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(printButton);
    printButton.addActionListener(this);

    getContentPane().add(buttonPanel,BorderLayout.NORTH);
    getContentPane().add(drawPanel);
    
    setSize(400,400);
    setVisible(true);
  }     
  
  public void actionPerformed(ActionEvent event)
  {
    if (event.getSource() == printButton)
      drawPanel.print();  
  }  
  
  public static void main(String[] args) {new PrintTest();}
  
  private JButton printButton = new JButton("Print");
  private DrawPanel drawPanel = new DrawPanel();
}

class DrawPanel extends JPanel implements Printable
{
  public void paint(Graphics gc)
  {
    draw((Graphics2D)gc) ;
  }  
  
  private void draw(Graphics2D gc)
  {
    Dimension panelSize = getSize();
    int width  = (int)panelSize.getWidth();
    int height = (int)panelSize.getHeight();

    gc.setPaint(new GradientPaint(width/4.0F, height/2.0F, Color.black,
                                  3.0F*width/4.0F, height/2.0F, Color.white));
    
    Ellipse2D ellipse 
      = new Ellipse2D.Double(width/4.0,height/4.0,width/2.0,height/2.0);
    gc.fill(ellipse);
  }

  public int print(Graphics g, PageFormat pf, int pi) throws PrinterException 
  {
    if (pi >= 1) 
      return Printable.NO_SUCH_PAGE;
    draw((Graphics2D) g);
    return Printable.PAGE_EXISTS;
  }
  
  public void print()
  {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(this);
    if (printJob.printDialog()) 
    {
      try {printJob.print();}   
        catch (Exception e) {e.printStackTrace();}
    }
  }    

