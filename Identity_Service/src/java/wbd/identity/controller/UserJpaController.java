/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import wbd.identity.Token;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import wbd.identity.User;
import wbd.identity.controller.exceptions.IllegalOrphanException;
import wbd.identity.controller.exceptions.NonexistentEntityException;
import wbd.identity.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Afrizal
 */
public class UserJpaController implements Serializable {

  public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(User user) throws RollbackFailureException, Exception {
    if (user.getTokenCollection() == null) {
      user.setTokenCollection(new ArrayList<Token>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Collection<Token> attachedTokenCollection = new ArrayList<Token>();
      for (Token tokenCollectionTokenToAttach : user.getTokenCollection()) {
        tokenCollectionTokenToAttach = em.getReference(tokenCollectionTokenToAttach.getClass(), tokenCollectionTokenToAttach.getVal());
        attachedTokenCollection.add(tokenCollectionTokenToAttach);
      }
      user.setTokenCollection(attachedTokenCollection);
      em.persist(user);
      for (Token tokenCollectionToken : user.getTokenCollection()) {
        User oldUidOfTokenCollectionToken = tokenCollectionToken.getUid();
        tokenCollectionToken.setUid(user);
        tokenCollectionToken = em.merge(tokenCollectionToken);
        if (oldUidOfTokenCollectionToken != null) {
          oldUidOfTokenCollectionToken.getTokenCollection().remove(tokenCollectionToken);
          oldUidOfTokenCollectionToken = em.merge(oldUidOfTokenCollectionToken);
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User persistentUser = em.find(User.class, user.getId());
      Collection<Token> tokenCollectionOld = persistentUser.getTokenCollection();
      Collection<Token> tokenCollectionNew = user.getTokenCollection();
      List<String> illegalOrphanMessages = null;
      for (Token tokenCollectionOldToken : tokenCollectionOld) {
        if (!tokenCollectionNew.contains(tokenCollectionOldToken)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Token " + tokenCollectionOldToken + " since its uid field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Collection<Token> attachedTokenCollectionNew = new ArrayList<Token>();
      for (Token tokenCollectionNewTokenToAttach : tokenCollectionNew) {
        tokenCollectionNewTokenToAttach = em.getReference(tokenCollectionNewTokenToAttach.getClass(), tokenCollectionNewTokenToAttach.getVal());
        attachedTokenCollectionNew.add(tokenCollectionNewTokenToAttach);
      }
      tokenCollectionNew = attachedTokenCollectionNew;
      user.setTokenCollection(tokenCollectionNew);
      user = em.merge(user);
      for (Token tokenCollectionNewToken : tokenCollectionNew) {
        if (!tokenCollectionOld.contains(tokenCollectionNewToken)) {
          User oldUidOfTokenCollectionNewToken = tokenCollectionNewToken.getUid();
          tokenCollectionNewToken.setUid(user);
          tokenCollectionNewToken = em.merge(tokenCollectionNewToken);
          if (oldUidOfTokenCollectionNewToken != null && !oldUidOfTokenCollectionNewToken.equals(user)) {
            oldUidOfTokenCollectionNewToken.getTokenCollection().remove(tokenCollectionNewToken);
            oldUidOfTokenCollectionNewToken = em.merge(oldUidOfTokenCollectionNewToken);
          }
        }
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = user.getId();
        if (findUser(id) == null) {
          throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User user;
      try {
        user = em.getReference(User.class, id);
        user.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<Token> tokenCollectionOrphanCheck = user.getTokenCollection();
      for (Token tokenCollectionOrphanCheckToken : tokenCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Token " + tokenCollectionOrphanCheckToken + " in its tokenCollection field has a non-nullable uid field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(user);
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<User> findUserEntities() {
    return findUserEntities(true, -1, -1);
  }

  public List<User> findUserEntities(int maxResults, int firstResult) {
    return findUserEntities(false, maxResults, firstResult);
  }

  private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(User.class));
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

  public User findUser(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(User.class, id);
    } finally {
      em.close();
    }
  }

  public int getUserCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<User> rt = cq.from(User.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
