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
    @NamedQuery(name = "HistoryWork.findAll", query = "SELECT h FROM HistoryWork h"),
    @NamedQuery(name = "HistoryWork.findById", query = "SELECT h FROM HistoryWork h WHERE h.id = :id"),
    @NamedQuery(name = "HistoryWork.findByWorkBegin", query = "SELECT h FROM HistoryWork h WHERE h.workBegin = :workBegin"),
    @NamedQuery(name = "HistoryWork.findByWorkEnd", query = "SELECT h FROM HistoryWork h WHERE h.workEnd = :workEnd"),
    @NamedQuery(name = "HistoryWork.findByCompany", query = "SELECT h FROM HistoryWork h WHERE h.company = :company"),
    @NamedQuery(name = "HistoryWork.findByPosition", query = "SELECT h FROM HistoryWork h WHERE h.position = :position"),
    @NamedQuery(name = "HistoryWork.findByCharacterOfWork", query = "SELECT h FROM HistoryWork h WHERE h.characterOfWork = :characterOfWork"),
    @NamedQuery(name = "HistoryWork.findByAcquiredSkills", query = "SELECT h FROM HistoryWork h WHERE h.acquiredSkills = :acquiredSkills")
})
public class HistoryWork implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "work_begin", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date workBegin;
    
    @Column(name = "work_end", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)    
    private Date workEnd;
   
    @Column(name = "company", length = 64, unique = false, nullable = true, updatable = true)   
    private String company;
   
    @Column(name = "position", length = 64, unique = false, nullable = true, updatable = true)
    private String position;
  
    @Column(name = "character_of_work", length = 512, unique = false, nullable = true, updatable = true)    
    private String characterOfWork;
   
    @Column(name = "acquired_skills", length = 512, unique = false, nullable = true, updatable = true)
    private String acquiredSkills;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users userId;

    public HistoryWork()
    {
    }

    public HistoryWork(Integer id)
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

    public Date getWorkBegin()
    {
        return workBegin;
    }

    public void setWorkBegin(Date workBegin)
    {
        this.workBegin = workBegin;
    }

    public Date getWorkEnd()
    {
        return workEnd;
    }

    public void setWorkEnd(Date workEnd)
    {
        this.workEnd = workEnd;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getCharacterOfWork()
    {
        return characterOfWork;
    }

    public void setCharacterOfWork(String characterOfWork)
    {
        this.characterOfWork = characterOfWork;
    }

    public String getAcquiredSkills()
    {
        return acquiredSkills;
    }

    public void setAcquiredSkills(String acquiredSkills)
    {
        this.acquiredSkills = acquiredSkills;
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
        if (!(object instanceof HistoryWork))
        {
            return false;
        }
        HistoryWork other = (HistoryWork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cecherz.model.entity.HistoryWork[ id=" + id + " ]";
    }
    
}
