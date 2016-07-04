public class GreeterTest
{
  public static void main(String[] args)
  {
    // Create an instance using the Compiler-supplied default constructor
    Greeter g = new Greeter();

    // Three times, send a service request message to the greeter
    for (int i=0; i<3; ++i)
    {
      g.greet();
    }
  }
}
