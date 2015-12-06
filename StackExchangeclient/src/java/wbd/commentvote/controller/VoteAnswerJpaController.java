/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import wbd.commentvote.User;
import wbd.commentvote.Answer;
import wbd.commentvote.VoteAnswer;
import wbd.commentvote.controller.exceptions.NonexistentEntityException;
import wbd.commentvote.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Afrizal
 */
public class VoteAnswerJpaController implements Serializable {

  public VoteAnswerJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(VoteAnswer voteAnswer) throws RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User uid = voteAnswer.getUid();
      if (uid != null) {
        uid = em.getReference(uid.getClass(), uid.getId());
        voteAnswer.setUid(uid);
      }
      Answer aid = voteAnswer.getAid();
      if (aid != null) {
        aid = em.getReference(aid.getClass(), aid.getId());
        voteAnswer.setAid(aid);
      }
      em.persist(voteAnswer);
      if (uid != null) {
        uid.getVoteAnswerCollection().add(voteAnswer);
        uid = em.merge(uid);
      }
      if (aid != null) {
        aid.getVoteAnswerCollection().add(voteAnswer);
        aid = em.merge(aid);
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

  public void edit(VoteAnswer voteAnswer) throws NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      VoteAnswer persistentVoteAnswer = em.find(VoteAnswer.class, voteAnswer.getId());
      User uidOld = persistentVoteAnswer.getUid();
      User uidNew = voteAnswer.getUid();
      Answer aidOld = persistentVoteAnswer.getAid();
      Answer aidNew = voteAnswer.getAid();
      if (uidNew != null) {
        uidNew = em.getReference(uidNew.getClass(), uidNew.getId());
        voteAnswer.setUid(uidNew);
      }
      if (aidNew != null) {
        aidNew = em.getReference(aidNew.getClass(), aidNew.getId());
        voteAnswer.setAid(aidNew);
      }
      voteAnswer = em.merge(voteAnswer);
      if (uidOld != null && !uidOld.equals(uidNew)) {
        uidOld.getVoteAnswerCollection().remove(voteAnswer);
        uidOld = em.merge(uidOld);
      }
      if (uidNew != null && !uidNew.equals(uidOld)) {
        uidNew.getVoteAnswerCollection().add(voteAnswer);
        uidNew = em.merge(uidNew);
      }
      if (aidOld != null && !aidOld.equals(aidNew)) {
        aidOld.getVoteAnswerCollection().remove(voteAnswer);
        aidOld = em.merge(aidOld);
      }
      if (aidNew != null && !aidNew.equals(aidOld)) {
        aidNew.getVoteAnswerCollection().add(voteAnswer);
        aidNew = em.merge(aidNew);
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
        Integer id = voteAnswer.getId();
        if (findVoteAnswer(id) == null) {
          throw new NonexistentEntityException("The voteAnswer with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      VoteAnswer voteAnswer;
      try {
        voteAnswer = em.getReference(VoteAnswer.class, id);
        voteAnswer.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The voteAnswer with id " + id + " no longer exists.", enfe);
      }
      User uid = voteAnswer.getUid();
      if (uid != null) {
        uid.getVoteAnswerCollection().remove(voteAnswer);
        uid = em.merge(uid);
      }
      Answer aid = voteAnswer.getAid();
      if (aid != null) {
        aid.getVoteAnswerCollection().remove(voteAnswer);
        aid = em.merge(aid);
      }
      em.remove(voteAnswer);
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

  public List<VoteAnswer> findVoteAnswerEntities() {
    return findVoteAnswerEntities(true, -1, -1);
  }

  public List<VoteAnswer> findVoteAnswerEntities(int maxResults, int firstResult) {
    return findVoteAnswerEntities(false, maxResults, firstResult);
  }

  private List<VoteAnswer> findVoteAnswerEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(VoteAnswer.class));
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

  public VoteAnswer findVoteAnswer(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(VoteAnswer.class, id);
    } finally {
      em.close();
    }
  }

  public int getVoteAnswerCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<VoteAnswer> rt = cq.from(VoteAnswer.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
