package za.co.solms.partsCatalog;

/**
 * Interface for a part identifier.
 */
public interface PartId
{
  public String getCode();
  
  public String getManufacturerId();
  
  public interface Mutable extends PartId
  {
    public void setCode(String newCode);
    
    public void setManufacturerId(String newManufacturer);
  }
}
