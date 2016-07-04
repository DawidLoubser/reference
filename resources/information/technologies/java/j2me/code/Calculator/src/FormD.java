import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class FormD extends Form
{
  private TextField r;
  private Command next,quit;
  public FormD()
  {
    super("Enter a value");
    r = new TextField("Result","", 10, TextField.NUMERIC);
    r.setLayout(Item.LAYOUT_CENTER);
    append(r);      
    next = new Command("OK",Command.OK,1);
    addCommand(next);
    quit = new Command("Quit", Command.EXIT,2);
    addCommand(quit);
  }
  public void setResult(int result)
  {
    r.setString(Integer.toString(result));
  }
}
