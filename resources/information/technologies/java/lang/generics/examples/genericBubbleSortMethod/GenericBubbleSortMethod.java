package za.co.solms.generics;

public class GenericBubbleSortMethod
{
  /** A function to sort an array of comparable objects. */
  public static <T extends Comparable<T>> void bubbleSort(T[] array)
  {
    for (int l=array.length; l>=2; --l)
      for (int i=0; i<l-1; ++i)
        if (array[i].compareTo(array[i+1])>0)
          swap(array, i, i+1);
  }

  protected static <T> void swap(T[] array, int i, int j)
  {
    T dummy = array[i];
    array[i] = array[j];
    array[j] = dummy;
  }

  protected static void print(Object[] objects)
  {
    System.out.print("{");
    for (int i=0; i<objects.length-1; ++i)
      System.out.print(objects[i] + ",");
    if (objects.length>0)
      System.out.print(objects[objects.length-1]);
    System.out.println("}");
  }

  /** Simple test for our generic function */
  public static void main(String[] args)
  {
    Double[] doubles = {1.2, 5.6, 2.4, 0.9, 3.9};

    String[] names = {"Piet", "Peter", "Piotr", "Petra", "Pieter", "Pete"};

    bubbleSort(doubles);
    print(doubles);

    bubbleSort(names);
    print(names);
  }
}
