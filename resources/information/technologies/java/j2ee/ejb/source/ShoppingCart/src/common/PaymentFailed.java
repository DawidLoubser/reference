package za.co.solms.finance;

public class PaymentFailed extends Exception
{
  public PaymentFailed() {}

  public PaymentFailed(String msg) {super(msg);}
}

