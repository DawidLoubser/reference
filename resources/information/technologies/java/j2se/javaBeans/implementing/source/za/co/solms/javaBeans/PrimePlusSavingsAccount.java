package za.co.solms.javaBeans;

/**
 * A prime-plus savings account has all the functionality 
 * of an account except that it enables one to set the 
 * reference to the prime rate and specify the spread 
 * above prime which is earned by this account.
 * 
 * @author fritz:solms.co.za
 * 
 */
public interface PrimePlusSavingsAccount extends
    Account
{
  public InterestRate getPrimeRate();

  public void setPrimeRate(InterestRate newRate);

  public double getSpreadAbovePrime();

  public void setSpreadAbovePrime(double newSpread);
}