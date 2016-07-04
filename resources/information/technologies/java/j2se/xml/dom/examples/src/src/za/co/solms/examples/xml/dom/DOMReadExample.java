package za.co.solms.examples.xml.dom;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;

/** A simple application to read a DOM document from a URI and 
 * output it. */
public class DOMReadExample
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
      // Obtain a builder for DOM documents (which is namespace aware)
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();

      // Ask the builder to parse the document from the given URI
      Document doc = builder.parse( args[0] );
      
      // Use the services of org.w3c.dom.Document to extract information
      // or manipulate it
      // ...
      
      // In order to properly serialize (save or display) in a cross-platform
      // manner, we need to use the DOM Load/Save extensions (DOMLS)
      DOMImplementation dom = builder.getDOMImplementation();
      DOMImplementationLS domLS = (DOMImplementationLS)dom;
      
      // Serialise to a String, which we then write to standard output
      String xml = domLS.createLSSerializer().writeToString( doc );
      System.out.println( xml );
    }
    catch (Exception e)
    {
      System.err.println("Problem: " + e);
    }
  }
}