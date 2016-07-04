\begin{verbatim}
import java.net.*;

public class JavasNSLookup
{
  public static int convertSignedToUnsigned(int i)
  {
    return (i+256)%256;
  }  
  
  public static void main(String args[])
  {
    try
     {
       if(args.length!=1)
         {
           System.out.println("Usage: java JavasNSLookupApp hostName/ipAddress");
           return;
        }   
       InetAddress host = InetAddress.getByName(args[0]);
       String hostName  = host.getHostName();
       byte ipAddress[] = host.getAddress();

       System.out.println("Host name: "+hostName);
       System.out.print("IP address: ");

       for(int i=0;i<ipAddress.length;++i)
         System.out.print(convertSignedToUnsigned(ipAddress[i]) + ".");
       System.out.println();
     }
   catch(UnknownHostException ex)
     {
       System.out.println("Unknown host");
       return;
     }
  }
}
\end{verbatim}
