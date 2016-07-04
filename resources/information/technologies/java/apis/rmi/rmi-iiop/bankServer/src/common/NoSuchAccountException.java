
package rmi.iiop.bank;

public class NoSuchAccountException extends RuntimeException
{
  public NoSuchAccountException ()           {}
  public NoSuchAccountException (String msg) {super(msg);}
}

