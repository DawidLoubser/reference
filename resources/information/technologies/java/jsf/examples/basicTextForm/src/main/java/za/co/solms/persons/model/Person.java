package za.co.solms.persons.model;

public class Person
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

	public Title getTitle()
	{
		return title;
	}

	public void setTitle(Title title)
	{
		this.title = title;
	}
	
	public enum Title { Ms, Miss, Mrs, Mr, Mstr, Dr, unknown}; 

	private String firstName, surname;
	private Title title;
}
