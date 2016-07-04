
package docs.components.XML.Parsers;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXElementCounter extends DefaultHandler
{
  public SAXElementCounter() {}

  public void count(String fileName) throws Exception
  {
    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    parserFactory.setValidating(true);
    SAXParser saxParser = parserFactory.newSAXParser();
    XMLReader xmlReader = saxParser.getXMLReader();
    xmlReader.setContentHandler(this);
    xmlReader.setErrorHandler(new MyErrorHandler(System.err));

    InputSource source
     = new InputSource(new java.io.File(fileName).toURL().toString());

    try
    {
    	xmlReader.parse(source);
    }
    catch (SAXException e)
    {
    	System.err.println(e.getMessage());
        System.exit(1);
    }
  }

  public void startDocument() {elementCount.clear();}

  public void startElement(String uri, String localName, String qName, Attributes attributes)
  {
     Counter counter = (Counter)elementCount.get(qName);
     if (counter == null)
       elementCount.put(qName, new Counter(1));
     else
       counter.increment();
  }

  public void endDocument()
  {
    System.out.println
      ("Reached end of document. Occurances per element = ");
    java.util.Iterator iter = elementCount.keySet().iterator();
    while (iter.hasNext())
    {
      String name = (String)iter.next();
      System.out.println(name + " " + elementCount.get(name));
    }
  }

  public static void main(String[] args) throws Exception
  {
    if (args.length != 1)
    {
      System.out.println("ERROR ** Usage: java SAXElementCounter xmlFileName");
      System.exit(0);
    }

    new SAXElementCounter().count(args[0]);
  }

  private java.util.TreeMap elementCount = new java.util.TreeMap();

  private static class MyErrorHandler implements ErrorHandler
  {
     MyErrorHandler(java.io.PrintStream out) {this.out = out;}

     private String getParseExceptionInfo(SAXParseException e)
     {
       String systemId = e.getSystemId();
       if (systemId == null)
         systemId = "null";

       return "URI=" + systemId + " Line=" + e.getLineNumber() +
                ": " + e.getMessage();
     }

     public void warning(SAXParseException e) throws SAXException
     {
       out.println("Warning: " + getParseExceptionInfo(e));
     }

     public void error(SAXParseException e) throws SAXException
     {
       throw new SAXException("Error: " + getParseExceptionInfo(e));
     }

     public void fatalError(SAXParseException e) throws SAXException
     {
       throw new SAXException("Fatal Error: " + getParseExceptionInfo(e));
     }

     private java.io.PrintStream out;
  }

  class Counter
  {
     public Counter() {this(0);}

     public Counter(int initialCount) {count = initialCount;}

     public void increment() {++count;}

     public int getValue() {return count;}

     public String toString(){return Integer.toString(count);}

     private int count;
   }
}

