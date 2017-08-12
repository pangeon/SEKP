package app.web.users;

import app.endpoint.UsersEndpoint;
import app.exception.AppException;
import app.model.entity.Users;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SessionScoped
public class UsersController implements Serializable {

    @EJB
    private UsersEndpoint usersEndpoint;

    private Users addedUser;

    public Users getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(Users addedUser) {
        this.addedUser = addedUser;
    }

    public void addUser() throws NoSuchAlgorithmException {
        if (addedUser == null) {
            throw new IllegalArgumentException("Próba dodania nowego użytkownika z pominięciem formularza");
        }
        usersEndpoint.addUser(addedUser);

        addedUser = null;
    }

    public List<Users> getUsersList() {
        return usersEndpoint.getUsersList();
    }

    public void downloadUserToEdit(Users user) {
        addedUser = usersEndpoint.downloadUserToEdit(user);
    }

    public void editUser() throws AppException {
        if (!(addedUser == null)) {
            usersEndpoint.addUserBeforeEditing(addedUser);
            addedUser = null;
        } else {
            throw new IllegalArgumentException("Proba edycji bez wczesniejszego wczytania");

        }
    }

    public void removeUser(Users user) {
        usersEndpoint.removeUser(user);
    }

    public Users getMyUser() {
        return usersEndpoint.getMyUser();
    }

    public String sendMessage(String resource, String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resource);
        FacesMessage facesMessage = new FacesMessage(resourceBundle.getString(key));
        facesContext.addMessage(null, facesMessage);
        return null;
    }

    public List<Users> searchUsers(String firstNamePattern, String lastNamePattern, String nickPattern, String emailPattern) {// tu beda kryteria do wyszukiwania) 
        return usersEndpoint.searchUsers(firstNamePattern, lastNamePattern, nickPattern, emailPattern);
    }
    public List<Users> searchUsersAdminPage(String loginPattern, String emailPatternAdminList, String rolePattern, String activationDatePattern) {
        return usersEndpoint.searchUsersAdminPage(loginPattern, emailPatternAdminList, rolePattern, activationDatePattern);
    }

}
