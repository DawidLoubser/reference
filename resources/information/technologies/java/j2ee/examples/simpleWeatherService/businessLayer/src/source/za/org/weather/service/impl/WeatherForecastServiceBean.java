package za.org.weather.service.impl;

import java.util.*;
import javax.ejb.*;
import za.org.weather.service.*;
import za.org.weather.service.WeatherForecast.Conditions;

/** A simple (useless) implementation of a weather forecast service 
 * as an EJB3 Stateless Session Bean. This service randomly produces
 * forecasts, but in reality might make use of service provider
 * objects which could be injected into it at runtime.
 */
@Stateless
@Local({WeatherForecastService.class})
@Remote({WeatherForecastService.class})
public class WeatherForecastServiceBean implements WeatherForecastService
{

  public List<WeatherForecast> getAllForecasts(
      Locality place, Calendar startDate)
  throws UnknownLocalityException
  {
    /* Test if we support this locality */
    if (! knownPlaces.contains( place.getName() ))
        throw new UnknownLocalityException( place );
    
    /* Create a random number of forecasts */
    int numForecasts = random.nextInt(5);
    List<WeatherForecast> list = new ArrayList<WeatherForecast>();
    
    for (int i = 0; i <= numForecasts; i++)
    {
      // Work out some random temps
      double maxTemp = 25 + ((random.nextDouble() * 10) - 5);
      double minTemp = 15 + ((random.nextDouble() * 10) - 5);
      
      // Date (create new, and increment according to day)
      Calendar d = (Calendar)startDate.clone();
      d.roll( Calendar.DATE, i);
      
      // Random conditions
      Conditions conditions = WeatherForecast.Conditions.values()
      [ random.nextInt( WeatherForecast.Conditions.values().length ) ];
      
      list.add( new WeatherForeCastImpl( 
          conditions, d, place, maxTemp, minTemp));
    }
    
    return list;
  }

  
  /* Random temperature support etc */
  private Random random = new Random();
  
  /* Some hard-coded 'known' places */
  private static Set<String> knownPlaces = new HashSet<String>();
  static
  {
    knownPlaces.add("Aberdeen");
    knownPlaces.add("Bloemfontein");
    knownPlaces.add("Cape Town");
    knownPlaces.add("Delmas");
    knownPlaces.add("East-London");
    knownPlaces.add("Johannesburg");
    knownPlaces.add("Pretoria");
  }
}