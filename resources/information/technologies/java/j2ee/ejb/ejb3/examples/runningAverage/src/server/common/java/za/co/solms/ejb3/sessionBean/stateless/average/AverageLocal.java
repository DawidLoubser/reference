package za.co.solms.ejb3.sessionBean.stateless.average;

import javax.ejb.Local;

/**
 * @author fritz@solms.co.za
 *
 * A local interface to  a session beans calculating the 
 * average of a collection of floating point numbers. 
 */
@Local public interface AverageLocal extends Average
{
}
