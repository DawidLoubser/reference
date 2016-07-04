package za.org.weather.presentationLayer;

import java.io.IOException;
import java.util.*;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import za.org.weather.service.*;

/** Use-case controller to do a weather forecast lookup.
 *  Uses the weather forecasting service as specified by the
 *  'WeatherServiceName' initialisation parameter (provided
 *  in the deployment descriptor).
 */
public class ForecastRequestServlet extends HttpServlet
{
  /** During initialisation, we look up our weather
   * forecast service EJB
   */
  public void init() throws ServletException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      weatherService = (WeatherForecastService)
      ctx.lookup( getInitParameter("WeatherServiceName") );
    }
    catch (Exception e)
    {
      throw new ServletException("Failed to look up weather service", e);
    }
  }
	
  /** When receiving a GET request, perform lookup using our model
   * (weather service bean) and dispatch to view (JSP).
   */ 
  protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) 
  throws ServletException, IOException
  {
    try
    {
      // Parse input parameter(s)
      String localityName = request.getParameter("locality");
      Locality locality = new LocalityImpl( localityName );
      Calendar today = Calendar.getInstance();
	
      // Invoke business logic
      List<WeatherForecast> results = 
          weatherService.getAllForecasts(locality, today);
			
      // Dispatch to view
      request.setAttribute("locality", locality);
      request.setAttribute("forecasts", results);
      request.getRequestDispatcher("/weatherForecastView.jspx")
            .forward(request, response);
    }
    catch (UnknownLocalityException e)
    {
      throw new ServletException(e);
    }
  }

  // Our handle to the weather service (through interface only)
  private WeatherForecastService weatherService;
}