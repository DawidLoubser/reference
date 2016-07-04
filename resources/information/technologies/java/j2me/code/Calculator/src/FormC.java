import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class FormC extends Form
{
	private TextField b;
	private Command next, back;
	public FormC()
	{
		super("Enter a value");
		b= new TextField("Value of B:","", 10, TextField.NUMERIC);
		b.setLayout(Item.LAYOUT_RIGHT);
		append(b);			
		next = new Command("OK",Command.OK,1);
		addCommand(next);
		back = new Command("BACK",Command.BACK,2);
		addCommand(back);
	}

	public int getIntValue()
	{
		return Integer.parseInt(b.getString());
	}
}
