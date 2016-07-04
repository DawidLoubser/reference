package za.co.solms.persons.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConsistentIdentityNumberValidator
	implements ConstraintValidator<ConsistentIdentityNumberConstraint, Person>
{

	@Override
	public void initialize(ConsistentIdentityNumberConstraint constraint)
	{
	}

	@Override
	public boolean isValid(Person person, ConstraintValidatorContext context)
	{
		System.out.println("############ Validating");
		Calendar cal = new GregorianCalendar();
		cal.setTime(person.getDateOfBirth());
		int lastTwoDigitsOfBirthYear = cal.get(Calendar.YEAR) / 100;
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		
		String idNo = person.getIdentityNumber();
		
		try
		{
			return (Integer.parseInt(idNo.substring(0,1)) == lastTwoDigitsOfBirthYear)
					&& (Integer.parseInt(idNo.substring(2,3)) == birthMonth)
					&& (Integer.parseInt(idNo.substring(5,6)) == birthDayOfMonth);
		}
		catch (NumberFormatException e)
		{
			System.out.println("############ NFE");
			return false;
		}	
	}

}
