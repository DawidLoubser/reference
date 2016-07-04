/**
 * 
 */
package za.co.solms.utils.primitives.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import za.co.solms.utils.primitives.ObservableDouble;
import za.co.solms.utils.primitives.ObservableDouble.Mutable;


/**
 * @author fritz@solms.co.za
 *
 */
public class DoubleSlider extends JPanel
{
   public DoubleSlider(ObservableDouble.Mutable data, String role, int minRange, int maxRange, double scalingFactor)
   {
      this.data = data;
      this.scalingFactor = scalingFactor;
      this.role = role;
      this.minRange = minRange;
      this.maxRange = maxRange;
      
      setup(); 
   }
   
   private void setup()
   {
      slider = new JSlider(JSlider.HORIZONTAL,minRange,maxRange,0);
      add(slider);
      setBorder(BorderFactory.createTitledBorder(role));

      new ViewAdapter();
      new Controller();
   }
   
   public void updateView()
   {
      int newRate = (int)Math.round(data.getValue()*scalingFactor); 
      slider.setValue(newRate);
   }
   
   private class ViewAdapter implements PropertyChangeListener
   {
      public ViewAdapter()
      {
         data.addObserver(this);
         updateView();
      }
      
      public void propertyChange(PropertyChangeEvent evt)
      {
         updateView();
      }
   }
   
   private class Controller implements ChangeListener
   {
      public Controller()
      {
         slider.addChangeListener(this);
      }
      
      public void stateChanged(ChangeEvent arg0)
      {
         data.setValue(slider.getValue()/scalingFactor);
      }
   };

   
   private JSlider slider;
   private ObservableDouble.Mutable data;
   private String role;
   private int minRange, maxRange;
   
   private double scalingFactor;
   
   private static final long serialVersionUID = 200608181353L;
}
