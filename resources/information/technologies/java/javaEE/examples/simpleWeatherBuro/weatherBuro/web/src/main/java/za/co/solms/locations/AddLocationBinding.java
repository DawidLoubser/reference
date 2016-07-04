/**
 * 
 */
package za.co.solms.locations;

import java.io.Serializable;

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
			locationServices.persistLocation(locationBinding.getLocation());
			return "locations";
		} 
		catch (LocationNameInUseException e)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Location name already used.", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("nameField", msg);

			return "";
		}
	}

	public LocationBinding getLocationBinding()
	{
		return locationBinding;
	}

	public void setLocationBinding(LocationBinding locationBinding)
	{
		this.locationBinding = locationBinding;
	}

	@EJB
	LocationServices locationServices;
	
	@ManagedProperty(value="#{locationBinding}")
	private LocationBinding locationBinding;

	private static final long serialVersionUID = 1L;
}
