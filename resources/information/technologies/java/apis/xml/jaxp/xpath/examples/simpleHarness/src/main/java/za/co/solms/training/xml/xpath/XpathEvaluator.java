/**
 * 
 */
package za.co.solms.training.xml.xpath;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.InputSource;

/**
 * @author fritz@solms.co.za
 *
 */
public interface XpathEvaluator
{
	public String evaluate(InputSource xmlDocument, String xpathExpression)
	 throws XPathExpressionException;
}
