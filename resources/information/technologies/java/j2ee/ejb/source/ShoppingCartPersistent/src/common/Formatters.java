package za.co.solms.utils;

import java.text.*;

public class Formatters
{
  private Formatters() {}

  public static DecimalFormat getPriceFormat()
  {
    return priceFormat;
  }

  private static final java.text.DecimalFormat priceFormat
    = new java.text.DecimalFormat("###,###,###,##0.00");
}
