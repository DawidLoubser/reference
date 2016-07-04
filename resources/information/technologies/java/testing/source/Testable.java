
package TestUtils;

import java.io.PrintStream;
import java.text.DateFormat;

/**
 * This interface must be implemented by any test class used by the testing
 * framework.
 * <P>
 * A 
 *
 * @author Fritz Solms
 * @version 1.0 final
 */
public abstract class Testable
{
  protected Testable() {}
  
  /**
   * Sets the output stream for detailed output to <code>System.out</code>
   * and runs the supplied test, <coderunTest()</code>.
   */
  public void runUserIOTest() throws TestFailedException
  {
    setOutputStream(System.out);
    runTest();
  }  
  
  /**
   * Run the test. this method must be implemented to perform the class-specific
   * testing.
   */
  public abstract void runTest() throws TestFailedException;
  
  /**
   * Set the flag to true if you want the test to supply detailed output to
   * an output stream like the terminal or a file. The default output stream
   * is <code>System.out</code>.
   */
  public void setDetailedOutput(boolean detailedOutput) 
    {this.detailedOutput = detailedOutput;}
    
  /**
   * Specify an output stream to which detailed output should be sent.
   * This method also sets the <code>detailedOutput</code> flag to true.
   */ 
  public void setOutputStream(PrintStream out) 
  {
    this.out = out;
    detailedOutput = true;
  } 
  
  protected boolean isEqual(double x1, double x2)
  {
    if (x1 != 0)
      return ((x1 - x2)/x1 < 1e-15);
    else    
      return ((x1 - x2) < 1e-15);
  }
  
  protected boolean detailedOutput = false;
  
  protected PrintStream out = System.out;
}  
