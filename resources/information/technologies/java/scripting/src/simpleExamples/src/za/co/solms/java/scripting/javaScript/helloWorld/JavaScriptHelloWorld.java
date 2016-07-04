/**
 * 
 */
package za.co.solms.java.scripting.javaScript.helloWorld;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author fritz@solms.co.za
 *
 */
public class JavaScriptHelloWorld
{
	public static void main(String[] args)
	{
		new JavaScriptHelloWorld().run();
	}

	public void run()
	{
		try
		{
			ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
			ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
			scriptEngine.eval("print('Hi there, mate')");
		}
		catch (ScriptException e)
		{
			System.out.println("Aborted script due to " + e);
		}
	}
}
