package my.client;

import za.co.example.unitconverter.Distance;
import za.co.example.unitconverter.SupportedUnitsRequest;
import za.co.example.unitconverter.UnitConversionRequest;
import za.co.example.unitconverter.UnitConversionResponse;
import za.co.example.unitconverter.soap.UnitConverter;
import za.co.example.unitconverter.soap.UnitConverterService;
import za.co.example.unitconverter.soap.UnsupportedUnit;

public class ClientApp 
{
	public static void main(String[] args) 
	{
		UnitConverterService service = new UnitConverterService();
		UnitConverter unitConverter = service.getUnitConverterSOAP();
		
		Distance from = new Distance();
		from.setUnit("km");
		from.setMagnitude(1.0);
		
		UnitConversionRequest req = new UnitConversionRequest();
		req.setFrom( from );
		req.setTo( "mile" );
		
		try
		{
			UnitConversionResponse res = unitConverter.convert( req );
			System.out.println( "Converted distance: " + res.getResult() );
		}
		catch ( UnsupportedUnit e )
		{
			System.err.println("** Unsupported unit! " + e.getMessage());
			System.err.println("Supported unit are: " + unitConverter.getSupportedUnits( new SupportedUnitsRequest() ));
		}
	}
}