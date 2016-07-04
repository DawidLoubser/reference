package za.co.solms.example;

/** A contract for a simplistic book shop */
public interface BookShop 
{
	/** Enquires as to the availability of a particular book.	
	 * @param request Contains information that identifies the book in question
	 * @return Information about the availability of the book
	 * @throws UnknownBookException If the request identifies an unknown book
	 * @throws InsufficientInformationException If the request does not identify a book
	 */
	public BookAvailabilityResponse enquireBookAvailability( BookAvailabilityRequest request )
	throws UnknownBookException, InsufficientInformationException;
	
	// etc. ...
}
