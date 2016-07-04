<![CDATA[
/*
 * LowLevelGUITest.java
 *
 * Created on April 14, 2003, 9:31 PM
 */
package za.co.solmstraining.j2me.lowlevelgui;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 *
 * @author  ernst
 */
public class LowLevelGUITest extends MIDlet
{
    
    /** Creates a new instance of LowLevelGUITest */
    public LowLevelGUITest()
    {
    }
    
    protected void destroyApp(boolean param) throws MIDletStateChangeException
    {
    }
    
    protected void pauseApp()
    {
    }
    
    protected void startApp() throws MIDletStateChangeException
    {
        if (display == null)
            init();
    }
    public void init()
    {
        display = Display.getDisplay(this);
        display.setCurrent(myCanvas);
    }
    
    public void keyPressed(int x, int y)
    {
    }
    
    private Display display;
    private javax.microedition.lcdui.Canvas myCanvas = new LowLevelCanvas(this);
    
}
]]>
