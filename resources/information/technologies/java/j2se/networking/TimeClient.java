import java.io.*;
import java.net.*;

public class TimeClient
{
  public static void main(String[] args)
  {
     if (args.length < 1)
     {
       System.err.println("Expected: <timeServer>");
       System.exit(1);
     }
     try
     {
        DatagramSocket socket = new DatagramSocket();
        InetAddress timeServer = InetAddress.getByName( args[0] );
        byte[] bytes = new byte[2];
        bytes[0] = 1;
        bytes[1] = 1;
        DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length,
                           timeServer, hostPort);
        socket.send(sendPacket);
        DatagramPacket receivedPacket = new DatagramPacket(new byte[512], 512);
        socket.receive(receivedPacket);
        System.out.write(receivedPacket.getData(), 0,
                                receivedPacket.getLength());
        System.out.println();
     }
     catch ( Exception error )
     {
       System.out.println(error);
     }
   }
   
   private static final int hostPort = 10013;
 }