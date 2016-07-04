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

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;


public class XMLEditor extends JPanel
{
  public XMLEditor(Node docNode)
  {
    XMLNode xmlNode = new XMLNode(docNode, null);
    
    TreeModel treeModel = new DefaultTreeModel(xmlNode);
    JTree tree = new JTree(treeModel);
    
    class SelectionListener implements TreeSelectionListener
    {
      public SelectionListener(JTree tree)
      {
        this.tree = tree;
      }  
        
      public void valueChanged(TreeSelectionEvent e)
      {
        XMLNode node = (XMLNode)tree.getLastSelectedPathComponent();
        
        if (node == null) return;
        
        if (node.isLeaf())
          JOptionPane.showMessageDialog(XMLEditor.this, 
           "leaf node");
        else
          JOptionPane.showMessageDialog
            (XMLEditor.this, new JScrollPane(new XMLEditor(node.getDocNode())),
             "Sub-Tree", JOptionPane.PLAIN_MESSAGE);
      }
      
      private JTree tree;
    }
      
    tree.addTreeSelectionListener(new SelectionListener(tree));
    
    tree.setShowsRootHandles(true);
    
    add(tree);
  }
    
  public static void main(String[] args) throws Exception
  {
    TestXMLNode test = new TestXMLNode(); 
    
    String fileName = "courses.xml";
    if (args.length != 0)
      fileName = args[0];
        
    Node root = test.createDocument(new File(fileName));

    XMLEditor editor = new XMLEditor(root);
    
    JFrame frame = new JFrame("XML Editor");
    JScrollPane scrollPane = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    editor.setBorder(BorderFactory.createTitledBorder("XML Document Tree"));
    frame.getContentPane().add(scrollPane);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.show();
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
