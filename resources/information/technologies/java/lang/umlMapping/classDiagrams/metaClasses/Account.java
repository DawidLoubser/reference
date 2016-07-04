public class Account
{
  /* static */ Account(String accNo)
  {
    // now in an instance service initializing the instance
    
    this.accNo = accNo;
    
    ++Account.numInstances;
  }
  
  // an instance service
  public TransactionConfirmation credit(double amount) {...}
  
  // a class service
  public static int getNumInstances() {return numInstances;}    
			
  // a class attribute		
  private static int numInstances = 0;
  
  // instance attributes
  private double balance;
  private String accNo;  						
}
