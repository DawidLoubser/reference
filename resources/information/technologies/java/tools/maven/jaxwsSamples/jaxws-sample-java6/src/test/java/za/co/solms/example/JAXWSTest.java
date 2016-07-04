package za.co.solms.example;

import net.webservicex.Currency;
import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;
import org.junit.Test;


public class JAXWSTest 
{
	@Test
	public void testCurrency()
	{
		Currency from = Currency.USD;
		Currency to = Currency.ZAR;
		
		System.out.printf("Getting conversion rate from %s to %s...\n", from, to);
		CurrencyConvertorSoap cc = new CurrencyConvertor().getCurrencyConvertorSoap();
		
		double rate = cc.conversionRate(from, to);
		
		System.out.printf("From web service: Conversion rate from %s to %s is %f\n", from, to, rate);
	}
}