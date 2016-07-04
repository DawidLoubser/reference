
package rmi.iiop.bank;

public class InsufficientFundsException extends RuntimeException
{
  public InsufficientFundsException ()           {}
  public InsufficientFundsException (String msg) {super(msg);}
}

