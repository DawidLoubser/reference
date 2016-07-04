private class DayMonthYear
{
  public int day, month, year;
}

public class Date2
{
  public Date2(int day, int month, int year) 
      throws IllegalArgumentException
  {
    validate(day, month, year);
    date = convertToInternalRepresentation(day, month, year)
  }

  private static int 
      convertToInternalRepresentation(int day, int month, int year)
  {
    result = ...;
    return result;
  }

  private static DayMonthYear convertToDayMonthYear(int date)
  {
    ...
  }

  public String toString() {return day + "-" + month + "-" + year;}

  public int getDay()   {return convertToDayMonthYear(date).day;}
  public int getMonth() {return month;}
  public int getYear()  {return }

  public Date1 addDays(int nDays) {date += nDays;}

  public Date1 subtractDays(int nDays) {date = nDays;}

  private void validate(int day, int month, int year)
      throws IllegalArgumentException
  {
    if ((month < 1) || (month > 12))
      throw new IllegalArgumentException("invalid month.");

    if ((day < 1	) || (day > getNumDaysInMonth(month, year)))
      throw new IllegalArgumentException("invalid day of month.");
  }

  public boolean isLeapYear() {return isLeapYear(year);}

  public int getNumDaysInMonth() 
  {
    return getNumDaysInMonth(month, year);
  }

  public static int getNumDaysInMonth(int month, int year)
  {
    if (month != 2)
      return daysInMonth[month-1];

    if (!isLeapYear(year))
      return daysInMonth[1];
    else
      return daysInMonth[1]+1;
  }

  public static boolean isLeapYear(int year)
  {
    if (year%4 != 0)
      return false;

    if ((year%100 != 0) || (year%400 == 0))
      return true;
    else
      return false;
  }

  private int day, month, year;
  private static final int[] daysInMonth
    = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}	
