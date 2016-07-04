package za.co.solms.ejb3.sessionBean.stateless.average;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import za.co.solms.ejb3.sessionBean.stateless.average.AverageLocal;

/**
 * @author fritz@solms.co.za
 * 
 * A servlet client for the Average stateless session bean
 */
public class AverageServlet extends HttpServlet
{
  /**
   * Processing HTTP Get requests.
   */
  protected void doGet(HttpServletRequest req, 
                       HttpServletResponse resp)
      throws ServletException, IOException
  {
    doit(req, resp);
  }

  /**
   * Processing HTTP Post requests.
   */
  protected void doPost(HttpServletRequest req, 
                        HttpServletResponse resp)
      throws ServletException, IOException
  {
    doit(req, resp);
  }

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
        = (AverageRemote)ctx.lookup(AverageRemote.class.getName());

      logger.info("Looked up average bean");
    } 
    catch (NamingException e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Demarshalls the HTTP request parameters, calls the session bean 
   * and generates the HTTP response.
   * 
   * @param req
   *          the request
   * @param resp
   *          the response
   * @throws ServletException
   * @throws IOException
   */
  protected void doit(HttpServletRequest request, 
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

    String action = request.getParameter("action");

    /*
     * Delegating business logic to session bean:
     */
    double result = 0;
    if (action.equals("Average"))
      result = averageCalculator.average(data);

    /*
     * Storing result in session context:
     */
    request.setAttribute("average", new Double(result));
    
    /*
     * Delegating response view to JSP:
     */
    RequestDispatcher dispatcher 
      = getServletContext( ).getRequestDispatcher("/averageResult.jsp");
    dispatcher.forward(request, response);
  }

  private AverageRemote averageCalculator;

  private Logger logger = Logger.getLogger(AverageServlet.class);
}
