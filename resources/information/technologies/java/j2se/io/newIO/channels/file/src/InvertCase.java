import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * A small class to invert the case of the text in a UTF-16 text file 
 * by manipulating a memory-mapped buffer. Note: Specifically only
 * works for UTF (UNICODE) text files.
 */
public class InvertCase
{
  public static void main(String[] args) throws IOException
  {
    if (args.length < 1)
    {
      System.out.println("Usage: InvertCase <UTFTextFile>");
      System.exit(1);
    }
    
    // Create a read/write file channel
    RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
    FileChannel channel = raf.getChannel();
    
    // Map contents into a read/write mapped buffer
    MappedByteBuffer data = 
        channel.map(FileChannel.MapMode.READ_WRITE, 
        0, channel.size());
    
    // Obtain a character view onto the bytes. If the text is not 
    // UTF-16 encoded, additional work needs to be done to do this
    CharBuffer chars = data.asCharBuffer();
    
    // For every byte read, mark the position in the stream,
    // reset to the position, and re-write inverted
    for (int i = 0; i < chars.limit(); i++)
    {
      // Save position
      chars.mark();
      
      // Invert character
      char c = chars.get();
      boolean isUpper = Character.isUpperCase( c );
      char n = isUpper ? Character.toLowerCase( c ) 
                       : Character.toUpperCase( c );
      // Write
      chars.reset();
      chars.put(n);
    }
    
    // Close the channel
    channel.close();
  }
}
