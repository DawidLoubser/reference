package za.co.solms.finance.ui.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@FacesValidator(value = "checkSumValidator")
public class CheckSumValidator implements Validator
{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException
	{
		String componentValue = value.toString();
		
		FacesMessage not10DigitsMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Not a 10 digit credit card number", "Enter 10 digit credit card number");
		
		if (componentValue.length() < 10) throw new ValidatorException(not10DigitsMsg);
		
		int sum = 0;
		for (int i=0; i<8; ++i)
		{
			try
			{
				sum += Integer.parseInt(componentValue.substring(i, i+1));
			}
			catch (NumberFormatException e)
			{
				throw new ValidatorException(not10DigitsMsg);
			}
		}
		try
		{
			int checkSum = Integer.parseInt(componentValue.substring(8, 10));
			
			if (sum != checkSum)
			{
				FacesMessage checkSumFailedMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Invalid credit card number", "Check sum failed");
				throw new ValidatorException(checkSumFailedMsg);
			}
		}
		catch (NumberFormatException e)
		{
			throw new ValidatorException(not10DigitsMsg);
		}
	}

}
