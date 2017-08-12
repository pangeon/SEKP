
package app.facade;

import app.model.entity.Qualifications;
import app.model.entity.System.PU.Qualifications_;
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
public class QualificationsFacade extends AbstractFacade<Qualifications>
{
    @PersistenceContext(unitName = "System.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public QualificationsFacade()
    {
        super(Qualifications.class);
    }
    public List<Qualifications> searchQualificationsProperties(String progLanguagePattern, String frameworkPattern, String foreignLanguagePattern, String softwarePattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Qualifications> criteriaQuery = criteriaBuilder.createQuery(Qualifications.class);
        Root<Qualifications> root = criteriaQuery.from(Qualifications.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        
        if (null != progLanguagePattern  && !(progLanguagePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Qualifications_.programmingLanguage), progLanguagePattern + '%'));
        }
        if (null != frameworkPattern && !(frameworkPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Qualifications_.frameworks), frameworkPattern + '%'));
        }
        if (null != foreignLanguagePattern && !(foreignLanguagePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Qualifications_.foreignLanguages), foreignLanguagePattern + '%'));
        }
        if (null != softwarePattern && !(softwarePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Qualifications_.software), softwarePattern + '%'));
        }
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Qualifications> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    
}
