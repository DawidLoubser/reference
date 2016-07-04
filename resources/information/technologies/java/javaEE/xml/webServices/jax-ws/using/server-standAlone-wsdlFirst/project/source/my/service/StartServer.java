package my.service;

import javax.xml.ws.Endpoint;
import za.co.example.unitconverter.soap.UnitConverter;

public class StartServer 
{
	public static void main(String[] args) 
	{
		// URL to expose endpoint (should be same as WSDL's)
		String url = "http://localhost:8080/unitConverter";
		
		// Publish a lightweight endpoint at the given address
		System.out.println("Publishing web service endpoint at: " + url);
		UnitConverter converter = new UnitConverterImpl();
		Endpoint e = Endpoint.publish(url, converter);
		
		/* Program will run until the executor service contained in the endpoint is
		   shut-down or the program is terminated (CTRL+C) */
	}
}