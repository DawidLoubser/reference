/**
 * 
 */
package za.co.solms.finance.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import za.co.solms.finance.AnnualCompounding;
import za.co.solms.finance.Compounding;
import za.co.solms.finance.CompoundingType;
import za.co.solms.finance.ContinuousCompounding;

/**
 * @author fritz@solms.co.za
 *
 */
public class CompoundingSelectorPanel extends JPanel
{
  public CompoundingSelectorPanel
     (CompoundingType.Mutable compoundingType, String role)
  {
     this.role = role;
     this.compoundingType = compoundingType;
     
     setup();
  }
  
  private void setup()
  {
     compoundingSelector = new JComboBox();
     initCompoundingSelector();
     
     add(compoundingSelector);
     
     new ViewAdapter(); 
     new Controller();
  }
  
  private void initCompoundingSelector()
  {
     compoundingSelector.addItem(ContinuousCompounding.getInstance());
     compoundingSelector.addItem(AnnualCompounding.getInstance());
  }
  
  private void updateView()
  {
     compoundingSelector.setSelectedItem(compoundingType.getCompounding());
  }
  
  private class ViewAdapter implements PropertyChangeListener
  {
     public ViewAdapter()
     {
        compoundingType.addObserver(this);
        updateView();
     }

   public void propertyChange(PropertyChangeEvent evt)
   {
      updateView();
   }
  }
  
  private class Controller implements ItemListener
  {
     public Controller()
     {
        compoundingSelector.addItemListener(this);
     }
     
     public void itemStateChanged(ItemEvent e)
     {
        compoundingType.setCompounding((Compounding)compoundingSelector.getSelectedItem());
     }
  }
  
  public String toString() {return role;}

   private CompoundingType.Mutable compoundingType;
   private JComboBox compoundingSelector = new JComboBox();
   private String role;
   private static final long serialVersionUID = 200608181455L;
}
