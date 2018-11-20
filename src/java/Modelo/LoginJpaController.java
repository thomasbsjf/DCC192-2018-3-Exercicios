
package Modelo;

import Modelo.exceptions.NonexistentEntityException;
import Modelo.exceptions.PreexistingEntityException;
import Modelo.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

public class LoginJpaController implements Serializable {

    private UserTransaction utx = null;
    
    @PersistenceUnit (unitName = "ExerciciosPU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("ExerciciosPU");
        }
        return emf.createEntityManager();
    }

        private void setUserTransaction() throws NamingException{
        Context context = new InitialContext();      
        
        if(utx == null){
            utx = (UserTransaction) context.lookup ("java:app/usuarioDB");
         }  
    }
    
    public void create(Login login) throws Modelo.exceptions.PreexistingEntityException, Modelo.exceptions.RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(login);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Modelo.exceptions.RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findLogin(login.getLogin()) != null) {
                throw new Modelo.exceptions.PreexistingEntityException("Login " + login + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Login login) throws Modelo.exceptions.NonexistentEntityException, Modelo.exceptions.RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            login = em.merge(login);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Modelo.exceptions.RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = login.getLogin();
                if (findLogin(id) == null) {
                    throw new Modelo.exceptions.NonexistentEntityException("The login with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws Modelo.exceptions.NonexistentEntityException, Modelo.exceptions.RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Login login;
            try {
                login = em.getReference(Login.class, id);
                login.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new Modelo.exceptions.NonexistentEntityException("The login with id " + id + " no longer exists.", enfe);
            }
            em.remove(login);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new Modelo.exceptions.RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Login> findLoginEntities() {
        return findLoginEntities(true, -1, -1);
    }

    public List<Login> findLoginEntities(int maxResults, int firstResult) {
        return findLoginEntities(false, maxResults, firstResult);
    }

    private List<Login> findLoginEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Login.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Login findLogin(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Login.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoginCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Login> rt = cq.from(Login.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    //Usar para pegar a lista
    public List<Login> findAll(){
        EntityManager em = getEntityManager();
         return em.createNamedQuery("Login.findAll").getResultList();
    }
    
}
