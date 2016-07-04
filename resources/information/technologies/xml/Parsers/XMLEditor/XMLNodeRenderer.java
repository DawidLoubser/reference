package docs.components.XML.Parsers.XMLEditor;

\begin{verbatim}
import org.w3c.dom.Node;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class XMLNodeRenderer implements TreeCellRenderer
{
   public Component getTreeCellRendererComponent
       (JTree tree, Object value, boolean selected, boolean expanded, 
        boolean leaf, int row, boolean hasFocus) 
   {
     Node node = (Node)value;
     String text = "";
     if (node.getNodeType() == Node.ATTRIBUTE_NODE)
       text += "attribute ";
     if (node.getNodeName() != null)
       text += node.getNodeName().trim() + ": ";
     if (node.getNodeValue() != null)  
        text += node.getNodeValue().trim();
        
      ImageIcon icon = new ImageIcon("litebulb.gif");
      switch (node.getNodeType())
      {
        case Node.ATTRIBUTE_NODE:
          icon = new ImageIcon("animbullet1b.gif"); break;
        case Node.TEXT_NODE:
          icon = new ImageIcon("speaker2.gif"); break;
      }    
        
     return new JLabel(text, icon, JLabel.LEFT);
   }      
}  
\end{verbatim}
