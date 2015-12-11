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
import wbd.commentvote.Question;
import wbd.commentvote.VoteQuestion;
import wbd.commentvote.controller.exceptions.NonexistentEntityException;
import wbd.commentvote.controller.exceptions.RollbackFailureException;

/**
 *
 * @author gazandic
 */
public class VoteQuestionJpaController implements Serializable {

  public VoteQuestionJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(VoteQuestion voteQuestion) throws RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User uid = voteQuestion.getUid();
      if (uid != null) {
        uid = em.getReference(uid.getClass(), uid.getId());
        voteQuestion.setUid(uid);
      }
      Question qid = voteQuestion.getQid();
      if (qid != null) {
        qid = em.getReference(qid.getClass(), qid.getId());
        voteQuestion.setQid(qid);
      }
      em.persist(voteQuestion);
      if (uid != null) {
        uid.getVoteQuestionCollection().add(voteQuestion);
        uid = em.merge(uid);
      }
      if (qid != null) {
        qid.getVoteQuestionCollection().add(voteQuestion);
        qid = em.merge(qid);
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

  public void edit(VoteQuestion voteQuestion) throws NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      VoteQuestion persistentVoteQuestion = em.find(VoteQuestion.class, voteQuestion.getId());
      User uidOld = persistentVoteQuestion.getUid();
      User uidNew = voteQuestion.getUid();
      Question qidOld = persistentVoteQuestion.getQid();
      Question qidNew = voteQuestion.getQid();
      if (uidNew != null) {
        uidNew = em.getReference(uidNew.getClass(), uidNew.getId());
        voteQuestion.setUid(uidNew);
      }
      if (qidNew != null) {
        qidNew = em.getReference(qidNew.getClass(), qidNew.getId());
        voteQuestion.setQid(qidNew);
      }
      voteQuestion = em.merge(voteQuestion);
      if (uidOld != null && !uidOld.equals(uidNew)) {
        uidOld.getVoteQuestionCollection().remove(voteQuestion);
        uidOld = em.merge(uidOld);
      }
      if (uidNew != null && !uidNew.equals(uidOld)) {
        uidNew.getVoteQuestionCollection().add(voteQuestion);
        uidNew = em.merge(uidNew);
      }
      if (qidOld != null && !qidOld.equals(qidNew)) {
        qidOld.getVoteQuestionCollection().remove(voteQuestion);
        qidOld = em.merge(qidOld);
      }
      if (qidNew != null && !qidNew.equals(qidOld)) {
        qidNew.getVoteQuestionCollection().add(voteQuestion);
        qidNew = em.merge(qidNew);
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
        Integer id = voteQuestion.getId();
        if (findVoteQuestion(id) == null) {
          throw new NonexistentEntityException("The voteQuestion with id " + id + " no longer exists.");
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
      VoteQuestion voteQuestion;
      try {
        voteQuestion = em.getReference(VoteQuestion.class, id);
        voteQuestion.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The voteQuestion with id " + id + " no longer exists.", enfe);
      }
      User uid = voteQuestion.getUid();
      if (uid != null) {
        uid.getVoteQuestionCollection().remove(voteQuestion);
        uid = em.merge(uid);
      }
      Question qid = voteQuestion.getQid();
      if (qid != null) {
        qid.getVoteQuestionCollection().remove(voteQuestion);
        qid = em.merge(qid);
      }
      em.remove(voteQuestion);
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

  public List<VoteQuestion> findVoteQuestionEntities() {
    return findVoteQuestionEntities(true, -1, -1);
  }

  public List<VoteQuestion> findVoteQuestionEntities(int maxResults, int firstResult) {
    return findVoteQuestionEntities(false, maxResults, firstResult);
  }

  private List<VoteQuestion> findVoteQuestionEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(VoteQuestion.class));
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

  public VoteQuestion findVoteQuestion(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(VoteQuestion.class, id);
    } finally {
      em.close();
    }
  }

  public int getVoteQuestionCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<VoteQuestion> rt = cq.from(VoteQuestion.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
