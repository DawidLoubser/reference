package za.co.solms.persons.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@ConsistentIdentityNumberConstraint
public class Person
{
	public Person() {}
	
	public Person(String firstName, String surname, Date dateOfBirth, String identityNumber)
	{
		setFirstName(firstName);
		setSurname(surname);
		setDateOfBirth(dateOfBirth);
		setIdentityNumber(identityNumber);
	}

	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getSurname()
	{
		return surname;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public String getIdentityNumber()
	{
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber)
	{
		this.identityNumber = identityNumber;
	}
	
	public boolean equals(Object o)
	{
		try
		{
			Person arg = (Person)o;
			return firstName.equals(arg.firstName) &&
				surname.equals(arg.surname) &&
				dateOfBirth.equals(arg.dateOfBirth) &&
				identityNumber.equals(arg.identityNumber);
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return identityNumber.hashCode();
	}
	
	//@Pattern(regexp="[A-Z][a-z]{*}")
	private String firstName;
	//@Pattern(regexp="[A-Z][a-z]{*}")
	private String surname;
	@Past
	private Date dateOfBirth;
	@Pattern(regexp="[0-9]{13}")
	private String identityNumber;
}
