package za.co.solms.average;

import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import za.co.solms.average.*;

/**
 * A servlet client for the Average stateless session bean
 */
public class AverageServlet extends HttpServlet
{

  /**
   * Gets handle to bean and looks up bean from naming service.
   */
  public void init() throws ServletException
  {
    super.init();
    try
    {
      InitialContext ctx = new InitialContext();
      logger.info("Got handle to naming context.");
      averageCalculator 
        = (AverageLocal)ctx.lookup("AverageBean/local");

      logger.info("Looked up average bean");
    } 
    catch (NamingException e)
    {
      throw new RuntimeException(e);
    }
  }


  /**
   * Upon POST request, demarshalls the HTTP request parameters, 
   * calls the session bean and generates the HTTP response.
   */
   protected void doPost(HttpServletRequest request, 
                      HttpServletResponse response)
      throws ServletException, IOException
  {
    /*
     * De-marshalling request:
     */
    String dataString = request.getParameter("data");
    Collection<Double> data = new LinkedList<Double>();
    StringTokenizer tokenizer 
      = new StringTokenizer(dataString, " ,;");
    while (tokenizer.hasMoreTokens())
      data.add(new Double(tokenizer.nextToken()));


    /*
     * Delegating business logic to session bean:
     */
    Double result = averageCalculator.average(data);

    /*
     * Storing result in request context:
     */
    request.setAttribute("average", result);
    
    /*
     * Delegating response view to JSP:
     */
    RequestDispatcher dispatcher 
      = getServletContext( ).getRequestDispatcher("/averageResult.jsp");
    dispatcher.forward(request, response);
  }

  private AverageLocal averageCalculator;
  private Logger logger = Logger.getLogger(AverageServlet.class);
}