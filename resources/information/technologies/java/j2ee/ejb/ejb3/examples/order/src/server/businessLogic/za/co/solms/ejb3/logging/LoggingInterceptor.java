package za.co.solms.ejb3.logging;

import java.lang.reflect.Type;

import javax.ejb.AroundInvoke;
import javax.ejb.InvocationContext;

import org.apache.log4j.Logger;

import za.co.solms.ejb3.comms.email.EMailInterceptor;

/**
 * @author fritz@solms.co.za
 *
 */
public class LoggingInterceptor 
{
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
        StringBuffer parametersString = new StringBuffer();
        Type[] types = ctx.getMethod().getTypeParameters();
        for (int i=0; i<types.length; ++i)
        { 
            parametersString.append(types[i].toString());
            if (i<types.length-1)
                parametersString.append(",");
        }    
        logger.info("Method " + ctx.getMethod().getName() + "(" + parametersString + ") called.");
        
        return ctx.proceed();
    }
    
    private static final Logger logger = Logger.getLogger(EMailInterceptor.class);
}
