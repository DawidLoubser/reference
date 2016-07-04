
import java.io.*; import java.net.*;

class EchoClient
{
  static public void main(String[] args)
  {
    try
    {
//      String host   = "www.SolmsTraining.co.za";
      String host = "192.168.1.1";
      int port      = 8101;

//        InetAddress timeServer = InetAddress.getLocalHost();
        InetAddress timeServer = InetAddress.getByName(host);
      Socket socket = new Socket(timeServer,port);

      PrintStream    out = new PrintStream(socket.getOutputStream());
      BufferedReader in  = new BufferedReader(new InputStreamReader
                                    (socket.getInputStream()));

      String userInput = "  ";
      BufferedReader kbd 
        = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Echo Client:");
      System.out.println("Enter CIAOU to terminate.");

      while (!"CIAOU".equals(userInput))
      {
        System.out.print("Now enter text: ");
        userInput = kbd.readLine();

        out.println(userInput);

        String received = in.readLine();
        System.out.println("Received: " + received);
      }
    }
    catch (IOException e) 
    {
      System.out.println("Connection closed"); 
      e.printStackTrace();
    }
  }
}
