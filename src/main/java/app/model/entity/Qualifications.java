
package app.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
{
    @NamedQuery(name = "Qualifications.findAll", query = "SELECT q FROM Qualifications q"),
    @NamedQuery(name = "Qualifications.findById", query = "SELECT q FROM Qualifications q WHERE q.id = :id"),
    @NamedQuery(name = "Qualifications.findByProgrammingLanguage", query = "SELECT q FROM Qualifications q WHERE q.programmingLanguage = :programmingLanguage"),
    @NamedQuery(name = "Qualifications.findByFrameworks", query = "SELECT q FROM Qualifications q WHERE q.frameworks = :frameworks"),
    @NamedQuery(name = "Qualifications.findByForeignLanguages", query = "SELECT q FROM Qualifications q WHERE q.foreignLanguages = :foreignLanguages"),
    @NamedQuery(name = "Qualifications.findBySoftware", query = "SELECT q FROM Qualifications q WHERE q.software = :software"),
    @NamedQuery(name = "Qualifications.findByOtherSkills", query = "SELECT q FROM Qualifications q WHERE q.otherSkills = :otherSkills")
})
public class Qualifications implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "programming_language", length = 64, unique = false, nullable = true, updatable = true)
    private String programmingLanguage;
    
    @Column(name = "frameworks", length = 64, unique = false, nullable = true, updatable = true)
    private String frameworks;
    
    @Column(name = "foreign_languages", length = 32, unique = false, nullable = true, updatable = true)
    private String foreignLanguages;
    
    @Column(name = "software", length = 64, unique = false, nullable = true, updatable = true)
    private String software;
    
    @Column(name = "other_skills", length = 512, unique = false, nullable = true, updatable = true)
    private String otherSkills;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users userId;

    public Qualifications()
    {
    }

    public Qualifications(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getProgrammingLanguage()
    {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage)
    {
        this.programmingLanguage = programmingLanguage;
    }

    public String getFrameworks()
    {
        return frameworks;
    }

    public void setFrameworks(String frameworks)
    {
        this.frameworks = frameworks;
    }

    public String getForeignLanguages()
    {
        return foreignLanguages;
    }

    public void setForeignLanguages(String foreignLanguages)
    {
        this.foreignLanguages = foreignLanguages;
    }

    public String getSoftware()
    {
        return software;
    }

    public void setSoftware(String software)
    {
        this.software = software;
    }

    public String getOtherSkills()
    {
        return otherSkills;
    }

    public void setOtherSkills(String otherSkills)
    {
        this.otherSkills = otherSkills;
    }

    public Users getUserId()
    {
        return userId;
    }

    public void setUserId(Users userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualifications))
        {
            return false;
        }
        Qualifications other = (Qualifications) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cecherz.model.entity.Qualifications[ id=" + id + " ]";
    }
    
}
