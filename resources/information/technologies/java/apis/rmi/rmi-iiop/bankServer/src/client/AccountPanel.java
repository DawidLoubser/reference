
package rmi.iiop.bank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccountPanel extends JPanel implements ActionListener
{
  public AccountPanel(JFrame frame)
  {
    setLayout(new GridLayout(3,1));

    this.frame = frame;

    add(accountNameLabel);
    accountNameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    JPanel amountPanel = new JPanel();
    amountPanel.add(new JLabel("Amount: "));
    amountPanel.add(amountField);

    JPanel creditDebitButtonPanel = new JPanel();
    creditDebitButtonPanel.add(creditButton);
    creditDebitButtonPanel.add(debitButton);
    creditButton.addActionListener(this);
    debitButton.addActionListener(this);

    JPanel creditDebitPanel = new JPanel();
    creditDebitPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    creditDebitPanel.add(amountPanel);
    creditDebitPanel.add(creditDebitButtonPanel);

    add(creditDebitPanel);

    JPanel balancePanel = new JPanel();
    balancePanel.add(new JLabel("Balance: "));
    balancePanel.add(balanceField);
    balanceField.setEditable(false);

    add(balancePanel);
  }

  public void setAccount(Account account)
  {
    this.account = account;

    if (account != null)
    {
      try
      {
        accountNameLabel.setText(account.getName());
        balanceField.setText(String.valueOf(account.getBalance()));
      }
      catch (java.rmi.RemoteException e)
      {
        JOptionPane.showMessageDialog(frame, e.getMessage(),
                                      "Remote Exception",
                                      JOptionPane.ERROR_MESSAGE);
      }
    }
    else
      {
        accountNameLabel.setText("");
        balanceField.setText("");
      }
  }

  public void actionPerformed(ActionEvent event)
  {
    if (account == null)
      JOptionPane.showMessageDialog(frame,"Not valid account.","Error",
                                    JOptionPane.ERROR_MESSAGE);

    try
      {
        if (event.getSource() == creditButton)
          account.credit(Double.parseDouble(amountField.getText()));
        else if (event.getSource() == debitButton)
          account.debit(Double.parseDouble(amountField.getText()));
        balanceField.setText(String.valueOf(account.getBalance()));
      }
    catch (java.rmi.RemoteException e)
    {
      JOptionPane.showMessageDialog(frame, e.getMessage(),
                                    "Remote Exception",
                                    JOptionPane.ERROR_MESSAGE);
    }
    catch (InsufficientFundsException e)
    {
      JOptionPane.showMessageDialog(frame, "Insufficient funds.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  private Account    account;
  private JFrame     frame;
  private JLabel     accountNameLabel = new JLabel("");
  private JTextField amountField      = new JTextField("",20);
  private JTextField balanceField     = new JTextField("",12);
  private JButton    creditButton     = new JButton("credit");
  private JButton    debitButton      = new JButton("debit");
}

