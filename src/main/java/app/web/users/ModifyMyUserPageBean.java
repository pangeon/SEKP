package app.web.users;

import app.exception.AppException;
import app.model.entity.Users;
import app.utils.hashgenerator.Sha;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Init;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ModifyMyUserPageBean {
    
    @Inject
    private UsersController usersController;
    
    private String newPassword;
    
    private String checkPassword;
    
    @PostConstruct
    private void loadMyAccountToController() {
        if (null == usersController.getAddedUser() || !usersController.getAddedUser().equals(usersController.getMyUser())) {
            usersController.setAddedUser(usersController.getMyUser());
        }
    }
    
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
    
    private void passwordGenerator() {
    }
    
    private void loginGenerator() {
        
    }
    
    public String modifyUser() throws NoSuchAlgorithmException, AppException {
        if (comparePassword()) {
            try {
                getUser().setPassword(Sha.hash256(newPassword));
                usersController.editUser();
                return "account";
            } catch (EJBException e) {
                usersController.sendMessage("i18n.messages", "not.unique");
                return null;
            }
        } else {
            usersController.sendMessage("i18n.messages", "passwords.not.matching");
            return null;
        }
    }
    
    public String modifyAccount() throws AppException {
        usersController.editUser();
        return "account";
    }
    
    public String modifyRole() throws AppException {
        usersController.editUser();
        return "list";
    }
}
