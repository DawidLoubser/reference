package za.co.solms.ui.web.phaseListeners;

import java.util.Map;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestParameterLogger implements PhaseListener
{

	@Override
	public void afterPhase(PhaseEvent event) {}

	@Override
	public void beforePhase(PhaseEvent event)
	{
		ExternalContext externalFacesContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametersString = buildParametersString(externalFacesContext.getRequestParameterMap());
		log.debug(parametersString);
	}
	
	private static String buildParametersString(Map<String, String> parametersMap)
	{
		StringBuffer result = new StringBuffer();
		for (String parameterName: parametersMap.keySet())
			result.append(buildParameterString(parameterName, parametersMap.get(parameterName))).append(eol);
		return result.toString();
	}
	
	private static String buildParameterString(String parameterName, String value)
	{
		return parameterName + " => " + value;
	}

	@Override
	public PhaseId getPhaseId()
	{
		return PhaseId.APPLY_REQUEST_VALUES;
	}

	private static final String eol = System.getProperty("line.separator");

	private static Log log = LogFactory.getLog(PhaseInvocationLogger.class);
}
