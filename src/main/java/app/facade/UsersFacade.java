
package app.facade;

import app.exception.AppException;
import app.exception.RollBackOperationException;
import app.exception.interceptors.LoggingInterceptor;
import app.model.entity.System.PU.Users_;
import app.model.entity.Users;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class UsersFacade extends AbstractFacade<Users>
{
    @PersistenceContext(unitName = "System.PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }
    
    @Resource
    private SessionContext sctx;

    public UsersFacade()
    {
        super(Users.class);
    }
    @Override
    public void edit(Users entity) throws AppException {
        try {
            super.edit(entity);
            em.flush();
        } catch (OptimisticLockException oe) {
            throw RollBackOperationException.BuildExceptionOptimisticLock(entity, oe);
        }
    }
    public Users getSingleWorker() {
        return (Users) em.createNamedQuery("Users.findByLogin").getSingleResult();
    }
    public Users findUserByLogin(String login) {
        TypedQuery tq = em.createNamedQuery("Users.findByLogin", Users.class);
        tq.setParameter("login", login);
        return (Users) tq.getSingleResult();
    } 
    public List<Users> searchUsers(String firstNamePattern, String lastNamePattern, String nickPattern, String emailPattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = criteriaQuery.from(Users.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        if (null != firstNamePattern && !(firstNamePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.firstName), firstNamePattern + '%'));
        }
        if (null != lastNamePattern  && !(lastNamePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.lastName), lastNamePattern + '%'));
        }
        if (null != nickPattern && !(nickPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.nickName), nickPattern + '%'));
        }
        if (null != emailPattern && !(emailPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.email), emailPattern + '%'));
        }
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Users> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    public List<Users> searchUsersAdminPage(String loginPattern, String emailPatternAdminList, String rolePattern, String activationDatePattern) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = criteriaQuery.from(Users.class);
        criteriaQuery.select(root);
        
        Predicate predicate = criteriaBuilder.conjunction();
        if (null != loginPattern && !(loginPattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.login), loginPattern + '%'));
        }
        if (null != emailPatternAdminList  && !(emailPatternAdminList.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.email), emailPatternAdminList + '%'));
        }
        if (null != rolePattern && !(rolePattern.isEmpty())) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Users_.roleName), rolePattern + '%'));
        }
        // Wartoby to naprawić sortowanie
        if (null != activationDatePattern && !(activationDatePattern.isEmpty())) {
            //TypedQuery<Users> actdate = em.createQuery("SELECT u FROM Users u WHERE u.activationDate = :activationDate", Users.class);
            //predicate = (Predicate) criteriaBuilder.desc(root.get(Users_.activationDate));
        }
        criteriaQuery = criteriaQuery.where(predicate);
        TypedQuery<Users> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }    
}
