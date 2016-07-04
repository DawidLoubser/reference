package za.co.solmstcd.chaining;

public class Employee 
{
	   public Employee(String empNo)
	   {
		   empNo = empNo;
	   }
	 
	   public Employee(String empNo, int initialBalance)
	   {
	     this(empNo);
	     balance = initialBalance;
	   }
	 
	   public void increase(int days) throws 
	   		NegativeDaysException
	   {
	     if (days < 0)
	       throw new NegativeDaysException("Cannot increase " +
	       		"leave with negative days.");
	     balance += days;
	   }
	 
	   public void decrease(int days)  throws 
	   		NegativeDaysException, AvailableDaysException
	   {
	     if (days < 0)
	    	 throw new NegativeDaysException("Cannot decrease days "
	    			 +	"with negative days.");
	 
	     if (balance - days > minimumBalance)
	       throw new AvailableDaysException("Insufficient leave " +
	       		"days available");	 
	     balance -= days;
	   }
	 
	   public double getBalance()
	   {
	     return balance;
	   }
	 
	   public String getEmpNumber()
	   {
	     return empNo;
	   }
	 
	   public String toString()
	   {
	     return empNo + ": " + balance;
	   }
	 
	   private String empNo;
	   private double balance=0, minimumBalance=0;
}
