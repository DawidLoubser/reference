/*
 * VehicleCostParameters.java
 *
 * Created on November 7, 2006, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package travel.cost;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author fritz@solms.co.za
 */
@XmlRootElement(name="vehicleCostParameters")
@XmlType(name="VehicleCostParameters", propOrder={"financeFactor", "fuelFactor", "repairsFactor", "tyresFactor"})
public class VehicleCostParameters {
    
    public VehicleCostParameters() {}
    
    /** Creates a new instance of VehicleCostParameters */
    public VehicleCostParameters(
            double financeFactor,
            double fuelFactor, 
            double repairsFactor, 
            double tyresFactor) 
    {
        this.financeFactor = financeFactor;
        this.fuelFactor = fuelFactor;
        this.repairsFactor = repairsFactor;
        this.tyresFactor = tyresFactor;
    }
    
    @XmlElement(required=true)
    public double getFinanceFactor() {return financeFactor;}
    @XmlElement(required=true)
    public double getFuelFactor() {return fuelFactor;}
    @XmlElement(required=true)
    public double getRepairsFactor() {return repairsFactor;}
    @XmlElement(required=true)
    public double getTyresFactor(){return tyresFactor;}
    
    public void setFinanceFactor(double financeFactor) {this.financeFactor = financeFactor;}
    public void setFuelFactor(double fuelFactor) {this.fuelFactor = fuelFactor;}
    public void setRepairsFactor(double repairsFactor) {this.repairsFactor = repairsFactor;}
    public void setTyresFactor(double tyresFactor) {this.tyresFactor = tyresFactor;}
    
    private double financeFactor, fuelFactor, repairsFactor, tyresFactor;
}
