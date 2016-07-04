package example;

import java.util.Set;

/** Contract that specifies the services offered by a simple
 * unit converter.
 */
public interface UnitConverter
{
  /** Converts the given distance into the equivalent distance
   * in the given unit.
   * @param unit The SI-code (or, if not applicable, the full name)
   * of the desired unit.
   * @throws UnsupportedUnitException If the requested unit, or the
   * unit of the given distance, is not supported.
   */
  public Distance convertDistance(Distance d, String unit)
      throws UnsupportedUnitException;

  /** Provides a set of all the distance units this converter
   * supports. */
  public Set<String> getSupportedDistanceUnits();
}