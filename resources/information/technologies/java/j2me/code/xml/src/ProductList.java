<![CDATA[
package za.co.solmstraining.j2me.commerce;

public class ProductList
{
  public ProductList() {}

  public ProductList(String[] productIds, long[] prices)
  {
    this.productIds = productIds;
    this.prices = prices;
  }

  public String[] getProductIds()
  {
    return productIds;
  }

  public String getProductId(int index)
  {
    return productIds[index];
  }

  public long getPrice(int index)
  {
    return prices[index];
  }

  public int getIndex(String productId)
    throws java.util.NoSuchElementException
  {
    int index = 0;
    while (!productIds[index].equals(productId)
            && index<productIds.length)
               ++index;
    if (index >= productIds.length)
     throw new java.util.NoSuchElementException();

    return index;
  }

  public long getPrice(String productId)
  {
    return prices[getIndex(productId)];
  }

  private String[] productIds 
               = {"Stary-glitter toothpaste",
                  "Knock-out purple hair colouring",
                  "Pirate's hook nail cleaner",
                  "Kitty's artificial nails",
                  "Status nose ring",
                  "Smokey aura"};
  private long[] prices = {1299, 2899, 3399, 4199, 11999, 6799};
}
]]>
