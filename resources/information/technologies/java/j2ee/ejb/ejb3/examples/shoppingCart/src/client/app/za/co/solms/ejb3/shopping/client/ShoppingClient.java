package za.co.solms.ejb3.shopping.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.InitialContext;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import za.co.solms.ejb3.shopping.cart.ShoppingCart;
import za.co.solms.ejb3.shopping.products.Product;

/**
 * @author fritz@solms.co.za
 *
 */
public class ShoppingClient extends JPanel 
{

public ShoppingClient()
  {
    connectToBean();    
    constructGUI();
    addListeners();
  }
  
  private void connectToBean()
  {
      try
      {
        InitialContext naming = new InitialContext();
        cart = (ShoppingCart)naming.lookup("ShoppingCartBean/remote");
      }  
      catch (Exception e)
      {
          e.printStackTrace();
          System.exit(-1);
      }
  }
  
  private void addListeners()
  {
      addButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event)
                {
                    cart.add((Product)productSelector.getSelectedItem(), Integer.parseInt(quantityField.getText()));
                }
              });
      
      clearButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event) {cart.clear();}
              });
      
      viewButton.addActionListener(new ActionListener()
              {
                public void actionPerformed(ActionEvent event) 
                {
                    cartView.setText(cart.getCartContent().toString());
                }
              });
  }
  
  private void constructGUI()
  {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    JPanel selectorPanel = new JPanel();
    selectorPanel.add(productSelector);
    selectorPanel.add(new JLabel("  quantity:"));
    selectorPanel.add(quantityField);
    add(selectorPanel);
    
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(addButton);
    buttonsPanel.add(viewButton);
    buttonsPanel.add(clearButton);
    add(buttonsPanel);
    
    JScrollPane contentPane = new JScrollPane(cartView);
    contentPane.setBorder(BorderFactory.createTitledBorder("Cart content: "));
    add(contentPane);
    
    for (Product p:cart.getProductsList())
      productSelector.addItem(p);
  }
  
  public static void main(String[] args)
  {
      JFrame frame = new JFrame("Shopping Client");
      frame.add(new ShoppingClient());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true) ;
  }
  
  private JComboBox productSelector = new JComboBox();
  private JButton addButton = new JButton("Add to cart");
  private JButton viewButton = new JButton("View cart");
  private JButton clearButton = new JButton("Clear cart");
  private JTextField quantityField = new JTextField("0", 4);
  private JTextArea cartView = new JTextArea(10, 50); 

  private ShoppingCart cart;
  
	private static final long serialVersionUID = 1L;
}
