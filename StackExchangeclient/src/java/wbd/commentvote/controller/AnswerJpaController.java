/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import wbd.commentvote.Question;
import wbd.commentvote.User;
import wbd.commentvote.VoteAnswer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import wbd.commentvote.Answer;
import wbd.commentvote.controller.exceptions.IllegalOrphanException;
import wbd.commentvote.controller.exceptions.NonexistentEntityException;
import wbd.commentvote.controller.exceptions.RollbackFailureException;

/**
 *
 * @author gazandic
 */
public class AnswerJpaController implements Serializable {

  public AnswerJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Answer answer) throws RollbackFailureException, Exception {
    if (answer.getVoteAnswerCollection() == null) {
      answer.setVoteAnswerCollection(new ArrayList<VoteAnswer>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Question qid = answer.getQid();
      if (qid != null) {
        qid = em.getReference(qid.getClass(), qid.getId());
        answer.setQid(qid);
      }
      User uid = answer.getUid();
      if (uid != null) {
        uid = em.getReference(uid.getClass(), uid.getId());
        answer.setUid(uid);
      }
      Collection<VoteAnswer> attachedVoteAnswerCollection = new ArrayList<VoteAnswer>();
      for (VoteAnswer voteAnswerCollectionVoteAnswerToAttach : answer.getVoteAnswerCollection()) {
        voteAnswerCollectionVoteAnswerToAttach = em.getReference(voteAnswerCollectionVoteAnswerToAttach.getClass(), voteAnswerCollectionVoteAnswerToAttach.getId());
        attachedVoteAnswerCollection.add(voteAnswerCollectionVoteAnswerToAttach);
      }
      answer.setVoteAnswerCollection(attachedVoteAnswerCollection);
      em.persist(answer);
      if (qid != null) {
        qid.getAnswerCollection().add(answer);
        qid = em.merge(qid);
      }
      if (uid != null) {
        uid.getAnswerCollection().add(answer);
        uid = em.merge(uid);
      }
      for (VoteAnswer voteAnswerCollectionVoteAnswer : answer.getVoteAnswerCollection()) {
        Answer oldAidOfVoteAnswerCollectionVoteAnswer = voteAnswerCollectionVoteAnswer.getAid();
        voteAnswerCollectionVoteAnswer.setAid(answer);
        voteAnswerCollectionVoteAnswer = em.merge(voteAnswerCollectionVoteAnswer);
        if (oldAidOfVoteAnswerCollectionVoteAnswer != null) {
          oldAidOfVoteAnswerCollectionVoteAnswer.getVoteAnswerCollection().remove(voteAnswerCollectionVoteAnswer);
          oldAidOfVoteAnswerCollectionVoteAnswer = em.merge(oldAidOfVoteAnswerCollectionVoteAnswer);
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

  public void edit(Answer answer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Answer persistentAnswer = em.find(Answer.class, answer.getId());
      Question qidOld = persistentAnswer.getQid();
      Question qidNew = answer.getQid();
      User uidOld = persistentAnswer.getUid();
      User uidNew = answer.getUid();
      Collection<VoteAnswer> voteAnswerCollectionOld = persistentAnswer.getVoteAnswerCollection();
      Collection<VoteAnswer> voteAnswerCollectionNew = answer.getVoteAnswerCollection();
      List<String> illegalOrphanMessages = null;
      for (VoteAnswer voteAnswerCollectionOldVoteAnswer : voteAnswerCollectionOld) {
        if (!voteAnswerCollectionNew.contains(voteAnswerCollectionOldVoteAnswer)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain VoteAnswer " + voteAnswerCollectionOldVoteAnswer + " since its aid field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (qidNew != null) {
        qidNew = em.getReference(qidNew.getClass(), qidNew.getId());
        answer.setQid(qidNew);
      }
      if (uidNew != null) {
        uidNew = em.getReference(uidNew.getClass(), uidNew.getId());
        answer.setUid(uidNew);
      }
      Collection<VoteAnswer> attachedVoteAnswerCollectionNew = new ArrayList<VoteAnswer>();
      for (VoteAnswer voteAnswerCollectionNewVoteAnswerToAttach : voteAnswerCollectionNew) {
        voteAnswerCollectionNewVoteAnswerToAttach = em.getReference(voteAnswerCollectionNewVoteAnswerToAttach.getClass(), voteAnswerCollectionNewVoteAnswerToAttach.getId());
        attachedVoteAnswerCollectionNew.add(voteAnswerCollectionNewVoteAnswerToAttach);
      }
      voteAnswerCollectionNew = attachedVoteAnswerCollectionNew;
      answer.setVoteAnswerCollection(voteAnswerCollectionNew);
      answer = em.merge(answer);
      if (qidOld != null && !qidOld.equals(qidNew)) {
        qidOld.getAnswerCollection().remove(answer);
        qidOld = em.merge(qidOld);
      }
      if (qidNew != null && !qidNew.equals(qidOld)) {
        qidNew.getAnswerCollection().add(answer);
        qidNew = em.merge(qidNew);
      }
      if (uidOld != null && !uidOld.equals(uidNew)) {
        uidOld.getAnswerCollection().remove(answer);
        uidOld = em.merge(uidOld);
      }
      if (uidNew != null && !uidNew.equals(uidOld)) {
        uidNew.getAnswerCollection().add(answer);
        uidNew = em.merge(uidNew);
      }
      for (VoteAnswer voteAnswerCollectionNewVoteAnswer : voteAnswerCollectionNew) {
        if (!voteAnswerCollectionOld.contains(voteAnswerCollectionNewVoteAnswer)) {
          Answer oldAidOfVoteAnswerCollectionNewVoteAnswer = voteAnswerCollectionNewVoteAnswer.getAid();
          voteAnswerCollectionNewVoteAnswer.setAid(answer);
          voteAnswerCollectionNewVoteAnswer = em.merge(voteAnswerCollectionNewVoteAnswer);
          if (oldAidOfVoteAnswerCollectionNewVoteAnswer != null && !oldAidOfVoteAnswerCollectionNewVoteAnswer.equals(answer)) {
            oldAidOfVoteAnswerCollectionNewVoteAnswer.getVoteAnswerCollection().remove(voteAnswerCollectionNewVoteAnswer);
            oldAidOfVoteAnswerCollectionNewVoteAnswer = em.merge(oldAidOfVoteAnswerCollectionNewVoteAnswer);
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
        Integer id = answer.getId();
        if (findAnswer(id) == null) {
          throw new NonexistentEntityException("The answer with id " + id + " no longer exists.");
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
      Answer answer;
      try {
        answer = em.getReference(Answer.class, id);
        answer.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The answer with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<VoteAnswer> voteAnswerCollectionOrphanCheck = answer.getVoteAnswerCollection();
      for (VoteAnswer voteAnswerCollectionOrphanCheckVoteAnswer : voteAnswerCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Answer (" + answer + ") cannot be destroyed since the VoteAnswer " + voteAnswerCollectionOrphanCheckVoteAnswer + " in its voteAnswerCollection field has a non-nullable aid field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Question qid = answer.getQid();
      if (qid != null) {
        qid.getAnswerCollection().remove(answer);
        qid = em.merge(qid);
      }
      User uid = answer.getUid();
      if (uid != null) {
        uid.getAnswerCollection().remove(answer);
        uid = em.merge(uid);
      }
      em.remove(answer);
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

  public List<Answer> findAnswerEntities() {
    return findAnswerEntities(true, -1, -1);
  }

  public List<Answer> findAnswerEntities(int maxResults, int firstResult) {
    return findAnswerEntities(false, maxResults, firstResult);
  }

  private List<Answer> findAnswerEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Answer.class));
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

  public Answer findAnswer(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Answer.class, id);
    } finally {
      em.close();
    }
  }

  public int getAnswerCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Answer> rt = cq.from(Answer.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
