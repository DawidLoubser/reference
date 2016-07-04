package za.co.solms.finance.ui.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import za.co.solms.finance.model.CreditCard;
import za.co.solms.finance.model.CreditCardNumberConstraint;

@ManagedBean
@SessionScoped
public class CaptureCreditCardBinding implements Serializable
{
	public String captureCreditCard()
	{
		CreditCard creditCard = new CreditCard(creditCardNumber, nameOnCreditCard);
		//persist credit card
		return "creditCardSavedConfirmation";
	}

	public String getNameOnCreditCard()
	{
		return nameOnCreditCard;
	}

	public void setNameOnCreditCard(String nameOnCreditCard)
	{
		this.nameOnCreditCard = nameOnCreditCard;
	}

	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
	}

	@Size(min=2, max=30, message="Please enter name on card")
	private String nameOnCreditCard;
	
	@Pattern(regexp="[0-9]{10}", message="Please enter 10 digit credit card number")
	@CreditCardNumberConstraint
	private String creditCardNumber;
}
