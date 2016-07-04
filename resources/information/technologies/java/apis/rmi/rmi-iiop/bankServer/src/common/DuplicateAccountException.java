
package rmi.iiop.bank;

public class DuplicateAccountException extends RuntimeException
{
  public DuplicateAccountException ()           {}
  public DuplicateAccountException (String msg) {super(msg);}
}

