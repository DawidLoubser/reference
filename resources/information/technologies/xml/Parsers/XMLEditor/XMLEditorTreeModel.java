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

import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;


public class XMLEditorTreeModel extends JPanel
{
  public XMLEditorTreeModel(Node docNode)
  {
    TreeModel treeModel = new XMLTreeModel(docNode);
    JTree tree = new JTree(treeModel);
    tree.setCellRenderer(new XMLNodeRenderer());
    
    class SelectionListener implements TreeSelectionListener
    {
      public SelectionListener(JTree tree)
      {
        this.tree = tree;
      }  
        
      public void valueChanged(TreeSelectionEvent e)
      {
        Node node = (Node)tree.getLastSelectedPathComponent();
        
        if (node == null) return;
        
        if (node.hasChildNodes())
          JOptionPane.showMessageDialog
            (XMLEditorTreeModel.this, new JScrollPane(new XMLEditorTreeModel(node)),
             "Sub-Tree", JOptionPane.PLAIN_MESSAGE);
        else
          if (node.getNodeType() == Node.TEXT_NODE)
          {
            String inputValue 
              = JOptionPane.showInputDialog(XMLEditorTreeModel.this,
                                            "Enter new text value: ", 
                                            node.getNodeValue());               
            node.setNodeValue(inputValue);
          }  
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
        
    root = test.createDocument(new File(fileName));

    XMLEditorTreeModel editor = new XMLEditorTreeModel(root);
    
    frame = new JFrame("XML Editor using TreeModel");
    JScrollPane scrollPane = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    editor.setBorder(BorderFactory.createTitledBorder("XML Document Tree"));
    frame.getContentPane().add(scrollPane);
    frame.pack();
    
    frame.addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent event)
        {
          int option = JOptionPane.showConfirmDialog(frame, 
                "save document", "Closing XML Editor",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                
          System.exit(0);
        }
      });      

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
  
  private static JFrame frame;  
  private static Node root;
}        
\end{verbatim}
