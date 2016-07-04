package za.co.solms.finance;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class AccountClientPanel extends JPanel
{
  public AccountClientPanel() {init();}

  public void init()
  {
    connect();

    accountPanel = new AccountPanel ();
    accountsManagerPanel
      = new AccountsManagerPanel
          (accountsManager, accountPanel);

    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.add("AcountsManager", accountsManagerPanel);
    tabbedPane.add("Account", accountPanel);

    add(tabbedPane);
  }

  public double getDouble(JTextField textField)
  {
    try
    {
      return amountFormatter.parse
        (textField.getText(),
         new ParsePosition(0)).doubleValue();
    }
    catch (NumberFormatException e)
    {
      JOptionPane.showMessageDialog
        (this, e.getMessage(), "Invalid input.",
         JOptionPane.ERROR_MESSAGE);
      return 0;
    }
  }

  public void connect()
  {
    try
    {
      InitialContext jndiContext = new InitialContext();

      Object beanHomeRef
        = jndiContext.lookup("ejb/AccountsManager2_0MySQL");
      AccountsManagerHome home =
        (AccountsManagerHome)PortableRemoteObject.narrow
          (beanHomeRef, AccountsManagerHome.class);

      accountsManager = home.create();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(AccountClientPanel.this,
                    e.getMessage(),
                    "Could not connect to session bean.",
                    JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void destroy()
  {
    try
    {
      accountsManager.remove();
      System.out.println("Removed session bean.");
    }
    catch (javax.ejb.RemoveException e)
    {
      System.out.println("Could not remove session bean.");
    }
    catch (java.rmi.RemoteException e)
    {
      System.out.println("Could not remove session bean.");
    }
  }

  private AccountsManager accountsManager;
  private AccountsManagerPanel accountsManagerPanel;
  private AccountPanel accountPanel;

  private DecimalFormat amountFormatter
    = new DecimalFormat("###,###,###,##0.00");
}
