package za.co.solms.shopping;

import java.awt.event.*;
import javax.swing.*;

public class ShoppingCartClient extends JFrame
{
  public ShoppingCartClient()
  {
    setTitle("Shopping Cart Client");
    cartPanel = new ShoppingCartPanel();
    getContentPane().add(cartPanel);

    addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent event)
        {
          cartPanel.finalize();
          System.exit(0);
        }
      });
    pack();
  }

  public static void main(String[] args)
  {
    new ShoppingCartClient().show();
  }

  private ShoppingCartPanel cartPanel;
}
