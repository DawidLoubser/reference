package za.co.solms.finance;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;

public class AccountsManagerBean implements SessionBean
{
  private AccountHome getHome()
  {
    try
    {
       InitialContext jndiContext = new InitialContext();
       Object ref = jndiContext.lookup
         ("java:comp/env/ejb/AccountBMP");
       return (AccountHome)PortableRemoteObject.narrow
                    (ref,AccountHome.class);
    }
    catch (Exception e)
    {
      throw new EJBException(e);
    }
  }

  public Account getAccount(String id)
           throws RemoteException, FinderException
  {
    AccountHome home = getHome();

    return home.findByPrimaryKey(id);
  }

  public void openAccount(String id, String name)
           throws RemoteException, AccountExistsException
  {
    AccountHome home = getHome();
    try
    {
      Account existingAccount = home.findByPrimaryKey(id);
      // Force loading of data here
      String dummy = existingAccount.getId();
      // If we get here, an Account with specified id exists already.
      throw new AccountExistsException();
    }
    catch (Exception e)
    {
       try
       {
         Account account = home.create(id);
         account.setName(name);
         System.out.println
          ("Added Account with id=" + id
             + ", name=" + name);
         return;
       }
       catch (Exception e2)
       {
         throw new EJBException (e2);
       }
     }
  }

  public void closeAccount(String id) throws RemoteException
  {
    AccountHome home = getHome();
    try
    {
      Account account = home.findByPrimaryKey(id);
      System.out.println
        ("Deleting account with id=" + account.getId());
      try
      {
        account.remove();
      }
      catch (RemoveException e)
      {
        System.out.println (e.toString());
      }
      return;
    }
    catch (Exception e)
    {
       System.out.println
         ("Caught Exception in closeAccount(id)");
       e.printStackTrace();
    }
  }

  public void deleteAll() throws RemoteException
  {
    AccountHome home = getHome();
    try
    {
       Collection accounts = home.findAll();
       System.out.println
         ("Found " + accounts.size() + " accounts");
       Iterator iter = accounts.iterator();
       {
        Account account = (Account)iter.next();
        System.out.println
        ("Deleting account with id=" + account.getId());
        try
        {
          account.remove();
        }
        catch (RemoveException e)
        {
          System.out.println (e.toString());
        }
      }
      return;
    }
    catch (Exception e)
    {
       System.out.println
         ("Caught Exception in deleteAll()");
       e.printStackTrace();
    }
  }

  public Account[] findAll()
           throws RemoteException, FinderException
  {
    AccountHome home = getHome();
    Collection all = home.findAll();
    return (Account[]) all.toArray();
  }


  public Account[] lookupByName(String name)
           throws RemoteException, FinderException
  {
    AccountHome home = getHome();
    Collection all = home.findAll();


    Collection accounts = new LinkedList();

    Iterator iter = all.iterator();
    while (iter.hasNext())
    {

    }

    iter = accounts.iterator();
    Account[] result = new Account[accounts.size()];
    for (int i = 0; i < result.length; ++i)
      result[i] = (Account)iter.next();
    return result;
  }

   public void ejbCreate() {}
   public void ejbRemove() {}
   public void ejbActivate() {}
   public void ejbPassivate() {}
   public void setSessionContext(SessionContext sc) {}
}
