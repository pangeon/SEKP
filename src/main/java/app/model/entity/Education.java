
package app.model.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries(
{
    @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e"),
    @NamedQuery(name = "Education.findById", query = "SELECT e FROM Education e WHERE e.id = :id"),
    @NamedQuery(name = "Education.findByEducationBegin", query = "SELECT e FROM Education e WHERE e.educationBegin = :educationBegin"),
    @NamedQuery(name = "Education.findByEducationEnd", query = "SELECT e FROM Education e WHERE e.educationEnd = :educationEnd"),
    @NamedQuery(name = "Education.findByNameOfSchool", query = "SELECT e FROM Education e WHERE e.nameOfSchool = :nameOfSchool"),
    @NamedQuery(name = "Education.findByKindOfSchool", query = "SELECT e FROM Education e WHERE e.kindOfSchool = :kindOfSchool"),
    @NamedQuery(name = "Education.findBySpecialization", query = "SELECT e FROM Education e WHERE e.specialization = :specialization")
})
public class Education implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "education_begin", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date educationBegin;
    
    @Column(name = "education_end", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date educationEnd;

    @Column(name = "name_of_school", length = 64, unique = false, nullable = true, updatable = true)
    private String nameOfSchool;

    @Column(name = "kind_of_school", length = 32, unique = false, nullable = true, updatable = true)
    private String kindOfSchool;

    @Column(name = "specialization", length = 512, unique = false, nullable = true, updatable = true)
    private String specialization;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users userId;

    public Education()
    {
    }

    public Education(Integer id)
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

    public Date getEducationBegin()
    {
        return educationBegin;
    }

    public void setEducationBegin(Date educationBegin)
    {
        this.educationBegin = educationBegin;
    }

    public Date getEducationEnd()
    {
        return educationEnd;
    }

    public void setEducationEnd(Date educationEnd)
    {
        this.educationEnd = educationEnd;
    }

    public String getNameOfSchool()
    {
        return nameOfSchool;
    }

    public void setNameOfSchool(String nameOfSchool)
    {
        this.nameOfSchool = nameOfSchool;
    }

    public String getKindOfSchool()
    {
        return kindOfSchool;
    }

    public void setKindOfSchool(String kindOfSchool)
    {
        this.kindOfSchool = kindOfSchool;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
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
        if (!(object instanceof Education))
        {
            return false;
        }
        Education other = (Education) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cecherz.model.entity.Education[ id=" + id + " ]";
    }
    
}
