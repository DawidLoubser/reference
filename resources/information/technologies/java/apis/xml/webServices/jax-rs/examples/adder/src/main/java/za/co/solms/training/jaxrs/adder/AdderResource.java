package za.co.solms.training.jaxrs.adder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/adder")
public class AdderResource
{
	@GET
	@Produces("text/plain")
	public String add(@QueryParam("x") String x, @QueryParam("y") String y)
	{
		return "Adder result: " + x + " + " + y + " = " 
			+ adder.add(Double.parseDouble(x), Double.parseDouble(y));
	}
	
	@GET
	@Produces("application/xml")
	public AdderResult add(@QueryParam("x") double x, @QueryParam("y") double y)
	{
		return new AdderResult(x, y, adder.add(x,y));
	}

	@XmlRootElement
	static public class AdderResult
	{
		public AdderResult() {}
		
		public AdderResult(double x, double y, double result)
		{
			setX(x);
			setY(y);
			setResult(result);
		}
		
		public double getX()
		{
			return x;
		}

		public void setX(double x)
		{
			this.x = x;
		}

		public double getY()
		{
			return y;
		}

		public void setY(double y)
		{
			this.y = y;
		}

		public double getResult()
		{
			return result;
		}

		public void setResult(double result)
		{
			this.result = result;
		}

		private double x, y, result;
	}
	
	private static Adder adder = new Adder();
}
