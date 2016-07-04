package za.co.solms.finance;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountsManagerPanel extends JPanel
{
  public AccountsManagerPanel(AccountsManager accountsMngr,
                              AccountPanel accountPnl)
  {
    this.accountsManager  = accountsMngr;
    this.accountPanel = accountPnl;

    JPanel idPanel = new JPanel();
    idPanel.add(new JLabel("Account Id: "));
    idPanel.add(accountIdField);

    JPanel namePanel = new JPanel();
    namePanel.add(new JLabel("Account Name: "));
    namePanel.add(accountNameField);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(selectAccountButton);
    buttonPanel.add(openAccountButton);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(idPanel);
    add(namePanel);
    add(buttonPanel);

    selectAccountButton.addActionListener(
      new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            try
            {
              Account account
                = accountsManager.getAccount
                    (accountIdField.getText().trim());
              accountNameField.setText(account.getName());
              accountPanel.setAccount(account);
            }
            catch (java.rmi.RemoteException ex)
            {
              JOptionPane.showMessageDialog
                (AccountsManagerPanel.this,
                 ex.getMessage(),
                 "Remote Exception",
                 JOptionPane.ERROR_MESSAGE);
            }
            catch (javax.ejb.FinderException ex)
            {
              JOptionPane.showMessageDialog
                (AccountsManagerPanel.this,
                 ex.getMessage(),
                 "Could not find account.",
                 JOptionPane.ERROR_MESSAGE);
              ex.printStackTrace();
            }
          }
        });

    openAccountButton.addActionListener(
      new ActionListener()
        {
          public void actionPerformed(ActionEvent ev)
          {
            try
            {
              String id = accountIdField.getText().trim();
              String name = accountNameField.getText().trim();
              accountsManager.openAccount(id, name);
              accountPanel.setAccount
                (accountsManager.getAccount(id));
            }
            catch (java.rmi.RemoteException ex)
            {
              JOptionPane.showMessageDialog
                (AccountsManagerPanel.this,
                 ex.getMessage(),
                 "Remote Exception",
                 JOptionPane.ERROR_MESSAGE);
            }
            catch (AccountExistsException ex)
            {
              JOptionPane.showMessageDialog
                (AccountsManagerPanel.this,
                 ex.getMessage(),
                 "Account exists already.",
                 JOptionPane.ERROR_MESSAGE);
            }
            catch (javax.ejb.FinderException ex)
            {
              JOptionPane.showMessageDialog
                (AccountsManagerPanel.this,
                 ex.getMessage(),
                 "Could not find account.",
                 JOptionPane.ERROR_MESSAGE);
            }
          }
        });
  }

  private JTextField accountIdField    = new JTextField(20);
  private JTextField accountNameField    = new JTextField(20);
  private JButton    selectAccountButton = new JButton("Select");
  private JButton    openAccountButton    = new JButton("Open Account");
  private AccountsManager accountsManager;
  private AccountPanel accountPanel;
}
