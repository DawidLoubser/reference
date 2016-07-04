import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Dimension;
import java.awt.Component;

/**
 * This is a default frame supplying a frame window for any component.
 * It inserts the component into a scroll pane and inserts the scroll
 * pane into the frame window.
 * Typically panels or applets are inserted into the frame.
 * <P>
 * <B>Note:</B> The frame window aborts the application when it is closed.
 *
 * @author Fritz Solms
 * @version 1.0 final
 */
public class DefaultFrame extends JFrame 
{
  /**
   * Creates a default frame window with specified title and initial size
   * containing the specified component.
   */
  public DefaultFrame(Component content, String title)
    {this(content, title, null);}
    
  public DefaultFrame(Component content, String title, String iconFileName)
    {this(content, title, null, iconFileName);}
    
  /**
   * Creates a default frame window with specified title and initial size
   * containing the specified component.
   */
  public DefaultFrame(Component content, String title,
                      Dimension initialSize, String iconFileName)
  {
    setTitle(title);

    if (iconFileName != null)
      this.setIconImage(new ImageIcon(iconFileName).getImage());
    
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent event)
          {System.exit(0);} });
    
    getContentPane().add(new JScrollPane(content));
    
    if (initialSize == null)
      pack();
    else  
      setSize(initialSize);
      
    setVisible(true);    
  }    
}  
