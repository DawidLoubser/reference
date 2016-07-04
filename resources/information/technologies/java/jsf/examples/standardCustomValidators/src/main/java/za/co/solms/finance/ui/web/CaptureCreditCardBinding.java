package za.co.solms.finance.ui.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.solms.finance.model.CreditCard;

@ManagedBean
@SessionScoped
public class CaptureCreditCardBinding implements Serializable
{
	public String captureCreditCard()
	{
		return "creditCardSavedConfirmation";
	}
	
	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}

	private CreditCard creditCard = new CreditCard("","");
}
