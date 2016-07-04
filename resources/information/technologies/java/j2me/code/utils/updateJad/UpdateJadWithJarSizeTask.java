<![CDATA[
package za.co.solmstraining.ant.j2me;

import java.io.*;

public class UpdateJadWithJarSizeTask
               extends org.apache.tools.ant.Task
{
	public void setJadfile(File jadFile) {this.jadFile = jadFile;	}

	public void execute() throws org.apache.tools.ant.BuildException
  {
		if(jadFile == null)
			throw new org.apache.tools.ant.BuildException
        ("jadfile attribute required.", location);

    java.util.Properties properties = null;
		try
    {
      properties = readPropertiesFile(jadFile);
    }
    catch (FileNotFoundException e)
    {
      throw new org.apache.tools.ant.BuildException
        ("jadfile " + jadFile + " not found.", location);
    }
    catch (IOException e)
    {
      throw new org.apache.tools.ant.BuildException
        ("I/O exception while reading properties from JAD file.",
         location);
    }

    try
    {
      updateJarSizeProperty(properties, jadFile.getParentFile());
    }
    catch (IOException e)
    {
      throw new org.apache.tools.ant.BuildException
        ("I/O exception while while determining size of jar file.",
         location);
    }

    try
    {
      writePropertiesFileWithColons(jadFile, properties);
    }
    catch (IOException e)
    {
      throw new org.apache.tools.ant.BuildException
        ("I/O exception while writing to jad file.",
         location);
    }

	}

  private void updateJarSizeProperty
                  (java.util.Properties properties,
                   File directoryContainingJar)
                     throws IOException,
                       org.apache.tools.ant.BuildException
  {
    String jarFileName = properties.getProperty( "MIDlet-Jar-URL" );
    if(jarFileName == null)
      throw new org.apache.tools.ant.BuildException
        ("MIDlet-JAR-URL property not set in JAD file.", location );

    File jarFile = new File(directoryContainingJar, jarFileName);

    if(!jarFile.isFile())
      throw new org.apache.tools.ant.BuildException
        ("Invalid JAR file name specified in JAD.", location );

    String jarSize = Long.toString(jarFile.length());

    properties.setProperty(midletJarSizeProperty, jarSize);
  }

  private java.util.Properties readPropertiesFile(File file)
             throws IOException
  {
    java.util.Properties properties = new java.util.Properties();
    FileInputStream in = new FileInputStream(file);
    properties.load(in);
    in.close();
    return properties;
  }

  private void writePropertiesFileWithColons
                 (File file, java.util.Properties properties)
                    throws IOException
  {
    // Properties.store() saves properties in form
    //    key=value
    // Some J2ME tools require format
    //    key: value
    PrintWriter out = new PrintWriter(new FileOutputStream(jadFile));
    java.util.Iterator keys = properties.keySet().iterator();
    while(keys.hasNext())
    {
      String key = (String)keys.next();
      out.print( key );
      out.print( ": " );
      out.println( properties.getProperty( key ) );
    }
    out.close();
  }

	private static final String midletJarSizeProperty = "MIDlet-Jar-Size";

	private File jadFile;
}
]]>
