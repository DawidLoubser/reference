package za.co.solms.ejb3.shopping.products;

import java.io.Serializable;

/**
 * @author fritz@solms.co.za
 *
 * Encapsulates products which can be added to a shopping cart.
 */
public class Product implements Serializable 
{
  public Product(String name, double price)
  {
  	this.name = name;
  	this.price = price;
  }
  
  public String getName() {return name;}
  
  public double getPrice() {return price;}
  
  public int hashCode()
  {
      return name.hashCode();
  }
  
  public boolean equals(Object o)
  {
      try
      {
          Product arg = (Product)o;
          return arg.getName().equals(name);
      }
      catch (ClassCastException e) {return false;}
  }
  
  public String toString() {return name;}
  private String name;
  private double price;
  private static final long serialVersionUID = 314159269876342876L;
}
