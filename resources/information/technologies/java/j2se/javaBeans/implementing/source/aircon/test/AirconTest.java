package aircon.test;

import aircon.*;

public class AirconTest
{
	public static void main(String[] args)
	{
		// Create a new settings object
		AirconditionSettings aircon = new AirconditionSettings();
		
		// Attach an observer to the settings, which may e.g. adjust
		// the airconditioning hardware to attain the requested temp
		aircon.addObserver( new AirconObserver() );
		
		// Create a small GUI that allows the user to change the temp
		new AirconTempController( aircon );
	}
}