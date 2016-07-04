\begin{verbatim}
import java.io.*;
import java.net.*;

public class WebClient
{
  public static void main(String args[])
  {
    try
    {
      Socket httpSocket = new Socket("www.SolmsTraining.co.za", 80);
      System.out.println("Established socket to " + httpSocket);

      getPage(httpSocket);
    }
    catch (UnknownHostException uhe)
    {
      System.out.println("UnknownHostException: " + uhe);
    }
    catch (IOException ioe)
    {
      System.err.println("IOException: " + ioe);
    }
  }

  public static void getPage(Socket httpSocket)
  {
    try
    {
      PrintStream out = new PrintStream(httpSocket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader
                                (httpSocket.getInputStream()));

      out.print("GET / HTTP/1.0\r\n\r\n");

      String responseLine;
      while ((responseLine = in.readLine()) != null)
        System.out.println(responseLine);

      out.close();
      in.close();
      httpSocket.close();
    }
    catch (IOException ioe)
    {
      System.out.println("IOException: " + ioe);
    }
  }
}
\end{verbatim}
