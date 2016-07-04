package za.co.solms.generics;

import java.util.*;

interface Map<K, V>
{
  /*
    The following would result in ambiguity error due to erasure:
    -------------------------------------------------------------

  // sets value associated to key to specified new value.
  public void set(K key, V newValue);

  // sets all entries with specified value to specified new value.
  public void set(V value, V newValue);

  */

  // ...

  // The following two methods do not result in an 
  // ambiguity error:
  // ---------------------------------------------
  /**
   * @return the value associated with the specified key.
   */
  public V get(K key);  // No problem
      
  /**
   * @return the set of keys which map onto the specified value
   */
  public Set<K> get(V value);  // No problem

}                    


public class AmbiguityTest
{
  /*
    The following two functions are ambiguous due to erasure:
    ---------------------------------------------------------

  public static double average(Collection<Integer> data)
  {
    double result = 0;
    for (Integer d:data)
      result += d/data.size();
    return result;
  }

  public static double average(Collection<Double> data)
  {
    double result = 0;
    for (Double d:data)
      result += d/data.size();
    return result;
  }
  */

  // Erasure does not result in an ambiguity error for 
  // the following two functions
  // --------------------------------------------------

  public static double sum(Collection<Double> data)
  {
    double result = 0;
    for (Double d:data)
      result += d;
    return result;
  }

  public static int sum(Collection<Integer> data)
  {
    int result = 0;
    for (Integer d:data)
      result += d;
    return result;
  }

  public static void main(String[] args)
  {
    List<Integer> dat1 = new LinkedList<Integer>();
    dat1.add(1);
    dat1.add(3);
    dat1.add(7);

    System.out.println(sum(dat1));

    Set<Double> dat2 = new TreeSet<Double>();
    dat2.add(1.11);
    dat2.add(3.21);
    dat2.add(1.98);

    System.out.println(sum(dat2)); 
  }
}