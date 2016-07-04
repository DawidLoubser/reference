package za.co.solms.partsCatalog;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PartBean implements Part.Mutable
{
  
  public PartPK getPartId()
  {
    return partPk;
  }

  public String getDescription()
  {
    return description;
  }

  public String getName()
  {
    return name;
  }

  public void setDescription(String newDescription)
  {
    this.description = newDescription;
  }

  public void setName(String newName)
  {
    this.name = newName;
  }

  public void setPartId(PartId newPartId)
  {
    this.partPk = new PartPK(newPartId.getCode(), newPartId.getManufacturerId());
  }

  @EmbeddedId
  private PartPK partPk;
  private String name, description;
}
