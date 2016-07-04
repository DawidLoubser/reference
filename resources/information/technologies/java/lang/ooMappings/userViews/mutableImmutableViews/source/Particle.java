/**
 * 
 */
package za.co.solms.media.graphics.twoD.animation.particle;

import za.co.solms.media.graphics.twoD.Shape;
import za.co.solms.media.graphics.twoD.Vector;
import za.co.solms.media.graphics.twoD.animation.force.Environment;
import za.co.solms.util.dateTime.TimeBased;

/**
 * The actual particle.
 * 
 * @author fritz:@solms.co.za
 *
 */
public interface Particle extends ObservableParticle, TimeBased 
{
	/**
	 * Initializes the particle to its initial state. 
	 * 
	 * Note: The particle time will be zero after initialization.
	 */
	public void init();
	
	/**
	 * 
	 * @return the current speed of the particle.
	 */
	public double getSpeed();
	
	/**
	 * 
	 * @return the current velocity of the particle.
	 */
	public Vector getVelocity();
	
	/**
	 * 
	 * @return the current cruising direction
	 */  
	public double getCruisingAngle();
	
	/**
	 * 
	 * @return the last updated time relative to the start of the life span of the particle.
	 */
	public double getParticleTime();
	/**
	 * 
	 * @return the current mass of the particle.
	 */
	public double getMass();
	
	/**
	 * This is a bounding shape for for the particle. 
	 * 
	 * By default this would simply be the bounding rectangle of the particle,
	 * but particle implementations could return a more accurate representation
	 * for their shape.
	 * 
	 * @return the current shape of the particle.
	 */
	public Shape getShape();
	
	/**
	 * 
	 * @return the environment of the particle.
	 */
	public Environment getEnvironment();

	/**
	 * This method returns the position the particle would reach at the specified point in time if the particle would
	 * continue with moving from its current position with its current velocity (either forward or backward in time).
	 * 
	 * @param time the time for which the exprected position is queried.
	 * @return the end position the particle would be reaching if it just continued crusing without forces and boundaries.
	 */
	public Vector.Mutable getExpectedPosition(double time);
	
	/**
	 * Requests the particle to cruise along with its current velocity up to the specified time,
	 * ignoring any forces and any other particles. If the provided time is prior or equal to the 
	 * current time of the particle, the request does nothing.
	 * 
	 * @param time (time - particle.getTime()) is the time the particle should cruise.
	 */
	public void cruise(double time);

	/**
	 * Updates the particle's position and current time, leaving the particle velocity unchanged.
	 * @param time the new particle time
	 * @param newPosition the new position for the particle
	 */
	public void update(double time, Vector newPosition);
	
	/**
	 * Adds the provided vector to the velocity of the particle.
	 * 
	 * @param dv the difference in velocity (v = v + dv)
	 */
	public void addToVelocity(Vector dv);
	  	
	
	/**
	 * Updates the particle's dynamic state.
	 * 
	 * @param time the new particle time, i.e. the last updated time for the particle.
	 * @param newPosition the particles position for the new time instant.
	 * @param newVelocity the particle's velocity for the new time instant.
	 * @throws InfiniteEnergyException if a particle with infinite mass is assigned a finite velocity.
	 */
	public void update(double time, Vector newPosition, Vector newVelocity) throws InfiniteEnergyException;

	/**
	 * Thrown if a particle with infinite mass is given a finite (non-zero) velocity.
	 * 
	 * @author fritz@solms.co.za
	 *
	 */
	public static class InfiniteEnergyException extends Exception 
	{
		private static final long serialVersionUID = 983487643892436L;
	}
	
	public static final String ENVIRONMENT_PROPERTY = "environment";
	
	public static final String VELOCITY_PROPERTY = "velocity";
}
