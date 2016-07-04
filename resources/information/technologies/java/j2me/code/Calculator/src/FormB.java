import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class FormB extends Form
{
	public static final int FUNCTION_PLUS=0;
	public static final int FUNCTION_MINUS=1;
	public static final int FUNCTION_MULTIPLY=2;
	public static final int FUNCTION_DIVIDE=3;
	private ChoiceGroup functions;
	private Command next;
	private Command back;
	
	public FormB()
	{
		super("Select operation : ");
		String [] operations = {"+","-","*","/"};
		functions = new ChoiceGroup("Operation",Choice.EXCLUSIVE,operations,null);
		append(functions);
			
		next = new Command("OK",Command.OK,1);
		addCommand(next);
		back = new Command("BACK",Command.BACK,2);
		addCommand(back);
	}

	public int getOperationID()
	{
		return 0;
	}
}
