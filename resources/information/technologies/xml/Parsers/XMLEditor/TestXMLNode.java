package docs.components.XML.Parsers.XMLEditor;

\begin{verbatim}
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.*;

public class TestXMLNode
{
  public static void main(String[] args) throws Exception
  {
    TestXMLNode test = new TestXMLNode();
 
    
    String fileName = "courses.xml";
    if (args.length != 0)
      fileName = args[0];
        
    Node root = test.createDocument(new File(fileName));
    
    XMLNode xmlNode = new XMLNode(root, null);
    
    PrintStream out = new PrintStream(new FileOutputStream("TextXMLNode.out"));
    
    xmlNode.printTree(System.out);
    xmlNode.printTree(out);
    
    out.close();
  }
  
  public static Document createDocument(java.io.File file) 
                            throws SAXException, java.io.IOException,
         ParserConfigurationException
  {
    DocumentBuilderFactory docBuilderFactory
      = DocumentBuilderFactory.newInstance();
    docBuilderFactory.setValidating(false);
    
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    return docBuilder.parse(file);
  }  
  

}
\end{verbatim}
