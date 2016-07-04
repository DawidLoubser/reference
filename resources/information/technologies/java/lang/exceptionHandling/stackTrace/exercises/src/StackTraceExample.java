package za.co.solmstcd.chaining;

import java.io.*;


public class StackTraceExample 
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
	        catch (Throwable t)
	        {
	        	/*
	        	 * Throwable is caught instead of Exception
	        	 * Customised exception with low-level 
	        	 * message is thrown
	        	 */
	            throw new MyAppException
	                ("Failed to read the database file: " + 
	                		filename, t);
	        }
	    }
	
	public static void main(String[] args)
	{        
                try
                {
                	//expects a parameter from the user 
                	getDB("myDB.db");
                }
                catch(MyAppException e)
                {
                	//We can access specific information 
                	//about the stack trace
                	displayStackTraceInfo(e);
                	//performing a printStackTrace is more
                	//useful after 1.4
                	e.printStackTrace();
                	
                }
	}
	
	public static boolean displayStackTraceInfo(Throwable ex)
	{
		if (ex == null) 
		{
			System.out.println("Parsed parameter is null");
			return false;
		}

		StringBuffer sbMsg = new StringBuffer();

		StackTraceElement[] stackElements = ex.getStackTrace();

		sbMsg.append("There are  " + stackElements.length + 
		" stack trace elements");
					
		for (int x = 0; x < stackElements.length; x++) {
			sbMsg.append("\n" + stackElements[x].toString());

		}

		System.out.println(sbMsg.toString());
		return true;
	} 
}
	
