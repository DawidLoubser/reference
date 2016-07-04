/**
 * 
 */
package za.co.solms.training.xml.xslt;

import za.co.solms.training.xml.xslt.Transformer;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author fritz@solms.co.za
 *
 */
public class TransformerImpl implements Transformer
{

	/* (non-Javadoc)
	 * @see za.co.solms.training.xml.xslt.Transformer#transform(javax.xml.transform.stream.StreamSource, javax.xml.transform.stream.StreamSource, javax.xml.transform.stream.StreamResult)
	 */
	@Override
	public void transform(StreamSource sourceXmlStream, 
								StreamSource xsltStream,
								StreamResult resultStream) 
		throws TransformerConfigurationException, TransformerException
	{
		// Use Saxon transformer for XPath-2 and XSLT-2 support.
		System.setProperty("javax.xml.transform.TransformerFactory",
								 "net.sf.saxon.TransformerFactoryImpl");
	
		//Get an XSL Transformer object
		javax.xml.transform.Transformer transformer 
			= TransformerFactory.newInstance().newTransformer(xsltStream);
		
		transformer.transform(sourceXmlStream, resultStream);
	}

}
