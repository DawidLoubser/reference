/**
 * 
 */
package za.co.solms.partsCatalog;

/**
 * A part for the parts catalog.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface Part
{
  public PartId getPartId();
  
  public String getName();
  
  public String getDescription();
  
  public interface Mutable extends Part
  {
    public void setPartId(PartId newPartId);
    
    public void setName(String name);
    
    public void setDescription(String newDescription);
  }
}
