package docs.components.XML.Parsers;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DOMElementCounter
{
  public DOMElementCounter() {}

  public Document createDocument(java.io.File file)
  throws SAXException, java.io.IOException,
         ParserConfigurationException
  {
    DocumentBuilderFactory docBuilderFactory
      = DocumentBuilderFactory.newInstance();
    docBuilderFactory.setValidating(true);

    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    return docBuilder.parse(file);
  }

  public void countElements(Node node, java.util.TreeMap elementCount)
  {
    if (node.getNodeType() == Node.ELEMENT_NODE)
    {
      String nodeName = node.getNodeName();

      Counter counter = (Counter)elementCount.get(nodeName);
      if (counter == null)
        elementCount.put(nodeName, new Counter(1));
      else
        counter.increment();
    }

    NodeList childNodes = node.getChildNodes();
    if (childNodes == null)
      return;

    for (int i=0; i<childNodes.getLength(); ++i)
      countElements(childNodes.item(i), elementCount);
  }

  public static void main(String[] args)
                                 throws java.io.IOException,
                                        SAXException,
                                        ParserConfigurationException
  {
    DOMElementCounter elementCounter = new DOMElementCounter();

    Node root
      = elementCounter.createDocument(new java.io.File(args[0]));

    java.util.TreeMap elementCount = new java.util.TreeMap();

    elementCounter.countElements(root, elementCount);

    System.out.println
      ("Reached end of document. Occurances per element = ");
    java.util.Iterator iter = elementCount.keySet().iterator();
    while (iter.hasNext())
    {
      String name = (String)iter.next();
      System.out.println(name + " " + elementCount.get(name));
    }
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

