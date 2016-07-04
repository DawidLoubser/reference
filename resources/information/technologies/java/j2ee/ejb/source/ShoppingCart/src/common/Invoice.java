package za.co.solms.finance;

import za.co.solms.shopping.Item;
import za.co.solms.utils.Formatters;

import java.util.*;

public class Invoice implements java.io.Serializable
{
  public Invoice(Map items, String clientName)
  {
    this.items = items;
    this.clientName = clientName;
  }  

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    
    result.append("\t\t\t\t\t").append(clientName).append("\n");
    result.append("\t\t\tINVOICE\n\n");
    
    java.text.DecimalFormat priceFormat 
      = Formatters.getPriceFormat();
    
    double total = 0;
    Iterator iter = items.keySet().iterator();
    while (iter.hasNext())
    {
      Item item = (Item)iter.next();
      result.append(item.getName()).append("\t\t");
      int quantity = ((Integer)items.get(item)).intValue();
      result.append(quantity).append("\t");
      double price = item.getPrice();
      result.append
        (priceFormat.format(price)).append("\n");
      total += price;
    }
    result.append("\n").append("TOTAL:\t\t\t\t\t");
    result.append(priceFormat.format(total)).append("\n");
    
    return result.toString();
  }        
    
  private Map items;
  private String clientName;
}      
