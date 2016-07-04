package za.co.solms.example;

/** Abstractly represents a library item. Could be extended by
 * 'Book', 'NewsPaper', etc. */
public class ItemInfo implements java.io.Serializable
{
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
  public int getSerialNumber()
  {
    return serialNumber;
  }

  public void setSerialNumber(int serialNumber)
  {
    this.serialNumber = serialNumber;
  }
  
  public String toString()
  {
    return "#" + serialNumber + ": " + name;
  }
  
  private String name;  
  private int serialNumber;
}
