
package TestUtils; 

import java.io.*;
import java.util.*;

/**
 * This class provides a collection of directory utilities like obtaining all
 * subdirectories of a specified root directory.
 *
 * @author Fritz Solms
 * @version 1.0 final
 */
public class DirectoryUtils
{
  /**
   * Returns a collection of strings representing the names of the 
   * subdirectories of a specified root directory.
   */
  static public Collection getAllSubDirectoryNames (File root)
                              throws IOException
  {
    Collection subDirectories = getAllSubDirectories(root);
    
    TreeSet directoryNames = new TreeSet();
    
    Iterator iter = subDirectories.iterator();
    while(iter.hasNext())
      directoryNames.add(((File)iter.next()).getCanonicalPath());

    return directoryNames;
  }

  /**
   * Returns a collection containing all subdirectories of a specified
   * root directory as instances of @link{java.io.File java.io.File}.
   */
  static public Collection getAllSubDirectories(File root)
  {
    if (!root.exists())
      throw new IllegalArgumentException("root dir does not exist.");
 
    TreeSet directories = new TreeSet();

    getDirectories(root, directories);

    return directories;
  }

  static private void getDirectories (File root, Collection directories)
  {
    File[] fileList = root.listFiles();
    for (int i=0; i<fileList.length; i++) 
      if (fileList[i].isDirectory()) 
        {
          directories.add(fileList[i]);
          getDirectories(fileList[i], directories);
        } 
  }

  /**
   * Returns a collection containing all files acceptable to a specified 
   * @link{java.io.FilenameFilter java.io.FilenameFilter} which reside either
   * within a specified root directory or within one of its subdirectories.
   * <P>
   * The returned collection of a specified contains instances of
   * @link{java.io.File java.io.File}.
   */
  static public Collection getAllFiles(File root, FilenameFilter filter)
  {
    Collection directories = getAllSubDirectories(root);
    
    Collection files = new TreeSet();

    File[] filesInDir = root.listFiles(filter);
    for (int i=0; i<filesInDir.length; ++i)
      files.add(filesInDir[i]);

    Iterator iter = directories.iterator();
    while (iter.hasNext())
    {
      File directory = (File)iter.next();
      filesInDir = directory.listFiles(filter);
      for (int i=0; i<filesInDir.length; ++i)
        files.add(filesInDir[i]);
    }
    return files;
  }
  
  /** Given a collection of files and/or directories in a sub directory
   *  of SCMB.QAD, this method returns a collection of corresponding class,
   *  interface and/or package names in Java naming format.
   */
  public static Collection convertFilesToPackageOrClassNames
                                             (Collection files)
                                   throws IOException            
  {
    TreeSet packageOrClassNames = new TreeSet();
    
    Iterator iter = files.iterator();
    while(iter.hasNext())
    {
      File directory = (File)iter.next();
      String packageOrClassName 
        = convertFileToPackageOrClassName(directory);
      if (packageOrClassName != null)  
        packageOrClassNames.add(packageOrClassName);
    }
    return packageOrClassNames;  
  }

  /** Given a file or directory which is within the SCMB.QAD hierarchy
   *  this method returns the corresponding class, interface or package name.
   */
  public static String convertFileToPackageOrClassName (File file)
                         throws IOException
  {
    if (file.isDirectory())
      return convertFileNameToPackageName(file.getPath());
    else  
      return convertFileNameToClassOrInterfaceName(file.getPath());
  }
                                                 
  /** Given a file or directory name which is within the SCMB.QAD hierarchy
   *  this method returns the corresponding class, interface or package name.
   */
  public static String convertFileNameToPackageName (String fileName)
  {
    try
    {
      return fileName.replace(fileSeparator, '.').substring(2,fileName.length()); 
    }
    catch (StringIndexOutOfBoundsException e) {return null;}
  }  
                                                 
  /** Given a file or directory name which is within the SCMB.QAD hierarchy
   *  this method returns the corresponding class, interface or package name.
   */
  public static String convertFileNameToClassOrInterfaceName (String fileName)
  {
    String className = convertFileNameToPackageName(fileName);
    
    int length = className.length();
    try
    {
      String extension = className.substring(length-6,length);
      if ((extension.equals(".java")) || (extension.equals(".class")))
        return className.substring(0,length-6);
      else
        return null;  
    }  
    catch (StringIndexOutOfBoundsException e) {return null;}
  }  
  	
 private static final char fileSeparator = System.getProperty("file.separator").charAt(0);
}  
