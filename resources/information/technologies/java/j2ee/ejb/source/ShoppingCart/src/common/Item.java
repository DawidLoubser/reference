package za.co.solms.shopping;

import za.co.solms.utils.Formatters;

public class Item implements java.io.Serializable, Comparable
{
  public Item(String name, double price)
  {
    this.name = name;
    this.price = price;
  }
  
  public String getName() {return name;}
  
  public double getPrice() {return price;}
  
  public String toString() 
  {
    return name + ": " 
      + Formatters.getPriceFormat().format(price);
  }
  
  public int compareTo(Object o)
  {
    Item arg = (Item)o;
    return name.compareTo(arg.getName());
  }  
  
  private String name;
  private double price;
}    

