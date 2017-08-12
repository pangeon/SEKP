
package app.facade;

import app.model.entity.Education;
import app.model.entity.HistoryWork;
import app.model.entity.System.PU.Education_;
import app.model.entity.System.PU.HistoryWork_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class EducationFacade extends AbstractFacade<Education>
{
    @PersistenceContext(unitName = "System.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EducationFacade()
    {
        super(Education.class);
    }
    public List<Education> searchEducationProperties(String nameOfSchoolPattern, String kindOfSchoolPattern, String specializationPattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Education> criteriaQuery = criteriaBuilder.createQuery(Education.class);
        Root<Education> root = criteriaQuery.from(Education.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        
        if (null != nameOfSchoolPattern  && !(nameOfSchoolPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Education_.nameOfSchool), nameOfSchoolPattern + '%'));
        }
        if (null != kindOfSchoolPattern && !(kindOfSchoolPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Education_.kindOfSchool), kindOfSchoolPattern + '%'));
        }
        if (null != specializationPattern && !(specializationPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Education_.specialization), specializationPattern + '%'));
        }
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Education> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
