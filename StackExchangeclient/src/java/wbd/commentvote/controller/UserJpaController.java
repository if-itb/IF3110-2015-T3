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
import wbd.commentvote.Answer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import wbd.commentvote.Question;
import wbd.commentvote.User;
import wbd.commentvote.VoteAnswer;
import wbd.commentvote.VoteQuestion;
import wbd.commentvote.controller.exceptions.IllegalOrphanException;
import wbd.commentvote.controller.exceptions.NonexistentEntityException;
import wbd.commentvote.controller.exceptions.RollbackFailureException;

/**
 *
 * @author gazandic
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
    if (user.getAnswerCollection() == null) {
      user.setAnswerCollection(new ArrayList<Answer>());
    }
    if (user.getQuestionCollection() == null) {
      user.setQuestionCollection(new ArrayList<Question>());
    }
    if (user.getVoteAnswerCollection() == null) {
      user.setVoteAnswerCollection(new ArrayList<VoteAnswer>());
    }
    if (user.getVoteQuestionCollection() == null) {
      user.setVoteQuestionCollection(new ArrayList<VoteQuestion>());
    }
    EntityManager em = null;
    try {
      utx.begin();
      em = getEntityManager();
      Collection<Answer> attachedAnswerCollection = new ArrayList<Answer>();
      for (Answer answerCollectionAnswerToAttach : user.getAnswerCollection()) {
        answerCollectionAnswerToAttach = em.getReference(answerCollectionAnswerToAttach.getClass(), answerCollectionAnswerToAttach.getId());
        attachedAnswerCollection.add(answerCollectionAnswerToAttach);
      }
      user.setAnswerCollection(attachedAnswerCollection);
      Collection<Question> attachedQuestionCollection = new ArrayList<Question>();
      for (Question questionCollectionQuestionToAttach : user.getQuestionCollection()) {
        questionCollectionQuestionToAttach = em.getReference(questionCollectionQuestionToAttach.getClass(), questionCollectionQuestionToAttach.getId());
        attachedQuestionCollection.add(questionCollectionQuestionToAttach);
      }
      user.setQuestionCollection(attachedQuestionCollection);
      Collection<VoteAnswer> attachedVoteAnswerCollection = new ArrayList<VoteAnswer>();
      for (VoteAnswer voteAnswerCollectionVoteAnswerToAttach : user.getVoteAnswerCollection()) {
        voteAnswerCollectionVoteAnswerToAttach = em.getReference(voteAnswerCollectionVoteAnswerToAttach.getClass(), voteAnswerCollectionVoteAnswerToAttach.getId());
        attachedVoteAnswerCollection.add(voteAnswerCollectionVoteAnswerToAttach);
      }
      user.setVoteAnswerCollection(attachedVoteAnswerCollection);
      Collection<VoteQuestion> attachedVoteQuestionCollection = new ArrayList<VoteQuestion>();
      for (VoteQuestion voteQuestionCollectionVoteQuestionToAttach : user.getVoteQuestionCollection()) {
        voteQuestionCollectionVoteQuestionToAttach = em.getReference(voteQuestionCollectionVoteQuestionToAttach.getClass(), voteQuestionCollectionVoteQuestionToAttach.getId());
        attachedVoteQuestionCollection.add(voteQuestionCollectionVoteQuestionToAttach);
      }
      user.setVoteQuestionCollection(attachedVoteQuestionCollection);
      em.persist(user);
      for (Answer answerCollectionAnswer : user.getAnswerCollection()) {
        User oldUidOfAnswerCollectionAnswer = answerCollectionAnswer.getUid();
        answerCollectionAnswer.setUid(user);
        answerCollectionAnswer = em.merge(answerCollectionAnswer);
        if (oldUidOfAnswerCollectionAnswer != null) {
          oldUidOfAnswerCollectionAnswer.getAnswerCollection().remove(answerCollectionAnswer);
          oldUidOfAnswerCollectionAnswer = em.merge(oldUidOfAnswerCollectionAnswer);
        }
      }
      for (Question questionCollectionQuestion : user.getQuestionCollection()) {
        User oldUidOfQuestionCollectionQuestion = questionCollectionQuestion.getUid();
        questionCollectionQuestion.setUid(user);
        questionCollectionQuestion = em.merge(questionCollectionQuestion);
        if (oldUidOfQuestionCollectionQuestion != null) {
          oldUidOfQuestionCollectionQuestion.getQuestionCollection().remove(questionCollectionQuestion);
          oldUidOfQuestionCollectionQuestion = em.merge(oldUidOfQuestionCollectionQuestion);
        }
      }
      for (VoteAnswer voteAnswerCollectionVoteAnswer : user.getVoteAnswerCollection()) {
        User oldUidOfVoteAnswerCollectionVoteAnswer = voteAnswerCollectionVoteAnswer.getUid();
        voteAnswerCollectionVoteAnswer.setUid(user);
        voteAnswerCollectionVoteAnswer = em.merge(voteAnswerCollectionVoteAnswer);
        if (oldUidOfVoteAnswerCollectionVoteAnswer != null) {
          oldUidOfVoteAnswerCollectionVoteAnswer.getVoteAnswerCollection().remove(voteAnswerCollectionVoteAnswer);
          oldUidOfVoteAnswerCollectionVoteAnswer = em.merge(oldUidOfVoteAnswerCollectionVoteAnswer);
        }
      }
      for (VoteQuestion voteQuestionCollectionVoteQuestion : user.getVoteQuestionCollection()) {
        User oldUidOfVoteQuestionCollectionVoteQuestion = voteQuestionCollectionVoteQuestion.getUid();
        voteQuestionCollectionVoteQuestion.setUid(user);
        voteQuestionCollectionVoteQuestion = em.merge(voteQuestionCollectionVoteQuestion);
        if (oldUidOfVoteQuestionCollectionVoteQuestion != null) {
          oldUidOfVoteQuestionCollectionVoteQuestion.getVoteQuestionCollection().remove(voteQuestionCollectionVoteQuestion);
          oldUidOfVoteQuestionCollectionVoteQuestion = em.merge(oldUidOfVoteQuestionCollectionVoteQuestion);
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
      Collection<Answer> answerCollectionOld = persistentUser.getAnswerCollection();
      Collection<Answer> answerCollectionNew = user.getAnswerCollection();
      Collection<Question> questionCollectionOld = persistentUser.getQuestionCollection();
      Collection<Question> questionCollectionNew = user.getQuestionCollection();
      Collection<VoteAnswer> voteAnswerCollectionOld = persistentUser.getVoteAnswerCollection();
      Collection<VoteAnswer> voteAnswerCollectionNew = user.getVoteAnswerCollection();
      Collection<VoteQuestion> voteQuestionCollectionOld = persistentUser.getVoteQuestionCollection();
      Collection<VoteQuestion> voteQuestionCollectionNew = user.getVoteQuestionCollection();
      List<String> illegalOrphanMessages = null;
      for (Answer answerCollectionOldAnswer : answerCollectionOld) {
        if (!answerCollectionNew.contains(answerCollectionOldAnswer)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Answer " + answerCollectionOldAnswer + " since its uid field is not nullable.");
        }
      }
      for (Question questionCollectionOldQuestion : questionCollectionOld) {
        if (!questionCollectionNew.contains(questionCollectionOldQuestion)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain Question " + questionCollectionOldQuestion + " since its uid field is not nullable.");
        }
      }
      for (VoteAnswer voteAnswerCollectionOldVoteAnswer : voteAnswerCollectionOld) {
        if (!voteAnswerCollectionNew.contains(voteAnswerCollectionOldVoteAnswer)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain VoteAnswer " + voteAnswerCollectionOldVoteAnswer + " since its uid field is not nullable.");
        }
      }
      for (VoteQuestion voteQuestionCollectionOldVoteQuestion : voteQuestionCollectionOld) {
        if (!voteQuestionCollectionNew.contains(voteQuestionCollectionOldVoteQuestion)) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("You must retain VoteQuestion " + voteQuestionCollectionOldVoteQuestion + " since its uid field is not nullable.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      Collection<Answer> attachedAnswerCollectionNew = new ArrayList<Answer>();
      for (Answer answerCollectionNewAnswerToAttach : answerCollectionNew) {
        answerCollectionNewAnswerToAttach = em.getReference(answerCollectionNewAnswerToAttach.getClass(), answerCollectionNewAnswerToAttach.getId());
        attachedAnswerCollectionNew.add(answerCollectionNewAnswerToAttach);
      }
      answerCollectionNew = attachedAnswerCollectionNew;
      user.setAnswerCollection(answerCollectionNew);
      Collection<Question> attachedQuestionCollectionNew = new ArrayList<Question>();
      for (Question questionCollectionNewQuestionToAttach : questionCollectionNew) {
        questionCollectionNewQuestionToAttach = em.getReference(questionCollectionNewQuestionToAttach.getClass(), questionCollectionNewQuestionToAttach.getId());
        attachedQuestionCollectionNew.add(questionCollectionNewQuestionToAttach);
      }
      questionCollectionNew = attachedQuestionCollectionNew;
      user.setQuestionCollection(questionCollectionNew);
      Collection<VoteAnswer> attachedVoteAnswerCollectionNew = new ArrayList<VoteAnswer>();
      for (VoteAnswer voteAnswerCollectionNewVoteAnswerToAttach : voteAnswerCollectionNew) {
        voteAnswerCollectionNewVoteAnswerToAttach = em.getReference(voteAnswerCollectionNewVoteAnswerToAttach.getClass(), voteAnswerCollectionNewVoteAnswerToAttach.getId());
        attachedVoteAnswerCollectionNew.add(voteAnswerCollectionNewVoteAnswerToAttach);
      }
      voteAnswerCollectionNew = attachedVoteAnswerCollectionNew;
      user.setVoteAnswerCollection(voteAnswerCollectionNew);
      Collection<VoteQuestion> attachedVoteQuestionCollectionNew = new ArrayList<VoteQuestion>();
      for (VoteQuestion voteQuestionCollectionNewVoteQuestionToAttach : voteQuestionCollectionNew) {
        voteQuestionCollectionNewVoteQuestionToAttach = em.getReference(voteQuestionCollectionNewVoteQuestionToAttach.getClass(), voteQuestionCollectionNewVoteQuestionToAttach.getId());
        attachedVoteQuestionCollectionNew.add(voteQuestionCollectionNewVoteQuestionToAttach);
      }
      voteQuestionCollectionNew = attachedVoteQuestionCollectionNew;
      user.setVoteQuestionCollection(voteQuestionCollectionNew);
      user = em.merge(user);
      for (Answer answerCollectionNewAnswer : answerCollectionNew) {
        if (!answerCollectionOld.contains(answerCollectionNewAnswer)) {
          User oldUidOfAnswerCollectionNewAnswer = answerCollectionNewAnswer.getUid();
          answerCollectionNewAnswer.setUid(user);
          answerCollectionNewAnswer = em.merge(answerCollectionNewAnswer);
          if (oldUidOfAnswerCollectionNewAnswer != null && !oldUidOfAnswerCollectionNewAnswer.equals(user)) {
            oldUidOfAnswerCollectionNewAnswer.getAnswerCollection().remove(answerCollectionNewAnswer);
            oldUidOfAnswerCollectionNewAnswer = em.merge(oldUidOfAnswerCollectionNewAnswer);
          }
        }
      }
      for (Question questionCollectionNewQuestion : questionCollectionNew) {
        if (!questionCollectionOld.contains(questionCollectionNewQuestion)) {
          User oldUidOfQuestionCollectionNewQuestion = questionCollectionNewQuestion.getUid();
          questionCollectionNewQuestion.setUid(user);
          questionCollectionNewQuestion = em.merge(questionCollectionNewQuestion);
          if (oldUidOfQuestionCollectionNewQuestion != null && !oldUidOfQuestionCollectionNewQuestion.equals(user)) {
            oldUidOfQuestionCollectionNewQuestion.getQuestionCollection().remove(questionCollectionNewQuestion);
            oldUidOfQuestionCollectionNewQuestion = em.merge(oldUidOfQuestionCollectionNewQuestion);
          }
        }
      }
      for (VoteAnswer voteAnswerCollectionNewVoteAnswer : voteAnswerCollectionNew) {
        if (!voteAnswerCollectionOld.contains(voteAnswerCollectionNewVoteAnswer)) {
          User oldUidOfVoteAnswerCollectionNewVoteAnswer = voteAnswerCollectionNewVoteAnswer.getUid();
          voteAnswerCollectionNewVoteAnswer.setUid(user);
          voteAnswerCollectionNewVoteAnswer = em.merge(voteAnswerCollectionNewVoteAnswer);
          if (oldUidOfVoteAnswerCollectionNewVoteAnswer != null && !oldUidOfVoteAnswerCollectionNewVoteAnswer.equals(user)) {
            oldUidOfVoteAnswerCollectionNewVoteAnswer.getVoteAnswerCollection().remove(voteAnswerCollectionNewVoteAnswer);
            oldUidOfVoteAnswerCollectionNewVoteAnswer = em.merge(oldUidOfVoteAnswerCollectionNewVoteAnswer);
          }
        }
      }
      for (VoteQuestion voteQuestionCollectionNewVoteQuestion : voteQuestionCollectionNew) {
        if (!voteQuestionCollectionOld.contains(voteQuestionCollectionNewVoteQuestion)) {
          User oldUidOfVoteQuestionCollectionNewVoteQuestion = voteQuestionCollectionNewVoteQuestion.getUid();
          voteQuestionCollectionNewVoteQuestion.setUid(user);
          voteQuestionCollectionNewVoteQuestion = em.merge(voteQuestionCollectionNewVoteQuestion);
          if (oldUidOfVoteQuestionCollectionNewVoteQuestion != null && !oldUidOfVoteQuestionCollectionNewVoteQuestion.equals(user)) {
            oldUidOfVoteQuestionCollectionNewVoteQuestion.getVoteQuestionCollection().remove(voteQuestionCollectionNewVoteQuestion);
            oldUidOfVoteQuestionCollectionNewVoteQuestion = em.merge(oldUidOfVoteQuestionCollectionNewVoteQuestion);
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
      Collection<Answer> answerCollectionOrphanCheck = user.getAnswerCollection();
      for (Answer answerCollectionOrphanCheckAnswer : answerCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Answer " + answerCollectionOrphanCheckAnswer + " in its answerCollection field has a non-nullable uid field.");
      }
      Collection<Question> questionCollectionOrphanCheck = user.getQuestionCollection();
      for (Question questionCollectionOrphanCheckQuestion : questionCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Question " + questionCollectionOrphanCheckQuestion + " in its questionCollection field has a non-nullable uid field.");
      }
      Collection<VoteAnswer> voteAnswerCollectionOrphanCheck = user.getVoteAnswerCollection();
      for (VoteAnswer voteAnswerCollectionOrphanCheckVoteAnswer : voteAnswerCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the VoteAnswer " + voteAnswerCollectionOrphanCheckVoteAnswer + " in its voteAnswerCollection field has a non-nullable uid field.");
      }
      Collection<VoteQuestion> voteQuestionCollectionOrphanCheck = user.getVoteQuestionCollection();
      for (VoteQuestion voteQuestionCollectionOrphanCheckVoteQuestion : voteQuestionCollectionOrphanCheck) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the VoteQuestion " + voteQuestionCollectionOrphanCheckVoteQuestion + " in its voteQuestionCollection field has a non-nullable uid field.");
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
