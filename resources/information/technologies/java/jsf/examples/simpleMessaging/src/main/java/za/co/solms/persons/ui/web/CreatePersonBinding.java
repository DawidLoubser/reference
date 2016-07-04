package za.co.solms.persons.ui.web;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	public void employmentChanged(ValueChangeEvent e)
	{
		Boolean newEmployedValue = (Boolean)e.getNewValue();
		if (newEmployedValue == true)
			employed = true;
		else
			employed = false; 
		FacesContext.getCurrentInstance().renderResponse();
	}

	public Title[] getTitles() {return Person.Title.values();}
	
	public String savePerson() 
	{
		if (person.getTitle().equals(Person.Title.Mstr) && employed)
		{
			FacesMessage msg = new FacesMessage("A " + Person.Title.Mstr 
					+ " should not be employed.");
         FacesContext context = FacesContext.getCurrentInstance();
         UIComponent component = getComponentWithComponentId(context.getViewRoot(), "titleField");
         if (component != null)
         	context.addMessage(component.getClientId(context), msg);
         else
         	context.addMessage(null, msg);
			
			return "";
		}
		
		if (employed)
			person.setEmployer(employer);
		
		return "personSavedConfirmation";
	}
	
   private static UIComponent getComponentWithComponentId(UIComponent parent, String id) 
   {
      if (id.equals(parent.getId())) 
          return parent;

      Iterator<UIComponent> children = parent.getFacetsAndChildren();
      while(children.hasNext())
      {
        UIComponent found = getComponentWithComponentId(children.next(), id);
        if(found != null) 
           return found;
      }  
      return null;
   }
      
	
	private boolean employed = false;
	private Person person = new Person("","",Person.Title.unknown);
	private Company employer = new Company("","");
	
	private static Log log = LogFactory.getLog(CreatePersonBinding.class);
}
