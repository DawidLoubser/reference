package za.co.solms.astrology;

import java.util.Random;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import za.co.solms.astrology.AstrologyFeeder;

@Stateful
@Remote({ AstrologyFeeder.class })
public class AstrologyFeederBean implements AstrologyFeeder
{

  @Interceptors(
  { za.co.solms.comms.email.NotifyForecastRequestInterceptor.class })
  public String generateForecast(PersonInfo personInfo) 
  throws WontSayException
  {
    String period = forecastPeriods[rng
        .nextInt(forecastPeriods.length)];

    String outcome = forecastOutcomes[rng
        .nextInt(forecastOutcomes.length)];

    if (outcome
        .endsWith("no longer with us"))
      throw new WontSayException();

    return "During the next " + period
        + " you will " + outcome + ".";
  }

  private final static String[] forecastPeriods =
  { "week", "fortnight", "month", "quarter",
      "year" };

  private static final String[] forecastOutcomes =
  {
      "find new love",
      "find more love",
      "make an important career move which will change your life",
      "make a career move which will not change anything",
      "experience some personal loss," + 
            " but come out of this as a stronger person",
      "step in front of a bus and be no longer with us" };

  private Random rng = new Random();
}