
package TestUtils;

public class TestFailedException extends RuntimeException
{
  public TestFailedException() {}

  public TestFailedException(String msg) {super(msg);}

  public TestFailedException(String msg, Class theClass) 
  {
    super(msg);
    this.theClass = theClass;
  }
  
  public Class getClassWhichFailed() {return theClass;}
  
  private Class theClass;
}  

