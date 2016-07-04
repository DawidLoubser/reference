package za.co.solms.persons.model;

import java.io.Serializable;

public class Person implements Serializable
{
	public Person(String firstName, String surName, Title title)
	{
		setFirstName(firstName);
		setSurname(surName);
		setTitle(title);
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
	
	public Company getEmployer()
	{
		return employer;
	}
	
	public void setEmployer(Company employer)
	{
		this.employer = employer;
	}

	public Title getTitle()
	{
		return title;
	}

	public void setTitle(Title title)
	{
		this.title = title;
	}
	
	public String toString()
	{
		StringBuffer result = new StringBuffer();
		result.append(title).append(" ").append(firstName).append(" ").append(surname);
		if (employer != null)
			result.append (" is employed at ").append(employer.toString());
		return result.toString();
	}
	
	public enum Title { Ms, Miss, Mrs, Mr, Mstr, Dr, unknown}; 

	private String firstName, surname;
	private Title title;
	private Company employer;
}
