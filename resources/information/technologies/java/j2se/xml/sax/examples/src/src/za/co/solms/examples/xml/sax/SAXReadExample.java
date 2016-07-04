package za.co.solms.examples.xml.sax;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/** Illustrates how to plug in a simple SAX content handler to report on
 * parsing activity.
 */
public class SAXReadExample
{
  public static void main(String[] args)
  {
    if (args.length < 1)
    {
      System.err.println("Expected: <xmldocument>");
      System.exit(1);
    }
    
    try
    {
      // Set up a factory for SAX aprsers, with the features we want (e.g.
      // being namespace aware)
      SAXParserFactory factory= SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      
      // Create a SAX Parser
      SAXParser parser = factory.newSAXParser();
      
      // Set up our content handler
      EchoContentHandler handler = new EchoContentHandler();
      
      // Parse the document
      parser.parse( args[0], handler );
    }
    catch (Exception e)
    {
      System.err.println("Parsing failed: " + e);
    }
    
  }

  
  /** A simple content handler that just reports 
   * selected parsing activities. */
  public static class EchoContentHandler extends DefaultHandler
  {
    public void setDocumentLocator(Locator locator){ /* Do nothing */ }

    /** Document has started */
    public void startDocument() throws SAXException
    {
      System.out.println("Document start");
    }

    /** Document has ended */
    public void endDocument() throws SAXException
    {
      System.out.println("Document end");
    }

    /** If parser is not namespace aware, namespace mappings
     * are simple attributes on an element. 
     */
    public void startPrefixMapping(String pf, String uri) 
    throws SAXException 
    {
      System.out.println("   Namespace '" + pf + "' mapped to: " + uri);
    }

    public void endPrefixMapping(String prefix) 
    throws SAXException { /* Do nothing */ }

    /** A new element has started. */
    public void startElement(String uri, String localName, String qName, 
        Attributes atts) throws SAXException
    {
      System.out.println("  Element: " + qName);
      for (int x = 0; x < atts.getLength(); x++)
      {
        System.out.println("   @ " + atts.getQName(x) + " = " 
            + atts.getValue(x) );
      }
    }

    /** Finished with element */
    public void endElement(String uri, String localName, String qName) 
    throws SAXException
    {
      System.out.println("  Element end: " + qName);
    }

    /** Character data. This may only represent a portion of a block of
     * character data, and may be called repeatedly. 
     */
    public void characters(char[] ch, int start, int length) 
    throws SAXException
    {
      System.out.println( new String(ch, start, length) );
    }

    /** Insignificant white space, e.g. space between elements */
    public void ignorableWhitespace(char[] ch, int start, int length) 
    throws SAXException { /* Do nothing */ }

    /** Processing instruction */
    public void processingInstruction(String target, String data) 
    throws SAXException
    {
      System.out.println("[PI for '"+target+"': " + data + "]");
    }

    /** Notification that an entity was skipped */
    public void skippedEntity(String name) 
    throws SAXException { /* Do nothing */ }
  }
}