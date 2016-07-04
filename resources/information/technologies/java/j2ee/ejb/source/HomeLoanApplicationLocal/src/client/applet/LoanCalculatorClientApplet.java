package za.co.solms.finance.calculators;

import javax.swing.*;

public class LoanCalculatorClientApplet extends JApplet
{
  public void init()
  {
    calculatorPanel = new LoanCalculatorPanel();
    getContentPane().add(calculatorPanel);
  }

  public void destroy()
  {
    calculatorPanel.destroy();
  }

  private LoanCalculatorPanel calculatorPanel;
}
