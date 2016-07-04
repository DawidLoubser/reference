import java.io.*; 
import java.net.*;

class EchoClient
{
  static public void main(String[] args)
  {
    try
    {
      if (args.length < 1)
      {
        System.err.println("Expected: <host>");
        System.exit(1);
      }
      
      String host   = args[0];
      int port      = 8101;

      InetAddress timeServer = InetAddress.getByName(host);
      Socket socket = new Socket(timeServer,port);

      PrintStream    out = new PrintStream(socket.getOutputStream());
      BufferedReader in  = new BufferedReader(new InputStreamReader
                                    (socket.getInputStream()));

      String userInput = "";
      BufferedReader kbd 
        = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("** Echo Client **");
      System.out.println("   (Enter 'CIAOU' to terminate)");

      while (!"CIAOU".equalsIgnoreCase(userInput))
      {
        System.out.print("Text: ");
        userInput = kbd.readLine();

        out.println(userInput);

        String received = in.readLine();
        System.out.println("Received: " + received);
      }
    }
    catch (IOException e) 
    {
      System.out.println("Connection closed: " + e);
    }
  }
}
