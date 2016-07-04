public interface Cheque
{
	public Date getPayDate();
	
	public Money getAmount();
	
	public interface Mutable extends Cheque
	{
		public void setPayDate(Date newPayDate);
		
		public void setAmount(Money newAmount);
	}
}