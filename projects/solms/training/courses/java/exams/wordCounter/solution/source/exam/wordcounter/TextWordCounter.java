package exam.wordcounter;

import java.io.*;
import java.util.*;
// Comment line below to use Java 1.3-compatible version of 'WordCounter'
import exam.wordcounter.java5.WordCounter;

/** A primitive shell program to count the occurrences of words in
 * a text file, making use of the @link WordCounter utility class.
 * 
 * TODO Add 'save to file' functionality
 * 
 * @author Dawid Loubser (Solms TCD)
 * @version 0.1 (Nov 4, 2005)
 */
public class TextWordCounter
{
	/** Receives two arguments: inputFile, outputFile. If outputfile
	 * is not provided, output is streamed to standard output.
	 */
	public static void main(String[] args)
	{
		// Usage instructions
		if (args.length < 1)
		{
			System.err.println("Usage: TextWordCounter <textFile> [outputFile]");
			return;
		}
		
		// Read file contents and build word map
		SortedMap wordMap = null;
		try
		{
			String text = readTextFile( new File(args[0]) );
			wordMap = WordCounter.countWords( text );
		} 
		catch (IOException e)
		{
			System.err.println("File I/O Error: " + e);
		}

		// Display to screen (file functionality not yet added)
		System.out.println( formatWordmap(wordMap) );
	}
	
	
	/** Reads the contents of the given file into a String */
	private static String readTextFile( File file ) throws IOException
	{
		// Chain a buffered reader (which reads strings) to the file input stream
		FileInputStream fileIn = new FileInputStream( file );
		BufferedReader reader = new BufferedReader( new InputStreamReader(fileIn) );
		
		// Read each line and append to the content
		// A space needs to be inserted where line breaks were, otherwise words
		// may be joined at line boundaries
		StringBuffer content = new StringBuffer();
		String line;
		while ( (line = reader.readLine()) != null )
		{
			content.append( line );
			content.append( " " );
		}
		return content.toString();
	}
	
	
	/** Produces output text for the given word map */
	private static String formatWordmap( SortedMap wordMap )
	{
		Iterator words = wordMap.entrySet().iterator();
		StringBuffer output = new StringBuffer();
		
		// Get each map entry, and append values to output
		while (words.hasNext())
		{
			Map.Entry entry = (Map.Entry)words.next();
			output.append( entry.getKey() + " " + entry.getValue() + "\n" );
		}
		
		return output.toString();
	}
}
