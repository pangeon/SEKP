package app.endpoint;

import app.authentication.beans.LoginEJB;
import app.exception.AppException;
import app.facade.UsersFacade;
import app.model.entity.Users;
import app.utils.hashgenerator.Sha;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class UsersEndpoint
{
    @EJB
    private UsersFacade usersFacade;

    @EJB
    private LoginEJB loginEJB;

    public void addUser(Users user) throws NoSuchAlgorithmException
    {
        Date currentTime = new Date();
        user.setActivationDate(currentTime);
        
        user.setRoleName("user_role");
        String passwordSHA26 = Sha.hash256(user.getPassword());
        user.setPassword(passwordSHA26);
                
        usersFacade.create(user);
    }
    public List<Users> getUsersList() 
    {
        return usersFacade.findAll();
    }
    public List<Users> searchUsers(String firstNamePattern, String lastNamePattern, String nickPattern, String emailPattern)// tu beda kryteria do wyszukiwania) 
    {
        // findAll() zastapic nowa meoda wyszukujaca wedlug kryteriow
        return usersFacade.searchUsers(firstNamePattern, lastNamePattern, nickPattern, emailPattern);
    }
    public List<Users> searchUsersAdminPage
    (String loginPattern, String emailPatternAdminList, String rolePattern, String activationDatePattern) {
        return usersFacade.searchUsersAdminPage(loginPattern, emailPatternAdminList, rolePattern, activationDatePattern);
    }
    public void addUserBeforeEditing(Users addedUser) throws AppException 
    {
        usersFacade.edit(addedUser);
    }
    public Users downloadUserToEdit(Users user) 
    {
        Users wanted_user = usersFacade.find(user.getUserId());
        return wanted_user;
    }
    public void removeUser(Users user) 
    {
        usersFacade.remove(user);
    }
    public Users getMyUser() {
        return loginEJB.getMyUser();
    }
}
