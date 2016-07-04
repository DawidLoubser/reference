
class A
{
  public String identify() {return "A";}
}

class B extends A
{
  public String identify() {return "B";}
}

class Classifier
{
  String identify(A x) {return "A";}
  String identify(B x) {return "B";}
}

public class MethodsResolvedStatically
{
  public static void main(String[] args)
  {
    Classifier classifier = new Classifier();
    A a = new B();
    System.out.println("a.identify(): " + a.identify());
    System.out.println("classifier.identify(a): " 
      + classifier.identify(a));
  }
}

