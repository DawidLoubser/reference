package za.co.solms.math.algebra.linear;

public class Vector<T extends Scalar> implements Cloneable
{
  public Vector(int length)
  {
    elements = new T[length];
    for (int i=0; i<elements.length; ++i)
      elements[i] = new T();
  }

  public Vector<T> add(Vector<T> arg)
  {
    Vector<T> result = (Vector<T>)this.clone();
    for (int i =0; i<elements.length; ++i)
      result.elements[i] = (T)result.elements[i].add(arg.elements[i]);
    return result;
  }

  public T dotProduct(Vector<T> arg)
  {
    T result = new T();
    for (int i=0; i<elements.length; ++i)
     result.add(((T)elements[i]).multiply((T)arg.elements[i]));
    return result;
  }

  public void assign(Vector<T> arg)
  {
    for (int i =0; i<elements.length; ++i)
      this.elements[i] = (T)arg.elements[i].clone();
  }

  public Vector<T> clone()
  {
    Vector<T> copy = null;

    try
    {
      copy = (Vector<T>)super.clone();
    }
    catch (CloneNotSupportedException e) {/* never thrown */}
      
    copy.assign(this);
    return copy;
  }

  private T[] elements;
}