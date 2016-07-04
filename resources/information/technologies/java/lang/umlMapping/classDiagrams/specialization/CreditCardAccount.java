public class CreditCardAccount extends Account
{
  public double getVoyagerMiles() {return voyagerMiles;}
  
  public void debit(double amount)
  {
     // business process for debiting credit card accounts here
  }  

  private double voyagerMiles;
}