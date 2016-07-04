package products;

import java.util.*;

/** Represents a group of products. It is a basic wrapper around a
 * java.util.Collection.
 */
public class Inventory
{    
    /** Default constructor */
    public Inventory()
    {
        products = new HashSet();
    }
    
    /** Add the given product to the inventory. Duplicates are not
     * alowed, because we are using a Set to store them internally.
     */
    public void add( Product p )
    {
        if (p != null)
        {
            products.add(p);
        }
    }

    /** Remove the given product */
    public void remove( Product p )
    {
        products.remove(p);
    }
    
    /** Obtain an iterator to list all the products */
    public Iterator listAll()
    {
        return products.iterator();
    }
    
    /** Enquire how many products are in the inventory */
    public int getProductCount()
    {
        return products.size();
    }
    
    /** String representation: Prints a (very basic) list */
    public String toString()
    {
        String s = "";
        Iterator i = listAll();
        int count = 0;
        while (i.hasNext())
        {
            s += ++count + ".\t" + i.next().toString() +"\n";
        }
        s += "(Total: "+ getProductCount() +")";
        return s;
    }
    
    // Storage for our products
    private Set products;
}