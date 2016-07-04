<![CDATA[
package za.co.solmstraining.j2me.utils.test;

import za.co.solmstraining.j2me.utils.Decimal;

public class DecimalTest
{
  public static void main(String[] args)
  {
    Decimal x = new Decimal(187652176576549L, 12);
    Decimal y = new Decimal(68226279843435L, 7);

    System.out.println("x = " + x);
    System.out.println("y = " + y);

    Decimal z = x.add(y);

    System.out.println("x + y = " + z);

    z = x.subtract(y);

    System.out.println("x - y = " + z);

    z = x.multiply(y);

    System.out.println("x * y = " + z);

    z = x.divide(y);

    System.out.println("x / y = " + z);

    x = new Decimal(1,0);
    y = new Decimal(3,0);

    System.out.println("x = " + x);
    System.out.println("y = " + y);

    z = x.divide(y);

    System.out.println("x / y = " + z);

    System.out.println("y^-2 = " + y.pow(-2));

    System.out.println("y^4 = " + y.pow(4));

    x = new Decimal("-1234.5678e-12");
    
    System.out.println("Created decimal from string '-1234.5678e-12': " + x);
  }
}
]]>
