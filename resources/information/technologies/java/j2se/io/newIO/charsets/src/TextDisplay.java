import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/** A simple class that decodes and displays text using NIO buffers 
 * and channels. The whole text file is read into 
 * memory at once.
 */
public class TextDisplay
{
    public static void main(String[] args) throws IOException
    {
        // File to show
        if (args.length < 1)
        {
            System.out.println("Usage: TextDisplay <file>");
            System.exit(1);
        }
        
        // Establish a Channel to the file
        FileInputStream fis = new FileInputStream( args[0] );
        FileChannel channel = fis.getChannel();
        
        // Establish a memory mapping onto the file
        ByteBuffer data = channel.map( 
        FileChannel.MapMode.READ_ONLY, 0, channel.size());
        
        // Decode using default encoder
        CharBuffer text = Charset.defaultCharset().decode( data );
        
        // Print to screen (must copy manually, since System.out is not
        // a channel. Note the usage of buffer range fields
        for (int i = text.position(); i < text.limit(); i++)
        {
            System.out.print( text.get() );
        }
 
        System.out.println();
        channel.close();
    }
}