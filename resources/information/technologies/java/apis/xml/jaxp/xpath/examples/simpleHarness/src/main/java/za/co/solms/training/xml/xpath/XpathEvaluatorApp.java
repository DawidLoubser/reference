package za.co.solms.training.xml.xpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.xml.sax.InputSource;

/**
 * Simple Harness taking a XML source file and XSLT transformation file as inputs and
 * generating the transformed output file.
 *  
 * @author fritz@solms.co.za
 *
 */
public class XpathEvaluatorApp
{
	public static void main(String[] args)
	{
		System.out.println("Querying " + args[0] + " for " + args[1]);
		if (args.length != 2)
		{
			System.out.println
				("Usage: java za.co.solms.training.xml.xpath.XpathEvaluatorApp <xmlSourceFile> <xpathExpression>");
			System.exit(0);
		}

		File xmlFile = new File(args[0]);
		String XpathExpressionString = args[1];
		try
		{
			BufferedReader reader 
				= new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile)));
			
			//StreamSource xmlSource = new StreamSource(xmlFile);
			InputSource xmlSource = new InputSource(reader);
			//String result
			Object o = new XpathEvaluatorImpl().evaluate(xmlSource, XpathExpressionString);
			System.out.println("Result = " + o.getClass() + " -> " + o);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}			{
		}

	}
}
