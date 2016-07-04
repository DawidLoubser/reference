/**
 * 
 */
package za.co.solms.finance.view;

import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import za.co.solms.finance.InterestRate;
import za.co.solms.utils.primitives.view.DoublePanel;

/**
 * @author fritz@solms.co.za
 *
 */
public class InterestRatePanel extends JPanel
{
	public InterestRatePanel(InterestRate.Mutable.Direct rate, String role)
	{
		this.rate = rate;
		this.role = role;
    
    setup();
	}
  
  private void setup()
  {
     JPanel outerPanel = new JPanel();
     this.add(outerPanel);
     add(outerPanel);
     outerPanel.setBorder(BorderFactory.createTitledBorder(role));
     
     JPanel innerPanel = new JPanel();
     outerPanel.add(innerPanel);
     
     innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
     innerPanel.add(new DoublePanel(rate.mutableGetRate(), InterestRate.RATE_PROPERTY, 
           new DecimalFormat("###0.00%")));
     innerPanel.add(new CompoundingSelectorPanel(rate.mutableGetCompoundingType(), InterestRate.COMPOUNDING_PROPERTY)); 
  }
	
	private String role;
	private InterestRate.Mutable.Direct rate;
	
	private static final long serialVersionUID = 200608180821L;
}
