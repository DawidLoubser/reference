
package TestUtils;

import java.io.*;
import java.util.*;
import sun.tools.javac.*;

/**
 * @author Fritz Solms
 * @version 1.0 
 *
 * This application searches through an entire directory (package) tree
 * recursively and
 * recompiles all <code>.java</code> files, reporting any errors. The
 * actual compilation errors are written by default into the file 
 * <code>compile.log</code>. Another file name can be specified as
 * the first command-line argument.
 * <P>
 * <H4>Usages</H4>
 * <UL>
 *   <LI>  <code>java CompileAll</code> </LI>
 *     <UL>
 *       <LI> Uses default values: </LI>
 *         <UL>
 *           <LI> the current directory as root directory for package.</LI>
 *           <LI> a default class path set in the code.</LI>
 *           <LI> <code>compile.log</code> as default log file.</LI>
 *         </UL>
 *     </UL>
 *   <LI> <code>java CompileAll rootDir </code>
 *   <LI> <code>java CompileAll rootDir classPath </code>
 *   <LI> <code>java CompileAll rootDir classPath logFile</code>
 * </UL>
 */
public class CompileAll
{
  private String rootDir = ".";
  private File errorLog = new File("compile.log");
      
  private String classPath
    = ".;d:\\fs;"
    + "e:\\jdk1.3\\lib\\tools.jar;";

  public static void main(String[] args) throws Exception
    {new CompileAll(args).compileRecursively();}  

  public CompileAll(String[] args) throws Exception
  {
    try
    {
      if (args.length > 3)
        throw new IllegalArgumentException
          ("Wrong no of command line arguments");
          
      if (args.length >= 1)
        rootDir = args[0];
        
      if (args.length >= 2)
        classPath = args[1];
        
      if (args.length >= 3)
        errorLog = new File(args[2]);
    }
    
    catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Usages:");
      System.out.println("  java CompileLibrary");
      System.out.println("  java CompileLibrary <rootDir>");
      System.out.println("  java CompileLibrary <rootDir> <classPath>");
      System.out.println("  java CompileLibrary <rootDir> <classPath> <logFileName>");
    }  
  }
  
  public void compileRecursively()
  {    
    try
    {
      Collection javaFiles = getJavaFiles();
    
      Collection filesWithErrors = compileJavaFiles(javaFiles, errorLog);
    
      System.out.println("Compiled " + javaFiles.size() + " java files.");
    
      System.out.println("The following " + filesWithErrors.size()
                       + " files contained errors:");
      Iterator iter = filesWithErrors.iterator();
      while (iter.hasNext())
        System.out.println(iter.next());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }  
  }
  
  public Collection compileJavaFiles(Collection javaFiles,
                                     File errorLog)     
                                         throws IOException
  {
    Main compiler = new Main(new FileOutputStream(errorLog),"");
    
    Collection filesWithErrors = new TreeSet();
    String[] args = new String[3];
    args[0] = "-classpath";
    args[1] = classPath;
    
    
    Iterator iter = javaFiles.iterator();
    while(iter.hasNext())
    {
      File javaFile = (File)iter.next();
      try
      {
        String fileName = javaFile.getCanonicalPath();
        System.out.print("Compiling " + fileName + " ... ");
        args[2] = fileName;
         compiler.compile(args);
        //   sun.tools.javac.Main.main(args);
        boolean successful = !compiler.compilationReportedErrors();
        if (successful)
          System.out.println("successful.");
        else
        {
          System.out.println("found errors. (see " + errorLog.getName() + ")");  
          filesWithErrors.add(fileName);
        }  
      }
      catch (IOException e) {e.printStackTrace();}  
    }
    return filesWithErrors;
  }     
    
  public Collection getJavaFiles()
  {
    FilenameExtensionFilter javaFilter = new FilenameExtensionFilter("java");
  
    Collection files 
      = DirectoryUtils.getAllFiles(new File(rootDir), javaFilter);
      
    return files;  
  }
}

