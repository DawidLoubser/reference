/**
 * 
 */
package za.co.solms.dateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * @author fritz@solms.co.za
 *
 */
@ManagedBean
@RequestScoped
public class DatePeriodBinding implements Serializable
{
	public DatePeriodBinding() {}

	public void ajaxPeriodValidator(AjaxBehaviorEvent event)
	{
		logger.info("***** Validating date period");
		logger.info("start = " + startDate);
		logger.info("end = " + endDate);
		 if ((startDate != null) && (endDate != null))
		 {
			 if (startDate.after(endDate))
				 warning = "Start date after end date";
			 else
				 warning = "no wanring";
		 }
		 logger.info("**** set warning to " + warning);
	}
	
	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public String getWarning()
	{
		return warning;
	}

	public void setWarning(String warning)
	{
		this.warning = warning;
	}

	private Date startDate, endDate;
	
	private String warning;
	
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(DatePeriodBinding.class.getName());
}
