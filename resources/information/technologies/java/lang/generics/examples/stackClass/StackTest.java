package za.co.solms.utils.collections;

public class StackTest
{
  public void run()
  {
    Stack<String> names = new Stack<String>();
    names.push("Peter");
    names.push("Tito");
    names.push("Sarie");
    names.push("Mohammed");
    names.push("Vinolia");

    System.out.println("Names:");
    System.out.println(names);

    try
    {
      String name = names.pop();
      System.out.println("popped " + name);
    }
    catch (Stack.EmptyException e)
    {
      System.out.println("Stack is empty.");
    }

    System.out.println("Names:");
    System.out.println(names);
    
    names.flush();
    System.out.println("Names:");
    System.out.println(names);

    try
    {
      String name = names.pop();
      System.out.println("popped " + name);
    }
    catch (Stack.EmptyException e)
    {
      System.out.println("Stack is empty.");
    }

    Stack<Double> data = new Stack<Double>();
    data.push(3.1415926);
    data.push(1.2345e67);
    System.out.println("Stack depth = " + data.depth());
    try
    {
      double x = data.pop();
      System.out.println("Popped " + x + " off stack.");
    }
    catch (Stack.EmptyException e)
    {
      System.out.println("Stack is empty.");
    }
  }

  public static void main(String[] args)
  {
    new StackTest().run();
  }
}