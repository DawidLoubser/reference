package za.co.solms.finance.loans;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class HomeLoanApplicationClient extends JApplet
{
  public HomeLoanApplicationClient() {init();}

  public void init()
  {
    try
    {
      connect();

      JPanel fieldsPanel = new JPanel();
      fieldsPanel.setLayout
        (new GridLayout(3,2));

      fieldsPanel.add(new JLabel("Loan amount: "));
      fieldsPanel.add(loanAmountField);
      fieldsPanel.add(new JLabel("Loan period in months: "));
      fieldsPanel.add(loanPeriodField);
      fieldsPanel.add(new JLabel("Monthly Salary: "));
      fieldsPanel.add(monthlySalaryField);

      JPanel mainPanel = new JPanel();
      mainPanel.add(fieldsPanel);

      getContentPane().add(mainPanel);
      getContentPane().add(applyForHomeLoanButton,
                           BorderLayout.SOUTH);

        applyForHomeLoanButton.addActionListener
            (new ActionListener()
        {
          public void actionPerformed(ActionEvent event)
          {
            double loanAmount   = getDouble(loanAmountField);
            int    loanPeriod   = getInt(loanPeriodField);
            double salary       = getDouble(monthlySalaryField);

            try
            {
              boolean granted = loanApplication.applyForHomeLoan
                (loanAmount, loanPeriod, salary);

              if (granted)
                JOptionPane.showMessageDialog
                  (HomeLoanApplicationClient.this,
                   "Congratulations!!! Home Loan granted.",
                   "Application successfull",
                   JOptionPane.INFORMATION_MESSAGE);
              else
                JOptionPane.showMessageDialog
                  (HomeLoanApplicationClient.this,
                   "Sorry, your home loan application was not successfull.",
                   "Application rejected",
                   JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e)
            {
              JOptionPane.showMessageDialog
                (HomeLoanApplicationClient.this,
                 e.toString(), e.getMessage(),
                 JOptionPane.ERROR_MESSAGE);
            }
          }
        });
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog
                   (HomeLoanApplicationClient.this,
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

      Object beanHomeRef
        = jndiContext.lookup("ejb/HomeLoanApplication");
      HomeLoanApplicationHome home =
        (HomeLoanApplicationHome)PortableRemoteObject.narrow
          (beanHomeRef, HomeLoanApplicationHome.class);

      loanApplication = home.create();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(HomeLoanApplicationClient.this,
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
      loanApplication.remove();
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
    super.destroy();
  }

  private HomeLoanApplication loanApplication;

  private JTextField loanAmountField = new JTextField(10);
  private JTextField monthlySalaryField = new JTextField(10);
  private JTextField loanPeriodField = new JTextField(3);

  private JButton applyForHomeLoanButton
    = new JButton("apply for Home Loan");

  private DecimalFormat amountFormatter
    = new DecimalFormat("###,###,###,##0.00");

  public static void main(String[] args)
  {
    loanApplicationClient = new HomeLoanApplicationClient();
    JFrame frame = new JFrame("Home-Loan Application");
    frame.getContentPane().add(loanApplicationClient);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.addWindowListener
      (new WindowAdapter()
        {
          public void windowClosing(WindowEvent e)
          {
            loanApplicationClient.destroy();
            System.exit(0);
          }
        }
      );

    frame.pack();
    frame.show();
  }
  private static HomeLoanApplicationClient loanApplicationClient;
}
