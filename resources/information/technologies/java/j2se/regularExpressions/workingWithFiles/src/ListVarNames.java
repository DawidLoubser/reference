import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.FileChannel.*;
import java.nio.charset.*;
import java.util.regex.*;

/** A small program to list all the variables (and their types)
 * by applying a regular expression pattern to a .java source
 * file. */
public class ListVarNames
{
  public static void main(String[] args) throws IOException
  {
    // Check program arguments
    if (args.length < 1)
    {
      System.err.println("Usage: <java source file>");
      return;
    }
    
    // Establish a NIO channel to the file
    FileChannel channel = new FileInputStream( args[0] ).getChannel();
    
    // Map it to memory
    ByteBuffer buf = channel.map( MapMode.READ_ONLY, 0, channel.size());
    
    // Decode it as UTF-8 text (the encoding used for .java files)
    CharBuffer text = Charset.forName("UTF-8").newDecoder().decode(buf);
    
    // Find all variable declarations (this is an overly-simplistic
    // pattern which may not be 100% accurate)
    String varPattern = "([\\w]+)\\s+([\\w]+)\\s*(;|=)";
    Matcher m = Pattern.compile( varPattern).matcher( text );
    while (m.find())
    {
      String varName = m.group(2);
      String type = m.group(1);
      System.out.println( varName + " (of type " + type + ")");
    }
  }
}