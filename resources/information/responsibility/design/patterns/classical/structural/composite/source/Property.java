package za.co.stc.finance;

public class Property extends PurchasedAsset
{
  public Property(String id, double purchasePrice,
                  java.util.Date purchaseDate)
  {
    super(id, purchasePrice, purchaseDate);
  }

  public double getValue(java.util.Date date)
  {
    return getPurchasePrice();
  }

  public String toString()
  {
    return " Property " + super.toString();
  }
}
