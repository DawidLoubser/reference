package za.co.solms.example;

/** A request to check out an item by a member. For simplicity, 
 * only a single item is supported for now. */
public class CheckoutRequest implements java.io.Serializable
{
  public MemberInfo getMember()
  {
    return member;
  }
  
  public void setMember(MemberInfo member)
  {
    this.member = member;
  }
  
  public ItemInfo getItem()
  {
    return item;
  }
  
  public void setItem(ItemInfo item)
  {
    this.item = item;
  }
  
  private MemberInfo member;
  private ItemInfo item;
}