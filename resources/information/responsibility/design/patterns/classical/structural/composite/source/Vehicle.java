package za.co.stc.finance;

import java.util.*;

public class Vehicle extends PurchasedAsset
{
  public Vehicle(String id, double purchasePrice,
                 Date purchaseDate,
                 double writeOffPeriodInYears)
  {
    super(id, purchasePrice, purchaseDate);
    writeOffPeriod = writeOffPeriodInYears;
  }

  public double getValue(Date date)
  {
    double age = ageInYears(date);

    return getPurchasePrice() * (1 - age / writeOffPeriod);
  }
  public String toString()
  {
    return " Vehicle " + super.toString()
      + " written off over " + writeOffPeriod + " years, "
      + " current value = R" + getValue(new Date());
  }

  private double writeOffPeriod;
}
