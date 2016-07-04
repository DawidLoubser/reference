package za.co.solms.finance.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator 
	implements ConstraintValidator<CreditCardNumberConstraint, String>
{
	@Override
	public void initialize(CreditCardNumberConstraint constraint) {}

	@Override
	public boolean isValid(String cardNumber, ConstraintValidatorContext context)
	{
		if (cardNumber.length() != 10)
			return false;
		
		boolean valid = true;
		int sum = 0;
		for (int i=0; i<8; ++i)
		{
			try
			{
				sum += Integer.parseInt(cardNumber.substring(i, i+1));
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}
		
		try
		{
			int checkSum = Integer.parseInt(cardNumber.substring(8, 10));
			
			if (sum != checkSum)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch (NumberFormatException e)
		{
			return false;
		}	
	}

}
