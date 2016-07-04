public class StringUtility
{
  /** A service to invert the case of a String - all uppercase 
   * characters will be made lowercase, and vice versa.
   * @param s The string to be inverted
   * @return An inverted string, or null if no string was provided
   */
  public static String invertCase( String s)
  {
    if (s == null) return null;
    
    String invertedString = "";
    for (int i=0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      char ic = Character.isUpperCase(c) ? 
          Character.toLowerCase(c) : Character.toUpperCase(c);
      invertedString += ic;
    }
    return invertedString;
  }
}