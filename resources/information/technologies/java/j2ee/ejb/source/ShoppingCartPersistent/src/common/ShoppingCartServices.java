package za.co.solms.shopping;

import za.co.solms.finance.PaymentFailed;
import za.co.solms.finance.CreditCard;
import za.co.solms.finance.Invoice;

public interface ShoppingCartServices
{
  public void add(Item item, int quantity)
    throws java.lang.IllegalArgumentException,
           java.rmi.RemoteException;

  public double getCost()
    throws java.rmi.RemoteException;

  public String getContent()
    throws java.rmi.RemoteException;

  public void clear()
    throws java.rmi.RemoteException;

  public Invoice purchase(CreditCard card)
    throws PaymentFailed,
           java.rmi.RemoteException;
}
