/*
 * Client.java
 *
 * Created on March 7, 2006, 11:31 PM
 */

package client;

//import common.VehicleCostParameters;
import javax.xml.ws.WebServiceRef;
import travel.cost.TravelCost;

public class Client
{/*
    @WebServiceRef(wsdlLocation="http://localhost:8080/TravelCost/TravelCostService?WSDL")
    static TravelCostService service;
    
    public static void main(String[] args)
    {
        new Client().run();
    }
    
    public void run()
    {
        try
        {
            TravelCost port = service.getTravelCostPort();
            //VehicleCostParameters pars = new VehicleCostParameters(133,11.98,23.67,20.17);
            double pars=1.2;
            double cost = port.getTravelCost(pars,6.06,100);
            System.out.println("TravelCost result = " + cost);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }*/
}
