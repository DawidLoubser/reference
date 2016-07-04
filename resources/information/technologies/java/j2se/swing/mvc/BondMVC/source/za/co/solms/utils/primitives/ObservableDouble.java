package za.co.solms.utils.primitives;

import za.co.solms.beans.Observable;
import za.co.solms.lang.Cloneable;

/**
 * 
 * @author fritz@solms.co.za
 *
 */
public interface ObservableDouble extends Observable, Cloneable
{
   
   public double getValue();
   
   public interface Mutable extends ObservableDouble
   {
      public void setValue(double newValue);
   }
   
   public interface Factory
   {
      public ObservableDouble.Mutable create(double value);
   }
   
   public String VALUE_PROPERTY = "value";
}
