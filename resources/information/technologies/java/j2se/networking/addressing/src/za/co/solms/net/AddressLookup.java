package za.co.solms.net;
import java.net.*;

/**
 * A small program to look up one or more addresses provided by the user as
 * arguments.
 */
public class AddressLookup
{
  public static void main(String[] args) throws Exception
  {
    if (args.length < 1)
    {
      System.err.println("Expected: <hostname> [hostname, ...]");
    }

    for (String s : args)
    {
      try
      {
        // Resolve name
        InetAddress address = InetAddress.getByName(s);

        // Display information
        System.out.println("IP:   " + address.getHostAddress());
        System.out.println("Name: " + address.getHostName());
        System.out.println("      " + address.getCanonicalHostName());
      } 
      catch (UnknownHostException e)
      {
        System.err.println("** " + e);
      }

      System.out.println();
    }
  }
}