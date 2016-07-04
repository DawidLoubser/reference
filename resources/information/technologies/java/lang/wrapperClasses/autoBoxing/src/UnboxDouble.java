package za.co.solmstcd.unbox;
/**
 * 
 * @author mandy
 * @version 1.0
 * 
 * The UnboxDouble class demonstrates a unboxing on an Double 
 * object into a double primitive.
 * This class gets the vat on an amount.
 */

public class UnboxDouble 
{
	public static void main(String[] args) 
	{
		//20.55 is autoboxed into a Double object
		Double price = 20.55; 
		
		//price is unboxed into a primitive double		
		System.out.println("The vat equals: R" + 
			getVat(price));		
	}
	
	public static double getVat(double amount)
	{
		return (amount*0.14);
	}

}
