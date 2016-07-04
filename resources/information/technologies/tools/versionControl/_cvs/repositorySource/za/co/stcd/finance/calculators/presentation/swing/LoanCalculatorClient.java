package za.co.stcd.finance.calculators.presentation.swing;

import javax.swing.*;

public class LoanCalculatorClient extends JFrame
{
  public LoanCalculatorClient()
  {
    setTitle("STCD Loan Calculator");

    calculatorPanel = new LoanCalculatorPanel();

    getContentPane().add(calculatorPanel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pack();
  }

  public static void main(String[] args)
  {
    new LoanCalculatorClient().show();
  }

  private LoanCalculatorPanel calculatorPanel;
}

