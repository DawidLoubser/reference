public class Date1
{
   public Date1(int day, int month, int year) 
               throws IllegalArgumentException
   {
     valiDate1(day, month, year);
     this.day = day;
     this.month = month;
     this.year = year;
   }
 
   public String toString() 
   {
     return day + "-" + month + "-" + year;
   }
 
   public int getDay()   {return day;}
   public int getMonth() {return month;}
   public int getYear()  {return year;}
 
   public Date1 addDays(int nDays)
   {
     if (nDays &lt; 0)
       return subtractDays(-nDays);
 
     int newDay = day;
     int newMonth = month;
     int newYear = year;
     for (int n=0; n&lt;nDays; ++n)
     {
       ++newDay;
       if (newDay > getNumDaysInMonth(newMonth, newYear))
       {
         ++newMonth;
         newDay = 1;
         if (newMonth > 12)
         {
           ++newYear;
           newMonth = 1;
         }
       }
 
     }
     return new Date1(newDay, newMonth, newYear);
   }
 
   public Date1 subtractDays(int nDays)
   {
     if (nDays < 0)
       return addDays(-nDays);

 
     int newDay = day;
     int newMonth = month;
     int newYear = year;
     for (int n=0; n&lt;nDays; ++n)
     {
       --newDay;
       if (newDay < 1)
       {
         --newMonth;
         if (newMonth < 1)
         {
           --newYear;
           newMonth = 12;
         }
 
         newDay = getNumDaysInMonth(newMonth, newYear);
       }
     }
     return new Date1(newDay, newMonth, newYear);
   }
 
   private void valiDate1(int day, int month, int year)
       throws IllegalArgumentException
   {
     if ((month < 1) || (month > 12))
       throw new IllegalArgumentException("invalid month.");
 
     if ((day < 1 ) || (day > getNumDaysInMonth(month, year)))
       throw new IllegalArgumentException("invalid day of month.");
   }
 
   public boolean isLeapYear() {return isLeapYear(year);}
 
   public int getNumDaysInMonth() {return getNumDaysInMonth(month, year);}
 
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
