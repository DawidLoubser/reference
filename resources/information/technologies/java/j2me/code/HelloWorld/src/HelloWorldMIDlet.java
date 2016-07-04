<![CDATA[
package za.co.solmstraining.j2me.gettingstarted.helloworld;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class HelloWorldMIDlet extends MIDlet
{
  public void startApp() throws MIDletStateChangeException
  {
    if (display == null) initMIDlet();
  }

  public void initMIDlet()
  {
    display = Display.getDisplay(this);
    display.setCurrent(new HelloWorldForm());
  }

  public void pauseApp()
  {
    System.out.println("MIDlet paused");
  }

  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  public void exitMIDlet()
  {
    notifyDestroyed();
    System.out.println("MIDlet destroyed.");
  }

  private class HelloWorldForm extends Form
  {
    public HelloWorldForm()
    {
      super("HelloWorld MIDlet");
      addCommand(exitCommand);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              HelloWorldMIDlet.this.exitMIDlet();
          }
        });
    }
  }

  private Display display;
  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);
}
]]>
