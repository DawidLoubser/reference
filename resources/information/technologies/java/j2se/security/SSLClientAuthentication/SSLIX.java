<![CDATA[
/*
 * SSLIX.java
 * The following is only required if the certificate on the server is self-
 * signed. In this case the certificate has to be imported into a keystore and 
 * given trusted permissions. It is also possible to add the Certificate 
 * Authorities Certificate to the keystore to address this problem .
 *
 * The Certificate and Key were generated with
 *  cd /etc/httpd/cont/
 *  make SOMETHING.crt
 *
 * This generates a keypair as well as a certificate
 *
 * To import this certificate into the default keystore used the following 
 * command :
 * 
 *    keytool -import -keystore "<JAVA_HOME>\jre\lib\security\cacerts" 
 *            -alias teddy 
 *            -storepass changeit         [This is the default]
 *            -file ssl.crt\SOMETHING.crt [The certificate  file]
 */
import java.net.*;
import javax.net.ssl.*;
import java.io.*;
import java.util.*;
import java.security.*;
/**
 *
 * @author  Alex
 */
public class SSLIX
{ 
  /** Creates a new instance of SSLIX */
  public SSLIX()
  {
  } 
  public void connect() throws Exception
  {
    URL url = new URL("https://teddy.earth");
    System.out.println("Connecting to Host : ["+url.getHost()+"]");
    System.setProperty("javax.net.ssl.keyStore","butterflyKS");
    System.setProperty("javax.net.ssl.keyStorePassword","changeit");
    System.out.println("KeyStore   : "+System.getProperty("javax.net.ssl.keyStore"));
    System.out.println("TrustStore : "+System.getProperty("javax.net.ssl.trustStore"));
    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
    connection.setDoOutput(false); // true for POST, false for GET
    connection.setDoInput(true);
    connection.setRequestMethod("GET");
    connection.setUseCaches(false);
    connection.setAllowUserInteraction(true);
    String aLine=null;
    InputStreamReader inReader = new InputStreamReader(connection.getInputStrea());
    BufferedReader aReader = new BufferedReader(inReader);
    while ((aLine = aReader.readLine()) != null)
      System.err.println(aLine);
    aReader.close();
    connection.disconnect();
  }
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    try
    {
      SSLIX ix = new SSLIX();
      ix.connect();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
]]>
