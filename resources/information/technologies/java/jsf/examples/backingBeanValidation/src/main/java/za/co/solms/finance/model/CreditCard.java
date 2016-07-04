package za.co.solms.finance.model;

public class CreditCard
{
	public CreditCard(){}
	
	public CreditCard(String number, String name)
	{
		setNumber(number);
		setName(name);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public boolean equals(Object o)
	{
		try
		{
			CreditCard arg = (CreditCard)o;
			
			return this.name.equals(arg.name) && this.number.equals(arg.number);			
		}
		catch (ClassCastException e) {return false;}
	}
	
	public int hashCode()
	{
		return name.hashCode() + number.hashCode();
	}
	
	public String toString()
	{
		return "Credit card: " + number + " (" + name + ")";
	}

	private String name, number;
}
