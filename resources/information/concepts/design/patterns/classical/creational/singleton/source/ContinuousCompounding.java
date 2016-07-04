package za.co.SolmsTraining.Finance.Primitives.Compounding;

import za.co.SolmsTraining.Finance.Primitives.DayCountConvention.DayCountConvention;

import java.io.Serializable;
import java.util.Date;

import java.io.Serializable;

/**
 * Implements the continuous compounding convention which allows users
 * to convert interest rates to discount factors and vice versa. 
 * <P>
 * The class cannot be externally instantiated. Instead users obtain a handle
 * to the only instance of the class via
 * <PRE>
 * Compounding compounding = ContinuousCompounding.getInstance();
 * </PRE>
 * @author  Fritz Solms
 * @version 1.0 final
 */
public final class ContinuousCompounding implements Compounding,
                                                    Serializable
{
  private ContinuousCompounding() {}  
    
  /**
   *  Receiving an annualized rate, a day count convention and a period 
   *  defined by a <code>date1</code> and an <code>date2</code> date, this 
   *  method returns the discount factor used to discount cashflows received 
   *  on <code>date2<code> to <code>date1</code>.
   *
   * @param annualizedRate the annualized rate (using the compounding type
   *                       specified by this compounding object.
   * @param date1 the date to which cash flows are to be discounted to.
   * @param date2 the date from which cash flows are to be discounted from
   * @param dayCountConvention The day count convention to be used for 
   *                           calculating the discount factor.
   *
   * @return the discount factor over the specified period for the specified 
   *         rate for this objects compounding type and the specified day 
   *         count convention.
   **/
  public double getDiscountFactor (double annualizedRate, 
                                   Date date1, Date date2, 
                                   DayCountConvention dayCountConvention)
  {                                       
    double nYears = dayCountConvention.getYearFraction(date1,date2);
    return Math.exp(-annualizedRate*nYears); 
  }  
  
 /**
  *  Receiving a discount factor which discounts payments received on 
  *  <code>date2</code> to <code>date1</code>, a day count convention and 
  *  the corresponding dates, this method returns the corresponding annualized 
  *  rate.
  *
  * @param annualizedRate the annualized rate (using the compounding type
  *                       specified by this compounding object.
  * @param date1 the date to which cash flows are to be discounted to.
  * @param date2 the date from which cash flows are to be discounted from
  * @param dayCountConvention The day count convention to be used for 
  *                           the rate.
  *
  * @return the annualized rate corresponding to the discount facrtor over the 
  *         specified period for the specified rate for this objects compounding 
  *         type and the specified day count convention.
  * @throws IllegalArgumentException when <code>date1 == date2</code>.
  *
  **/
  public double getRateFromDiscountFactor(double discountFactor, 
                                          Date date1, Date date2, 
                                          DayCountConvention dayCountConvention)
                  throws IllegalArgumentException                                          
  {
    double nYears = dayCountConvention.getYearFraction(date1, date2);
    
    if (nYears == 0)
      throw new IllegalArgumentException
                   ("discount factor over zero time period");
  
    if (discountFactor == 1) return 0;
    
    return -Math.log(discountFactor)/nYears; 
  }  
  
  /*
   * @return a descriptive string.
   */
  public String toString() {return stringID;}

  /**
   * String identifier for this day compounding convention.
   */
  public static final String stringID = "continuous_compounding";
  
  /**
   * @return the unique instance of the class.
   */
  public static ContinuousCompounding getInstance()
  {
  	if (theInstance == null)
  	  theInstance = new ContinuousCompounding();
  	  
  	return theInstance;
  }	  
  
  private static final ContinuousCompounding theInstance;
}
