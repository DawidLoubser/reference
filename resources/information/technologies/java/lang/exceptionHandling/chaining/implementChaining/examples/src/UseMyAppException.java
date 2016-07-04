package za.co.solmstcd.chaining;

import java.io.*;

public class UseMyAppException 
{	
	
	/**
	 * the method getDB() tries to read a file
	 * 
	 * @throws MyAppException
	 */
	public static void getDB(String filename) throws MyAppException {

	        try {
	            FileReader freader = new FileReader(filename);	    
	            freader.read();
	        }
	        catch(Exception e)
	        {
	                  throw new MyAppException
	                ("Failed to read the database file: " + 
	                		filename, e);
	        }
	        catch (Throwable t)
	        {
	        	/*
	        	 * Throwable is also caught
	        	 * Customised exception with low-level 
	        	 * message is thrown
	        	 */
	        	t.printStackTrace();
	        }
	    
	    }
	
	public static void main(String[] args)
	{
        String filename = "myDB.db";
        try
        {
        	getDB(filename);
        }
        catch(MyAppException e)
        {
        	e.printStackTrace();
        }
	}
}
	
