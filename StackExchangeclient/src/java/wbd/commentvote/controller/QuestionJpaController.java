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
import wbd.commentvote.User;
import wbd.commentvote.Answer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import wbd.commentvote.Question;
import wbd.commentvote.VoteQuestion;
import wbd.commentvote.controller.exceptions.IllegalOrphanException;
import wbd.commentvote.controller.exceptions.NonexistentEntityException;
import wbd.commentvote.controller.exceptions.RollbackFailureException;

/**
 *
 * @author gazandic
 */
public class QuestionJpaController implements Serializable {

  public QuestionJpaController(UserTransaction utx, EntityManagerFactory emf) {
    this.utx = utx;
    this.emf = emf;
  }
  private UserTransaction utx = null;
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Question question) throws RollbackFailureException, Exception {
    if (question.getAnswerCollection() == null) {
      question.setAnswerCollection(new ArrayList<Answer>());
    }
    if (question.getVoteQuestionCollection() == null) {
      question.setVoteQuestionCollection(new ArrayList<VoteQuestion>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      User uid = question.getUid();
      if (uid != null) {
        uid = em.getReference(uid.getClass(), uid.getId());
        question.setUid(uid);
      }
      Collection<Answer> attachedAnswerCollection = new ArrayList<Answer>();
      for (Answer answerCollectionAnswerToAttach : question.getAnswerCollection()) {
        answerCollectionAnswerToAttach = em.getReference(answerCollectionAnswerToAttach.getClass(), answerCollectionAnswerToAttach.getId());
        attachedAnswerCollection.add(answerCollectionAnswerToAttach);
      }
      question.setAnswerCollection(attachedAnswerCollection);
      Collection<VoteQuestion> attachedVoteQuestionCollection = new ArrayList<VoteQuestion>();
      for (VoteQuestion voteQuestionCollectionVoteQuestionToAttach : question.getVoteQuestionCollection()) {
        voteQuestionCollectionVoteQuestionToAttach = em.getReference(voteQuestionCollectionVoteQuestionToAttach.getClass(), voteQuestionCollectionVoteQuestionToAttach.getId());
        attachedVoteQuestionCollection.add(voteQuestionCollectionVoteQuestionToAttach);
      }
      question.setVoteQuestionCollection(attachedVoteQuestionCollection);
      em.persist(question);
      if (uid != null) {
        uid.getQuestionCollection().add(question);
        uid = em.merge(uid);
      }
      for (Answer answerCollectionAnswer : question.getAnswerCollection()) {
        Question oldQidOfAnswerCollectionAnswer = answerCollectionAnswer.getQid();
        answerCollectionAnswer.setQid(question);
        answerCollectionAnswer = em.merge(answerCollectionAnswer);
        if (oldQidOfAnswerCollectionAnswer != null) {
          oldQidOfAnswerCollectionAnswer.getAnswerCollection().remove(answerCollectionAnswer);
          oldQidOfAnswerCollectionAnswer = em.merge(oldQidOfAnswerCollectionAnswer);
        }
      }
      for (VoteQuestion voteQuestionCollectionVoteQuestion : question.getVoteQuestionCollection()) {
        Question oldQidOfVoteQuestionCollectionVoteQuestion = voteQuestionCollectionVoteQuestion.getQid();
        voteQuestionCollectionVoteQuestion.setQid(question);
        voteQuestionCollectionVoteQuestion = em.merge(voteQuestionCollectionVoteQuestion);
        if (oldQidOfVoteQuestionCollectionVoteQuestion != null) {
          oldQidOfVoteQuestionCollectionVoteQuestion.getVoteQuestionCollection().remove(voteQuestionCollectionVoteQuestion);
          oldQidOfVoteQuestionCollectionVoteQuestion = em.merge(oldQidOfVoteQuestionCollectionVoteQuestion);
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

  public void edit(Question question) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Question persistentQuestion = em.find(Question.class, question.getId());
      User uidOld = persistentQuestion.getUid();
      User uidNew = question.getUid();
      Collection<Answer> answerCollectionOld = persistentQuestion.getAnswerCollection();
      Collection<Answer> answerCollectionNew = question.getAnswerCollection();
      Collection<VoteQuestion> voteQuestionCollectionOld = persistentQuestion.getVoteQuestionCollection();
      Collection<VoteQuestion> voteQuestionCollectionNew = question.getVoteQuestionCollection();
      List<String> illegalOrphanMessages = null;
      for (Answer answerCollectionOldAnswer : answerCollectionOld) {
        if (!answerCollectionNew.contains(answerCollectionOldAnswer)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Answer " + answerCollectionOldAnswer + " since its qid field is not nullable.");
        }
      }
      for (VoteQuestion voteQuestionCollectionOldVoteQuestion : voteQuestionCollectionOld) {
        if (!voteQuestionCollectionNew.contains(voteQuestionCollectionOldVoteQuestion)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain VoteQuestion " + voteQuestionCollectionOldVoteQuestion + " since its qid field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (uidNew != null) {
        uidNew = em.getReference(uidNew.getClass(), uidNew.getId());
        question.setUid(uidNew);
      }
      Collection<Answer> attachedAnswerCollectionNew = new ArrayList<Answer>();
      for (Answer answerCollectionNewAnswerToAttach : answerCollectionNew) {
        answerCollectionNewAnswerToAttach = em.getReference(answerCollectionNewAnswerToAttach.getClass(), answerCollectionNewAnswerToAttach.getId());
        attachedAnswerCollectionNew.add(answerCollectionNewAnswerToAttach);
      }
      answerCollectionNew = attachedAnswerCollectionNew;
      question.setAnswerCollection(answerCollectionNew);
      Collection<VoteQuestion> attachedVoteQuestionCollectionNew = new ArrayList<VoteQuestion>();
      for (VoteQuestion voteQuestionCollectionNewVoteQuestionToAttach : voteQuestionCollectionNew) {
        voteQuestionCollectionNewVoteQuestionToAttach = em.getReference(voteQuestionCollectionNewVoteQuestionToAttach.getClass(), voteQuestionCollectionNewVoteQuestionToAttach.getId());
        attachedVoteQuestionCollectionNew.add(voteQuestionCollectionNewVoteQuestionToAttach);
      }
      voteQuestionCollectionNew = attachedVoteQuestionCollectionNew;
      question.setVoteQuestionCollection(voteQuestionCollectionNew);
      question = em.merge(question);
      if (uidOld != null && !uidOld.equals(uidNew)) {
        uidOld.getQuestionCollection().remove(question);
        uidOld = em.merge(uidOld);
      }
      if (uidNew != null && !uidNew.equals(uidOld)) {
        uidNew.getQuestionCollection().add(question);
        uidNew = em.merge(uidNew);
      }
      for (Answer answerCollectionNewAnswer : answerCollectionNew) {
        if (!answerCollectionOld.contains(answerCollectionNewAnswer)) {
          Question oldQidOfAnswerCollectionNewAnswer = answerCollectionNewAnswer.getQid();
          answerCollectionNewAnswer.setQid(question);
          answerCollectionNewAnswer = em.merge(answerCollectionNewAnswer);
          if (oldQidOfAnswerCollectionNewAnswer != null && !oldQidOfAnswerCollectionNewAnswer.equals(question)) {
            oldQidOfAnswerCollectionNewAnswer.getAnswerCollection().remove(answerCollectionNewAnswer);
            oldQidOfAnswerCollectionNewAnswer = em.merge(oldQidOfAnswerCollectionNewAnswer);
          }
        }
      }
      for (VoteQuestion voteQuestionCollectionNewVoteQuestion : voteQuestionCollectionNew) {
        if (!voteQuestionCollectionOld.contains(voteQuestionCollectionNewVoteQuestion)) {
          Question oldQidOfVoteQuestionCollectionNewVoteQuestion = voteQuestionCollectionNewVoteQuestion.getQid();
          voteQuestionCollectionNewVoteQuestion.setQid(question);
          voteQuestionCollectionNewVoteQuestion = em.merge(voteQuestionCollectionNewVoteQuestion);
          if (oldQidOfVoteQuestionCollectionNewVoteQuestion != null && !oldQidOfVoteQuestionCollectionNewVoteQuestion.equals(question)) {
            oldQidOfVoteQuestionCollectionNewVoteQuestion.getVoteQuestionCollection().remove(voteQuestionCollectionNewVoteQuestion);
            oldQidOfVoteQuestionCollectionNewVoteQuestion = em.merge(oldQidOfVoteQuestionCollectionNewVoteQuestion);
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
        Integer id = question.getId();
        if (findQuestion(id) == null) {
          throw new NonexistentEntityException("The question with id " + id + " no longer exists.");
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
      Question question;
      try {
        question = em.getReference(Question.class, id);
        question.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The question with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Collection<Answer> answerCollectionOrphanCheck = question.getAnswerCollection();
      for (Answer answerCollectionOrphanCheckAnswer : answerCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Question (" + question + ") cannot be destroyed since the Answer " + answerCollectionOrphanCheckAnswer + " in its answerCollection field has a non-nullable qid field.");
      }
      Collection<VoteQuestion> voteQuestionCollectionOrphanCheck = question.getVoteQuestionCollection();
      for (VoteQuestion voteQuestionCollectionOrphanCheckVoteQuestion : voteQuestionCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Question (" + question + ") cannot be destroyed since the VoteQuestion " + voteQuestionCollectionOrphanCheckVoteQuestion + " in its voteQuestionCollection field has a non-nullable qid field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      User uid = question.getUid();
      if (uid != null) {
        uid.getQuestionCollection().remove(question);
        uid = em.merge(uid);
      }
      em.remove(question);
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

  public List<Question> findQuestionEntities() {
    return findQuestionEntities(true, -1, -1);
  }

  public List<Question> findQuestionEntities(int maxResults, int firstResult) {
    return findQuestionEntities(false, maxResults, firstResult);
  }

  private List<Question> findQuestionEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Question.class));
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

  public Question findQuestion(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Question.class, id);
    } finally {
      em.close();
    }
  }

  public int getQuestionCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Question> rt = cq.from(Question.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
