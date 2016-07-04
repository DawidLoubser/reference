package za.org.example.web;

import java.io.*;
import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import za.org.example.*;

/** Front-controller for the 'view one day forecast' use case. */
 public class OneDayForecastServlet extends javax.servlet.http.HttpServlet
 {
	/** If a valid locality name is passed as parameter, invokes the
	 * business service (EJB) and hand resulting data to JSP. If not, 
	 * sends client to a suitable input view */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
	  String localityName = request.getParameter("locality");
		if ( localityName == null)
		{
		  request.getRequestDispatcher("/oneDaySelection.xhtml")
            .forward(request, response);
		}
		else
		{
		  try
		  {
		    WeatherForecast wf = weatherForecaster.getOneDayForecast( localityName );
		    request.setAttribute("forecast", wf);
		    request.getRequestDispatcher("/weatherForecastDisplay.jspx")
		    .forward(request, response);
		  }
		  catch (UnknownLocalityException e)
		  {
		    dispatchToInputView(request, response);
		  }
		  catch (NoForecastAvailableException e)
		  {
		    request.getRequestDispatcher("/noForecastAvailable.jspx")
        .forward(request, response);
		  }
		}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
  throws ServletException, IOException 
  {
	  // Both GET and POST are handled identically for this use-case
    doPost(request, response);
  }   
  
	
	@EJB
	private WeatherForecaster weatherForecaster;
}