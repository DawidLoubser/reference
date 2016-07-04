/**
 * 
 */
package za.co.solms.utils.primitives;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import za.co.solms.beans.ObservableBase;

/**
 * @author fritz@solms.co.za
 *
 */
public class ObservableDoubleImpl extends
      ObservableBase implements ObservableDouble.Mutable
{
   private ObservableDoubleImpl(double value)
   {
      setValue(value);
   }

   /* (non-Javadoc)
    * @see za.co.solms.beans.ObservableBase#getProperties()
    */
   @Override
   public Set<String> getProperties()
   {
      return properties;
   }

   /* (non-Javadoc)
    * @see za.co.solms.beans.ObservableBase#getThisHandle()
    */
   @Override
   protected Object getThisHandle()
   {
      return this;
   }

   /* (non-Javadoc)
    * @see za.co.solms.utils.primitives.ObservableDouble#getValue()
    */
   public double getValue()
   {
      return value;
   }
   
   public void setValue(double newValue)
   {
      if (newValue != value)
      {
         double oldValue = value;
         value = newValue;
         propertyChangeSupport.firePropertyChange
            (VALUE_PROPERTY, oldValue, value);
      }
   }
   
   public int hashCode()
   {
     return new Double(value).hashCode();
   }
   
   static
   {
      Set<String> props = new TreeSet<String>();
      props.add(VALUE_PROPERTY);
      properties = Collections.unmodifiableSet(props);
   }
   
   public Object clone() {return super.clone();}
   
   public static Factory getDefaultFactory() {return factory;}
   
   private static class FactoryImpl implements Factory
   {
      public ObservableDouble.Mutable create(double value)
      {
         return new ObservableDoubleImpl(value);
      }
   }
   
   private double value;
   private static final Set<String> properties;
   private static final Factory factory = new FactoryImpl();
}
