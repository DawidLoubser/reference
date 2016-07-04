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
	
	public void addAccount(Account acc)
	{
		accounts.add(acc);
		acc.addPropertyListener(this, "balance");
	}

    public void removeAccount(Account acc)
    {
        for (Account aggrAcc : accounts)
        {
            if (aggrAcc == acc)
            {
                aggrAcc.removePropertyListener(this);
            }
        }
        accounts.remove( acc );
    }

    /** Called whenever an account's balance changes */
	public void propertyChanged(PropertyChangeEvent event)
	{
        double newExposure = getExposure();
		if (prevExposure != newExposure)
        {
		  propertyChangeSupport.firePorpertyChange
		     ("exposure", prevExposure, newExposure);
        }
        prevExposure = newExposure;
	}


	private Set<Account> accounts = new HashSet<Account>();
	private PropertyChangeSupport propertyChangeSupport;

    // Need to add a 'memory' to detect changes as accounts change
    private double prevExposure;
}
