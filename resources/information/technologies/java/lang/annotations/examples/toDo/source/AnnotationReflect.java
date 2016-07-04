package za.co.solms.annotations;

import java.lang.reflect.*;
import java.lang.annotation.*;

public class AnnotationReflect
{
  /** A service to show the details of the given annotation
   * (i.e. its values) as present on the given annotated element.
   */
  public static void reflect
          (AnnotatedElement annotatedElement, Class annotationType)
  {
    System.out.println("Reflecting on " + annotatedElement);
    boolean annotationPresent 
       = annotatedElement.isAnnotationPresent(annotationType);
    if (annotationPresent)
    {
      Annotation annotation 
        = annotatedElement.getAnnotation(annotationType);
      Class annotationClass = annotation.getClass();
      Method[] methods = annotationClass.getMethods();
      for (Method method:methods)
      {
        try
        {
          if ((!method.getName().equals("toString")) &&  
              (!method.getName().equals("hashCode")) &&
              (!method.getName().equals("getClass")))
            System.out.println(method.getName() + ": " 
                + toString(method.invoke(annotation)));
        }
        catch (Exception e) {}
      }
    }
  }


  /** A service to generally traverse a class, and reflect on all
   * it's annotations.
   */
  public static void reflectOnClass
           (Class theClass, Class annotationType)
  {
    System.out.println("Reflecting on class " 
      + theClass.getName());
    System.out.println("================");
    reflect(theClass, annotationType);
    System.out.println();
	
    Constructor[] constructors = theClass.getConstructors();
    for (Constructor constructor:constructors)
      reflect(constructor, annotationType);
    System.out.println();
	
    Method[] methods = theClass.getDeclaredMethods();
    for (Method method:methods)
      reflect(method, annotationType);
    System.out.println();
  }

  
  /** A service to reflect on any critical ToDo annotations */
  public static void hasCriticalToDo(Class theClass)
  {
    System.out.println("Check whether class " 
        + theClass + " has critical ToDo ");
    System.out.println("===================");

    ToDo toDo = (ToDo)theClass.getAnnotation(ToDo.class);
    if ((toDo != null) 
        && (toDo.importance() == ToDo.Importance.CRITICAL))
      reflect(theClass, ToDo.class);
    System.out.println();
  }
  
  
  /** A convenience service to print out String arrays
   * more legibly, and for any other type, as per
   * toString().
   */
  private static String toString(Object o)
  {
    if (o instanceof String[])
    {
      StringBuffer result = new StringBuffer("{|");
      for (String s:(String[])o)
        result.append(s).append("|");
      result.append('}');
      return result.toString();
     }
     return o.toString();
   }
  

  /** Main program, which expects a set of class names as arguments */
  public static void main(String[] args)
  {
    for (String arg: args)
    {
      try
      {
        // Use the class loader to programatically load a class by name
        Class theClass = Class.forName(arg);
        
        // Display general ToDo annotations info for the class
        reflectOnClass(theClass, ToDo.class);
        
        // Display info on any 'ciritcal' ToDo annotations
        hasCriticalToDo(theClass);
      }
      catch (ClassNotFoundException e)
      {
        System.out.println(arg + " not found.");
      }
    }
  }
}