package example.basic;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** This servlet echoes all request parameters back to the
 * user as plain text. It does so for both GET and POST
 * requests.
 * @author Solms TCD
 */
public class EchoServlet extends HttpServlet
{	
	/** Handles both GET and POST requests */
	private void process(HttpServletRequest request, 
			HttpServletResponse response) throws 
			ServletException, IOException
	{
		// Indicate to the client the type of content we're generating
		response.setContentType( "text/plain" );
		
		// Get a handle to the writer (to send text to client)
		PrintWriter out = response.getWriter();
		
		// Get all parameters and send them to the client
		Enumeration params = request.getParameterNames();
		out.println("Received the following request parameters:\n");
		while ( params.hasMoreElements() )
		{
			String name = (String)params.nextElement();
			String value = request.getParameter( name );
			out.println(" " + name + " : " + value);
		}
	}
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws 
			ServletException, IOException
	{
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws 
			ServletException, IOException
	{
		process(request, response);
	}
}