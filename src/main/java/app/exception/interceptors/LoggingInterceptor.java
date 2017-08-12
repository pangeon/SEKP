
package app.exception.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

    @Resource
    private SessionContext sessionContext;
    @AroundInvoke
    public Object additionalInvokeForMethod(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder("Wywołanie metody biznesowej "
                + invocation.getTarget().getClass().getName() + "."
                + invocation.getMethod().getName());
        sb.append("z tożsamością: " + sessionContext.getCallerPrincipal().getName());
        try {
            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" z parametrem " + param.getClass().getName() + "=" + param.toString());
                    } else {
                        sb.append(" z parametrem null");
                    }
                }
            }

            long startTime = System.currentTimeMillis();
            Object result = invocation.proceed();
            long duration = System.currentTimeMillis() - startTime;
            sb.append(" czas wykonania " + duration + " ms");

            if (result != null) {
                sb.append(" zwrócono " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" zwrócono wartość null");
            }

            return result;
        } catch (Exception ex) {
            sb.append(" wystapil wyjatek " + ex);
            throw ex; //ponowne zgloszenie wyjatku
        } finally {
            Logger.getGlobal().log(Level.INFO, sb.toString());
        }
    }
}