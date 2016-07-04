package exam.wordcounter;

import java.util.*;

/** Counts the words (separated by spaces) in a String, producing
 * a lexicographically ordered map. Many approaches would be suitable,
 * but this class has been implemented as a 'utility class' -
 * it does not have state, and there is no need to create an instance.
 *
 * NOTE: This class explicitly does not make use of Java 5 features.
 * 
 * @author Dawid Loubser (Solms TCD)
 * @version 0.1 (Nov 4, 2005)
 */
public class WordCounter
{
	/** Disable instantiation */
	private WordCounter(){}

	
	/** Produces a map of word counts, found in the given input String
	 * @param input String, with space-separated words
	 * @return A sorted map, with String keys and Integer values
	 * @throws IllegalArgumentException If the given string is empty
	 */
	public static SortedMap countWords( String input ) throws IllegalArgumentException
	{
		if (input == null || input.length() < 1)
		{
			throw new IllegalArgumentException("Input contains no characters");
		}
		
		// Create the map in which word -> count pairs will be stored
		SortedMap map = new TreeMap();
		
		// In Java 1.4 and 1.5, it's recommended to use String.split()
		// (with regular expressions) rather than StringTokenizer
		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens())
		{
			String word = st.nextToken();
			
			// If the map contains the word, increment it's count, otherwise
			// add it. java.lang.Integer is immutable, so we have to create
			// a new one every time, or create our own e.g. WordCount class
			Integer count = (Integer)map.get( word );
			if (count == null)
			{
				map.put( word, new Integer(1) );
			}
			else
			{
				map.put( word, new Integer( count.intValue() + 1 ) );
			}
		}
		
		return map;
	}
}
