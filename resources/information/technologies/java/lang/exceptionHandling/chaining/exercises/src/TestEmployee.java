package za.co.solmstcd.chaining;

public class TestEmployee {

	public static void main(String[] args) 
	{
		Employee emp = new Employee("123456789", 15);
		
		try
		{
			addLeaveDays(emp,-1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			removeLeaveDays(emp,10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			removeLeaveDays(emp,-10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			emp = null;
			removeLeaveDays(emp,99);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addLeaveDays(Employee emp, int d) 
		throws Exception
	{
		try
		{
			emp.increase(d);	
		}
		catch(IllegalArgumentException iae)
		{
			throw new IllegalArgumentException("Cannot add " +
					"leave days. Invalid value has " +
					"been entered",iae);
		}
		catch(Exception e)
		{
			throw new Exception("An unknown exception has occurred " +
					"in "+e.getStackTrace(), e);
		}
	}
	
	public static void removeLeaveDays(Employee emp, int d) 
		throws Exception
	{
		try
		{
			emp.decrease(d);	
		}
		catch(LeaveException le)
		{
			throw new LeaveException("Cannot process " +
					"leave application. Contact HR with " +
					"any queries.",le);
		}
		catch(IllegalArgumentException iae)
		{
			throw new IllegalArgumentException("Cannot remove " +
					"leave days. Invalid value has " +
					"been entered",iae);
		}
		catch(Exception e)
		{
			StackTraceElement[] element = e.getStackTrace();
		
			throw new Exception("An unknown exception has " +
					"occurred in "+element[0].getClassName() +
					" at line"+	element[0].getLineNumber(), e);
		}
	}
}
