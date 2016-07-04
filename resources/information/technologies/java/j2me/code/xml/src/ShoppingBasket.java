<![CDATA[
package za.co.solmstraining.j2me.commerce;

import java.util.*;

public class ShoppingBasket
{
  public ShoppingBasket(ProductList products)
  {
    this.products = products;
  }

  public LineItem addLineItem(String productId, int quantity)
  {
    for (int i=0; i<lineItems.size(); ++i)
    {
      LineItem lineItem = (LineItem)lineItems.elementAt(i);
      if (lineItem.getProductId().equals(productId))
      {
        lineItem.setQuantity(quantity);
        return lineItem;
      }
    }
    LineItem lineItem = new LineItem(productId, quantity);
    lineItems.addElement(lineItem);
    return lineItem;
  }
  
  public void remove(LineItem lineItem)
  {
    lineItems.removeElement(lineItem);
  }

  public LineItem getLineItem(int lineNo)
  {
    return (LineItem)lineItems.elementAt(lineNo);
  }

  public void deleteLineItem(int lineNo)
  {
    lineItems.removeElementAt(lineNo);
  }

  public long getCost()
  {
    long totalCost = 0;
    java.util.Enumeration iter = lineItems.elements();
    while (iter.hasMoreElements())
    {
      LineItem lineItem = (LineItem)iter.nextElement();
      long price = products.getPrice(lineItem.getProductId());
      totalCost += lineItem.getQuantity()*price;
    }
    return totalCost;
  }

  public Vector getLineItems() {return lineItems;}

  private java.util.Vector lineItems = new java.util.Vector();
  private ProductList products;
}
]]>
