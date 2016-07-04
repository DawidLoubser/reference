public class Date1Test
{
   public static void main(String[] args)
   {
     Date1 d = new Date1(10, 10, 2000);
     System.out.println(d);
     System.out.println("is leap year: " + d.isLeapYear());
     System.out.println("number of days in month = " 
            + d.getNumDaysInMonth() + "/n");
 
     d = d.addDays(700);
     System.out.println(d);
     System.out.println("is leap year: " + d.isLeapYear());
     System.out.println("number of days in month = " 
           + d.getNumDaysInMonth() + "/n");
 
     d = d.subtractDays(700);
     System.out.println(d);
 
     Date1 d2 = new Date1(29, 2, 2001);
   }
}
