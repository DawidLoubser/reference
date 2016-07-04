/**
 * 
 */
package za.co.solms.java.scripting.enginesList;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * @author fritz@solms.co.za
 *
 */
public class ListAvailableScriptingEngines
{
	public static void main(String[] args)
	{
		new ListAvailableScriptingEngines().listScriptingEngines();
	}
	
	public void listScriptingEngines()
	{
		for (ScriptEngineFactory scriptEngineFactory: new ScriptEngineManager().getEngineFactories())
			printScriptEngineDetails(scriptEngineFactory);
	}
	
	public void printScriptEngineDetails(ScriptEngineFactory scriptEngineFactory)
	{
		StringBuffer text = new StringBuffer();
		text.append(scriptEngineFactory.getEngineName());
		text.append(" v").append(scriptEngineFactory.getEngineVersion()).append(eol);
		text.append("   => ").append(scriptEngineFactory.getLanguageName());
		text.append(" v").append(scriptEngineFactory.getLanguageVersion());
		text.append(", mime types:");
		for (String mimeType:scriptEngineFactory.getMimeTypes())
			text.append(mimeType).append(" ");
		text.append(eol);
		
		System.out.println(text);
	}

	private static final String eol = System.getProperty("line.separator");
}
