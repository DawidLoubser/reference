/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package za.co.solms.jaxrs;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import java.lang.reflect.Type;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author paulsandoz
 */
@Provider
public class EJBProvider implements InjectableProvider<EJB, Type> {

    public ComponentScope getScope() {
        return ComponentScope.Singleton;
    }

    public Injectable getInjectable(ComponentContext cc, EJB ejb, Type t) {
        if (!(t instanceof Class)) return null;

        try {
            Class c = (Class)t;
            Context ic = new InitialContext();

            final Object o = ic.lookup(c.getName());

            return new Injectable<Object>() {
                public Object getValue() {
                    return o;
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
