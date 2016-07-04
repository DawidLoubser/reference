package za.co.stc.finance;

import java.util.*;

public class Portfolio extends Asset
{
  public Portfolio(String id) {super(id);}

  public void add(Valuable asset)
  {
    assets.add(asset);
  }

  public double getValue(Date date)
  {
    double sum = 0;
    Iterator iter = assets.iterator();
    while (iter.hasNext())
    {
      Asset asset = (Asset)iter.next();
      sum += asset.getValue(date);
    }
    return sum;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("Portfolio: " + super.toString());
    result.append(", current value = "
      + getValue(new Date()) + eol);
    Iterator iter = assets.iterator();
    while (iter.hasNext())
      result.append(iter.next()).append(eol);
    return result.toString();
  }

  private Collection assets = new LinkedList();

  private final static String
    eol = System.getProperty("line.separator");
}
