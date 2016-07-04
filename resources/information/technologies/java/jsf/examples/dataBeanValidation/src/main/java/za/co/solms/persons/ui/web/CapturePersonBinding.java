package za.co.solms.persons.ui.web;

import javax.faces.bean.ManagedBean;

import za.co.solms.persons.model.ConsistentIdentityNumberConstraint;
import za.co.solms.persons.model.Person;

@ManagedBean
public class CapturePersonBinding
{
	public String savePerson()
	{
		return "personSavedConfirmation";
	}
	
	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	private Person person = new Person("","", null, "");
}
