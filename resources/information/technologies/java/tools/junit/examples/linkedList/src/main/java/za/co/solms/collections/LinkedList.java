package za.co.solms.collections;

public class LinkedList implements List
{
	public LinkedList() {}
	
	@Override
	public void add(Object o)
	{
		Node newNode = new Node(o);
		
		if (head == null)
			head = newNode;
		else
			insertBeforeNode(newNode, head);
	}
	
	private void insertBeforeNode(Node newNode, Node node)
	{
		newNode.setPrevious(node.previous);
		node.setPrevious(newNode);
		newNode.setNext(node);
		newNode.getPrevious().setNext(newNode);
	}
	
	private Node getNodeAtIndex(int index) throws InvalidIndexException
	{
		if ((head==null) || (index >= size()))
			throw new InvalidIndexException();
		
		Node node = head;
		for (int i=0; i<index; ++i)
			node = node.getNext();
		
		return node;
	}

	@Override
	public void insert(Object o, int index) throws InvalidIndexException
	{
		insertBeforeNode(new Node(o), getNodeAtIndex(index));
	}
	
	private void removeNode(Node nodeToRemove)
	{
		assert nodeToRemove != null;
		assert head != null : "fix this coding bug";
		
		if (nodeToRemove == head)
		{
			head = head.getNext();
			if (head.getNext() == head)
			{
				head = null;
			}
		}
		nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
		nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());		
	}

	@Override
	public void remove(int index) 
	{
		try
		{
			removeNode(getNodeAtIndex(index));
		}
		catch (InvalidIndexException e) {/* ignore if not there */}
	}

	@Override
	public void remove(Object o)
	{		
		Node current = head;
		Node next = current.getNext();
		if (current.getContent() == o)
			removeNode(current);
		
		while (next != head)
		{
			current = current.getNext();
			next = current.getNext();
			if (current.getContent() == o)
				removeNode(current);
		}
	}

	@Override
	public Object get(int index) throws InvalidIndexException
	{
		if (index >= size())
			throw new InvalidIndexException();
		
		return getNodeAtIndex(index).getContent();
	}

	@Override
	public int size()
	{
		if (head == null)
			return 0;

		int size = 0;
		Node current = head;
		do
		{
			++size;
			current = current.getNext();
		}
		while (current != head);
			
		return size;
	}

	@Override
	public void clear()
	{
		head = null;
	}
	
	private Node head = null;

	private static class Node
	{
		public Node(Object o) 
		{
			setContent(o);
			setNext(this);
			setPrevious(this);
		}
		
		public Object getContent()
		{
			return content;
		}
		public void setContent(Object content)
		{
			this.content = content;
		}
		public Node getNext()
		{
			return next;
		}
		public void setNext(Node next)
		{
			this.next = next;
		}
		public Node getPrevious()
		{
			return previous;
		}
		public void setPrevious(Node previous)
		{
			this.previous = previous;
		}
		private Object content;
		private Node next, previous;
	}
	
	private class LinkedListIterator implements Iterator
	{
		public LinkedListIterator() 
		{
			current = head;
		}

		@Override
		public Object next() throws InvalidIndexException
		{
			if (current == null)
				throw new InvalidIndexException();
			
			Object result = current.getContent();
			
			current = current.getNext();
			if (current == head)
				current = null;

			return result;
		}

		@Override
		public boolean hasNext()
		{
			return (current != null) && (current.getNext() != head);
		}
	
		private Node current = null;
	}
	
}
