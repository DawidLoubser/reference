/**
 * 
 */
package za.co.solms.training.xml.xslt;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author fritz@solms.co.za
 *
 */
public interface Transformer
{
	/**
	 * 
	 * @param sourceXmlStream
	 * @param xsltStream
	 * @param resultStream
	 */
	public void transform(StreamSource sourceXmlStream, 
			               StreamSource xsltStream, 
			               StreamResult resultStream)
		throws TransformerConfigurationException, TransformerException;
}
