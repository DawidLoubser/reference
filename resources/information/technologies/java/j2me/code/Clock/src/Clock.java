<![CDATA[
package za.co.solmstraining.j2me.highlevelgui;

import javax.microedition.lcdui.*;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Clock extends MIDlet
{
  public void startApp() throws MIDletStateChangeException
  {
    if (display == null) initMIDlet();
  }

  public void initMIDlet()
  {
    display = Display.getDisplay(this);
    display.setCurrent(new ClockForm());
  }

  public void pauseApp(){}

  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  public void exitMIDlet() {notifyDestroyed();}

  private class ClockForm extends Form
  {
    public ClockForm()
    {
      super("MIDP Clock");

      addCommand(exitCommand);
      timeField.setDate(new java.util.Date());
      append(timeField);
      
      new Thread()
      {
        public void run()
        {
          System.out.println("run called.");
          while (true)
          {
            try
            {
              sleep(100);
            }
            catch (InterruptedException e)
            {
              timeField.setDate(new java.util.Date());
              System.out.println(new java.util.Date());
            }
          }  
        }
      }.start();

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              Clock.this.exitMIDlet();
          }
        });
    }
    private DateField timeField
      = new DateField(null, DateField.DATE_TIME);
  }

  private Display display;
  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);
}
]]>
