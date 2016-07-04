package za.co.solms.finance;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.awt.*;
import javax.swing.*;

public class AccountClient extends JFrame
{
  public AccountClient()
  {
    super("Account Client");
    AccountClientPanel accountClient = new AccountClientPanel();
    getContentPane().add(accountClient);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    pack();
  }

  public static void main(String[] args)
  {
    new AccountClient().show();
  }
}
