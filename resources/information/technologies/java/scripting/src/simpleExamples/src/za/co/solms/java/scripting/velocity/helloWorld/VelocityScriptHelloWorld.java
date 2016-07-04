/**
 * 
 */
package za.co.solms.java.scripting.velocity.helloWorld;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author fritz@solms.co.za
 *
 */
public class VelocityScriptHelloWorld
{

	public static void main(String[] args)
	{
		new VelocityScriptHelloWorld().run();
	}

	public void run()
	{
		try
		{	
			ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
			ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("velocity");
			scriptEngine.put("today", new Date());
			scriptEngine.put("dateFormat", new SimpleDateFormat("dd MMM yyyy"));
			scriptEngine.eval("Hi there. I am from a velocity script. I like the colour $dateFormat.format($today).");
		}
		catch (ScriptException e)
		{
			System.out.println("Aborted script due to " + e);
		}
	}

}
