
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
public class AdminUserListPageBean
{
    @Inject
    private UsersController UsersController;
               
    private List<Users> adminListUsers;
    private DataModel<Users> dataModelUsersForAdmin;
    
    private String loginPattern = "";
    private String emailPatternAdminList = "";
    private String rolePattern = ""; 
    private String activationDatePattern = "";

    @PostConstruct
    private void initAdminList() {
        adminListUsers = UsersController.searchUsersAdminPage(loginPattern, emailPatternAdminList, rolePattern, activationDatePattern);
        dataModelUsersForAdmin = new ListDataModel<>(adminListUsers);
    }

    public DataModel<Users> getUsersListAdminPage() {
        return (dataModelUsersForAdmin = 
        new ListDataModel<>(UsersController.searchUsersAdminPage(loginPattern, emailPatternAdminList, rolePattern, activationDatePattern)));
    }
    
    public void adminCriteriaList() {
        initAdminList();
    }
    public void clean() {
        loginPattern = "";
        emailPatternAdminList = "";
        rolePattern = ""; 
        activationDatePattern = "";
    }
    public String removeUser() {
        UsersController.removeUser(dataModelUsersForAdmin.getRowData());
        return "list";
    }
    public String modifyRole() {
        UsersController.downloadUserToEdit(dataModelUsersForAdmin.getRowData());
        return "roleManager";
    }
//    public String showUser() {
//        UsersController.downloadUserToEdit(dataModelUsersForAdmin.getRowData());
//        return "DataUserList";
//    }
//    public String advanceSearch() {
//        return "advanceSearch";
//    }
    // Gettery i setter do pól wyszukiwania

    public String getLoginPattern()
    {
        return loginPattern;
    }

    public void setLoginPattern(String loginPattern)
    {
        this.loginPattern = loginPattern;
    }

    public String getEmailPatternAdminList()
    {
        return emailPatternAdminList;
    }

    public void setEmailPatternAdminList(String emailPatternAdminList)
    {
        this.emailPatternAdminList = emailPatternAdminList;
    }

    public String getRolePattern()
    {
        return rolePattern;
    }

    public void setRolePattern(String rolePattern)
    {
        this.rolePattern = rolePattern;
    }

    public String getActivationDatePattern()
    {
        return activationDatePattern;
    }

    public void setActivationDatePattern(String activationDatePattern)
    {
        this.activationDatePattern = activationDatePattern;
    }
}
