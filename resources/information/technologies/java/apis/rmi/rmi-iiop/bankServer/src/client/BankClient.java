package rmi.iiop.bank;

import java.awt.*;
import java.awt.event.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.swing.*;

public class BankClient extends JFrame implements ActionListener
{
  public BankClient() throws Exception
  {
    setTitle("Bank Client");
    addWindowListener(new WindowAdapter()
                    {
                        public void windowClosing(WindowEvent e)
                          {System.exit(0);}
                    });

    Context ic;
    Object objref;
    Bank bank;

    try {
      ic = new InitialContext();
    } catch (NamingException e) {
        System.out.println("failed to obtain context" + e);
        e.printStackTrace();
        return;
    }
        
    // STEP 1: Get the Object reference from the Name Service
    // using JNDI call.
    try 
    {
      objref = ic.lookup(serverName);
      System.out.println("Client: Obtained a ref. to Bank server.");
    } 
    catch (NamingException e) 
    {
        System.out.println("failed to lookup object reference for name " + serverName);
        e.printStackTrace();
        return;
    }

    // STEP 2: Narrow the object reference to the concrete type and
    // invoke the method.
    try {
      bank = (Bank) PortableRemoteObject.narrow(
        objref, Bank.class);
    } catch (ClassCastException e) {
        System.out.println("narrow failed");
        e.printStackTrace();
        return;
      } catch( Exception e ) {
            System.err.println( "Exception " + e + "Caught" );
            e.printStackTrace( );
            return;
        }
  
    accountPanel = new AccountPanel (this);
    bankPanel    = new BankPanel    (this, bank, accountPanel);

    tabbedPane = new JTabbedPane();
    tabbedPane.add("Bank",    bankPanel);
    tabbedPane.add("Account", accountPanel);

    getContentPane().add(tabbedPane, BorderLayout.CENTER);

    pack();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event)
    {tabbedPane.setSelectedIndex(1);}

  private Bank         bank;
  private BankPanel    bankPanel;
  private AccountPanel accountPanel;
  private JTabbedPane  tabbedPane;
  
  private static String serverName="BankService";

  public static void main(String[] args) throws Exception 
  {
       if (args.length < 1)
      {
        System.out.println("Usage: java rmi.iiop.bank.server.BankServer <serverName>");
        System.exit(0);
      }  
     serverName = args[0];

    new BankClient();
  }
}

