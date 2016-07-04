
package rmi.iiop.bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankPanel extends JPanel implements ActionListener
{
  public BankPanel(JFrame frame, Bank bank, AccountPanel accountPanel)
  {
    this.frame = frame;
    this.bank  = bank;
    this.accountPanel = accountPanel;

    JPanel namePanel = new JPanel();
    namePanel.add(new JLabel("Account Name: "));
    namePanel.add(accountNameField);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(selectAccountButton);
    buttonPanel.add(newAccountButton);

    selectAccountButton.addActionListener(this);
    newAccountButton.addActionListener(this);

    setLayout(new GridLayout(2,1));
    add(namePanel);
    add(buttonPanel);
  }

  public void actionPerformed(ActionEvent event)
  {
    try
    {
      if (event.getSource() == selectAccountButton)
        accountPanel.setAccount(bank.getAccount(accountNameField.getText()));
      else if (event.getSource() == newAccountButton)
        accountPanel.setAccount(bank.newAccount(accountNameField.getText()));
      ((ActionListener)frame).actionPerformed(event);
    }
    catch (java.rmi.RemoteException remoteException)
    {
      JOptionPane.showMessageDialog(frame, remoteException.getMessage(),
                                    "Remote Exception",
                                    JOptionPane.ERROR_MESSAGE);
    }
    catch (NoSuchAccountException noSuchAccount)
    {
      JOptionPane.showMessageDialog(frame, "No such account.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
    catch (DuplicateAccountException duplicateAccount)
    {
      JOptionPane.showMessageDialog(frame, "Duplicate account.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  private JTextField accountNameField    = new JTextField(20);
  private JButton    selectAccountButton = new JButton("Select");
  private JButton    newAccountButton    = new JButton("New Account");
  private JFrame     frame;
  private Bank       bank;
  private AccountPanel accountPanel;
}

