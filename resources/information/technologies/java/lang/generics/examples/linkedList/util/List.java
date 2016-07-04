package util;

public interface List<T> 
{
	public T get(int position) throws OutOfBoundsException;
	
	public Iterator<T> iterator();
	
	public interface Mutable<T> extends List<T>
	{
		public void add(T o, int position) throws OutOfBoundsException;
		
		public void remove(int position) throws OutOfBoundsException;
		
		public Iterator.Mutable<T> iterator();
	}
	
	public class OutOfBoundsException extends Exception 
	{
		private static final long serialVersionUID = 200702271203L;
	}
	
	public class NodeDoesNotExist extends Exception 
	{
		private static final long serialVersionUID = 200702271323L;
	}
	
}
