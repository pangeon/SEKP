
package app.facade;

import app.model.entity.HistoryWork;
import app.model.entity.System.PU.HistoryWork_;
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
public class HistoryWorkFacade extends AbstractFacade<HistoryWork>
{
    @PersistenceContext(unitName = "System.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public HistoryWorkFacade()
    {
        super(HistoryWork.class);
    }
    public List<HistoryWork> searchHistoryWorkProperties(String companyPattern, String positionPattern, String aquiredSkillsPattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<HistoryWork> criteriaQuery = criteriaBuilder.createQuery(HistoryWork.class);
        Root<HistoryWork> root = criteriaQuery.from(HistoryWork.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        
        if (null != companyPattern  && !(companyPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(HistoryWork_.company), companyPattern + '%'));
        }
        if (null != positionPattern && !(positionPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(HistoryWork_.position), positionPattern + '%'));
        }
        if (null != aquiredSkillsPattern && !(aquiredSkillsPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(HistoryWork_.acquiredSkills), aquiredSkillsPattern + '%'));
        }
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<HistoryWork> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    
}
