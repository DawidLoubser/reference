<![CDATA[
package za.co.solmstraining.j2me.commerce;

public class LineItem
{
  public LineItem(String productId, int quantity)
  {
    this.productId = productId;
    this.quantity = quantity;
  }

  public String getProductId() {return productId;}

  public int getQuantity() {return quantity;}
  
  public void setQuantity(int newQuantity) {quantity = newQuantity;}

  public String toString()
  {
    return Integer.toString(quantity) + " x " + productId;
  }

  private String productId;
  private int quantity;
}
]]>
