package za.co.solmstraining.utils.random;

/**
 * A pseudo-random number generator which allows to externally store
 * its encapsulated state as captured by a memento. The state  of the
 * pseudo-random number generator can be reconstructed from the memento
 * at some later stage.
 */
public class RandomNumberGenerator
{
  /**
   * Creates a new random number generator using the machine date/time as seed.
   */
  public RandomNumberGenerator()
  {
    randomize();
  }

  /**
   * Creates a new random number generator using the supplied seed.
   */
  public RandomNumberGenerator(long seed)
  {
    setSeed(seed);
  }

  public void setSeed(long seed)
  {
    this.seed = seed;
  }

  /**
   * Recalculates a seed using the current date/time.
   */
  public void randomize()
  {
    seed = new java.util.Date().getTime() * radixRange;
    seed *= 0xFFFFFFFF;
  }

  /**
   *  Returns the next integer between 0 and rangeUpper.
   */
  public int nextInteger(int rangeUpper)
  {
    long hi = seed / radixQuotient;
    long lo = seed - hi * radixQuotient;
    seed = radixConstant * lo - radixRange * hi;
    if (seed == 0) seed = radixRange;

    int result = (int)seed;
    if (result < 0)
      result = -result;

    return result % rangeUpper;
  }

  private static class Memento implements za.co.solmstraining.utils.Memento
  {
    public Memento(long seed) {this.seed = seed;}

    public long getSeed() {return seed;}

    private long seed;
  }

  public za.co.solmstraining.utils.Memento getState()
  {
    return new Memento(seed);
  }

  public void setState(za.co.solmstraining.utils.Memento memento)
  {
    seed = ((Memento)memento).getSeed();
  }

  private static final long radixRange = 0x7FFFFFFF;
  private static final long radixConstant = 0x00000000000041A7;
  private static final long radixQuotient = radixRange / radixConstant;
  private static final long radixRemainder = radixRange % radixConstant;
  private long seed = 0;
}
