package za.co.solms.training.jaxrs.echo;
 
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
 
// The Echo resource is hosted at the relative URI "/echo"
@Path("/echo")
public class EchoResource 
{
   // Processes HTTP GET requests which accept text/plain
   @GET
   @Produces("text/plain")
   public String echoAsTextMessage
   	(@DefaultValue("Mollo") @QueryParam("msg") String message) 
   {
       return "Echo: " + message;
   }

   // Processes HTTP GET requests which accept application.xml
    @GET
    @Produces("application/xml")
    public ResponseMessage echoAsXmlMessage
    	(@DefaultValue("Mollo") @QueryParam("msg") String message) 
    {
   	 return new ResponseMessage("Echo:", message);
    }
}