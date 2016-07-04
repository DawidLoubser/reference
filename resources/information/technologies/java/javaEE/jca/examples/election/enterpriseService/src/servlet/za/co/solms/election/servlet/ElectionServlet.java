package za.co.solms.election.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

import za.co.solms.election.sessionBean.contract.ElectionLocal;

/**
 * A servlet processing the user events received from the
 * election client view (JSP).
 *
 * @author fritz@solms.co.za
 */
public class ElectionServlet extends HttpServlet
{
  
  /**
   * Creates a new instance of ElectionServlet
   */
  public ElectionServlet()
  {
  }
  
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
      electionConnection 
        = (ElectionLocal)ctx.lookup(ElectionLocal.class.getName());

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
    String party = request.getParameter("party");
    String action = request.getParameter("action");

    /*
     * Delegating business logic to session bean:
     */
    int numVotes = -1;
    if (action.equalsIgnoreCase("getVotes"))
    {
      try
      {    
        logger.info("Requesting the number of votes for " + party);
        numVotes = electionConnection.getVotes(party);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }	      
    }    
    else if (action.equalsIgnoreCase("addVotes"))
    {
      try
      {    
        try
        {
          numVotes = Integer.parseInt(request.getParameter("numVotes"));
          logger.info("Adding " + numVotes + " to " + party);
          electionConnection.addVotes(party,numVotes);
          numVotes = electionConnection.getVotes(party);
        }
        catch (Exception e) {logger.info("Invalid input");}
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }	      
    }    

    /*
     * Storing result in session context:
     */
    request.setAttribute("numVotes", new Integer(numVotes));
    request.setAttribute("party", party);
    
    /*
     * Delegating response view to JSP:
     */
    RequestDispatcher dispatcher 
      = getServletContext().getRequestDispatcher("/ElectionClient.jsp");
    dispatcher.forward(request, response);
  }

  private ElectionLocal electionConnection;

  private Logger logger = Logger.getLogger(ElectionServlet.class.getName());

}

