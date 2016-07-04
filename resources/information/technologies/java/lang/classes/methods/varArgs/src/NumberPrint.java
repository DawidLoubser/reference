public class NumberPrint
{
  /** A service to print the given numbers each with the given
   * prefix and suffix.
   */
  public static void print(String prefix, String suffix, Number... nums)
  {
    for (Number n : nums)
    {
      System.out.println( prefix + n + suffix );
    }
  }
  
  /** Simple test program */
  public static void main(String[] args)
  {
    print("<-", "...>", 1, 2, 3, 4, 5);
    print("[", "]", 30.3, 40.4);
  }
}
