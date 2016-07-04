package za.co.solms.average;

import javax.ejb.Remote;

/**
 * A remote interface for session beans calculating 
 * the average of a collection of floating point numbers. 
 */
@Remote 
public interface AverageRemote extends Average {}