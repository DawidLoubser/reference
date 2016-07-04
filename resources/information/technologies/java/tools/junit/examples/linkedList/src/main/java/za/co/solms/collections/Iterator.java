package za.co.solms.collections;

/**
 * A contract for a simple forward traversing iterator which you can use to step 
 * through a collection.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface Iterator
{
	/**
	 * Returns the current element and sets the iterator to
	 * 
	 * @return the object to which the iterator is pointing prior to
	 * 	the method being called.
	 * @throws InvalidIndexException if the end of the collection has been reached.
	 */
	public Object next() throws InvalidIndexException;
	
	/**
	 * 
	 * @return true if the end of the collection has not been reached and
	 * 	false otherwise.
	 */
	public boolean hasNext();
}
