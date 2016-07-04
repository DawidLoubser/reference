
package rmi.iiop.bank;

import java.util.*;
import javax.rmi.PortableRemoteObject;

public class BankImpl extends PortableRemoteObject
                      implements Bank
{
  public BankImpl() throws java.rmi.RemoteException {}
  
  public Account getAccount(String name) throws java.rmi.RemoteException,
                                                NoSuchAccountException
    {return accounts.get(name);}                                                  
  
  public Collection<String> getAccounts() throws java.rmi.RemoteException
    {return accounts.keySet();}  
  
  public Account newAccount(String name) throws java.rmi.RemoteException,
                                                DuplicateAccountException
  {
    if (accounts.get(name) != null)                                                    
      throw new DuplicateAccountException();
      
    Account account = new AccountImpl(name);
    accounts.put(name,account);
    return account;
  }    
                                                
  private TreeMap<String,Account> accounts = new TreeMap<String,Account>();                                                
}

