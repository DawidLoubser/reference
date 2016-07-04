<![CDATA[
package za.co.solmstraining.j2me.highlevelgui;

import za.co.solmstraining.j2me.finance.LoanCalculator;
import za.co.solmstraining.j2me.utils.Decimal;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class LoanCalculatorMIDlet extends MIDlet
{
  public void startApp() throws MIDletStateChangeException
  {
    if (display == null) initMIDlet();
  }

  public void initMIDlet()
  {
    display = Display.getDisplay(this);
    display.setCurrent(new LoanCalculatorForm());
  }

  public void pauseApp(){}

  public void destroyApp(boolean unconditional)
                  throws MIDletStateChangeException
  {
    exitMIDlet();
  }

  public void exitMIDlet() {notifyDestroyed();}

  private class LoanCalculatorForm extends Form
  {
    public LoanCalculatorForm()
    {
      super("Loan Calculator");

      setTicker(new Ticker("Our special home loan rate stands at "
         + interestRate.multiply(new Decimal(1,2)) + "%"));
      append(loanAmountField);
      append(loanPeriodField);
      append(monthlyInstallmentField);

      addCommand(calcCommand);
      addCommand(exitCommand);

      setCommandListener(new CommandListener()
        {
          public void commandAction(Command cmd, Displayable d)
          {
            if (cmd == exitCommand)
              LoanCalculatorMIDlet.this.exitMIDlet();

            if (cmd == calcCommand)
              calculate();
          }
        });

    }

    public void calculate()
    {
      Decimal loanAmount = new Decimal(loanAmountField.getString());
      int loanPeriod = Integer.parseInt(loanPeriodField.getString());
      Decimal monthlyInstallment // = new Decimal(0,0);
        = new Decimal(monthlyInstallmentField.getString());

      if (monthlyInstallment.equals(zero))
        {
          monthlyInstallment
            = loanCalculator.calcMonthlyInstallment
                (loanAmount, interestRate, loanPeriod);

          monthlyInstallmentField.setString(monthlyInstallment.toString());
        }
      else
        {
          loanAmount
            = loanCalculator.calcLoanAmount
                (monthlyInstallment, interestRate, loanPeriod);
          loanAmountField.setString(loanAmount.toString());
        }

    }

    private TextField loanAmountField = new TextField
      ("loan amount", "0", 20, TextField.ANY);
    private TextField loanPeriodField = new TextField
      ("loan period", "0", 9, TextField.ANY);
    private TextField monthlyInstallmentField = new TextField
      ("installments", "0", 20, TextField.ANY);
  }

  private static final Command calcCommand
    = new Command("Calculate", Command.SCREEN, 1);
  private static final Command exitCommand
    = new Command("Exit", Command.EXIT, 1);

  private Decimal interestRate = new Decimal(135,-1);
  private LoanCalculator loanCalculator = new LoanCalculator();

  private static final Decimal zero = new Decimal(0,0);

  private Display display;
}
]]>
