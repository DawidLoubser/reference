package docs.components.XML.Parsers.XMLEditor;

\begin{verbatim}
import java.util.*;

import javax.swing.tree.*;
import javax.swing.event.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import java.io.*;

public class XMLTreeModel implements TreeModel
{
  public XMLTreeModel(Node root)
  {
    this.root = root;
  }
    
  public void addTreeModelListener(TreeModelListener listener) 
  {
    listeners.add(listener);
  }  

  public Object getChild(Object parent, int index) 
  {
    Node node = (Node)parent;
    
    NamedNodeMap attributes = node.getAttributes();
    if (attributes != null)
    {
      if (index < attributes.getLength())
        return attributes.item(index);
        
      index -= attributes.getLength();
    }    
      
    return node.getChildNodes().item(index);
  }
    
  public int getChildCount(Object parent) 
  {
    Node node = (Node)parent;
    
    int count = 0;
    if (node.getAttributes() != null)
      count += node.getAttributes().getLength();
    if (node.getChildNodes() != null)
      count += node.getChildNodes().getLength();
    
    return count;
  }
          
  public int getIndexOfChild(Object parent, Object child) 
  {
    Node node = (Node)parent;
    
    NamedNodeMap attributesMap = node.getAttributes();
    if (attributesMap != null)
      for (int i=0; i<attributesMap.getLength(); ++i)
        if (attributesMap.item(i) == child)
          return i;
    
    NodeList children = node.getChildNodes();
    if (children != null)
      for (int i=0; i<children.getLength(); ++i)
        if (children.item(i) == child)
          return attributesMap.getLength() + i;

    return -1;    
  }         
  
  public Object getRoot() 
  {
    return root;
  }         
  
  public boolean isLeaf(Object value) 
  {
    Node node = (Node)value;
    return node.hasChildNodes() == false;
  }        
  
  public void removeTreeModelListener(TreeModelListener listener) 
  {
    listeners.remove(listener);
  }  

  public void valueForPathChanged(TreePath path, Object newValue)  {}
    
  private Node root;
  private Set listeners = new HashSet();
}
\end{verbatim}
