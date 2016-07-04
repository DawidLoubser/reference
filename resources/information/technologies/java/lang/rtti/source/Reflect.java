
import java.lang.reflect.*;

public class Reflect
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    Class classDescriptor = Class.forName(args[0]);

    Constructor[] constructors = classDescriptor.getConstructors();

    if (constructors.length > 0)
      System.out.println("Constructors = ");
    for (int i=0; i<constructors.length; ++i)
      System.out.println(constructors[i]);

    Method[] methods = classDescriptor.getMethods();

    if (methods.length > 0)
      System.out.println("\nMethods = ");
    for (int i=0; i<methods.length; ++i)
      System.out.println(methods[i]);

    Field[] fields = classDescriptor.getFields();

    if (fields.length > 0)
      System.out.println("\nFields = ");
    for (int i=0; i<fields.length; ++i)
      System.out.println(fields[i]);
  }
}

