import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.*;

public class TimeServer
{
  public static void main(String[] args)
  {
     try
     {
        DatagramPacket receivedPacket = new DatagramPacket(new byte[512],512);

        DatagramSocket timeServer = new DatagramSocket(timeServerPort);
        while (true)
        {
          timeServer.receive(receivedPacket);

          System.out.println("Received datagram from " +
                                    receivedPacket.getAddress());

          String dateTime = dateFormat.format(new Date());
          byte[] bytes = dateTime.getBytes();

          DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length,
                       receivedPacket.getAddress(), receivedPacket.getPort());

         timeServer.send(sendPacket);
       }
     }
     catch ( Exception error )
     { System.out.println(error); }
   }
   
   private static final int timeServerPort = 10013;
   private static final DateFormat dateFormat 
     = new SimpleDateFormat("HH:mm:ss 'on' d-M-yyyy");
 }