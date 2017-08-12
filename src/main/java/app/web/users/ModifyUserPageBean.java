package app.web.users;

import app.exception.AppException;
import app.model.entity.Users;
import app.utils.hashgenerator.Sha;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ModifyUserPageBean {

    @Inject
    private UsersController usersController;

    private String newPassword;
    private String checkPassword;

    public Users getUser() {
        return usersController.getAddedUser();
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getCheckPassword() {
        return checkPassword;
    }
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }    
    private boolean comparePassword() {
        return (getNewPassword().equals(getCheckPassword()));
    }
    public String modifyUser() throws NoSuchAlgorithmException, AppException {
        if (comparePassword()) {
            try {
                getUser().setPassword(Sha.hash256(newPassword));
                usersController.editUser();
                return "list";
            } catch (EJBException e) {
                usersController.sendMessage("i18n.messages", "not.unique");
                return null;
            }
        } else {
            usersController.sendMessage("i18n.messages", "passwords.not.matching");
            return null;
        }
    }
    public String modifyRole() throws AppException {
        usersController.editUser();
        return "list";
    }
}
