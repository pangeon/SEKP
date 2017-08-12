
package app.web.users;

import app.model.entity.Users;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class UserListPageBean
{
    @Inject
    private UsersController UsersController;
               
    private List<Users> listUsers;
    private DataModel<Users> dataModelUsers;
    
    private String firstNamePattern = "";
    private String lastNamePattern = "";
    private String nickPattern = "";
    private String emailPattern = "";

    @PostConstruct
    private void initList() {
        listUsers = UsersController.searchUsers(firstNamePattern, lastNamePattern, nickPattern, emailPattern);
        dataModelUsers = new ListDataModel<>(listUsers);
    }
    public DataModel<Users> getUsersList() {
        return (dataModelUsers = 
        new ListDataModel<>(UsersController.searchUsers(firstNamePattern, lastNamePattern, nickPattern, emailPattern)));
    }
    public void clean() {
        firstNamePattern = "";
        lastNamePattern = "";
        nickPattern = "";
        emailPattern = "";
    }
    public void search() {
        initList();
    }
    public String showUser() {
        UsersController.downloadUserToEdit(dataModelUsers.getRowData());
        return "DataUserList";
    }
    public String advanceSearch() {
        return "advanceSearch";
    }
    // Gettery i setter do pól wyszukiwania
    public String getFirstNamePattern() {
        return firstNamePattern;
    }   
    public void setFirstNamePattern(String firstNamePattern) {
        this.firstNamePattern = firstNamePattern;
    }
    public String getLastNamePattern() {
        return lastNamePattern;
    }
    public void setLastNamePattern(String lastNamePattern) {
        this.lastNamePattern = lastNamePattern;
    }
    public String getNickPattern() {
        return nickPattern;
    }
    public void setNickPattern(String nickPattern) {
        this.nickPattern = nickPattern;
    }
    public String getEmailPattern() {
        return emailPattern;
    }
    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }
}
