<![CDATA[
package za.co.solmstraining.j2me.utils;

public class Decimal
{
  public Decimal(long mantissa, long exponent)
  {
    this.mantissa = mantissa;
    this.exponent = exponent;
  }

  public Decimal(String s) throws IllegalArgumentException
  {
    mantissa = 0;
    exponent = 0;

    int ePos = s.indexOf('e');

    String mantissaStr = null;
    String exponentStr = null;
    if (ePos != -1)
      {
        mantissaStr = s.substring(0, ePos);
        exponentStr = s.substring(ePos+1, s.length());
      }
    else
      mantissaStr = s;

    int pointPos = mantissaStr.indexOf('.');
    String digitsBeforePointStr = null;
    String digitsAfterPointStr = null;
    if (pointPos != -1)
      {
        digitsBeforePointStr
          = mantissaStr.substring(0, pointPos);
        digitsAfterPointStr
          = mantissaStr.substring(pointPos+1, mantissaStr.length());
      }
    else
      digitsBeforePointStr
        = mantissaStr.substring(0, mantissaStr.length());

    long digitsBeforePoint = Long.parseLong(digitsBeforePointStr);
    long digitsAfterPoint = 0;
    if (digitsAfterPointStr != null)
      digitsAfterPoint = Long.parseLong(digitsAfterPointStr);

    if (exponentStr != null)
      exponent = Long.parseLong(exponentStr);
    if (digitsBeforePoint != 0)  
      exponent += numDigits(digitsBeforePoint)-1;

    long fact = 1;
    int numDigitsAfterPoint = numDigits(digitsAfterPoint);
    for (int i=0; i<numDigitsAfterPoint; ++i)
      fact *= 10;

    if (digitsBeforePoint >= 0)
      mantissa = fact*digitsBeforePoint + digitsAfterPoint;
    else
      mantissa = fact*digitsBeforePoint - digitsAfterPoint;
  }

  public Decimal add(Decimal arg)
  {
    long mantissa1 = mantissa;
    long exponent1 = exponent;
    long mantissa2 = arg.mantissa;
    long exponent2 = arg.exponent;

    return add(mantissa1, exponent1, mantissa2, exponent2);
  }

  public Decimal subtract(Decimal arg)
  {
    long mantissa1 = mantissa;
    long exponent1 = exponent;
    long mantissa2 = arg.mantissa;
    long exponent2 = arg.exponent;

    /* Subtraction = addution with negative number. */
    return add(mantissa1, exponent1, -mantissa2, exponent2);
  }

  private Decimal add(long mantissa1, long exponent1,
                       long mantissa2, long exponent2)
  {
    /* First scale mantissas so that we are working to highest
       resolution. */
    while (numDigits(mantissa1) < numDigits(mantissa2))
      mantissa1 *= 10;
    while (numDigits(mantissa2) < numDigits(mantissa1))
      mantissa2 *= 10;

    /* Make the first number the one with the largest exponent */
    long exponentDif = exponent1 - exponent2;
    if (exponentDif < 0)
    {
      long dumM = mantissa1;
      long dumE = exponent1;
      mantissa1 = mantissa2;
      exponent1 = exponent2;
      mantissa2 = dumM;
      exponent2 = dumE;
      exponentDif = - exponentDif;
    }

    /* Scale mantissas according to their exponents */
    while (exponentDif > 0)
    {
      if (numDigits(mantissa1) < maxDigits)
      {
        mantissa1 *= 10; /* try and up scale mantissa 1*/
        ++exponent2;
      }
      else
      {
        /* if long overflow danger, we have to downscale
           mantissa 2 instead. */
        mantissa2 = dropDigit(mantissa2);
        ++exponent2;
      }
      --exponentDif;
    }

    int initialMantissaDigits
      = java.lang.Math.max(numDigits(mantissa1),numDigits(mantissa2));
    /* Down-scale if overflow may occur. */
    if (initialMantissaDigits > maxDigits)
    {
      mantissa1 = dropDigit(mantissa1);
      mantissa2 = dropDigit(mantissa2);
    }

    /* Perform the addition.*/
    mantissa1 += mantissa2;
    int exponentShift = numDigits(mantissa1) - initialMantissaDigits;
    /* Check if, a carry resulted or a cancellation (if the numbers
       had different signs) resulted in an exponent shift. */
    exponent1 += exponentShift;

    return new Decimal(mantissa1,exponent1);
  }

  public Decimal multiply(Decimal arg)
  {
    long mantissa1 = mantissa;
    long exponent1 = exponent;
    long mantissa2 = arg.mantissa;
    long exponent2 = arg.exponent;

    int numDigits1 = numDigits(mantissa1);
    int numDigits2 = numDigits(mantissa2);

    /* Down-scale if long-overflow can be expected. */
    while (numDigits1+numDigits2 > maxDigits)
    {
      if (numDigits1 > numDigits2)
        {
          mantissa1 = dropDigit(mantissa1);
          --numDigits1;
        }
      else
        {
          mantissa2 = dropDigit(mantissa2);
          --numDigits2;
        }
    }
    int expectedDigits = numDigits1+numDigits2-1;

    /* Perform the multiplication. */
    mantissa1 *= mantissa2;
    exponent1 += exponent2;
    /* Check if a carry resulted in an extra digit. */
    if (numDigits(mantissa1)>expectedDigits)
      ++exponent1;

    return new Decimal(mantissa1, exponent1);
  }

  public Decimal divide(Decimal arg)
  {
    long mantissa1 = mantissa;
    long exponent1 = exponent;
    long mantissa2 = arg.mantissa;
    long exponent2 = arg.exponent;

    int numDigits1 = numDigits(mantissa1);
    int numDigits2 = numDigits(mantissa2);

    /* Up-scale numerator to maximum resolution to maintain
       maximum number of digits during division. */
    while (numDigits1 < maxDigits)
    {
      mantissa1 *= 10;
      ++numDigits1;
    }

    /* If necessary, down-scale denominator to prevent
       unnecessary digit loss during integer division. */
    while (numDigits2 > maxDigits/2)
    {
      mantissa2 = dropDigit(mantissa2);
      --numDigits2;
    }
    int expectedDigits = numDigits1 - numDigits2+1;

    /* Perform the division. */
    mantissa1 /= mantissa2;
    exponent1 -= exponent2;

    /* Check for extra digit drop. */
    if (numDigits(mantissa1) < expectedDigits)
      --exponent1;

    return new Decimal(mantissa1, exponent1);
  }

  public Decimal pow(int n)
  {
    Decimal result = new Decimal(1,0);

    if (mantissa == 0) return new Decimal(0,0);
    if (n == 0) return result; /* x^0 == 1 */

    boolean positivePower = true;
    if (n < 0)
    {
      positivePower = false;
      n = -n;
    }

    for (int i=0; i<n; ++i)
      if (positivePower)
        result = result.multiply(this);
      else
        result = result.divide(this);

    return result;
  }

  public String toString()
  {
    String mantissaStr = Long.toString(mantissa);
    int pos=1;
    if (!(Character.isDigit(mantissaStr.charAt(0))))
      ++pos;
    mantissaStr = mantissaStr.substring(0,pos) + '.'
                + mantissaStr.substring(pos,mantissaStr.length());
    if (mantissaStr.charAt(mantissaStr.length()-1) == '.')
      mantissaStr += '0';
    if (exponent != 0)
      mantissaStr += "e" + exponent;
    return mantissaStr;
  }
  
  public boolean equals(Object o)
  {
    try
    {
      Decimal arg = (Decimal)o;
      return ((mantissa == arg.mantissa) && (exponent == arg.exponent));
    }
    catch (ClassCastException e)
    {
      return false;
    }
  }

  private static long dropDigit(long x)
  {
    return (x+5)/10;
  }

  private static int numDigits(long x)
  {
    int digits = 0;
    while (x != 0)
    {
      ++ digits;
      x /= 10;
    }
    return digits;
  }

  private long mantissa = 0, exponent = 0;

  private static final int maxDigits = 15;
  private static final String invalidDecimalString
      = "Invalid decimal number string";
}
]]>
