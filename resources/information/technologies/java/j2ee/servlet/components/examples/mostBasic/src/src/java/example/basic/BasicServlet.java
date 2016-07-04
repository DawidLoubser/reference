package example.basic;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** This is a basic servlet to generate some information, and hand
 * it to a JSP for rendering.
 * @author Solms TCD
 */
public class BasicServlet extends HttpServlet
{
	/** Upon initialisation, record date/time */
	public void init() throws ServletException
	{
		birth = new Date();
	}
	
	/** Handle a GET request from a client */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException
	{
		// Make birth date, as well as current date, available
		// to JSP page
		Date now = new Date();
		request.setAttribute("servletBirth", birth);
		request.setAttribute("servletNow", now);
		
		// Dispatch to JSP
		RequestDispatcher dispatch = 
			request.getRequestDispatcher("/dates.jspx");
		dispatch.forward(request, response);
	}
	
	// Store creation date / time
	private Date birth;
}
