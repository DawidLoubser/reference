import org.junit.*;

public class TestWithBeforeAfter
{
  @Before // Executed before each test case
  public void setUp() throws Exception
  {
    System.out.println("Creating new subject");
    subject = new ClassUnderTest();
  }

  @After //Executed after each test case
  public void tearDown() throws Exception
  {
    System.out.println("Cleaning up");
    // Clean up resources, release connections, etc
    //...
  }
  
  @Test
  public void testSomething()
  {
    System.out.println("Testing something");
    //...
  }
  
  @Test
  public void testSomethingElse()
  {
    System.out.println("Testing something else");
    //...
  }

  private ClassUnderTest subject;
}