package za.co.solms.finance;

public class InsufficientFundsException extends Exception
{
  public InsufficientFundsException ()           {}
  public InsufficientFundsException (String msg) {super(msg);}
}
