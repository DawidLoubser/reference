/*
 * TravelCost.java
 *
 * Created on November 7, 2006, 5:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package travel.cost;

import javax.jws.WebService;

/**
 *
 * @author fritz
 */
public interface TravelCost {
    double getTravelCost(TravelCostRequest travelCostRequest);
    
}
