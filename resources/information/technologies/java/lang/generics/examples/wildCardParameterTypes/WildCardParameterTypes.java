package za.co.solms.generics;

import java.util.*;

public class WildCardParameterTypes
{
  /** A generic service to print the given collection
   * of elements to standard output.
   */
  public static void print(Collection<?> collection)
  {
    for (Object o:collection)
    {
      System.out.println(o);
    }
  }

  public static void main(String[] args)
  {
    // Here we do provide a type parameter
    List list = new LinkedList();
    list.add("Jill"); 
    list.add(-1);
    list.add("Jillian"); 
    list.add(5);
    list.add("Jilli"); 
    list.add("Jilla"); 

    print(list);
    
    // Here, we bind the type parameter to integers
    List<Integer> parametrizedList = new ArrayList<Integer>();
    parametrizedList.add(2);
    parametrizedList.add(5);
    parametrizedList.add(-1);
    parametrizedList.add(3);
    parametrizedList.add(7);
    
    print(parametrizedList);
  }
}
