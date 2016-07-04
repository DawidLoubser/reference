package za.co.solms.example.motors.impl;

import java.util.GregorianCalendar;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeFactory;
import za.co.solms.example.motors.*;
import za.co.solms.example.motors.soap.MotorValueEstimator;
import za.co.solms.example.motors.soap.UnknownMotorVehicle;

/** A basic implementation of the motor value estimator contract */

@WebService(endpointInterface="za.co.solms.example.motors.soap.MotorValueEstimator")
public class BasicMotorValueEstimator implements MotorValueEstimator 
{
	public MotorValueEstimationResult estimateMotorValue( MotorValueEstimationRequest request )
	throws UnknownMotorVehicle 
	{
		// Simple bogus implementation of value guessing logic
		double v = Math.random() * 100000.0;
		
		if (request.getCondition().equals( Condition.LIKE_NEW))
		{
			v *= 1.5;
		}
		else if (request.getCondition().equals( Condition.WORN))
		{
			v *= 0.5;
		}
		
		
		MonetaryValue retailValue = new MonetaryValue();
		retailValue.setAmount( v );
		retailValue.setCurrency( "ZAR" );
		
		MonetaryValue tradeValue = new MonetaryValue();
		tradeValue.setAmount( v * 0.8 ); // 80% of retail
		tradeValue.setCurrency( "ZAR" );
		
		MotorValueEstimationResult result = new MotorValueEstimationResult();
		result.setEstimatedRetailValue( retailValue );
		result.setEstimatedTradeValue( tradeValue );
		
		try
		{
			result.setEstimationDateTime( DatatypeFactory.newInstance().newXMLGregorianCalendar( new GregorianCalendar() ) );
		}
		catch (Exception e)
		{
			throw new RuntimeException("Failed to set XML date/time", e);
		}
		
		return result;
	}
}
