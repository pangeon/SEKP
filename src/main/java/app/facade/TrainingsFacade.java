
package app.facade;

import app.model.entity.System.PU.Trainings_;
import app.model.entity.Trainings;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class TrainingsFacade extends AbstractFacade<Trainings>
{
    @PersistenceContext(unitName = "System.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TrainingsFacade()
    {
        super(Trainings.class);
    }
    public List<Trainings> searchTrainingsProperties(String contentTrainingPattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Trainings> criteriaQuery = criteriaBuilder.createQuery(Trainings.class);
        Root<Trainings> root = criteriaQuery.from(Trainings.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        if (null != contentTrainingPattern && !(contentTrainingPattern.isEmpty() )) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Trainings_.contentTraining), contentTrainingPattern + '%'));
        }     
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Trainings> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
        
    }
    
}
