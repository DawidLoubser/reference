package example.impl;

import java.util.*;
import javax.ejb.*;
import javax.jws.*;
import example.*;

/**
 * A simple EJB (Session Bean) implementation of a unit converter that
 * converts distances by relying on known the known ratios of the
 * various units to a standard unit (meters).
 * 
 * Annotated for web services publication through JAX-WS.
 */
@Stateless
@Local( { UnitConverter.class })
@Remote( { UnitConverter.class })
@WebService
public class UnitConverterBean implements UnitConverter
{
  public @WebResult(name = "convertedDistance")
  Distance convertDistance(@WebParam(name = "distance")
  Distance d, @WebParam(name = "desiredUnit")
  String unit) throws UnsupportedUnitException
  {
    // Check preconditions
    if (!unitConversions.containsKey(unit)) {
      throw new UnsupportedUnitException(unit);
    }
    if (!unitConversions.containsKey(d.getUnit())) {
      throw new UnsupportedUnitException(d.getUnit());
    }

    // Convert (take to standard unit, then to desired unit)
    double inMeter = d.getMagnitude()
        * unitConversions.get(d.getUnit());
    double inDesiredUnit = inMeter / unitConversions.get(unit);
    return new Distance(inDesiredUnit, unit);
  }

  public Set<String> getSupportedDistanceUnits()
  {
    // Return a snapshot of all the supported units as
    // a sorted set
    SortedSet<String> units = new TreeSet<String>();
    units.addAll(unitConversions.keySet());
    return units;
  }

  // Constant set of units (and conversion factor to the standard unit)
  private static final SortedMap<String, Double> unitConversions = 
    new TreeMap<String, Double>();
  static {
    unitConversions.put("m", 1.0);
    unitConversions.put("cm", 0.01);
    unitConversions.put("mm", 0.001);
    unitConversions.put("km", 1000.0);
    // Approximations
    unitConversions.put("mile", 1600.0);
    unitConversions.put("inch", 0.025);
    unitConversions.put("yard", 1.1);
    // etc...
  }
}