public class GreeterTest
{
  public static void main( String[] args )
  {
      // Create a greeter with a name
      Greeter g = new Greeter("Jack");
      
      // Ask it to greet three times
      for (int i=0; i<3; i++)
      {
        g.greet();
      }
  }
}