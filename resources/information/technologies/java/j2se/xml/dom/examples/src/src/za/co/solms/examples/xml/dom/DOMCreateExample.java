package za.co.solms.examples.xml.dom;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.ls.*;

/** A simple application to illustrate DOM creation */
public class DOMCreateExample
{
  public static void main(String[] args)
  {
    try
    {
      // Obtain a builder for DOM documents (which is namespace aware)
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      
      // Get a handle to the actual DOM implementation
      DOMImplementation dom = builder.getDOMImplementation();
      
      // Use it to create an XML document (list of people)
      Document doc = 
        dom.createDocument("urn:solms:examples:xml","people",null);
      Node root = doc.getFirstChild();
      
      // Add a person
      Attr name = doc.createAttribute("name");
      name.setNodeValue("John");
      Element person = doc.createElement("person");
      person.setAttributeNode( name );
      root.appendChild( person );
      
      // Add another person
      name = doc.createAttribute("name");
      name.setNodeValue("Sarel");
      person = doc.createElement("person");
      person.setAttributeNode( name );
      root.appendChild( person );
      
      // In order to properly serialize (save or display) in a cross-platform
      // manner, we need to use the DOM Load/Save extensions (DOMLS)
      DOMImplementationLS domLS = (DOMImplementationLS)dom;
      
      // Output to standard output
      String xml = domLS.createLSSerializer().writeToString( doc );
      System.out.println( xml );
    }
    catch (ParserConfigurationException e)
    {
      System.err.println("DOM error: " + e);
    }
  }
}