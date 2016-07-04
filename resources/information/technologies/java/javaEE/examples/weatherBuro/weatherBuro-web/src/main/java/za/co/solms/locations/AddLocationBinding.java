/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import za.co.solms.location.LocationServices;
import za.co.solms.location.LocationServices.LocationNameInUseException;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class AddLocationBinding implements Serializable
{
	public AddLocationBinding() {}
	
	public String addLocation()
	{
		try
		{
			locationServices.persistLocation(locationBackingBean.getLocation());
			return "locations";
		} 
		catch (LocationNameInUseException e)
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Locale locale = facesContext.getELContext().getLocale();

			ResourceBundle resourceBundle 
			  = ResourceBundle.getBundle("language.messages", locale);
			FacesMessage msg = new FacesMessage(resourceBundle.getString("locationNameInUse"));
			
			facesContext.addMessage("nameField", msg);

			return "";
		}
	}

	public LocationBackingBean getLocationBackingBean()
	{
		return locationBackingBean;
	}

	public void setLocationBackingBean(LocationBackingBean locationBackingBean)
	{
		this.locationBackingBean = locationBackingBean;
	}

	@EJB
	LocationServices locationServices;
	
	@ManagedProperty(value="#{locationBackingBean}")
	private LocationBackingBean locationBackingBean;

	private static final long serialVersionUID = 1L;
}
