public class Greeter
{
  /** The client must provide a name for the greeter
      being constructed */
  public Greeter( String theName )
  {
      name = theName;
  }
  
  public void greet()
  {
    // A greeter will greet with it's own name
    System.out.println("Hi there, mate. My name is " + name);
  }
  
  // Attributes of a greeter
  private String name;
}