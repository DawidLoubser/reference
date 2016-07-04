package travel.cost.service;

import travel.cost.TravelCost;
import travel.cost.TravelCostRequest;
import travel.cost.VehicleCostParameters;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace="urn:vehicles:cost",name="TravelCostService")
@Stateless
@Remote({TravelCost.class})
public class TravelCostBean implements TravelCost
{
    public double getTravelCost(TravelCostRequest costRequest)
    {
        VehicleCostParameters vehPars = costRequest.getVehicleCostParameters();
        
        return  (vehPars.getFinanceFactor()
                + vehPars.getFuelFactor()*costRequest.getFuelCostPerLiter()
                + vehPars.getRepairsFactor()
                + vehPars.getTyresFactor()) * costRequest.getDistanceInKm();
    }
}


