package util;

public interface Iterator<T> 
{
	public  boolean hasNext();
	
	public T next() throws List.OutOfBoundsException;
	
	public interface Mutable<T> extends Iterator<T>
	{
		public void remove() throws List.OutOfBoundsException;
	}
}
