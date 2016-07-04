package za.co.solms.generics;

public class DoublyLinkedList<T> implements List<T>
{
  public DoublyLinkedList() {}
 
  public void insert(T content, int before) throws IndexOutOfBoundsException
  {
    if (head == null)
    {
      head = new Node<T>(content);	    
      head.setNext(head);
      head.setPrevious(head);
      ++length;
      return;
    }  
    
    Node<T> currentNode = getNode(before);

    Node<T> newNode = new Node<T>(content);
    currentNode.getPrevious().setNext(newNode);
    newNode.setPrevious(currentNode.getPrevious());
    currentNode.setPrevious(newNode);
    newNode.setNext(currentNode);
    if (currentNode == head)
      head = newNode;
    ++length;
  }  
    
  
  public Node<T> getNode(int index) throws IndexOutOfBoundsException
  {
    if (index >= length)
      throw new IndexOutOfBoundsException();

    Node<T> currentNode = head;
    
    if (index <= length/2)
      for (int i=0; i<index-1; ++i)
	currentNode = currentNode.getNext();
    else
      for (int i=length; i>=index; --i)
	currentNode = currentNode.getPrevious();

    return currentNode;
  }

  
  public T get(int index) throws IndexOutOfBoundsException
  {	  
    return getNode(index).getContent();
  }  
  
  
  public void remove(int index) throws IndexOutOfBoundsException
  {
    Node<T> toBeRemoved = getNode(index);
    toBeRemoved.getPrevious().setNext(toBeRemoved.getNext());
    toBeRemoved.getNext().setPrevious(toBeRemoved.getPrevious());

    if (toBeRemoved == head)
      if (head.getNext() == head)
        head = null;
      else
        head = head.getNext();

    --length;
  } 

  
  public String toString()
  {
    String result = "{";
    if (head != null)
    {
      Node<T> currentNode = head;
      result += currentNode.getContent();
      while ((currentNode = currentNode.getNext())!= head)
      {
        result += "," + currentNode.getContent().toString();
      }
    }
    result += "}";
    return result;
  }  

  
  public Iterator<T> iterator()
  {	  
    return new Iterator<T>()
      {
        public T next() 
		{
		  if (head == null) 
	            throw new IndexOutOfBoundsException();
			  
		  T content = currentNode.getContent();
		  currentNode = currentNode.getNext();
		  return content;
		}
	
		public boolean hasNext()
		{
	          if (head == null) return false;
		  
		  return (currentNode.getNext() != head);
		}  
	      
        private Node<T> currentNode = head;
      };	
  }
	  
  
  private static class Node<T>
  {
    public Node(T content) {this.content = content;}
	  
    public Node<T> getNext() {return next;}

    public Node<T> getPrevious() {return previous;}

    public void setNext(Node<T> nextNode) {next = nextNode;}

    public void setPrevious(Node<T> prevNode) {previous = prevNode;}

    public T getContent() {return content;}

    private Node<T> next, previous;

    private T content;
  }

  
  private Node<T> head;
  private int length = 0;
}
