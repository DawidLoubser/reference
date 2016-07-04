/*
 * TravelCostRequest.java
 *
 * Created on November 7, 2006, 6:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package travel.cost;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author fritz
 */
@XmlRootElement(name="travelCostRequest")
@XmlType(name="TravelCostRequest", 
    propOrder={"distanceInKm","fuelCostPerLiter","vehicleCostParameters"})
public class TravelCostRequest {
    
    /** Creates a new instance of TravelCostRequest */
    public TravelCostRequest() {}
    
    public double getFuelCostPerLiter() {return fuelCostPerLiter;}
    public double getDistanceInKm() {return distanceInKm;}
    public VehicleCostParameters getVehicleCostParameters() 
    {
        return vehicleCostParameters;
    }
    
    public void setFuelCostPerLiter(double fuelCostPerLiter)
    {
        this.fuelCostPerLiter = fuelCostPerLiter;
    }
    
    public void setDistanceInKm(double distanceInKm)
    {
        this.distanceInKm = distanceInKm;
    }
    
    public void setVehicleCostParameters(VehicleCostParameters vehicleCostParameters)
    {
        this.vehicleCostParameters = vehicleCostParameters;
    }
    
    private VehicleCostParameters vehicleCostParameters;
    private double fuelCostPerLiter, distanceInKm;
    
}
