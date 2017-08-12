
package app.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@SecondaryTables     
(
        {
            @SecondaryTable(name = "workers")
        }
)
@NamedQueries(
{
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
})
public class Users implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name="version")
    @Version
    private long version;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "activation_date", unique = false, nullable = false, updatable = true)    
    private Date activationDate;
    
    @Column(name = "login", length = 16, unique = true, nullable = false, updatable = false)
    private String login;
    
    @Column(name = "nick_name", length = 16, unique = true, nullable = false, updatable = true)
    private String nickName;

    @Column(name = "password", length = 64, unique = false, nullable = false, updatable = true)
    private String password;
    
    @Column(name = "email", length = 48, unique = true, nullable = false, updatable = true)
    private String email; 
    
    @Column(name = "role_name", length = 16, unique = false, nullable = false, updatable = true)
    private String roleName;

    //TABELA DANYCH PERSONALNYCH
    @Column(name = "city", table = "workers", length = 32, unique = false, nullable = true, updatable = true)
    private String city;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", table = "workers", unique = false, nullable = true, updatable = true)
    private Date birthDate;
    
    @Column(name = "first_name", table = "workers",  length = 16, unique = false, nullable = true, updatable = true)
    private String firstName;
    
    @Column(name = "last_name", table = "workers",  length = 32, unique = false, nullable = true, updatable = true)
    private String lastName;
    
    @Column(name = "phone_number", table = "workers", length = 16, unique = false, nullable = true, updatable = true)
    private String phoneNumber;
    
    @Column(name = "zip_code", table = "workers", length = 16, unique = false, nullable = true, updatable = true)
    private String zipCode;
    
    @Column(name = "country", table = "workers",  length = 32, unique = false, nullable = true, updatable = true)
    private String country;
    
    @Column(name = "street", table = "workers",  length = 32, unique = false, nullable = true, updatable = true)
    private String street;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private List<Qualifications> qualificationsCollection = new ArrayList<Qualifications>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private List<HistoryWork> historyWorkCollection = new ArrayList<HistoryWork>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private List<Education> educationCollection = new ArrayList<Education>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    private List<Trainings> trainingsCollection = new ArrayList<Trainings>();

    public Users()
    {
    }

//    public Users(String login, String password, String email, String roleName)
//    {
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.roleName = roleName;
//    }

    public Users(
            Integer userId, 
            String login,
            String nickName,
            String password, 
            String email, 
            String roleName,
            String userName,
            Date activationDate, 
            String city, 
            Date birthDate, 
            String firstName, 
            String lastName, 
            String phoneNumber, 
            String zipCode, 
            String country, 
            String street)
    {
        this.userId = userId;
        this.login = login;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.roleName = roleName;
        this.activationDate = activationDate;
        this.city = city;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.country = country;
        this.street = street;
    }
    
    
    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
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
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }    
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

    public List<Qualifications> getQualificationsCollection()
    {
        return qualificationsCollection;
    }

    public void setQualificationsCollection(List<Qualifications> qualificationsCollection)
    {
        this.qualificationsCollection = qualificationsCollection;
    }

    public List<HistoryWork> getHistoryWorkCollection()
    {
        return historyWorkCollection;
    }

    public void setHistoryWorkCollection(List<HistoryWork> historyWorkCollection)
    {
        this.historyWorkCollection = historyWorkCollection;
    }

    public List<Education> getEducationCollection()
    {
        return educationCollection;
    }

    public void setEducationCollection(List<Education> educationCollection)
    {
        this.educationCollection = educationCollection;
    }

    public List<Trainings> getTrainingsCollection()
    {
        return trainingsCollection;
    }

    public void setTrainingsCollection(List<Trainings> trainingsCollection)
    {
        this.trainingsCollection = trainingsCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Users other = (Users) obj;
        if (!Objects.equals(this.userId, other.userId))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Users{" + "userId=" + userId + ", login=" + login + '}';
    }
  
}
