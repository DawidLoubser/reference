package za.co.solms.training.javaee.math;

import java.io.Serializable;
import java.util.logging.Logger;

import za.co.solms.training.javaee.math.Divider;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class DividerBinding implements Serializable
{
	public DividerBinding() {}
	
	public String divide()
	{
		try
		{
			result = divider.divide(numerator,denominator);
			return "dividerResult";
		}
		catch (Divider.DivideByZeroException e)
		{
			FacesMessage msg = new FacesMessage("Denominator may not be zero.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public String ok() {return "divider";}
	
	public double getNumerator()
	{
		return numerator;
	}

	public void setNumerator(double numerator)
	{
		this.numerator = numerator;
	}

	public double getDenominator()
	{
		return denominator;
	}

	public void setDenominator(double denominator)
	{
		this.denominator = denominator;
	}
	
	public double getResult()
	{
		return result;
	}

	public void setResult(double result)
	{
		this.result = result;
	}

	@EJB
	private Divider divider;

	private double numerator, denominator, result;
	
	private static final Logger logger = Logger.getLogger(DividerBinding.class.getName());
}
