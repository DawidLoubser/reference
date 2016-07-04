package news;

import java.util.*;

/**
 * A simplistic application to print the latest news headlines.
 * @author Solms TCD
 */
public class Headlines
{
  /** User should either provide a url, or a logical name */
  public static void main(String[] args) throws Exception
  {
    if (args.length < 1)
    {
      System.err.println("Expected: <source>");
      System.err.println("   source: <uri>");
      System.err.println("       or   'slashdot', 'cnn', 'makezine'");
      System.exit(1);
    }

    // If arg is not recognised source, treat as explicit URL
    String url = newsSources.get(args[0]);
    if (url == null)
    {
      url = args[0];
    }

    // Get the news headlines
    NewsFinder finder = new NewsFinder();
    List<String> headlines = finder.getHeadlines(url);

    // Display
    System.out.println("Headlines from " + args[0]);
    System.out.println("as of: " + new Date() + "\n");
    for (String headline : headlines)
    {
      System.out.println("* " + headline);
    }
  }

  // Some common news sources, accessible by keyword
  private static final Map<String, String> newsSources = 
    new HashMap<String, String>();
  static
  {
    newsSources.put("slashdot", "http://rss.slashdot.org/Slashdot/slashdot");
    newsSources.put("cnn", "http://rss.cnn.com/rss/cnn_topstories.rss");
    newsSources.put("makezine", "http://www.makezine.com/blog/index.xml");
  }
}