public interface Iterator {...}				
				
public class LinkedList implements List	
{
  public Iterator iterator()
  {
    return new LLIterator();
  }
  
  ...
  
  private class LLIterator implements Iterator
  {
    public LLIterator()
    {
      current = head;
        // possible because inner class instance has 
        // handle to outer class instance
    }
    
    public Object next()
    {
      Object content = current.getContent();
      current = current.next();
      return content;
    }    
    ...
  }
  
  private static class Node
  {
    ...
    
    private Object content;
    private Node next, previous;
  } 
  
  private Node head;
}        			   