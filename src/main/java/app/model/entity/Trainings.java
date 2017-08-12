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
    @NamedQuery(name = "Trainings.findAll", query = "SELECT t FROM Trainings t"),
    @NamedQuery(name = "Trainings.findById", query = "SELECT t FROM Trainings t WHERE t.id = :id"),
    @NamedQuery(name = "Trainings.findByTrainingBegin", query = "SELECT t FROM Trainings t WHERE t.trainingBegin = :trainingBegin"),
    @NamedQuery(name = "Trainings.findByTrainingEnd", query = "SELECT t FROM Trainings t WHERE t.trainingEnd = :trainingEnd"),
    @NamedQuery(name = "Trainings.findByContentTraining", query = "SELECT t FROM Trainings t WHERE t.contentTraining = :contentTraining"),
    @NamedQuery(name = "Trainings.findByTrainingTime", query = "SELECT t FROM Trainings t WHERE t.trainingTime = :trainingTime")
})
public class Trainings implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "training_begin", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date trainingBegin;
    
    @Column(name = "training_end", unique = false, nullable = true, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date trainingEnd;
    
    @Column(name = "content_training", length = 512, unique = false, nullable = true, updatable = true)
    private String contentTraining;
    
    @Column(name = "training_time", unique = false, nullable = true, updatable = true)
    private Short trainingTime;
    
    @JoinColumn(name = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users userId;

    public Trainings()
    {
    }

    public Trainings(Integer id)
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

    public Date getTrainingBegin()
    {
        return trainingBegin;
    }

    public void setTrainingBegin(Date trainingBegin)
    {
        this.trainingBegin = trainingBegin;
    }

    public Date getTrainingEnd()
    {
        return trainingEnd;
    }

    public void setTrainingEnd(Date trainingEnd)
    {
        this.trainingEnd = trainingEnd;
    }

    public String getContentTraining()
    {
        return contentTraining;
    }

    public void setContentTraining(String contentTraining)
    {
        this.contentTraining = contentTraining;
    }

    public Short getTrainingTime()
    {
        return trainingTime;
    }

    public void setTrainingTime(Short trainingTime)
    {
        this.trainingTime = trainingTime;
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
        if (!(object instanceof Trainings))
        {
            return false;
        }
        Trainings other = (Trainings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "cecherz.model.entity.Trainings[ id=" + id + " ]";
    }
    
}
