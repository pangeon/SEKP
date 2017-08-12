
package app.web.users;

import app.model.entity.Users;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@RequestScoped
@Named
public class NewUserPageBean
{
    @Inject
    private UsersController usersController;
    
    
    private final Users user = new Users();

    public Users getUser()
    {
        return user;
    }
            
    //TABELA DANYCH SYSTEMOWYCH
    private String login;
    
    private String nickName;
    
    private String e_mail;
    
    private String password;
        
    private String againPassword = "";
    
    private Date activationDate;

    private String roleName;
    
    //TABELA DANYCH PERSONALNYCH

    private String city;
    
    private Date birthDate;
    
    private String firstName;
    
    private String lastName;
    
    private String phoneNumber;
    
    private String zipCode;
    
    private String country;
    
    private String street;
            
    public UsersController getUsersController()
    {
        return usersController;
    }

    public void setUsersController(UsersController usersController)
    {
        this.usersController = usersController;
    }
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }    
    public String getE_mail()
    {
        return e_mail;
    }

    public void setE_mail(String e_mail)
    {
        this.e_mail = e_mail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAgainPassword()
    {
        return againPassword;
    }

    public void setAgainPassword(String againPassword)
    {
        this.againPassword = againPassword;
    }
    
    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    //TABELA DANYCH PERSONALNYCH
    public Date getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(Date activationDate)
    {
        this.activationDate = activationDate;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }
    //METODA STERUJĄ FORMULARZEM
    public String addUserAndCheckPasswords(){
        if (!(user.getPassword().equals(againPassword))) {
            usersController.sendMessage("i18n.messages", "passwords.not.matching");
            return null;
        } else if (againPassword.equals(user.getPassword()))  {
            usersController.setAddedUser(user);
            return "confirmPanel";
        }
        return "errorPage";  
    }
    
}

