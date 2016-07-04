package za.co.solmstcd.autobox;

/**
 * @author mandy
 * @version 1.0
 * 
 * The class AutoBoxInt demonstrates changing between 
 * a primitive int and an Integer object using autoboxing.
 * This class simply prints out the value of the object Integer 
 */

public class AutoBoxInt
{
	public static void main(String[] args) 
	{
		  int x = 100;       	// a primitive int type
		  Integer objectX = x;  // Here x is 'AUTOBOXED' to an Integer
		  
		  System.out.println ("The value of objectX is:"+ 
				  objectX.toString());    
	}
	

}
