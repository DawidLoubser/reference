
package rmi.countValue;

public interface Counter extends java.io.Serializable
{
  public void increment();

  public int getCount();
}

