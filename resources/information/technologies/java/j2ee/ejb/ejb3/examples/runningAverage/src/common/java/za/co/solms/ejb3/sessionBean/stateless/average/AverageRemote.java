package za.co.solms.ejb3.sessionBean.stateless.average;

import javax.ejb.Remote;

/**
 * @author fritz@solms.co.za
 *
 * A remote interface for session beans calculating 
 * the average of a collection of floating point numbers. 
 */
@Remote public interface AverageRemote extends Average
{
}
