package za.co.solms.training.xml.xslt;

import java.io.File;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Simple Harness taking a XML source file and XSLT transformation file as inputs and
 * generating the transformed output file.
 *  
 * @author fritz@solms.co.za
 *
 */
public class TransformApp
{
	public static void main(String[] args) 
		throws TransformerException, TransformerConfigurationException
	{
		System.out.println("Transforming " + args[0] + " via " + args[1] + " to " + args[2]);
		if (args.length != 3)
		{
			System.out.println
				("Usage: java za.co.solms.training.xml.xslt.TransformApp <xmlSourceFile> <xsltFile> <outputFileName>");
			System.exit(0);
		}

		File xmlFile = new File(args[0]); 
		File xsltFile = new File(args[1]);
		File resultFile = new File(args[2]);
		
		StreamSource xmlSource = new StreamSource(xmlFile);
		StreamSource xsltSource = new StreamSource(xsltFile);
		StreamResult resultStream = new StreamResult(resultFile);
		
		new TransformerImpl().transform(xmlSource, xsltSource, resultStream);
	}
}
