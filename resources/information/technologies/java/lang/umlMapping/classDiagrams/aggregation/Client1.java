public class Client
{
	...
	
	public double getExposure()
	{
		double exposure = 0;
		for (Account acc : accounts)
        {
			exposure += acc.getBalance();
        }
		return exposure;
	}

    public Set<Account> getAccounts()
    {
        return accounts;
    }

	private Set<Account> accounts = new HashSet<Account>();
}	