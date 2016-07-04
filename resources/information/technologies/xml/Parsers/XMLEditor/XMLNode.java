package docs.components.XML.Parsers.XMLEditor;

\begin{verbatim}
import java.util.*;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.io.*;

public class XMLNode implements TreeNode
{
  public XMLNode(Node docNode, XMLNode parentNode)
  {
    this.docNode = docNode;
    this.parentNode = parentNode;
    
    buildSubTree();
  }
  
  private void buildSubTree()
  {
    childNodesMap.clear();
    
    NamedNodeMap attributesMap = docNode.getAttributes();
    if (attributesMap != null)
    {
      for (int i=0; i<attributesMap.getLength(); ++i)
      {
        Node attributeNode = attributesMap.item(i);
        XMLNode treeNode = new XMLNode(attributeNode, this);
        childNodesMap.put(treeNode, attributeNode);
      }   
    }      
    
    NodeList docChildNodes = docNode.getChildNodes();
    if (docChildNodes != null)
    {
      for (int i=0; i<docChildNodes.getLength(); ++i)
      {
        Node childNode = docChildNodes.item(i);
        int nodeType = childNode.getNodeType();

        String value = childNode.getNodeValue();
        if (value != null)
          value = value.trim();
        if ((nodeType != Node.TEXT_NODE) || (value.length() != 0))
        {       
          XMLNode treeNode = new XMLNode(childNode, this);
          childNodesMap.put(treeNode, childNode);
        }  
      }  
    }  
  }    
  
  public Enumeration children()
  {
    return new Vector(childNodesMap.keySet()).elements();
  }    
  
  public boolean getAllowsChildren()
  {
    return (docNode.getNodeType() == Node.ELEMENT_NODE);   
  }

  public TreeNode getChildAt(int childIndex)
  {
    if (childIndex >= childNodesMap.size());
    
    Iterator iter = childNodesMap.keySet().iterator();
    for (int i=0; i<childIndex; ++i)
      iter.next();
    return (XMLNode)iter.next();
  }  
  
  public int getChildCount()
  {
    return childNodesMap.size(); 
  }
  
  public int getIndex(TreeNode treeNode)
  {
    Iterator iter = childNodesMap.keySet().iterator();
    int index = 0;
    while (iter.hasNext())
    {
      XMLNode node = (XMLNode)iter.next();
      if (node == treeNode)
        return index;
      ++index;
    }    
        
    throw new IllegalArgumentException("No such child node.");
  }
  
  public TreeNode getParent()
  {
    return parentNode;
  }  
  
  public boolean isLeaf()
  {
    return (getChildCount() == 0);
  }  
  
  public void setDocNode(Node docNode)
  {
    this.docNode = docNode;
  }
  
  public Node getDocNode()
  {
    return docNode;
  }
  
  public void printTree(PrintStream out)
  {
    out.println("Node name = " + docNode.getNodeName());
    out.println("Node type = " + nodeType(docNode.getNodeType()));
    out.println("Node value = " + docNode.getNodeValue());
    out.println();
    Iterator iter = childNodesMap.keySet().iterator();
    while (iter.hasNext())
      ((XMLNode)iter.next()).printTree(out);
  }  
  
  public String toString()
  {
    int nodeType = docNode.getNodeType();
    
    switch (nodeType)
    {
      case Node.ELEMENT_NODE:
             return nodeType(nodeType) + ": " + docNode.getNodeName();
             
      case Node.ATTRIBUTE_NODE:
             return nodeType(nodeType) + ": " + docNode.getNodeName()
                                              + " = " + docNode.getNodeValue();
                                              
      case Node.TEXT_NODE:
             return nodeType(nodeType) + ": " + docNode.getNodeValue();
             
      default:
             return nodeType(nodeType) + ": " + docNode.getNodeName()
                                              + " = " + docNode.getNodeValue();
   } 
  } 
  
  public static String nodeType(int type)
  {
    switch (type)
    {
      case Node.ATTRIBUTE_NODE: return "attribute";
      case Node.CDATA_SECTION_NODE: return "CData section";
      case Node.COMMENT_NODE: return "comment";
      case Node.DOCUMENT_FRAGMENT_NODE: return "document fragment";
      case Node.DOCUMENT_NODE: return "document node";
      case Node.DOCUMENT_TYPE_NODE: return "document type";
      case Node.ELEMENT_NODE: return "element";
      case Node.ENTITY_NODE: return "entity";
      case Node.ENTITY_REFERENCE_NODE: return "entity reference";
      case Node.NOTATION_NODE: return "notation";
      case Node.PROCESSING_INSTRUCTION_NODE: return "processing instruction";
      case Node.TEXT_NODE: return "text";
      default: return "unknown type";
    }
  }    
    
  private Node docNode;
  private XMLNode parentNode;
  private Map childNodesMap = new HashMap();
}
\end{verbatim}
