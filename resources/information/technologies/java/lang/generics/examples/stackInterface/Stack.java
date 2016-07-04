package za.co.solms.utils.collections;

public interface Stack<T>
{
  public void push(T element);

  public T pop() throws EmptyException;

  public void flush();

  public int depth();

  public static class EmptyException extends Exception
  {
    public EmptyException() {}
    public EmptyException(String msg) {super(msg);}
  }
}
