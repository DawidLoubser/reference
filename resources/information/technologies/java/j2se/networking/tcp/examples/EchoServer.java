import java.io.*;
import java.net.*;

class EchoServer
{                    
  public static void main(String[] args)
  {
     ServerSocket server = null;
     System.out.println("Echo Server");
     try
     {
        server = new ServerSocket(myPort);
     }  
     catch (IOException exception)
     {
       System.out.println("Could not create server socket on port : " + myPort );
       return;
     }
     
     Socket socket = null;
     try
     {
            
        System.out.println("Server waiting for connections on port " + myPort);
        socket = server.accept();
     }  
     catch (IOException exception)
     {
       System.out.println("IO exception when accepting client connection: " 
         + exception);
       return;
     }
        
     try
     {
        BufferedReader in  = new BufferedReader(new InputStreamReader 
                                    (socket.getInputStream()));
        PrintStream     out = new 
                PrintStream(socket.getOutputStream());
        
        boolean done = false;
        while(!done)
        {
          String str = in.readLine();  
          System.out.println(str);
          if (str == null) 
             done = true;
          else  
             {
                out.println("Echo: " + str);
                if (str.toUpperCase().trim().equals("CIAOU"))
                   done = true;
             }
        }      
      }
      catch (IOException error ) 
      {
        System.out.println(error);
      } 
      finally
      {
        try
        {
          socket.close();
        }
        catch (IOException e) {}
      }  
   }
   private static final int myPort = 8101;
 }   