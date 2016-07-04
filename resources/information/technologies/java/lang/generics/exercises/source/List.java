package za.co.solms.generics;

public interface List<T>
{
  public void insert(T element, int after);

  public  T get(int index);

  public void remove(int index);

  public Iterator<T> iterator();
}  
