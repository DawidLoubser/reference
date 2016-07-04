
package za.co.solmstraining.math;

public class PointR2 implements Cloneable
{
  public PointR2(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {return x;}
  public double getY() {return y;}
  
  public void setX(double newX) {x = newX;}
  public void setY(double newY) {y = newY;}
  
  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException e) 
    {
      return null; /* will never occur */
    }  
  }
  
  public boolean equals(Object o)
  {
    try
    {
      PointR2 arg = (PointR2)o;
      return ((arg.x == x) && (arg.y == y));
    }
    catch (ClassCastException e) {return false;}
  }    
  
  public String toString()
  {
    return "(" + x + "," + y + ")";
  }  
  
  private double x, y;
}    
