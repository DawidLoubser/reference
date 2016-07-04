package za.co.solms.utils.collections;

public class Stack<T>
{
  public void push(T element)
  {
    Node<T> newNode = new Node<T>(element);
    newNode.setNext(head);
    head = newNode;
  }

  public T pop() throws EmptyException
  {
    if (head == null)
      throw new EmptyException();

    T result = head.getContent();
    head = head.next();  
      // Old head becomes unreferenced and garbage collected
    return result;
  }

  public void flush()
  {
    try
    {
      while (true)
        pop();
    }
    catch (EmptyException e) {}
  }

  public int depth()
  {
    int depth = 0;
    Node<T> current = head;
    while (current != null)
    {
      ++depth;
      current = current.next();
    }
    return depth;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    Node<T> current = head;
    while (current != null)
    {
      result.append('[').append(current.getContent()).append(']');
      current = current.next();
    } 
    return result.toString();
  }

  private static class Node<T>
  {
    public Node(T content)
    {
      this.content = content;
    }

    public T getContent() {return content;}

    public Node<T> next() {return next;}

    public void setNext(Node<T> node) {next = node;}

    private Node<T> next;
    private T content;
  }

  public static class EmptyException extends Exception
  {
    public EmptyException() {}
    public EmptyException(String msg) {super(msg);}
  }

  private Node<T> head;
}