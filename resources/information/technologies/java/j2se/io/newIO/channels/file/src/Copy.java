package io;
import java.io.*;

/** This is a simple streaming file copy program, with buffering to
 * optimise performance. */
public class Copy
{
  public static void main(String[] args) throws IOException
  {
    if (args.length != 2)
    {
      System.out.println("Required: <inputFile> <outputFile>");
      System.exit(1);
    }
    
    FileInputStream in = null;
    try
    {
      in = new FileInputStream(args[0]);
    } catch (IOException e)
    {
      System.out.println("ERROR: Could not open input file");
      e.printStackTrace();
      System.exit(1);
    }
    
    FileOutputStream out = null;
    try
    {
      out = new FileOutputStream(args[1]);
    } catch (IOException e)
    {
      System.out.println("ERROR: Could not create output file");
      e.printStackTrace();
      System.exit(1);
    }
    
    BufferedInputStream bin = new BufferedInputStream(in, 10000);
    BufferedOutputStream bout = new BufferedOutputStream(out, 10000);
    
    try
    {
      int bte = bin.read();
      while (bte != -1)
      {
        bout.write(bte);
        bte = bin.read();
      }
    } catch (EOFException e)
    {
    // End of file reached
    }
    finally
    {
      bin.close();
      bout.close();
    }
  }
}