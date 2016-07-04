package util;

import util.List;

public class LinkedList<T> implements List.Mutable<T> 
{
	public LinkedList() {head = null;}
	
	public void add(T o, int position) throws OutOfBoundsException 
	{
		assert checkState();
		int orgSize = size;
		
	  if ((position < 0) || (position > size))
		  throw new OutOfBoundsException();
	  
	  Node<T> newNode = new Node<T>(o);
	  if (head == null)
		  head = newNode;
	  else
	  {
		  Node<T> currentNode = head;
		  try
		  {
			  currentNode = getNodeAt(position);
		  }
		  catch (OutOfBoundsException e)
		  {}
		  currentNode.getPrevious().setNext(newNode);
		  newNode.setPrevious(currentNode.getPrevious());
		  currentNode.setPrevious(newNode);
		  newNode.setNext(currentNode);

		  if (position == 0)
			  head = newNode;
	  }
	  ++size;
	  
	  assert checkState();
	  assert (size == orgSize + 1);
	  try
	  {
		  assert (getPositionOf(newNode) == position);
	  }
	  catch (NodeDoesNotExist e)
	  {
		  assert false : "Node not inserted";
	  }
	}
	
	public int getSize()
	{
		return size;
	}

	public void remove(int position) throws OutOfBoundsException 
	{
		assert checkState() : "Inconsistent state";
		int orgSize = size;
		
		  if ((position < 0) || (position >= size))
			  throw new OutOfBoundsException();
		  
		  Node<T> currentNode = getNodeAt(position);

		  if (currentNode == head)
			  if (size == 1)
				  head = null;
			  else
				  head = head.getNext();
		  
		  currentNode.getNext().setPrevious(currentNode.getPrevious());
		  currentNode.getPrevious().setNext(currentNode.getNext());
		  
		  --size;
		  
		  assert (size == orgSize-1);
		  
		  assert checkState() : "Inconsistent state";
	}
	
	private int getPositionOf(Node<T> node) throws NodeDoesNotExist
	{
		Node<T> currentNode = head;
		for (int i=0; i<size; ++i)
		{
			if (currentNode == node)
				return i;
			currentNode = currentNode.getNext();
		}
		throw new NodeDoesNotExist();
	}
	
	private Node<T> getNodeAt(int position) throws OutOfBoundsException
	{
		  if ((position < 0) || (position >= size))
			  throw new OutOfBoundsException();
		  
		  Node<T> currentNode = head;
		  for (int i=0; i<position; ++i)
			  currentNode = currentNode.getNext();
		  
		  return currentNode;
	}

	public T get(int position) throws OutOfBoundsException 
	{
		return getNodeAt(position).getContent();
	}
	
	private boolean checkState()
	{
		if (head == null)
			return size == 0;
		
		int numElements = 1;
		Node currentNode = head;
		while (currentNode.getNext() != head)
		{	
			currentNode = currentNode.getNext();
			++numElements;
		}
		return numElements == size; 
	}

	public Iterator.Mutable<T> iterator() 
	{
		return new Iterator.Mutable<T>()
		{

			public void remove()  throws List.OutOfBoundsException
			{
				if (currentNode == null)
					throw new OutOfBoundsException();

				Node<T> newCurrentNode = null;
				if (currentNode.getNext() != head)
				  newCurrentNode = currentNode.getNext();
				
				try
				{
					int pos = getPositionOf(currentNode);
					LinkedList.this.remove(pos);
				}
				catch (Exception e)
				{
					assert false : "Should never have occured";
				}
				currentNode.getPrevious().setNext(currentNode.getNext());
				currentNode.getNext().setPrevious(currentNode.getPrevious());
				
				currentNode = newCurrentNode;
			}

			public boolean hasNext() 
			{
				return (currentNode != null);
			}

			public T next() throws List.OutOfBoundsException
			{
				if (currentNode == null)
					throw new List.OutOfBoundsException();
				
				T result = currentNode.getContent();
				if (currentNode.getNext() != head)
					currentNode = currentNode.getNext();
				else
					currentNode = null;
				
				return result;
			}
			
			private Node<T> currentNode = head;
		};
	}
	
	private Node<T> head;
	private int size = 0;

	private static class Node<Tp>
	{
		public Node(Tp content)
		{
			this.content = content;
			previous = this;
			next = this;
		}
		
		public Tp getContent() {return content;}
		
		public Node<Tp> getNext() {return next;}
		public Node<Tp> getPrevious() {return previous;}
		
		public void setNext(Node<Tp> nextNode) {next = nextNode;}
		public void setPrevious(Node<Tp> previousNode) {previous = previousNode;}
		
		private Tp content;
		private Node<Tp> next, previous;
	}
}
