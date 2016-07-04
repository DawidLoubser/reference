package za.co.stc.finance;

import java.util.*;

public abstract class PurchasedAsset extends Asset
{
  public PurchasedAsset(String id,
                        double purchasePrice,
                        Date purchaseDate)
  {
    super(id);
    this.purchasePrice = purchasePrice;
    this.purchaseDate = purchaseDate;
  }

  public double ageInYears(Date date)
  {
    double ageInMilliSecs
      = date.getTime() - purchaseDate.getTime();
    return ageInMilliSecs / milliSecsPerYear;
  }

  public double getPurchasePrice() {return purchasePrice;}

  public Date getPurchaseDate() {return purchaseDate;}

  public String toString()
  {
    return super.toString() + " purchased on "
      + purchaseDate + " for R" + purchasePrice;
  }

  private static final double milliSecsPerYear
    = 1000.0 * 60 * 60 * 24 * 365.25;

  private double purchasePrice;
  private Date purchaseDate;
}
