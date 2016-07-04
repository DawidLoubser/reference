package za.co.solms.example;

/** A request that checks in (returns) an item to the library.
 * For simplicity, only a single item is supported.
 */
public class CheckInRequest implements java.io.Serializable
{
  public ItemInfo getItem()
  {
    return item;
  }

  public void setItem(ItemInfo item)
  {
    this.item = item;
  }
  
  private ItemInfo item;
}
