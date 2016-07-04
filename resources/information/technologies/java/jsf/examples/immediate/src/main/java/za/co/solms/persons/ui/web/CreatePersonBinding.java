package za.co.solms.persons.ui.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.solms.persons.model.Company;
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
	
	public boolean isEmployed()
	{
		return employed;
	}

	public void setEmployed(boolean employed)
	{
		this.employed = employed;
	}

	public Company getEmployer()
	{
		return employer;
	}

	public void setEmployer(Company employer)
	{
		this.employer = employer;
	}

	public Title[] getTitles() {return Person.Title.values();}
	
	public String savePerson() 
	{
		if (employed)
			person.setEmployer(employer);
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

	private boolean employed = false;
	private Person person = new Person("","",Person.Title.unknown);
	private Company employer = new Company("","");
}
