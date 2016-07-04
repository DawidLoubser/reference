package za.co.solms.media.graphics.twoD;

import za.co.solms.beans.Observable;

/**
 * A contract for simple two-dimensional read-only vector. 
 * There is a inner subcontract, Mutable,providing write access to vectors.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface Vector extends Cloneable, Observable
{
  /**
   * 
   * @return the x-coordinate value
   */
  public double getX();
  
  /**
   * 
   * @return the y-coordinate value
   */
  public double getY();
  
  /**
   * 
   * @return the length of the vector
   */
  public double getLength();
  
  /**
   * Returns the distance from the point this vector is pointing to to the point
   * the argument vector is pointing to.
   * 
   * @param arg the argument vector for which the position is queried.
   * @return the distance from this vector to the argument vector.
   */
  public double getDistance(Vector arg);
  
  /**
   * The extended contract providing read and write access to vectors.
   * 
   * @author fritz@solms.co.za
   *
   */
  public interface Mutable extends Vector
  {
    /**
     * Adds the argument vector to this vector.
     * 
     * @param v the argument vector
     */
    public Vector.Mutable add(Vector v);
    
    /**
     * Subtracts the argument vector from this vector.
     * 
     * @param v the argument vector
     */
    public Vector.Mutable subtract(Vector v);

    /**
     * Multiplies this vector by the supplied scalar.
     * 
     * @param x the scalar
     */
    public Vector.Mutable multiply(double x);
    
    /**
     * Divides this vector by the scalar.
     * 
     * @param x the scalar
     */
    public Vector.Mutable divide(double x);

    /**
     * Sets the x component of the vector.
     * 
     * @param x the new value for the x-component.
     * @return the this object itself
     */
    public Vector.Mutable setX(double x);
    
    /**
     * Sets the y-component of the vector
     * @param y the neq value for the y-component.
     * @return the this object itself
     */
    public Vector.Mutable setY(double y);
    
    /**
     * Sets the coordinates of this vector equal to those of the argument vector
     * 
     * @param arg the argument vector
     * @return the this object itself
     */
    public  Vector.Mutable set(Vector arg);
  }
  
  /**
   * 
   * @return a clone of this object.
   */
  public Object clone();
  
  public static final String Y_PROPERTY = "y";
  public static final String X_PROPERTY = "x";
}
