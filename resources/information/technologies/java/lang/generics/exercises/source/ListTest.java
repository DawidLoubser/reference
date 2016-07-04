package za.co.solms.generics.test;

import za.co.solms.generics.*;

public class ListTest
{
  public void run()
  {
    List<String> names = new DoublyLinkedList<String>();
    names.insert("Piet",0);
    names.insert("Pieter",0);
    names.insert("Pietie",0);
    names.insert("Piotr",0);
    names.insert("Pete",0);
    names.insert("Peter",0);
    System.out.println(names);

    names.insert("Petra", 3);
    System.out.println(names);

    names.insert("Petrus", 5);
    System.out.println(names);
  }
  
  public static void main(String[] args)
  {
    new ListTest().run();
  }  
}	
