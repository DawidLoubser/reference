import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class FormA extends Form
{
	private TextField a;
	private Command next;
	public FormA()
	{
		super("Enter a value");
		a = new TextField("Value of A:","", 10, TextField.NUMERIC);
		a.setLayout(Item.LAYOUT_EXPAND);
		append(a);			
		next = new Command("OK",Command.OK,1);
		addCommand(next);
	}

	public int getIntValue()
	{
		return Integer.parseInt(a.getString());
	}
}
