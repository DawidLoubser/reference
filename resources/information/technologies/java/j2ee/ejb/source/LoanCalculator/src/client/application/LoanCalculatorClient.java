package za.co.solms.finance.calculators;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class LoanCalculatorClient extends JFrame
{
  public LoanCalculatorClient()
  {
    setTitle("Solms Training Loan Calculator");

    calculatorPanel = new LoanCalculatorPanel();

    getContentPane().add(calculatorPanel);

    addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent event)
        {
          calculatorPanel.destroy();
          System.exit(0);
        }
      });

    pack();
  }

  public static void main(String[] args)
  {
    new LoanCalculatorClient().show();
  }

  private LoanCalculatorPanel calculatorPanel;
}

