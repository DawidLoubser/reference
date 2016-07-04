import java.io.*;
import java.net.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/** Illustrates basic JAXP Transformation */
public class TransformExample
{
  public static void main(String[] args)
  throws TransformerException, IOException
  {
    // Get a transformer factory
    TransformerFactory factory = TransformerFactory.newInstance();
    
    // Locate our XSLT style sheet (e.g. using the class loader to get
    // it relative to the classpath, regardless of whether the app
    // is packaged as a JAR, or in an open directory structure)
    StreamSource xslt = new StreamSource( 
        TransformerTest.class.getClassLoader()
        .getResourceAsStream("my/cool/templates.xsl") );
    
    // Create a Transformer using my XSLT
    Transformer transformer = factory.newTransformer( xslt );
    
    // Set up source and result (for example, transform an RSS feed)
    Source source = new StreamSource( 
        new URL("http://example.com/news.rss").openStream() );
    Result result = 
      new StreamResult( new File("/home/baz/todaysNews/news.xhtml") );
    
    // Perform transformation
    transformer.transform( source, result );
  }
}