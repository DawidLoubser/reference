import java.io.*;
import java.net.*;

class EchoHandlerThread extends Thread
{
   EchoHandlerThread(Socket clServSock, int nthreads)
     { clientServerSocket = clServSock; threadNo = nthreads; }

   public void run()
   {
      try
         {
            BufferedReader in = new BufferedReader(
             new InputStreamReader(clientServerSocket.getInputStream()));
            PrintStream out =
             new PrintStream(clientServerSocket.getOutputStream());

        out.println("Hello! Enter CIAOU to close connection.\r");

        boolean done = false;
        while(!done)
        {
          String str = in.readLine();
          if (str == null)
             done = true;
          else
             {
                out.println("Echo (thread " + threadNo + "):" + str);
                if (str.toUpperCase().trim().equals("CIAOU"))
                   done = true;
             }
        }
        clientServerSocket.close();
      }
      catch ( Exception error )
      {
        System.out.println(error);
      }
   }

   private static final int myPort = 8101;
   private Socket clientServerSocket;
   private int threadNo;
}

public class MultiThreadEchoServer
{
   public static void main(String[] args)
   {
      int nthreads = 1;
      try
         {
            ServerSocket server = new ServerSocket(myPort);
            
            System.out.println("Server waiting for connections on port " + myPort);

            while (true)
            {
               Socket clientServerSocket = server.accept();
               System.out.println("New client connected: client No = " +
                                          nthreads);
               new EchoHandlerThread(clientServerSocket, nthreads).start();
               nthreads++;
            }
         }
      catch (IOException exception) { System.out.println(exception); }
   }

   private static final int myPort = 8101;
 }