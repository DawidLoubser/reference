/**
 * 
 */
package za.co.solms.finance;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import za.co.solms.beans.ObservableBase;;

/**
 * @author fritz@solms.co.za
 *
 */
public class CompoundingTypeImpl extends
      ObservableBase implements CompoundingType.Mutable
{
   private CompoundingTypeImpl(Compounding compounding)
   {
      this.compounding = compounding;
   }
   
   /* (non-Javadoc)
    * @see za.co.solms.finance.CompoundingType#getCompounding()
    */
   public Compounding getCompounding()
   {
      return compounding;
   }
   
   public void setCompounding(Compounding compoundng)
   {
      if (!compoundng.equals(this.compounding))
      {
         Compounding oldCompounding = this.compounding;
         this.compounding = compoundng;
         propertyChangeSupport.firePropertyChange
            (COMPOUNDING_PROPERTY, oldCompounding, this.compounding);
      }
   }

   @Override
   public Set<String> getProperties()
   {
      return properties;
   }

   @Override
   protected Object getThisHandle()
   {
      return this;
   }
   
   private static class FactoryImpl implements CompoundingType.Factory
   {
      public CompoundingType.Mutable create(Compounding compounding)
      {
         return new CompoundingTypeImpl(compounding);
      }
   }
   
   static
   {
      Set<String> props = new TreeSet<String>();
      props.add(COMPOUNDING_PROPERTY);
      properties = Collections.unmodifiableSet(props);
   }
   
   public static Factory getDefaultFactory() {return factory;}
   
   private static final Set<String> properties;
   private Compounding compounding;
   private static final Factory factory = new FactoryImpl();
}
