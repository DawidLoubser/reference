package za.co.solms.partsCatalog;

import java.io.Serializable;
import javax.persistence.Embeddable;
import za.co.solms.partsCatalog.PartId;

/**
 * Primary key class for part id which has been annotated as
 * embeddable such that it can be used as the primary key class
 * for an entity persisted via an JPA entity manager.
 */
@Embeddable
public class PartPK implements PartId.Mutable, Serializable
{
  public PartPK(String code, String manufacturerId)
  {
    setCode(code);
    setManufacturerId(manufacturerId);
  }
  
  protected PartPK(){}
  
  public void setCode(String newCode)
  {
    this.code = newCode;
  }

  public void setManufacturerId(String newManufacturer)
  {
    this.manufacturerId = newManufacturer;
  }

  public String getCode()
  {
    return code;
  }

  public String getManufacturerId()
  {
    return manufacturerId;
  }
  
  private String code, manufacturerId;
  private static final long serialVersionUID=200609211600L;
}
