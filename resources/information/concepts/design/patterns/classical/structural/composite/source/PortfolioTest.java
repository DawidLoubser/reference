package za.co.stc.finance;

import java.util.*;

public class PortfolioTest
{
  public static void main(String[] args)
  {
    new PortfolioTest().run();
  }

  public void run()
  {
    Portfolio p1 = new Portfolio("myStuff");
    p1.add(new Property("myHouse", 180000,
      new GregorianCalendar(1999, 0, 20).getTime()));
    p1.add(new Vehicle("myCar", 350000,
      new GregorianCalendar(2000, 10, 22).getTime(), 5));

    System.out.println("portfolio (p1) value = "
      + p1.getValue(new Date()));

    System.out.println(p1);

    try
    {
      Thread.currentThread().sleep(10000);
    }
    catch (InterruptedException e) {}

    System.out.println(p1);

    Portfolio p2 = new Portfolio("myOtherStuff");
    p2.add(new Property("myBeachHouse", 120000,
      new GregorianCalendar(2002, 0, 20).getTime()));
    p2.add(new Vehicle("myBeachBuggy", 35000,
      new GregorianCalendar(2001, 10, 22).getTime(), 10));

    p1.add(p2);

    System.out.println("\n\n" + p1);
  }
}
