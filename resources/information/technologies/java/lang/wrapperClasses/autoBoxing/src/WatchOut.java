package za.co.solmstcd.autobox;

/**
 * 
 * @author mandy
 * @version 1.0
 * 
 * The class WatchOut demonstrates why you cannot  
 * perform a comparison as if the Integer objects were 
 * primitives.
 */
public class WatchOut 
{

	public static void main(String[] args) 
	{
		/*Without autoboxing*/
		Integer i1 = new Integer( 2);
		Integer i2 = new Integer( 2);
		System.out.println( i1==i2); //displays false

		/*With autoboxing*/
		Integer k1 = 150;
		Integer k2 = 150;
		System.out.println( k1==k2); //displays false
		  
		/*object comparison*/
		System.out.println( k1.equals(k2)); //displays true
	}

}
