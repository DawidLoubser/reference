package za.co.solms.shopping;

import za.co.solms.finance.PaymentFailed;
import za.co.solms.finance.CreditCard;

import java.awt.event.*;
import javax.swing.*;

public class ShoppingCartPanel extends JPanel
{
  public ShoppingCartPanel() {init();}
  
  public void init()  
  {
    initItems();
    createGUI();
    connectToBean();
  }

  private void createGUI()
  {
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("Add Item", new JScrollPane(createAddItemPanel()));
    tabbedPane.add("Query Content", new JScrollPane(createQueryContentPane()));
    tabbedPane.add("Purchase", new JScrollPane(createPurchasePane()));
    
    add(tabbedPane);
  }

  private JPanel createAddItemPanel()
  {
    JPanel panel = new JPanel();

    panel.add(itemList);

    panel.add(new JLabel("  quantity:"));
    panel.add(quantityField);

    JButton addButton = new JButton("add");
    panel.add(addButton);

    addButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          try
          {
            Item item = (Item)itemList.getSelectedItem();
            int quantity
              = Integer.parseInt(quantityField.getText().trim());
            cart.add(item, quantity);
          }
          catch (java.rmi.RemoteException ex)
          {
            ex.printStackTrace();
            JOptionPane.showMessageDialog
            (ShoppingCartPanel.this, "RMI error during adding",
                "Communication Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
          }
        }
      });

    return panel;
  }
  
  private JPanel createQueryContentPane()
  {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JButton queryContentButton = new JButton("query content");
        
    panel.add(queryContentButton);
    panel.add(new JScrollPane(contentField));
    
    contentField.setEditable(false);  
    
    queryContentButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
    {
      try
      {
        contentField.setText(cart.getContent());
      }
      catch (java.rmi.RemoteException ex)
      {
        JOptionPane.showMessageDialog
         (ShoppingCartPanel.this, "RMI error during adding",
          "Communication Error", JOptionPane.ERROR_MESSAGE);
      }    
    }
  });
    return panel;
  }        

  private JPanel createPurchasePane()
  {
    JPanel panel = new JPanel();
    
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
  
    JButton queryCostButton = new JButton("total cost");
    JPanel costPanel = new JPanel();
    costPanel.add(queryCostButton);
    costPanel.add(costField);
    panel.add(costPanel);
    
    costField.setEditable(false);
    
    queryCostButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          try
          {
            costField.setText(String.valueOf(cart.getCost()));
          }
          catch (java.rmi.RemoteException ex)
          {
            JOptionPane.showMessageDialog
             (ShoppingCartPanel.this, "RMI error during adding",
              "Communication Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      });
    
    JButton purchaseButton = new JButton("purchase");
    JPanel purchasePanel = new JPanel();
    purchasePanel.setLayout
      (new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));
    purchasePanel.add(creditCardPanel);
    purchasePanel.add(purchaseButton);  
    purchasePanel.setBorder
      (BorderFactory.createRaisedBevelBorder());
    JPanel outerPanel = new JPanel();
    outerPanel.add(purchasePanel);
    outerPanel.setBorder
      (BorderFactory.createEmptyBorder(10,10,10,10));
    
    panel.add(purchasePanel);
    
    purchaseButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ev)
        {
          try
          {
            cart.purchase(creditCardPanel.getCard());
          }
          catch (java.rmi.RemoteException ex)
          {
            JOptionPane.showMessageDialog
             (ShoppingCartPanel.this, "RMI error during adding",
              "Communication Error", JOptionPane.ERROR_MESSAGE);
          }
          catch (PaymentFailed ex)
          {
            JOptionPane.showMessageDialog
             (ShoppingCartPanel.this, "Payment failed",
              ex.getMessage(), JOptionPane.ERROR_MESSAGE);
          }    
        }
      });  
    
    return panel;
  }    
  

  private void connectToBean()
  {
    javax.naming.Context context = null;
    
    try
    {
      context = new javax.naming.InitialContext();
    }
    catch (javax.naming.NamingException e)
    {
      System.out.println("Could not create initial naming context.");
      System.exit(0);
    } 
    
    String beanName = "ejb/ShoppingCart";

    try
    {    
      ShoppingCartHome cartHome 
        = (ShoppingCartHome)javax.rmi.PortableRemoteObject.narrow
            (context.lookup(beanName), ShoppingCartHome.class);
      cart = cartHome.create("Peter Smith");
    }
    catch (javax.naming.NamingException e1)
    {
      System.out.println
        ("Could not resolve bean " + beanName );
      System.exit(0);   
    }
    catch (Exception e2)
    {
      System.out.println
        ("Could connect to bean " + beanName);
      System.exit(0);   
    }
  }    
  
  private void initItems()
  {
    itemList.addItem(new Item("Programming in Java", 4850));
    itemList.addItem(new Item("Advanced Java", 4850));
    itemList.addItem(new Item("CORBA", 3880));
    itemList.addItem(new Item("OOAD via UML", 4850));
    itemList.addItem(new Item("Enterprise Java Beans", 2910));
    itemList.addItem(new Item("Web Services", 2910));
    itemList.addItem(new Item("XML", 2910));
    itemList.addItem(new Item("Software Requirements", 2910));
    itemList.addItem(new Item("Managing OO Projects", 2910));
  }

  public void finalize()
  {
    try
    {
      System.out.println("Removing cart.");
      cart.remove();
    }
    catch (Exception e) {}  
  }  

  private ShoppingCart cart;

  private JTextField quantityField
    = new JTextField("0",5);  
    
  private JComboBox itemList = new JComboBox();  
  private JTextField costField = new JTextField("", 10);
  private JTextArea contentField = new JTextArea("",8,30);
  private CreditCardPanel creditCardPanel = new CreditCardPanel();

  private class CreditCardPanel extends JPanel
  {
    public CreditCardPanel()  
    {
      setLayout(new java.awt.GridLayout(4,2));
      setBorder
        (BorderFactory.createTitledBorder("Credit Card Details"));
      add(new JLabel("Card type (Master/VISA)"));
      add(typeField);
      add(new JLabel("Card number"));
      add(numberField);
      add(new JLabel("Issued to"));
      add(nameField);
      add(new JLabel("Expiry date"));
      add(expiryDateField);
    }
  
    public CreditCard getCard()
    {
      try
      {
        return new CreditCard
          (typeField.getText().trim(),
             numberField.getText().trim(),
               nameField.getText().trim(),
                 dateFormat.parse(expiryDateField.getText()));
      }
      catch (java.text.ParseException ex)
      {
        JOptionPane.showMessageDialog
          (ShoppingCartPanel.this, "Invalid date.",
            "Input Error", JOptionPane.ERROR_MESSAGE);
      }
      return null;
    }
    private JTextField typeField = new JTextField("",20);
    private JTextField numberField = new JTextField("",20);
    private JTextField nameField = new JTextField("",20);
    private JTextField expiryDateField = new JTextField("",20);
  }
  
  private static final java.text.DateFormat dateFormat
    = new java.text.SimpleDateFormat("MM/yy");
}

