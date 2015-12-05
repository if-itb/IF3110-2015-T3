/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import wbd.identity.Token;
import wbd.identity.User;
import wbd.identity.controller.exceptions.NonexistentEntityException;
import wbd.identity.controller.exceptions.PreexistingEntityException;
import wbd.identity.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Afrizal
 */
public class TokenJpaController implements Serializable {

  public TokenJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Token token) throws PreexistingEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User uid = token.getUid();
      if (uid != null) {
        uid = em.getReference(uid.getClass(), uid.getId());
        token.setUid(uid);
      }
      em.persist(token);
      if (uid != null) {
        uid.getTokenCollection().add(token);
        uid = em.merge(uid);
      }
      utx.commit();
    } catch (Exception ex) {
      try {
        utx.rollback();
      } catch (Exception re) {
        throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
      }
      if (findToken(token.getVal()) != null) {
        throw new PreexistingEntityException("Token " + token + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Token token) throws NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Token persistentToken = em.find(Token.class, token.getVal());
      User uidOld = persistentToken.getUid();
      User uidNew = token.getUid();
      if (uidNew != null) {
        uidNew = em.getReference(uidNew.getClass(), uidNew.getId());
        token.setUid(uidNew);
      }
      token = em.merge(token);
      if (uidOld != null && !uidOld.equals(uidNew)) {
        uidOld.getTokenCollection().remove(token);
        uidOld = em.merge(uidOld);
      }
      if (uidNew != null && !uidNew.equals(uidOld)) {
        uidNew.getTokenCollection().add(token);
        uidNew = em.merge(uidNew);
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
        String id = token.getVal();
        if (findToken(id) == null) {
          throw new NonexistentEntityException("The token with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Token token;
      try {
        token = em.getReference(Token.class, id);
        token.getVal();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The token with id " + id + " no longer exists.", enfe);
      }
      User uid = token.getUid();
      if (uid != null) {
        uid.getTokenCollection().remove(token);
        uid = em.merge(uid);
      }
      em.remove(token);
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

  public List<Token> findTokenEntities() {
    return findTokenEntities(true, -1, -1);
  }

  public List<Token> findTokenEntities(int maxResults, int firstResult) {
    return findTokenEntities(false, maxResults, firstResult);
  }

  private List<Token> findTokenEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Token.class));
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

  public Token findToken(String id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Token.class, id);
    } finally {
      em.close();
    }
  }

  public int getTokenCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Token> rt = cq.from(Token.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
