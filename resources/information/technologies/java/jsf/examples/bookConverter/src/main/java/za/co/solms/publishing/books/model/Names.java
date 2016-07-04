package za.co.solms.publishing.books.model;

import sun.java2d.Surface;

public class Names
{
	public Names(String firstName, String middleNames, String lastName)
	{
		setFirstName(firstName);
		setMiddleNames(middleNames);
		setLastName(lastName);
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleNames()
	{
		return middleNames;
	}

	public void setMiddleNames(String middleNames)
	{
		this.middleNames = middleNames;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public boolean equals(Object o)
	{
		try
		{
			Names arg = (Names)o;
			return firstName.equals(arg.firstName) &&
				middleNames.equals(arg.middleNames)  &
				  lastName.equals(arg.lastName);
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return firstName.hashCode() + middleNames.hashCode() + lastName.hashCode();
	}
	
	public String toString()
	{
		return firstName + " " + middleNames + " " + lastName;
	}

	private String firstName, middleNames, lastName;
}
