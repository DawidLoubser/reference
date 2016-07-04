package za.co.solmstcd.autobox;

/**
 * 
 * @author mandy
 * @version 1.0
 *
 * The class OverLoadedMethods demonstrates 
 * the behaviour of autoboxing when overloading methods.
 */

public class OverloadedMethods 
{
	public static void main(String[] args)
	{
		long primitiveLong = 1;
		Integer objectInt = 2;
		int primitiveInt = 2;
		
//		calls first method
		System.out.println(getLongValue (primitiveLong)); 
		
//		calls second method
		System.out.println(getLongValue (objectInt)); 
		
		 /*calls first method
		and does not autobox*/
		System.out.println(getLongValue (primitiveInt));
	}
	
	public static long getLongValue(long l)
	{
		return (l*2);//Multiplies by 2 only if it's a long value
	}
	
	public static long getLongValue(Integer i)
	{
		return (i*3); //Multiplies by 3 only if it's an Integer
	}

}
