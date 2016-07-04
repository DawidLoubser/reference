package za.co.solms.ui.web.phaseListeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PhaseInvocationLogger implements PhaseListener
{
	@Override
	public void beforePhase(PhaseEvent event)
	{
		log.debug("Starting phase: " + event.getPhaseId());		
	}

	@Override
	public void afterPhase(PhaseEvent event)
	{
		log.debug("Completed phase: " + event.getPhaseId());		
	}

	@Override
	public PhaseId getPhaseId()
	{
		return PhaseId.ANY_PHASE;
	}

	private static Log log = LogFactory.getLog(PhaseInvocationLogger.class);
}
