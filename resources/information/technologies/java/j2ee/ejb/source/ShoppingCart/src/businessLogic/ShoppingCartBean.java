package za.co.solms.shopping;

import za.co.solms.finance.PaymentFailed;
import za.co.solms.finance.CreditCard;
import za.co.solms.finance.Invoice;
import za.co.solms.utils.Formatters;

import java.util.*;

public class ShoppingCartBean 
  implements ShoppingCartServices,
             javax.ejb.SessionBean
{
  public void setSessionContext
    (javax.ejb.SessionContext sessionContext) {}
    
  public void ejbRemove() throws java.rmi.RemoteException 
  {
    System.out.println("Removed shopping cart bean for " 
      + clientName + ".");
  }
  
  public void ejbActivate()  throws java.rmi.RemoteException
  {
    System.out.println("Activated shopping cart bean for " 
      + clientName + ".");
  }

  public void ejbPassivate() throws java.rmi.RemoteException
  {
    System.out.println("Passivated shopping cart bean for " 
      + clientName + ".");
  }
  
  public void ejbCreate(String clientName)
  {
    this.clientName = clientName;
    System.out.println("Created shopping cart bean for " 
      + clientName + ".");
  }

  public void add(Item item, int quantity) 
    throws java.lang.IllegalArgumentException,
           java.rmi.RemoteException
  {
  System.out.println("adding " + quantity + " of " + item);
    if (content.get(item) == null)
      content.put(item, new Integer(quantity));
    else
      content.put(item,
        new Integer(((Integer)content.get(item)).intValue() 
          + quantity));
    System.out.println("Added " + item + " to " + clientName
      + "'s shopping cart.");
  }             

  public double getCost() 
    throws java.rmi.RemoteException
  {
    System.out.println(clientName + " requested cart cost."); 
    double cost = 0;
    try
    {
    if (content.size() != 0)
    {
      Iterator iter = content.keySet().iterator();
      while (iter.hasNext())
        {
          Item item = (Item)iter.next();
          int quantity = ((Integer)content.get(item)).intValue();
          cost += item.getPrice() * quantity;
        }
    }
    }
    catch (RuntimeException e) {}    
    return cost;  
  }  
  
  public String getContent() 
    throws java.rmi.RemoteException
  {
    System.out.println(clientName + " requested cart content.");
    
    java.text.DecimalFormat priceFormat
      = Formatters.getPriceFormat();
    
    StringBuffer result = new StringBuffer();
    try
    {
    Iterator iter = content.keySet().iterator();
    while (iter.hasNext())
    {
      Item item = (Item)iter.next();
      result.append(item.getName()).append("\t\t");
      int quantity = ((Integer)content.get(item)).intValue();
      result.append(quantity).append("\t");
      double price = item.getPrice();
      result.append(priceFormat.format(price)).append("\n");
    }  
    }
    catch (RuntimeException e) {}    
    return result.toString();
  }    
  
  public void clear() 
    throws java.rmi.RemoteException
  {
    content.clear();
    System.out.println(clientName + " cleared cart.");
  }  
  
  public Invoice purchase(CreditCard card)
    throws PaymentFailed,
           java.rmi.RemoteException
  {
    try
    {
    if (!clientName.equals(card.getCardHolderName()))
      throw new PaymentFailed("client not card holder");
    
    if (!card.isValid(new Date()))
      throw new PaymentFailed("Card invalid.");  
      
    // paymentBean.debit(card, getCost());    
    
    Invoice invoice = new Invoice(content, clientName);

    clear();
    
    System.out.println(clientName + " purchased with card: "
      + card);
    return invoice;
    }
    catch (RuntimeException e) {return null;}    
    
  }           

  private Map content = new TreeMap();
  private String clientName = "No name";
}           
