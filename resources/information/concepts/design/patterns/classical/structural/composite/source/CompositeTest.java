import java.util.*;

interface Valuable
{
  public double value(Date date);
}  

abstract class Asset implements Valuable
{


class Vehicle implements Asset
{
  public Vehicle(Date purchaseDate,
                 double price)
    {
      this.purchaseDate = purchaseDate;
      this.price = price;
    }
    
    public double value(Date date)
    {
      double age = age(date);
      
      if (age > 5)
        return 0;
        
      return price * (1 - age/5);
    }                
    
    public double age(Date date)
    {
      return (date.getTime() - purchaseDate.getTime())
                / (1000.0*60*60*24*365.25);
    }           
    
    public String toString()
    {
      return "Vehicle bought on "
        + purchaseDate + " for R" + price 
        + ", current value = " + value(new Date());
    }
    
    private Date purchaseDate;
    private double price;
}

class Property implements Asset
{
  public Property(double price)
  {
    this.price = price;
  }
  
  public double value(Date date)
  {
    return price;
  }
    
    public String toString()
    {
      return "Property bought for R" 
        + price 
        + ", current value = " + value(new Date());
    }
  
  private double price;
}

class Portfolio implements Asset
{
  public Portfolio()
  {
  }
   
  public void add(Asset asset)
  {
    assets.add(asset);
  }
  
  public double value(Date date)
  {
    double sum = 0;
    
    Iterator iter = assets.iterator();
    while (iter.hasNext())
    {
      Asset asset = (Asset)iter.next();
      sum += asset.value(date);
    }
      
    return sum;
  }
  
  public String toString()
  {
    String result = "Portforlio "
      + ", value = R" + value(new Date()) + "\n";
      
    Iterator iter = assets.iterator();
    while (iter.hasNext())
      result += iter.next().toString() + "\n";
      
      return result;
  }       
  
  public Collection assets = new HashSet();
} 

public class CompositeTest
{
  public void run()
  {
    Portfolio p = new Portfolio();
  
      Date d = new GregorianCalendar(2000,0,20).getTime();
       
    Date now = new Date();   
      
      Vehicle x = new Vehicle(d, 23000);
    p.add(x);
       
    p.add(new Property(400000));   
    
    Portfolio p2 = new Portfolio();
  
    d = new GregorianCalendar(2001,0,20).getTime();
     
    x = new Vehicle(d, 600000);
    
    p2.add(x);
       
    p2.add(new Property(80000));   
    
    p.add(p2);
    
    System.out.println(p);
  }
  
  public static void main(String[] args)
  {
    new CompositeTest().run();
  }
}
