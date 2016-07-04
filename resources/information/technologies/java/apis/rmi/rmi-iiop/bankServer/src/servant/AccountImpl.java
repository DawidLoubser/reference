package rmi.iiop.bank;

import javax.rmi.PortableRemoteObject;

public class AccountImpl extends PortableRemoteObject
                        implements Account
{
  public AccountImpl(String name) throws java.rmi.RemoteException
  {this.name = name;}

  public String getName() throws java.rmi.RemoteException
    {return name;}

  public void credit (double amount) throws java.rmi.RemoteException
    {balance += amount;}

  public void debit (double amount) throws java.rmi.RemoteException,
                                          InsufficientFundsException
  {
    if (balance - amount < minimumBalance)
      throw new InsufficientFundsException();
    balance -= amount;
  }

  public double getBalance() throws java.rmi.RemoteException
    {return balance;}

  private String name;
  private double balance = 0;
  private double minimumBalance = 0;
}

