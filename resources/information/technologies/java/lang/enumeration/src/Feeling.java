/** A subjective description of the feeling produced by a temperature */
public enum Feeling
{
  // The set of constant values, with their attributes initialised
  // to fixed values via the contructor
  Freezing(-50, 0), Cold(0,15), Chilly(15, 20), 
  Mild(20, 25), Hot(25,30), Burning(30,45);
  
  
  // Constructor (to accept and set up attributes)
  private Feeling( double min, double max )
  {
    this.min = min;
    this.max = max;
  }
  
  /** Gets the minimum temperature (in C) that induces this feeling. */
  public double getMin()
  {
    return min;
  }
  
  /** Gets the maximum temperature (in C) that induces this feeling. */
  public double getMax()
  {
    return min;
  }
  
  // Attributes
  private double min, max;
}