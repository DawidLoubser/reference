
import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Encrypter
{
  public static void main(String[] args) 
                      throws IOException, GeneralSecurityException
  {
    if (args.length != 3)
      {
        System.out.println
          ("USAGE: java Encrypt <encrypt/decrypt> <source> <destination>");
        return;
      }
      
    int mode = Cipher.DECRYPT_MODE;
    if (args[0].equals("encrypt"))
      mode = Cipher.ENCRYPT_MODE;
    
    InputStream inStream;
    try
    {
      inStream = new FileInputStream(args[1]);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Could not find input file: " + args[1]);
      return;
    }          
          
    OutputStream outStream;
    try
    {
      outStream = new FileOutputStream(args[2]);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Could create output file: " + args[2]);
      return;
    }          
    
    Encrypter encrypter = new Encrypter();
    
    System.out.println("Enter pass-phrase/password");
    String passPhrase 
      = new BufferedReader(new InputStreamReader(System.in)).readLine();
    
    encrypter.encrypt(inStream, outStream, passPhrase, mode);
  }
  
  private Cipher createCipher(String passPhrase, int mode)
                  throws GeneralSecurityException
  {
    final String algorithm = "PBEWithMD5AndDES";
    
    PBEKeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray());
    
    SecretKeyFactory keyFactory 
      = SecretKeyFactory.getInstance(algorithm);
    
    SecretKey key = keyFactory.generateSecret(keySpec);
    
    MessageDigest messageDigester = MessageDigest.getInstance("MD5");
    
    messageDigester.update(passPhrase.getBytes());
    
    byte[] digest = messageDigester.digest();
    byte[] salt = new byte[8];
    for (int i=0; i<8; ++i)
      salt[i] = digest[i];
    
    PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 20);
    
    Cipher cipher = Cipher.getInstance(algorithm);
    
    cipher.init(mode, key, parameterSpec);
    
    return cipher;
  }
  
  public void encrypt(InputStream inStream, OutputStream outStream,
                      String passPhrase, int mode) 
                        throws IOException, GeneralSecurityException
  {
    Cipher cipher = createCipher(passPhrase, mode);
    
    CipherInputStream cin = new CipherInputStream(inStream, cipher);
    BufferedOutputStream out = new BufferedOutputStream(outStream);
    
    final int bufferSize = 8;
    byte[] buffer = new byte[bufferSize];
   
    try
    { 
      int numBytesRead = 0;
      do
      {
        numBytesRead = cin.read(buffer);
        if (numBytesRead > 0)
          out.write(buffer,0,numBytesRead);
      }
      while (numBytesRead == 8);
    }
    catch (EOFException e) {}  
    
    cin.close();
    out.close();
  }      
}

