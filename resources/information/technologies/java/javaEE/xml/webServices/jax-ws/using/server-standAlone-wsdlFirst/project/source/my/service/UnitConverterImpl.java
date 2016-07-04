package my.service;

import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import za.co.example.unitconverter.Distance;
import za.co.example.unitconverter.SupportedUnits;
import za.co.example.unitconverter.SupportedUnitsRequest;
import za.co.example.unitconverter.SupportedUnitsResponse;
import za.co.example.unitconverter.UnitConversionRequest;
import za.co.example.unitconverter.UnitConversionResponse;
import za.co.example.unitconverter.UnitValue;
import za.co.example.unitconverter.soap.UnitConverter;
import za.co.example.unitconverter.soap.UnsupportedUnit;

/** A simple implementation of the Unit Converter, which for now only supports distances */
@WebService(endpointInterface="za.co.example.unitconverter.soap.UnitConverter")
public class UnitConverterImpl implements UnitConverter
{
	public UnitConversionResponse convert(UnitConversionRequest req)
	throws UnsupportedUnit
	{
		if (req.getFrom() instanceof Distance)
		{
			Map<String, Double> distanceFactors = unitFactors.get( Distance.class );
			
			// Check pre-conditions
			if ( !distanceFactors.containsKey(req.getFrom().getUnit()) )
			{
				throw new UnsupportedUnit("Unsupported unit: " + req.getFrom().getUnit(), 
						new za.co.example.unitconverter.UnsupportedUnit());
			}
			if ( !distanceFactors.containsKey(req.getTo()) )
			{
				throw new UnsupportedUnit("Unsupported unit: " + req.getTo(), 
						new za.co.example.unitconverter.UnsupportedUnit());
			}
			
			double distanceInMeter = req.getFrom().getMagnitude() / distanceFactors.get( req.getFrom().getUnit() );
			double distanceInReq = distanceInMeter * distanceFactors.get(  req.getTo() );
			Distance result = new Distance();
			result.setUnit( req.getTo() );
			result.setMagnitude( distanceInReq );
			UnitConversionResponse response = new UnitConversionResponse();
			response.setResult( result );
			return response;
		}
		else
		{
			throw new UnsupportedUnit("Only distance supported (for now)", 
					new za.co.example.unitconverter.UnsupportedUnit());
		}
	}
	
	
	public SupportedUnitsResponse getSupportedUnits(SupportedUnitsRequest body)
	{
		SupportedUnitsResponse r = new SupportedUnitsResponse();
		for ( Map.Entry<Class, Map<String,Double>> entry :  unitFactors.entrySet())
		{
			try
			{
				SupportedUnits su = new SupportedUnits();
				su.setValueType( (UnitValue)entry.getKey().newInstance() );
				su.getUnit().addAll( entry.getValue().keySet() );
				r.getSupportdUnits().add( su );
			}
			catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
		return r;
	}
	
	
	// We store all supported units, and their factors to a 'common' unit for each
	// type, e.g. meters for distance, in a map of maps, keyed by UnitValue type (e.g. Distance)
	private static final Map<Class,Map<String,Double>> unitFactors = new HashMap<Class, Map<String,Double>>();
	static
	{
		Map<String, Double> distances = new HashMap<String, Double>();
		distances.put("m", 1.0);
		distances.put("cm", 100.0);
		distances.put("mm", 1000.0);
		distances.put("inch", 39.370079);
		distances.put("foot", 3.2808399);
		unitFactors.put( Distance.class, distances);
		// etc... (perhaps load from file)
	}
}