package za.co.solmstcd.autobox;

/**
 * 
 * @author mandy
 * @version 1.0
 * 
 * The class BeforeAutoBox demonstrates changing between 
 * a primitive int and an Integer object without autoboxing.
 * This class determines the square of an integer.
 */
public class BeforeAutoBox 
{	
	public static void main(String[] args) 
	{
		int x = 10; // a primitive int type
		  
		/*This is how an Integer is created without autoboxing.*/
		Integer objectX = new Integer(x);
		int result = 
		getSquare(objectX.intValue());							
		  
		System.out.println ("The square of "+x+" is: "+ result);
	}

	public static int getSquare(int squareRoot)
	{
		return squareRoot*squareRoot;
	}
}
