
import java.io.*;

public class AccessCheck
{
  public static void main(String[] args)
  {
    System.out.println("OS: " + System.getProperty("os.name"));
    System.out.println("Java version" + System.getProperty("java.version"));

    String fileSeparator = System.getProperty("file.separator");
    System.out.println("File separator: " + fileSeparator);
    
    try
    {
      System.out.println("Java home: " + System.getProperty("java.home"));
    }
    catch (java.security.AccessControlException e)
    {
      System.out.println("Access Control Exception: " + e);
    }
    
    try
    {
      System.out.println("User home: " + System.getProperty("user.home"));
    }
    catch (java.security.AccessControlException e)
    {
      System.out.println("Access Control Exception: " + e);
    }
 
    String fileName = ".." + fileSeparator + "AccessCheck.tst";
    try
    {
      if (fileName == null)
        System.out.println("Property fileName not found.");
      else
        {  
          BufferedReader in = new BufferedReader(new InputStreamReader
                                   (new FileInputStream(fileName)));
          try 
          {                        
            while (in.ready())
              System.out.println(in.readLine());
          }
          catch (EOFException e){}    
        } 
    } 
    catch (FileNotFoundException e)
    {
      System.out.println("Could not find file " + fileName);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (java.security.AccessControlException e)
    {
      System.out.println("Access Control Exception: " + e);
    }
  }
}
