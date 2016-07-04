package za.co.solms.election.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author fritz
 */
public class ElectionServerTest
{
  
  public ElectionServerTest()
  {
  }
  
  public void run()
  {
    try
    {
      InetAddress timeServer = InetAddress.getByName(host);
      Socket socket = new Socket(timeServer,serverPort);

      PrintStream    out = new PrintStream(socket.getOutputStream());
      BufferedReader in  = new BufferedReader(new InputStreamReader
                                    (socket.getInputStream()));
      
      String request="addVotes|Peoples Party|200";
      out.println(request);
      String response = in.readLine();
      System.out.println(response);
      
      request="addVotes|Peoples Party|350";
      out.println(request);
      response = in.readLine();
      System.out.println(response);
      
      request="getVotes|Peoples Party";
      out.println(request);
      response = in.readLine();
      System.out.println(response);
      
      socket.close();
    }  
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {new ElectionServerTest().run();}

  private static final String host = "localhost";
  private static final int serverPort=12345;
}
