package exam.wordcounter.java5;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Counts the words in a String, producing
 * a lexicographically ordered map. Many approaches would be suitable,
 * but this class has been implemented as a 'utility class' -
 * it does not have state, hence no need to create an instance.
 * 
 * The algorithm used values simplicity over performance, and makes use
 * of Java 1.4 and 1.5 features.
 *
 * @author Dawid Loubser (Solms TCD)
 * @version 0.1 (Nov 4, 2005)
 */
public class WordCounter
{
	/** Disable instantiation */
	private WordCounter(){}

	
	/** Produces a map of word counts, found in the given input String. Uses
	 * regular expressions to match on proper words only.
	 * @param input String, with space-separated words
	 * @return A sorted map
	 * @throws IllegalArgumentException If the given string is empty
	 */
	public static SortedMap<String,Integer> countWords( String input ) throws IllegalArgumentException
	{
		if (input == null || input.length() < 1)
		{
			throw new IllegalArgumentException("Input contains no characters");
		}
		
		// Create the map in which word -> count pairs will be stored
		SortedMap<String,Integer> map = new TreeMap<String,Integer>();
		
		// Match on words using regular expressions
		Pattern p = Pattern.compile("[\\w]+");
		Matcher m = p.matcher( input );
		while (m.find())
		{
			String word = m.group();
			Integer count = map.get( word );
			
			// Increment the word's word count (if it's not there,
			// count will be 0 because of autoboxing
			if (count == null)
				map.put( word, 1 );
			else
				map.put( word, ++count );
		}
		
		return map;
	}
}