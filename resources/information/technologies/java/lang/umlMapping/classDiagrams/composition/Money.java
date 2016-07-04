public class Money implements Cloneable
{
	public Money(double amount, String currency)
	{
		setAmount(amount);
		setCurrency(currency);
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double newAmount)
	{
		this.amount = amount;
		// can do this because primitive
	}
	
	public String getCurrency()
	{
		return currency;
	}
	
	public vois setCurrency(String newCurrency)
	{
		this.currency = currency;
		// can do this because immutable
	}
	
	public Money clone()
	{
		Money copy = null;
		try
		{
			copy = (Money)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			/* never thrown, merely removing precondition */
		}
		return copy;
	}

	private double amount;
	private String currency;
}