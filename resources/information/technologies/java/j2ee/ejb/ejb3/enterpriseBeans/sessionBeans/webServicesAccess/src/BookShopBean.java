package za.co.solms.example.impl;

import javax.ejb.*;
import javax.jws.*;
import za.co.solms.example.*;

@Stateless
@Remote({BookShop.class})
@WebService
public class BookShopBean implements BookShop 
{
	public BookAvailabilityResponse enquireBookAvailability( BookAvailabilityRequest request ) 
	throws UnknownBookException, InsufficientInformationException 
	{
		// TODO Implement...
		return null;
	}
}