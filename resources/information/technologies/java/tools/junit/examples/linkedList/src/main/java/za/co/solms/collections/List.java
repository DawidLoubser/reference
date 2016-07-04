package za.co.solms.collections;

/**
 * A ordered collection providing random access.
 * 
 * @author fritz@solms.co.za
 *
 */
public interface List
{
	/**
	 * Appends object to end of list. The object may be null.
	 * 
	 * @param o the object to be added
	 */
	public void add(Object o);
	
	/**
	 * Inserts the object o before current index such that index of object equal provided index.
	 * 
	 * @param o
	 * @param index
	 * @throws InvalidIndexException
	 */
	public void insert(Object o, int index)
		throws InvalidIndexException;
	
	/**
	 * Removes the object at the specified index if such an index exists and does nothing
	 * otherwise.
	 * @param index the index of the object to be removed.
	 */
	public void remove(int index);
	
	/**
	 * Remove all occurances of object o in the list.
	 * 
	 * @param o the object to be removed
	 */
	public void remove(Object o);
	
	/**
	 * Returns the object held at the specified position in the list
	 * @param index the index for which the object is to be retrieved.
	 * @return the object at the provided index
	 * @throws InvalidIndexException if the collection does not have an element
	 * 	at the specified index.
	 */
	public Object get(int index) throws InvalidIndexException;
	
	/**
	 * 
	 * @return the number of elements in the list.
	 */
	public int size();
	
	/**
	 * Removes all elements from the list.
	 */
	public void clear();	
}
