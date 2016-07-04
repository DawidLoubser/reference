package za.co.solms.finance;

public class AccountExistsException extends Exception
{
  public AccountExistsException() {}

  public AccountExistsException(String msg) {super(msg);}
}
