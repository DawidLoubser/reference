package za.co.solms.finance.calculators;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class LoanCalculatorPanel extends JPanel
{
  public LoanCalculatorPanel() {init();}

  public void init()
  {
    try
    {
      connect();

      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout
        (new GridLayout(4,3));

      fieldsPanel.add(new JLabel("Loan amount: "));
      fieldsPanel.add(loanAmountField);
      fieldsPanel.add(calcLoanAmountButton);
      fieldsPanel.add(new JLabel("Monthly installment: "));
      fieldsPanel.add(installmentField);
      fieldsPanel.add(calcInstallmentButton);
      fieldsPanel.add(new JLabel("Loan period in months: "));
      fieldsPanel.add(loanPeriodField);
      fieldsPanel.add(calcLoanPeriodButton);
      fieldsPanel.add(new JLabel("Interest Rate (%): "));
      fieldsPanel.add(interestRateField);

      JPanel mainPanel = new JPanel();
      mainPanel.add(fieldsPanel);

      add(mainPanel);

      calcLoanAmountButton.addActionListener
            (new ActionListener()
        {
          public void actionPerformed(ActionEvent event)
          {
            double installment  = getDouble(installmentField);
            int loanPeriod   = getInt(loanPeriodField);
            double interestRate = getDouble(interestRateField)/100;

            try
            {
              double loanAmount = loanCalculator.calcLoanAmount
                (installment, interestRate, loanPeriod);

              loanAmountField.setText
                (amountFormatter.format(loanAmount));
            }
            catch (Exception e)
            {
              JOptionPane.showMessageDialog
                (LoanCalculatorPanel.this,
                 e.toString(), e.getMessage(),
                 JOptionPane.ERROR_MESSAGE);
              e.printStackTrace(System.out);
            }
          }
        });

        calcInstallmentButton.addActionListener
            (new ActionListener()
        {
          public void actionPerformed(ActionEvent event)
          {
            double loanAmount   = getDouble(loanAmountField);
            int    loanPeriod   = getInt(loanPeriodField);
            double interestRate = getDouble(interestRateField)/100;

            try
            {
              double installment = loanCalculator.calcMonthlyInstallment
                (loanAmount, interestRate, loanPeriod);

              installmentField.setText
                (amountFormatter.format(installment));
            }
            catch (Exception e)
            {
              JOptionPane.showMessageDialog
                (LoanCalculatorPanel.this,
                 e.toString(), e.getMessage(),
                 JOptionPane.ERROR_MESSAGE);
              e.printStackTrace(System.out);
            }
          }
        });

      calcLoanPeriodButton.addActionListener
            (new ActionListener()
        {
          public void actionPerformed(ActionEvent event)
          {
            double loanAmount   = getDouble(loanAmountField);
            double installment  = getDouble(installmentField);
            double interestRate = getDouble(interestRateField)/100;

            try
            {
              int loanPeriod = loanCalculator.calcLoanPeriodInMonths
                (loanAmount, installment, interestRate);

              loanPeriodField.setText(Integer.toString(loanPeriod));
            }
            catch (Exception e)
            {
              JOptionPane.showMessageDialog
                (LoanCalculatorPanel.this,
                 e.toString(), e.getMessage(),
                 JOptionPane.ERROR_MESSAGE);
              e.printStackTrace(System.out);
            }
          }
        });
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(LoanCalculatorPanel.this,
                    e.getMessage(),
                    "Could not connect to session bean.",
                    JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
      System.exit(0);
    }
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

  public int getInt(JTextField textField)
  {
    try
    {
      return Integer.parseInt(textField.getText());
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

      System.out.println("Now looking up session bean " + jndiName + " ...");

      Object beanHomeRef
        = jndiContext.lookup(jndiName);

      System.out.println("got it");

      LoanCalculatorHome home =
        (LoanCalculatorHome)PortableRemoteObject.narrow
          (beanHomeRef, LoanCalculatorHome.class);

      loanCalculator = home.create();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(LoanCalculatorPanel.this,
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
      loanCalculator.remove();
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

  private LoanCalculator loanCalculator;

  private JTextField loanAmountField = new JTextField(10);
  private JTextField installmentField = new JTextField(10);
  private JTextField interestRateField = new JTextField(10);
  private JTextField loanPeriodField = new JTextField(3);

  private JButton calcLoanAmountButton
    = new JButton("calculate");
  private JButton calcInstallmentButton
    = new JButton("calculate");
  private JButton calcLoanPeriodButton
    = new JButton("calculate");

  private DecimalFormat amountFormatter
    = new DecimalFormat("###,###,###,##0.00");

  private static final String jndiName = "ejb/LoanCalculator";
}
