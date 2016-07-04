/**
 * 
 */
package za.co.solms.finance.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import za.co.solms.finance.MaturityBond;
import za.co.solms.utils.DateTime.view.DatePanel;
import za.co.solms.utils.primitives.view.DoubleSlider;

/**
 * @author fritz@solms.co.za
 * 
 * Core elements of MVC based UI design
 *   - observable model
 *   - direct access to model object components breaking encapsulation
 *   - assemble UI for composite object from component object UI elements
 *   - specify role for each UI component
 * 
 */
public class MaturityBondPanel extends JPanel
{
	public MaturityBondPanel(MaturityBond.Mutable.Direct bond, String role)
	{
		this.role = role;
		setBond(bond);
    
    setup();
	}
	
	private void setBond(MaturityBond.Mutable.Direct bnd)
	{
		this.bond = bnd;

    updateView();
    
		bnd.addObserver(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent arg0)
			{
			   updateView();
			}
		});
	}
  
  private void setup()
  {
     JPanel outerPanel = new JPanel();
     add(outerPanel);
     JPanel innerPanel = new JPanel();
     innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
     outerPanel.add(innerPanel);
     innerPanel.setBorder(BorderFactory.createTitledBorder(role));
     
     JPanel topPanel = new JPanel();
     innerPanel.add(topPanel);
     
     topPanel.add(new DatesPanel());
     topPanel.add(new InterestRatePanel(bond.mutableGetInterestRate(), "Interest earned"));
     
     innerPanel.add(new DoubleSlider
            (bond.mutableGetInterestRate().mutableGetRate(), "Rate", 0, 100,100));
  }
  
  private class DatesPanel extends JPanel
  {
     public DatesPanel()
     {
       JPanel datesPanel = new JPanel();
       datesPanel.setLayout(new BoxLayout(datesPanel, BoxLayout.Y_AXIS));
       // FIXME cater for replacement
       datesPanel.add(new DatePanel(bond.mutableGetEffectiveDate(), "Effective date", dateFormat));
       DatePanel maturityDatePanel = new DatePanel(bond.mutableGetMaturityDate(), "Maturity date", dateFormat);
       datesPanel.add(maturityDatePanel);
       this.add(datesPanel);
     }
     private static final long serialVersionUID = 200608181518L;
  }
  
  private void updateView()
  {
     // nothing to do here, only component views which update themselves.
  }
	
	private MaturityBond.Mutable.Direct bond;
	private String role;
	private static final long serialVersionUID = 200608180900L;
  private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
}
