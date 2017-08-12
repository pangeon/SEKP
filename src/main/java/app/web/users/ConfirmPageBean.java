
package app.web.users;

import app.model.entity.Users;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ConfirmPageBean
{
    @Inject
    private UsersController usersController;

    public Users getAddedUsers() 
    {
        return usersController.getAddedUser();
    }
    public String addUser() 
    {
        try
        {
            usersController.addUser();
            return "main";
        } catch (Exception e)
        {
            usersController.sendMessage("i18n.messages", "system.error");
            return null;
        }               
    }
    Date date = new Date();
    String LocaleDate = date.toLocaleString();

    public String getLocaleDate()
    {
        return LocaleDate;
    }

    public void setLocaleDate(String LocaleDate)
    {
        this.LocaleDate = LocaleDate;
    }
}
