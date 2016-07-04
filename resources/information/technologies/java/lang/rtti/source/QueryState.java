
package za.co.SolmsTraining.rtti;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class QueryState
{
  public static void main(String[] args)
  {
    new QueryState().run();
  }

  public void run()
  {
    class SomeClass
    {
      public SomeClass(int i, int j, int k, Date date)
      {
        this.i = i;
        this.j = j;
        this.k = k;
        this.date = date;
      }

      public int getI() {return i;}
      public int getJ() {return j;}
      public int getK() {return k;}
      public Date getDate() {return date;}

      private int i;
      protected int j;
      int k;
      Date date;
    }

    Object o = new SomeClass(17, 18, 19, new Date());

    reportPublishedState(o);
    reportFields(o);
  }

  public void reportPublishedState(Object o)
  {
    Method[] methods = o.getClass().getMethods();

    for (int i=0; i<methods.length; ++i)
    {
      Method method = methods[i];

      if (method.getName().startsWith("get")
          && (method.getParameterTypes().length == 0))
      {
        try
        {
          System.out.println(method.getName() + "() => "
            + method.invoke(o,null));
        }
        catch (IllegalAccessException e)
        {
          System.out.println("Cannot access query method " + method.getName());
        }
        catch (Exception e)
        {
          System.out.println("Should never be thrown.");
        }
      }
    }
  }

  public void reportFields(Object o)
  {
    Field[] fields = o.getClass().getDeclaredFields();

    for (int i=0; i<fields.length; ++i)
    {
      System.out.print(Modifier.toString(fields[i].getModifiers()) + " "
                       + fields[i].getName() + " => ");
      try
      {
        Object value = fields[i].get(o);
        System.out.println(value);
      }
      catch (IllegalAccessException e)
      {
        System.out.println("not accesible");
      }
    }
  }
}


