<![CDATA[
package za.co.solmstraining.j2me.highlevelgui;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class CommandPrioritiesTest extends MIDlet
{
  public void startApp() throws MIDletStateChangeException
  {
    if (display == null) initMIDlet();
  }

  public void initMIDlet()
  {
    display = Display.getDisplay(this);
    display.setCurrent(new CommandPrioritiesForm());
  }

  public void pauseApp(){}
  
  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  public void exitMIDlet() {notifyDestroyed();}

  private class CommandPrioritiesForm extends Form
  {
    public CommandPrioritiesForm()
    {
      super("Command Priorities Test");

      addCommand(nextCommand);
      addCommand(backCommand);
      addCommand(helpCommand);
      addCommand(stopCommand);
      addCommand(exitCommand);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              CommandPrioritiesTest.this.exitMIDlet();
          }
        });
    }
  }

  private Display display;
  private static final Command nextCommand
    = new Command("Next", Command.OK, 1);
  private static final Command backCommand
    = new Command("Back", Command.BACK, 1);
  private static final Command stopCommand
    = new Command("Stop", Command.HELP, 3);
  private static final Command helpCommand
    = new Command("Help", Command.HELP, 3);
  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);
}
]]>
