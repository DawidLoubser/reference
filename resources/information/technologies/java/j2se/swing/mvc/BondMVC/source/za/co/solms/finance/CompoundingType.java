/**
 * 
 */
package za.co.solms.finance;

import za.co.solms.beans.Observable;

/**
 * @author fritz@solms.co.za
 *
 */
public interface CompoundingType extends Observable
{
   public Compounding getCompounding();
   
   public interface Mutable extends CompoundingType
   {
      public void setCompounding(Compounding compounding);
   }
   
   public interface Factory
   {
      public CompoundingType.Mutable create(Compounding compounding);
   }
   
   public static final String COMPOUNDING_PROPERTY = "compounding";
}
