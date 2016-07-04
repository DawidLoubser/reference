package za.co.solms.example.simple.client;

import java.util.List;
import za.co.solms.example.simple.*;
import za.co.solms.example.simple.soap.*;
import za.co.solms.example.simple.soap.UnknownArtistOrSong;

public class MusicalExpertApp
{
	public static void main(String[] args)
	{
		// Create an instance of the Service (a proxy factory)
		MusicExpertService service = new MusicExpertService();
		
		// Create a proxy
		MusicExpert musicExpert = service.getMusicExpertSOAP();
		
		// Use the web service via the proxy
		ClassifySongRequest req = new ClassifySongRequest();
		req.setArtistName("Felix Laband");
		req.setSongName("Tomorrow Perhaps");
		
		// Display result
		try
		{
			ClassifySongResponse result = musicExpert.classifySong( req );
			List<MusicalStyle> styles = result.getStyle();
			System.out.println("Song exhibits the following styles: " + styles);
		}
		catch (UnknownArtistOrSong e)
		{
			System.err.println("Unknown: " + e);
		}
	}
}