
package app.exception;
import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public abstract class AppException extends Exception {
    
    protected AppException(String info, Throwable throwable) {
        super(info, throwable);
    }
    protected AppException(String info) {
        super(info);
    }
}
