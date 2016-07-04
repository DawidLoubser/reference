package za.co.solms.media.graphics.twoD;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * A simple 2-dimensional vector class.
 * 
 * @author fritz@solms.co.za
 *
 */
public class VectorImpl implements Vector.Mutable
{
  /**
   * Creates an instance of  VectorImpl from a vector.
   * 
   * @param vector the vector used to create the new instance of VectorImpl
   */
  public VectorImpl(Vector vector)
  {
    this.x = vector.getX();
    this.y = vector.getY();
  }
  
  /**
   * Created a new VectorImpl using the provided x and y coordinates.
   * @param x the horizontal coordinate
   * @param y the vertical coordinate
   */
  public VectorImpl(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Returns the length of the vector.
   */
  public double getLength()
  {
    return Math.sqrt(x*x + y*y);
  }
  
  /**
   * Adds another vector to this vector and returns this vector.
   */
  public Vector.Mutable add(Vector v)
  {
    setX(this.x + v.getX());
    setY(this.y + v.getY());
    return this;
  }

  /**
   * Subtracts another vector to this vector and returns this vector.
   */
  public Vector.Mutable subtract(Vector v)
  {
    setX(this.x - v.getX());
    setY(this.y - v.getY());
    return this;
  }

  /**
   * Multiplies this vector by a scalar and returns this vector.
   */
  public Vector.Mutable multiply(double arg)
  {
    setX(this.x * arg);
    setY(this.y * arg);
    return this;
  }

  /**
   * Divides this vector by a scalar and returns this vector.
   */
  public Vector.Mutable divide(double x)
  {
    setX(this.x / x);
    setY(this.y / x);
    return this;
  }
  
  public Vector.Mutable set(Vector arg)
  {
	  setX(arg.getX());
	  setY(arg.getY());
	  return this;
  }

  /**
   * @see za.co.solms.media.graphics.twoD.Vector.Mutable#setX(double)
   */
  public Vector.Mutable setX(double x)
  {
	  if (this.x != x)
	  {
		  	double oldX = x;
		  	this.x = x;
		    changeSupport.firePropertyChange(X_PROPERTY,oldX, x);
	  }  
      return this;
  }

  /**
   * @see za.co.solms.media.graphics.twoD.Vector.Mutable#setY(double)
   */
  public Vector.Mutable setY(double y)
  {
	  if (this.y != y)
	  {
		  	double oldY = y;
		  	this.y = y;
		    changeSupport.firePropertyChange(Y_PROPERTY,oldY, y);
	  }  
      return this;
  }
  
  /**
   * @see za.co.solms.media.graphics.twoD.Vector.Mutable#getDistance(Vector)
   */
  public double getDistance(Vector arg)
  {
	  double dx = arg.getX() - this.getX();
	  double dy = arg.getY() - this.getY();
	  return Math.sqrt(dx*dx + dy*dy);
  }

  /**
   * @see za.co.solms.media.graphics.twoD.Vector#getX()
   */
  public double getX()
  {
    return x;
  }

  /**
   * @see za.co.solms.media.graphics.twoD.Vector#getY()
   */
  public double getY()
  {
    return y;
  }

  /**
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return "(" + x + "," + y + ")";
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  public Object clone()
  {
    Vector copy = null;
    try
    {
      copy = (Vector)super.clone();
    }
    catch (CloneNotSupportedException e) 
    {
      /* never thrown, just reducing preconditions of contract. */
    }
    return copy;
  }

  	public boolean equals(Object o)
  	{
  		try
  		{
  			Vector arg = (Vector)o;
  			return ((getX() == arg.getX()) && (getY() == arg.getY()));
  		}
  		catch (ClassCastException e)
  		{
  			return false;
  		}
  	}
  	
  	public int hashCode()
  	{
  		return new Double(x).hashCode() + new Double(y).hashCode();
  	}
	
	public void addObserver(PropertyChangeListener listener) 
	{
		changeSupport.addPropertyChangeListener(listener);
	}

	public void addObserver(String propertyName, PropertyChangeListener listener) 
	{
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removeObserver(PropertyChangeListener listener) 
	{
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removeObserver(String propertyName, PropertyChangeListener listener) 
	{
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * Polymorphic access to the this handle used for property change management.
	 * 
	 * NOTE: This method must e overridden by any concrete subclass
	 * 
	 * @return
	 */
	protected Object getThisHandle() {return this;}

	protected PropertyChangeSupport changeSupport
	  = new PropertyChangeSupport(getThisHandle());

  private double x, y;
}
