public class Claim
{

  public Collection<ClaimItem> getClaimItems() {return claimItems;}
  
  public ContactDetail[] getContactDetails() {return contactDetails;}			
	
  private ContactDetails[] contactDetails;															
												
  private Collection<ClaimItem> claimItems;																
}	