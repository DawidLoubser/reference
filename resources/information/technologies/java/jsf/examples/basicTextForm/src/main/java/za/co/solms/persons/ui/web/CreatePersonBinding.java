package za.co.solms.persons.ui.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.solms.persons.model.Person;
import za.co.solms.persons.model.Person.Title;

@ManagedBean
@SessionScoped
public class CreatePersonBinding
{
	public CreatePersonBinding() {}
	
	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}
	
	public Title[] getTitles() {return Person.Title.values();}
	
	public String savePerson() 
	{
		//try
		//{
		//  servicesLayer.savePerson(person);
		    return "personSavedConfirmation";
		//}
		//catch (SomeException e)
		//{
		//  return "someOtherView";
		//}    
	}

	private Person person = new Person("","",Person.Title.unknown);
}
