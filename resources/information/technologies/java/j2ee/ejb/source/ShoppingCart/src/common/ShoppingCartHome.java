package za.co.solms.shopping;

public interface ShoppingCartHome extends javax.ejb.EJBHome
{
  ShoppingCart create(String clientName)
    throws java.rmi.RemoteException,
           javax.ejb.CreateException;
}

