public class Average2
{
  /** Computes the average of the given array of numbers */
  public static double average( double... numbers )
  {
    double total = 0;
    for (double n : numbers)
    {
      total += n;
    }
    return total / numbers.length;
  }
}