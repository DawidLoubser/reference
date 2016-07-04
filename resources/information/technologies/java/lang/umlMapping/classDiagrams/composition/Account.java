public class Account implements Cloneable
{
	public Account(Money openingBalance)
	{
		this.credit(openingBalance);
	}
	
	public void credit(Money amount) throws IncorrectCurrency
	{
		if (!amount.getCurrency().equals(balance.getCurrency()))
			throw new IncorrectCurrency();
		
		balance.setAmount(balance.getAmount() + amount.getAmount());
		
		propertyChangeSupport.firePropertyChange("balance",null,null);
	}
	
	public void debit(Money amount) throws IncorrectCurrency
	{
		if (!amount.getCurrency().equals(balance.getCurrency()))
			throw new IncorrectCurrency();
		
		balance.setAmount(balance.getAmount() - amount.getAmount());
		
		propertyChangeSupport.firePropertyChange("balance",null,null);
	}
	
	public Account clone()
	{
		Account copy = null;
		try
		{
			copy = (Account)super.clone();
			copy.balance = this.balance.clone(); 
			// Need to clone components
		}
		catch (CloneNotSupportedException e)
		{
			/* never thrown, merely removing precondition */
		}
		return copy;
	}
	
	public Money getBalance()
	{
		return balance.clone();
	}
	
	...
	
	private Money balance;
	
	public static class IncorrectCurrency extends Exception {}
}