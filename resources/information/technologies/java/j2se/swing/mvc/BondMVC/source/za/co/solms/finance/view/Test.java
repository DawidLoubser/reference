package za.co.solms.finance.view;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JFrame;

import za.co.solms.finance.ContinuousCompounding;
import za.co.solms.finance.InterestRate;
import za.co.solms.finance.InterestRateImpl;
import za.co.solms.finance.MaturityBond;
import za.co.solms.finance.MaturityBondImpl;

public class Test
{
	private Test() {}
	
	public static void main(String[] args)
	{
		new Test().run();
	}
	
	public void run()
	{
     Random rng = new Random();
		 setup();
    
    while (true)
    {
       for (int i=1; i<50; ++i)
       {
          try
          {
            Thread.sleep(50);
          }
          catch (InterruptedException e) {}
          
          bond.mutableGetInterestRate().setRate(rng.nextDouble());
       }
       try
       {
          Thread.sleep(10000);
       }
       catch (InterruptedException e) {}
    }
	}
	
	public void setup()
	{
     createModel();
		
		JFrame frame = new JFrame("Test");
    
		frame.getContentPane().add(new MaturityBondPanel(bond, "Maturity bond"));
    
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
  
  private void createModel()
  {
     InterestRate rate = InterestRateImpl.getFactory().create
         (0.94, ContinuousCompounding.getInstance());
     Date effectiveDate = new GregorianCalendar(2006,7,18).getTime();
     Date maturityDate = new GregorianCalendar(2015,7,18).getTime();
     
     bond = MaturityBondImpl.getDefaultFactory().create
           (100, rate, effectiveDate, maturityDate);
  }
	
	private MaturityBond.Mutable.Direct bond; 
}
