
package app.exception;

import app.model.entity.Users;
import javax.persistence.OptimisticLockException;

public class RollBackOperationException extends AppException {

    static final public String KEY_OPTIMISTIC_LOCK = "error.optimisticlock";
    
    private RollBackOperationException(String info, Throwable throwable) {
        super(info, throwable);
    }
    private RollBackOperationException(String info) {
        super(info);
    }
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    static public RollBackOperationException BuildExceptionOptimisticLock(Users users, OptimisticLockException throwable) {
        RollBackOperationException rbopx = new RollBackOperationException(KEY_OPTIMISTIC_LOCK, throwable);
        rbopx.setUsers(users);
        return rbopx;
    }
    
}
