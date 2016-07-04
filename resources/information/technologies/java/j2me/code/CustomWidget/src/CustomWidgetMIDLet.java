import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class CustomWidgetMIDLet extends MIDlet implements CommandListener
{
  private Form form;
  public CustomWidgetMIDLet()
  {
   form = new Form("Hallo World");
   form.append(new CustomWidget("Hallo"));
   form.addCommand(new Command("EXIT", Command.EXIT, 0));
   form.setCommandListener(this);
  }
  
  public void startApp()
  {
    Display.getDisplay(this).setCurrent(form);
  }
  
  public void pauseApp()
  {
  }
  
  public void destroyApp(boolean unconditional)
  {
  }

  public void commandAction(Command command, Displayable displayable)
  {
    notifyDestroyed();
  }
}
