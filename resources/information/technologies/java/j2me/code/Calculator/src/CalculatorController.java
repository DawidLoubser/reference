import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class CalculatorController implements CommandListener
{
  private FormA formA;
  private FormB formB;
  private FormC formC;
  private FormD formD;
  private MIDlet midlet;
  
  public CalculatorController(MIDlet midlet)
  {
    this.midlet = midlet;
    formA = new FormA();
    formA.setCommandListener(this);

    formB = new FormB();
    formB.setCommandListener(this);
    
    formC = new FormC();
    formC.setCommandListener(this);
    
    formD = new FormD();
    formD.setCommandListener(this);
  }

  public void initialiseDisplay()
  {
    try
     { 
       InputStream in = getClass().getResourceAsStream("/splashImage.jpg");
       Image splashImage = Image.createImage(in);
       Alert splashAlert = new Alert("MIDP2 Calculator", "Welcome to the MIDP2 Calcultor" , splashImage, AlertType.INFO);
      splashAlert.setTimeout(2000);
       Display.getDisplay(midlet).setCurrent(splashAlert, formA);
     } 
     catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void commandAction(Command command, Displayable displayable)
  {
    if (displayable == formA)
    {
       Display.getDisplay(midlet).setCurrent(formB);
    }
    if (displayable == formB)
    {
      if (command.getCommandType()==Command.BACK)
      {
         Display.getDisplay(midlet).setCurrent(formA);
      }
      else
      {
         Display.getDisplay(midlet).setCurrent(formC);
      }
    }
    if (displayable == formC)
    {
      if (command.getCommandType()==Command.BACK)
      {
         Display.getDisplay(midlet).setCurrent(formB);
      }
      else
      {
        int a = formA.getIntValue();
        int b = formC.getIntValue();
        formD.setResult(a+b);  
        Display.getDisplay(midlet).setCurrent(formD);
      }
    }
    if (displayable == formD)
    {
      if (command.getCommandType()==Command.EXIT)
      {
				midlet.notifyDestroyed();
      } 
      else
      {     
        Display.getDisplay(midlet).setCurrent(formA);
      }
    }
  }
}
