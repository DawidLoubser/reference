import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/** This class illustrates using a channel to
 * write bytes to a given file.
 */
public class ChannelWriteTest
{
  public static void main(String[] args) throws IOException
  {
    // Check if user provided a file
    if (args.length < 1)
    {
      System.out.println("Usage: ChannelWriteTest <file>");
      System.exit(1);
    }
    
    // Set up a file channel to write to the given filename
    FileOutputStream stream = new FileOutputStream( args[0] ); 
    FileChannel channel = stream.getChannel();
    
    // Create a direct memory buffer to store some numbers
    // This allocates 512 bytes of memory
    ByteBuffer buf = ByteBuffer.allocateDirect(512);
    		
    // Add some numbers to the buffer
    buf.put((byte)1); buf.put((byte)2); buf.put((byte)3);
    buf.put((byte)4); buf.put((byte)5); buf.put((byte)6);
    
    // Flip the buffer: otherwise it's limit is still set
    // to the original size, which means 512 bytes (most 
    // of which are empty) will be written to the file
    buf.flip();
    		
    // Write the underlying byte buffer out to file
    channel.write( buf );
    
    // Close channel (which also closes underlying stream)
    channel.close();
  }
}