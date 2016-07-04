package za.co.solms.ejb3.friends;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements java.io.Serializable, Cloneable
{
  public Address() {}
  
  public Address(String street, String suburb)
  {
    this.street = street;
    this.suburb = suburb;
  }
  
  public String getStreet() {return street;}
  public String getSuburb() {return suburb;}
  
  public void setStreet(String street){this.street = street;}
  public void setSuburb(String suburb){this.suburb = suburb;}
  
  public String toString()
  {
    return street + ", " + suburb;
  }
  
  public Object clone()
  {
    try
    {
      return (Address)super.clone();
    }
    catch (CloneNotSupportedException e)
    {
      /*never thrown */
      return null;
    }
  }
  
  private String street, suburb;
  private static final long serialVersionUID = 9876543895473L;
}