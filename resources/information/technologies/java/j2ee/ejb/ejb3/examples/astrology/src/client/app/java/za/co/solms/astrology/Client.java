package za.co.solms.astrology;

import java.util.GregorianCalendar;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import za.co.solms.astrology.AstrologyFeeder.WontSayException;

public class Client
{
  public void run()
  {
    // Construct input to astrology service
    AstrologyFeeder.PersonInfo personInfo = new AstrologyFeeder.PersonInfo(
        "Nostradamus",
        "nostradamus@prophetsUnlimited.com",
        new GregorianCalendar(1503, 11, 14)
            .getTime());

    try
    {
      // Look up astrology service bean
      InitialContext ctx = new InitialContext();
      String jndiName = "AstrologyFeederBean/remote";
      AstrologyFeeder feeder = (AstrologyFeeder) ctx
          .lookup(jndiName);

      // Request business service
      String foreCast = feeder
          .generateForecast(personInfo);
      System.out.println("Forecast: "
          + foreCast);
    } catch (WontSayException e)
    {
      System.out
          .println("This is bad, they are to scared to tell.");
    } catch (NamingException e)
    {
      e.printStackTrace();
    }
  }


  public static void main(String[] args)
  {
    new Client().run();
  }

}