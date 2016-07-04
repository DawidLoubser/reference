package za.co.solms.ejb3.shopping.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import za.co.solms.ejb3.shopping.Invoice;
import za.co.solms.ejb3.shopping.Shopping;
import za.co.solms.ejb3.shopping.products.Product;

/**
 * @author fritz@solms.co.za
 *
 */
public class ShoppingClient extends JTabbedPane 
{
  public ShoppingClient()
  {
    connectToBean();    
    constructGUI();
  }
  
  private void connectToBean()
  {
      try
      {
        InitialContext naming = new InitialContext();
        shopping = (Shopping)naming.lookup(Shopping.class.getName());
      }  
      catch (Exception e)
      {
          e.printStackTrace();
          System.exit(-1);
      }
  }
  
  private void constructGUI()
  {
      add("Shopping shopping", new CartPanel());
      add("Orders", new OrdersPanel());
  }
  
  private class CartPanel extends JPanel
  {
    public CartPanel()
    {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      JPanel selectorPanel = new JPanel();
      selectorPanel.add(productSelector);
      selectorPanel.add(new JLabel("  quantity:"));
      selectorPanel.add(quantityField);
      add(selectorPanel);
      
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.add(addButton);
      buttonsPanel.add(removeButton);
      buttonsPanel.add(viewButton);
      buttonsPanel.add(clearButton);
      buttonsPanel.add(buyButton);
      add(buttonsPanel);
      
      JScrollPane contentPane = new JScrollPane(cartView);
      add(contentPane);
      
      for (Product p:shopping.getProductsList())
        productSelector.addItem(p);
      
      addButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event)
                {
                    shopping.add((Product)productSelector.getSelectedItem(), Integer.parseInt(quantityField.getText()));
                }
              });
      
      removeButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event)
                {
                    shopping.add((Product)productSelector.getSelectedItem(), -Integer.parseInt(quantityField.getText()));
                }
              });
      clearButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event) {shopping.clear();}
              });
      buyButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event)
                {
                    System.out.println("Buing shopping ...");
                    int orderNo = shopping.buyCart();
                    JOptionPane.showMessageDialog(frame, "PurchaseOrder no:" + orderNo);
                }
              });
      viewButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event) 
                {
                    cartView.setText(shopping.getCartContent());
                }
              });
    }
    private JComboBox productSelector = new JComboBox();
    private JButton addButton = new JButton("Add to shopping");
    private JButton removeButton = new JButton("Remove from shopping");
    private JButton viewButton = new JButton("View shopping");
    private JButton clearButton = new JButton("Clear shopping");
    private JButton buyButton = new JButton("Buy shopping");
    private JTextField quantityField = new JTextField("0", 4);
    private JTextArea cartView = new JTextArea(10, 50); 
  }
  
  private class OrdersPanel extends JPanel
  {
      private OrdersPanel()
      {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel orderNoPanel = new JPanel();
        orderNoPanel.add(new JLabel(" Order number: "));
        orderNoPanel.add(orderNoField);
        add(orderNoPanel);
        
        JPanel costPanel = new JPanel();
        costPanel.add(new JLabel("Cost of order: "));
        costPanel.add(costField);
        add(costPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(costButton);
        add(buttonPanel);
        
        costButton.addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                      int orderNo = Integer.parseInt(orderNoField.getText());
                      double cost = shopping.getOrderCost(orderNo);
                      costField.setText(priceFormat.format(cost));
                  }
                });
      }
      private JTextField orderNoField = new JTextField("",10);
      private JTextField costField = new JTextField("",10);
      private JButton costButton = new JButton("Get cost");
  }
  
  public static void main(String[] args)
  {
      frame = new JFrame("Shopping Client");
      frame.add(new ShoppingClient());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true) ;
  }
  
  private DecimalFormat priceFormat = new DecimalFormat("R#####0.00");
  private Shopping shopping;
  private static JFrame frame;
}
