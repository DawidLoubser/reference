
package TestUtils;

import java.io.*;
import java.util.*;

/**
 * @author Fritz Solms
 * @version 1.0
 *
 * This application searches through the entire library for any test class
 * named <code>xxxTest.class</code>, checks whether it is 
 * <code>Testable</code>, and if so it runs the test without detailed output
 * and reports any exceptions thrown.
 */
public class RunTests
{
  public static void main(String[] args) throws Exception
  {
    String rootDir = ".";
      
    TestClassNameFilter testClassFilter = new TestClassNameFilter();
  
    Collection files 
      = DirectoryUtils.getAllFiles(new File(rootDir), testClassFilter);
      
      System.out.println("files: " + files);

    Collection classNames 
      = DirectoryUtils.convertFilesToPackageOrClassNames (files);      
      
      System.out.println("classNames = " + classNames);
 
    Iterator iter = classNames.iterator();
    while(iter.hasNext())
    {
      String className = (String)iter.next();
      try
      {
        Class testClass = Class.forName(className);
        try
        {
          Testable testable = (Testable)testClass.newInstance();
          try
          {
            testable.runTest();
          }
          catch (TestFailedException e)
          {
            System.out.println("FAILED test:  <" + className + ">.");
            System.out.println(e);
          }    
          System.out.println("Passed test:  <" + className + ">.");
        }
        catch (ClassCastException e)  
        {
          System.out.println("<" + className + "> not testable.");
        } 
      }  
      catch (Exception e) {} 
      catch (Error e) {} 
    }   
  }
}
