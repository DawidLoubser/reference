package za.co.solms.election.server;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class ElectionServer
{
  /**
   * A thread processing requests from a particular connection. 
   * It extracts requests from a socket, forwards them to a 
   * contoller and send the response back via the socket.
   */
  class ConnectionHandler extends Thread
  {
    /**
     * Creates a connection handler for a provided socket and 
     * a provided controller.
     */
    public ConnectionHandler
             (Socket socket, int connectionNo, Controller controller)
    {
      this.connectionNo = connectionNo;
      this.controller = controller;
      this.socket = socket;
    }
    
    /**
     * The run method for the thread contains the outer 
     * request processing loop.
     */
    public void run()
    {
      try
      {
        BufferedReader in = new BufferedReader(new InputStreamReader
            (socket.getInputStream()));
        PrintStream out = new
            PrintStream(socket.getOutputStream());
        
        while(true)
        {
          String command = in.readLine();
          String response = controller.processCommand(command);
          out.println(response);
        }
      }
      catch ( Exception e )
      {
        System.out.println
          ("Closing connection " + connectionNo + " due to " + e);
      }
      finally
      {
        try
        {
          socket.close();
        }
        catch (Exception e)
        {}
      }
    }
    
    private Socket socket;
    private int connectionNo;
    private Controller controller;
  }
  
  /**
    * The controller demarshals the incoming request, 
    * maps it onto the business logic layer, and
    * marshalls the response back onto the communication protocol.
    */
  class Controller
  {
    /**
     * Creates a controller for a provided election manager.
     */
    public Controller(ElectionManager electionManager)
    {
      this.electionManager = electionManager;
    }
    
    /**
     * the command processing service.
     */
    public String processCommand(String command)
    {
      StringTokenizer tokenizer = new StringTokenizer(command, "|");
      String cmd = tokenizer.nextToken();
      
      if (cmd.equals("addVotes"))
      {  
          String party = tokenizer.nextToken(); 
          electionManager.addVotes
            (party, Integer.parseInt(tokenizer.nextToken())); 
          return "";
      }
      
      if (cmd.equals("getVotes"))    
      {  
          return Integer.toString
            (electionManager.getVotes(tokenizer.nextToken()));
      }
      
      return "Error: illegal command";
    }
    
    private ElectionManager electionManager;
  }
  
  /**
   * The business logic class which maintains the votes 
   * for the various parties.
   */
  class ElectionManager
  {
    /**
      * Adds a number of votes to a party.
      */
    private void addVotes(String party, int numVotes)
    {
      if (votes.get(party) == null)
        votes.put(party, numVotes);
      else
      {
        int currentVotes = votes.get(party);
        votes.put(party, currentVotes + numVotes);
      }
    }
    
    /**
      * Returns the number of votes for a specified party.
      */
    public int getVotes(String party)
    {
      Integer numVotes = votes.get(party);
      
      if (numVotes == null) return 0;
      
      return numVotes.intValue();
    }
    
    private Map<String,Integer> votes 
      = new TreeMap<String,Integer>();
  }
  
  /**
   * The main loop accepting new connections and spawning 
   * threads processing requests off these connections.
   */
  public void run()
  {
    int connectionNo = 1;
    try
    {
      ServerSocket server = new ServerSocket(serverPort);
      
      System.out.println
        ("Waiting for connections on port " + serverPort);
      
      while (true)
      {
        Socket socket = server.accept();
        System.out.println
          ("New connection no = " + connectionNo);
        new ConnectionHandler(socket, connectionNo, controller).start();
        connectionNo++;
      }
    }
    catch (IOException exception)
    { 
      System.out.println(exception); 
      exception.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    new ElectionServer().run();
  }
  
  private static final int serverPort = 12345;
  private ElectionManager electionManager = new ElectionManager();
  private Controller controller = new Controller(electionManager);
}
