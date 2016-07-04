package za.co.solms.persons.model;

import java.io.Serializable;

public class Company implements Serializable
{
	public Company(String name, String telephoneNo)
	{
		setName(name);
		setTelephoneNo(telephoneNo);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTelephoneNo()
	{
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo)
	{
		this.telephoneNo = telephoneNo;
	}
	
	public String toString()
	{
		return name + "(Tel:" + telephoneNo + ")";
	}
	
	private String name;
	private String telephoneNo;
}
