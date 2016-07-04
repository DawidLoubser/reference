 /**
 * 
 */
package za.co.solms.training.ejb.counter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * @author fritz@solms.co.za
 *
 */
@RequestScoped
@ManagedBean
public class CounterBinding implements Serializable
{
	public CounterBinding() {}
	
	public CounterServices getCounterServices()
	{
		return counterServices;
	}

	public void setCounterServices(CounterServices counterServices)
	{
		this.counterServices = counterServices;
	}



	public String getCounterName()
	{
		return counterName;
	}



	public void setCounterName(String counterName)
	{
		this.counterName = counterName;
	}



	public long getCounterValue()
	{
		return counterValue;
	}



	public void setCounterValue(long counterValue)
	{
		this.counterValue = counterValue;
	}


	public String getCounter()
	{
		try
		{
			counterValue = counterServices.getCount(counterName);
		}
		catch (CounterServices.NoSuchCounterException e)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No existing counter selected.", "You need to select an existing counter first.");
			FacesContext.getCurrentInstance().addMessage("nameField", msg);
		}
		return "";
	}


	public String addCounter()
	{
		try
		{
			try
			{
				counterServices.createCounter(counterName);
				counterValue = counterServices.getCount(counterName);
			}
			catch (CounterServices.DuplicateCounterException e)
			{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Counter with that name exists already", "You need to select an existing counter first.");
				FacesContext.getCurrentInstance().addMessage("nameField", msg);
			}
		
			counterValue = counterServices.getCount(counterName);
		}
		catch (CounterServices.NoSuchCounterException e)
		{
			assert false:"Should never occur - fix coding bug";
		}
		return "";
	}
	
	public String getCount()
	{
		try
		{
			if (counterName != null)
			{
				counterValue = counterServices.getCount(counterName);
			}	
			else
			{
				throw new CounterServices.NoSuchCounterException();
			}
		}
		catch (CounterServices.NoSuchCounterException e)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No existing counter selected.", "You need to select an existing counter first.");
			FacesContext.getCurrentInstance().addMessage("nameField", msg);
		}

		return "";
	}
	
	public String incrementCounter()
	{
		try
		{
			if (counterName != null)
			{
				counterServices.incrementCounter(counterName);
				counterValue = counterServices.getCount(counterName);
			}	
			else
			{
				throw new CounterServices.NoSuchCounterException();
			}
		}
		catch (CounterServices.NoSuchCounterException e)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No existing counter selected.", "You need to select an existing counter first.");
			FacesContext.getCurrentInstance().addMessage("nameField", msg);
		}

		return "";
	}

	@EJB
	private CounterServices counterServices;
	
	private String counterName = null;
	private long counterValue = 0;

	private static final long serialVersionUID = 1L;
}
