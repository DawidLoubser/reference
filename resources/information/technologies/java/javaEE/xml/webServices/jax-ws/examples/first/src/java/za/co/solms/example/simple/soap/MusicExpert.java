package za.co.solms.example.simple.soap;

import javax.jws.*;
import javax.jws.soap.*;
import javax.xml.bind.annotation.*;
import za.co.solms.example.simple.*;

/**
 * An expert that can classify music        
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 */
@WebService(name = "MusicExpert", targetNamespace = "http://solms.co.za/example/simple")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
public interface MusicExpert 
{
    @WebMethod
    @WebResult( name = "classifySongResponse", 
                targetNamespace = "http://solms.co.za/example/simple", 
                partName = "classifySongResponse" )
    public ClassifySongResponse classifySong(
        @WebParam( name = "classifySongRequest", 
      		       targetNamespace = "http://solms.co.za/example/simple", 
      		       partName = "classifySongRequest")
        ClassifySongRequest classifySongRequest)
        throws UnknownArtistOrSong;
}