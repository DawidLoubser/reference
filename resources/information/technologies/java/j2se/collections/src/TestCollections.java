 import java.util.*;
 
 class TestCollections
 {
   public static void main(String[] args)
   {
     LinkedList list = new LinkedList();
     list.add("Peter");
     list.add("Sibusiso");
     list.add("Pieter");
     list.add("Clare");
     list.add("Pat");
 
     System.out.println(list);
 
     Collections.sort(list);
 
     System.out.println(list);
 
     System.out.print("Pieter is in position "
                       + Collections.binarySearch(list, "Pieter"));
   }
 }   

