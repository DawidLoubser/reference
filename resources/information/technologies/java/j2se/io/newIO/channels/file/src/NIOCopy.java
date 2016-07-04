package io;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/** This is a simple file copy program that uses Java NIO to
 * copy between two channels.
 */
public class NIOCopy
{
  public static void main(String[] args) throws IOException
  {
    if (args.length != 2)
    {
      System.out.println("Required: <inputFile> <outputFile>");
      System.exit(1);
    }
    
    // Set up a file channel to the given file
    FileChannel source = 
        new FileInputStream( args[0] ).getChannel();
    
    // Create a channel for the new file
    FileChannel dest = 
        new FileOutputStream(args[1]).getChannel();
    
    // Ask the channel to copy for us
    dest.transferFrom( source, 0, source.size() );
    
    // Close both channels
    source.close();
    dest.close();
  }
}