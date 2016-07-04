package za.co.solms.math.statistics;

import za.co.solms.math.Variable1D;

/**
 * Abstractly represents a random, 1-dimensional variable,
 which could be used in any type of mathematical or physics
 framework.
 */
public interface RandomVariable1D extends Variable1D
{
    /** 
     * @return the probability distribution used to generate 
     * random two dimensional points.
     */
    public ProbabilityDistribution1D getProbabilityDistribution();
    
    public static final String PROBABILITY_DISTRIBUTION_PROPERTY 
        = "probabilityDistribution";
	
    /**
     * For mutable two dimensional random variables 
     * the probability distribution can be changed.
     * 
     * @author fritz@solms.co.za
     *
     */
    public interface Mutable  
                    extends RandomVariable1D, Variable1D.Mutable
    {
        /**
         * @param p the new probability distribution to be used 
         * by this random variable.
         */
        public void setProbabilityDistribution(ProbabilityDistribution1D p);
		
		
        /**
         * An interface providing direct access to the components 
         * of the object, breaking encapsulation and the nature of 
         * the composition relationship.
         */
        public interface Direct extends RandomVariable1D.Mutable, 
        Variable1D.Mutable.Direct
        {
            /**
             * 
             * @return a mutable handle to the underlying 
             * probability distribution.
             */
            public ProbabilityDistribution1D.Mutable 
                mutableGetProbabilityDistribution();
            
            /**
             * Directly sets the probability distribution reference 
             * to the provided object.
             * 
             * @param probabilityDistribution the new 
             * probability distribution to be used.
             */
            public void directSetProbabilityDistribution
             (ProbabilityDistribution1D.Mutable probabilityDistribution);
        }
    }
	
	
    /** Contract for the behaviour of a factory object that produces
     * instances of RandomVariable1D */
    public interface Factory
    {
        public RandomVariable1D create
            (ProbabilityDistribution1D probabilityDistribution);
    }
}