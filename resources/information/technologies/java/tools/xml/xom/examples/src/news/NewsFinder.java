package news;

import java.io.*;
import java.net.*;
import java.util.*;
import nu.xom.*;

/**
 * A simple component to look up news headlines from a RSS XML 
 * document, using the XOM API and XPath
 * @author Solms TCD
 */
public class NewsFinder
{
  /**
   * Return the latest headlines as a List of strings
   * @param uri The URI of the RSS XML containing news items
   */
  public List<String> getHeadlines(String uri) throws IOException,
      URISyntaxException, ParsingException
  {
    // Get an inputstream from the URI (could be file or network)
    InputStream in = new URI(uri).toURL().openStream();

    try
    {
      // Build a XOM tree
      Builder builder = new Builder();
      Document news = builder.build(in);

      // Query it (using XPath) for the news headlines only.
      // We specifically construct a non-namespace-aware XPath to
      // bypass the differences in RSS versions
      Nodes results = news
          .query("//*[local-name()='item']/*[local-name()='title']");

      // Build the list of headlines
      List<String> headlines = new ArrayList<String>();
      for (int x = 0; x < results.size(); x++)
      {
        // Add the text contents of each title to the list
        headlines.add(results.get(x).getValue());
      }
      return headlines;
    } finally
    {
      // Try to close the stream
      try
      {
        in.close();
      } catch (Exception ignored)
      {
      }
    }
  }
}