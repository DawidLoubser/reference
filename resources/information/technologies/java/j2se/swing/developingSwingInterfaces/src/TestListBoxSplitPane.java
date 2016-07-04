import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.Vector;

public class TestListBoxSplitPane extends JFrame
{
  public TestListBoxSplitPane() 
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent event)
        {System.exit(0);}
    });    

    setTitle("List Boxes and Split Panes");
    
    getContentPane().setLayout(new BorderLayout());
    
    getContentPane().add(new ShoppingListSplitPane(), BorderLayout.CENTER);

    pack();
    setVisible(true);
  }

  public static void main(String[] args) {new TestListBoxSplitPane();}
}

class ShoppingListSplitPane extends JSplitPane implements ListSelectionListener 
{
  public ShoppingListSplitPane()
  {
    super(HORIZONTAL_SPLIT,false);
    setOneTouchExpandable(true);

    Vector allGroceriesList = getGroceriesList();
    
    allGroceries = new JList(allGroceriesList); 
    allGroceries.addListSelectionListener(this);

    requiredGroceriesData = new DefaultListModel();
    requiredGroceries = new JList(requiredGroceriesData);   

    allGroceries.setSelectionMode
                      (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    
    JScrollPane leftScrollPane = new JScrollPane(allGroceries);
    JScrollPane rightScrollPane = new JScrollPane(requiredGroceries);

    leftScrollPane.setBorder
      (BorderFactory.createTitledBorder("Select from here"));
    rightScrollPane.setBorder
      (BorderFactory.createTitledBorder("Required items"));
    
    setLeftComponent  (leftScrollPane);
    setRightComponent (rightScrollPane);

    Dimension minimumSize = new Dimension(100, 30);
    leftScrollPane.setMinimumSize(minimumSize);
    rightScrollPane.setMinimumSize(minimumSize);

    setDividerLocation(200);
    setDividerSize(5);

    setLeftComponent(leftScrollPane);
    setRightComponent(rightScrollPane);

    setPreferredSize(new Dimension(400, 300));
  }

  public void valueChanged(ListSelectionEvent event)
  {
    Object[] selected = allGroceries.getSelectedValues();

    requiredGroceriesData.clear();
    for (int i=0; i<selected.length; ++i)
      requiredGroceriesData.addElement(selected[i]); 
  }

  private Vector getGroceriesList()
  {
    Vector allGroceries = new Vector();
    try
    {
      FileInputStream fin = new FileInputStream("Groceries.dat");
      BufferedReader in = new BufferedReader(new InputStreamReader(fin));

      String grocery = in.readLine();
      while (grocery != null)
      {
        allGroceries.addElement(grocery);
        grocery = in.readLine();
      }
    }
    catch (Exception e) {}

    return allGroceries;
  }

  private JList allGroceries, requiredGroceries;
  private DefaultListModel requiredGroceriesData;
}
