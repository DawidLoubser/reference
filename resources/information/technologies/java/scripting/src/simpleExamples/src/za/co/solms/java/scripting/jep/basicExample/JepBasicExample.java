/**
 * 
 */
package za.co.solms.java.scripting.jep.basicExample;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author fritz@solms.co.za
 *
 */
public class JepBasicExample
{
	public static void main(String[] args)
	{
		new JepBasicExample().run();
	}

	public void run()
	{
		try
		{	
			ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
			ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("jep");
			scriptEngine.put("x", 1.2);
			System.out.println(scriptEngine.eval("2.5*exp(4*x^2 - x)/(3*x)"));
			System.out.println("Done ...");
		}
		catch (ScriptException e)
		{
			System.out.println("Aborted script due to " + e);
		}
	}

	

}
