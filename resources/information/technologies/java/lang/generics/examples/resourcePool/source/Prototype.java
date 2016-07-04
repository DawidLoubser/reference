package za.co.solms.utils.resource.pooling;

public interface Prototype<T> extends Cloneable
{
  public T clone();
}
