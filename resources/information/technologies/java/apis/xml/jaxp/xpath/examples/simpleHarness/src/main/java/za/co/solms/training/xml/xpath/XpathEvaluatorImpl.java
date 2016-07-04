/**
 * 
 */
package za.co.solms.training.xml.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

/**
 * @author fritz@solms.co.za
 *
 */
public class XpathEvaluatorImpl implements XpathEvaluator
{

	/* (non-Javadoc)
	 * @see za.co.solms.training.xml.xpath.XpathEvaluator#evaluate(java.lang.String, org.xml.sax.InputSource)
	 */
	@Override
	public String evaluate(InputSource xmlDocument, String xpathString) throws XPathExpressionException
	{
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression xpathExpression = xpath.compile(xpathString);
		
		Object result = xpathExpression.evaluate(xmlDocument, XPathConstants.NODESET);
		System.out.println("Result object = " + result.getClass());
		
		return result.toString();
	}

}
