package rmi.iiop.bank.server;

import javax.naming.Context;
import javax.naming.InitialContext;
import rmi.iiop.bank.*;
import java.util.Properties;
import org.omg.CORBA.ORB;

public class BankServer {
	public static void main(String[] args) {
		try 
		{
		      if (args.length < 1)
		      {
		        System.out.println("Usage: java rmi.iiop.bank.server.BankServer <serverName>");
		        System.exit(0);
		      }  
		     String serverName = args[0];
                Properties iiopProperties = new Properties();
                iiopProperties.put( CONTEXT_NAME, IIOP_STRING );
                iiopProperties.put( URL_NAME, IIOP_URL_STRING );
                InitialContext iiopContext = new InitialContext( iiopProperties );
		
			// Step 1: Instantiate the Bank servant
			System.out.println("Creating Bank.");
			BankImpl bank = new BankImpl();		

			// Step 2: Publish the reference 
			// in the Naming Service using JNDI API
			System.out.println("Binding bank to name: " + serverName);
			/**
			Context initialNamingContext = new InitialContext();
			initialNamingContext.rebind(serverName, bank);**/
			iiopContext.rebind(serverName, bank);
			System.out.println("Bank is now ready");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
    static final String CONTEXT_NAME = "java.naming.factory.initial";
    static final String IIOP_STRING  = "com.sun.jndi.cosnaming.CNCtxFactory";

    static final String URL_NAME = "java.naming.provider.url";
    static final String IIOP_URL_STRING  = "iiop://localhost:1060";
}
