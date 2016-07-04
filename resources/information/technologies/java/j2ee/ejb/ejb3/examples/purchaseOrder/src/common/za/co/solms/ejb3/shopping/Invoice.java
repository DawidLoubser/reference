package za.co.solms.ejb3.shopping;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

/**
 * @author fritz@solms.co.za
 *
 */
public class Invoice 
{
  public Invoice(double itemsCost, double taxCost)
  {
    this.itemsCost = itemsCost;
    this.taxCost = taxCost;
  }
  
  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("Items cost: " + priceFormatter.format(itemsCost)).append(lineSeparator);
    result.append("  Tax cost: " + priceFormatter.format(taxCost)).append(lineSeparator);
    result.append("Total cost: " + priceFormatter.format(itemsCost + taxCost)).append(lineSeparator);
    
    return result.toString();
  }
  
  private double itemsCost, taxCost;
  
  private static final DecimalFormat priceFormatter = new DecimalFormat("R#####0.00");
  private static final Logger logger = Logger.getLogger(Invoice.class);
  private static final String lineSeparator = System.getProperty("line.separator");
}

