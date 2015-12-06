/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import entity.Answer;
import entity.Answervoter;
import entity.Question;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mfikria
 */
@WebService(serviceName = "Answer_WS")
public class Answer_WS {
    private tool.ConsumerREST cRest = new tool.ConsumerREST();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswers")
    public List<Answer> getAnswers(@WebParam(name = "qid") int qid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
       
        return (List<Answer>)em.createQuery("SELECT q FROM Answer q WHERE q.qid="+qid, Answer.class).getResultList();
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteAnswer")
    public Integer voteAnswer(@WebParam(name = "token") String token, @WebParam(name = "aid") int aid, @WebParam(name = "value") String value) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        int uid = cRest.getUidByToken(token);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        // increment countvotes in Question table
        Answer a = em.find(Answer.class, aid);
                
        em.getTransaction().begin();
        try {
            Answervoter avoter;
            if(hasVotedAnswer(aid, uid, 0)) {
                avoter = (Answervoter)em.createQuery("SELECT a FROM Answervoter a WHERE a.aid=:aid AND a.uid=:uid", Answervoter.class)
                        .setParameter("aid", aid).setParameter("uid", uid)
                        .getSingleResult();
                avoter.setValue(value);
            }
            else {
                avoter = new Answervoter();
                avoter.setAid(aid);
                avoter.setUid(uid);
                avoter.setValue(value);
                avoter.setCreatedtime(new Date());
                em.persist(avoter);
            }
            
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            int vote = getVotesAnswer(aid);
            a.setCountvotes(vote);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        
        emf.close();
        
        return 1;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteAllVoteAnswer")
    public Boolean deleteAllVoteAnswer(@WebParam(name = "token") String token,@WebParam(name = "aid") int aid) {
        // validate token
        int res = cRest.auth(token);
        if(res == -1 || res == 0)
            return false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
       
        // validate user
        int uid = cRest.getUidByToken(token);
        Answer answer = em.find(Answer.class, aid);
        Question question = em.find(Question.class, answer.getQid());
        if(question.getUid() != uid)
            return false;
        
        em.getTransaction().begin();
        try {
            int ex = em.createNativeQuery("DELETE FROM answervoter WHERE aid = " + aid)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        
        emf.close();
        return true;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getVotesAnswer")
    public Integer getVotesAnswer(@WebParam(name = "aid") int aid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        List< String > res = (List<String>)em.createQuery("SELECT a.value FROM Answervoter a WHERE a.aid="+aid, String.class).getResultList();
        Integer sum = 0;
        for(String it : res) {
            sum += Integer.parseInt(it);
        }
        return sum;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "hasVotedAnswer")
    public Boolean hasVotedAnswer(@WebParam(name = "aid") int aid, @WebParam(name = "uid") int uid, @WebParam(name = "status") int status) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
       
        em.getTransaction().begin();
        int count = 0;
        try {
            if(status == 0) {
            count = ((Number)em.createQuery("SELECT COUNT(av) FROM Answervoter av WHERE av.aid=:aid AND av.uid=:uid")
                    .setParameter("aid", aid).setParameter("uid", uid)
                    .getSingleResult()).intValue();
            }
            else {
                count = ((Number)em.createQuery("SELECT COUNT(av) FROM Answervoter av WHERE av.aid=:aid AND av.uid=:uid AND av.value=:value")
                    .setParameter("aid", aid).setParameter("uid", uid)
                    .setParameter("value", status + "")
                    .getSingleResult()).intValue();
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
        emf.close();
        
        return count > 0;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAnswer")
    public Integer createAnswer(@WebParam(name = "token") String token, @WebParam(name = "answer") Answer answer) { 
        // validate token
        if(cRest.auth(token) == 0)
            return 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        Question q = em.find(Question.class, answer.getQid());
        
        em.getTransaction().begin();
        try {
            q.setCountanswers(1+getCountAnswer(answer.getQid()));
            em.persist(answer);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        
        emf.close();
        return cRest.auth(token);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCountAnswer")
    public Integer getCountAnswer(@WebParam(name = "qid") int qid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        return (Integer)em.createQuery("SELECT a FROM Answer a WHERE a.qid=:qid", Integer.class)
                .setParameter("qid", qid).getResultList().size();
    }
    
    
}
