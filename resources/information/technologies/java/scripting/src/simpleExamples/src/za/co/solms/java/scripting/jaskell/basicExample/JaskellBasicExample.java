/**
 * 
 */
package za.co.solms.java.scripting.jaskell.basicExample;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author fritz@solms.co.za
 *
 */
public class JaskellBasicExample
{
	public static void main(String[] args)
	{
		new JaskellBasicExample().run();
	}

	public void run()
	{
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("jaskell");
		
		System.out.println(System.getProperty("java.class.path"));
		
		InputStream scriptStream = 
	      this.getClass().getResourceAsStream("/factorial.jaskell");
		System.out.println("scriptStream = " + scriptStream);
	  try 
	  {
	    Reader scriptReader = new InputStreamReader(scriptStream);
	    scriptEngine.eval(scriptReader);
	    System.out.println(scriptEngine.eval("factorial 30"));
	  } 
	  catch (ScriptException ex) 
	  {
	    ex.printStackTrace();
	  }
	}
}
