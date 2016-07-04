import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class CalculatorMIDLet extends MIDlet implements CommandListener
{
	private CalculatorController controller;
	public CalculatorMIDLet()
	{
		controller = new CalculatorController(this);
	}
	
	public void startApp()
	{
		controller.initialiseDisplay();
	}
	
	public void pauseApp()
	{
	}
	
	public void destroyApp(boolean unconditional)
	{
	}

	public void commandAction(Command command, Displayable displayable)
	{
	}
}
