package my.service.tests;

import static org.junit.Assert.*;
import my.service.UnitConverterImpl;
import org.junit.Before;
import org.junit.Test;
import za.co.example.unitconverter.Distance;
import za.co.example.unitconverter.SupportedUnitsRequest;
import za.co.example.unitconverter.UnitConversionRequest;
import za.co.example.unitconverter.UnitConversionResponse;
import za.co.example.unitconverter.soap.UnitConverter;
import za.co.example.unitconverter.soap.UnsupportedUnit;

public class UnitConverterTest
{
	@Test
	public void testConvert() throws UnsupportedUnit
	{
		// Create request
		// Note: We are assuming meter and millimeter is supported
		Distance from = new Distance();
		from.setUnit("m");
		from.setMagnitude(30.0);
		UnitConversionRequest req = new UnitConversionRequest();
		req.setFrom(from);
		req.setTo("mm");
		
		// Use service
		UnitConversionResponse response = converter.convert( req );
		
		// Make assertions regarding the expected result
		assertEquals(30000.0, response.getResult().getMagnitude(), PREC);
		assertEquals("mm", response.getResult().getUnit());
	}

	@Test
	public void testGetSupportedUnits()
	{
		assertNotNull( converter.getSupportedUnits(new SupportedUnitsRequest()) );
	}
	
	
	@Before
	public void setUp() throws Exception
	{
		converter = new UnitConverterImpl();
	}
	
	private UnitConverter converter;
	private static final double PREC = 0.000001;

}