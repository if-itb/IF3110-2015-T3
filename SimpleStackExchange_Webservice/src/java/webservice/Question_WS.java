/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import entity.Question;
import entity.Questionvoter;
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
@WebService(serviceName = "Question_WS")
public class Question_WS {
    private tool.ConsumerREST cRest = new tool.ConsumerREST();

   /**
     * Web service operation
     */
    @WebMethod(operationName = "createQuestion")
    public Integer createQuestion(@WebParam(name = "token") String token, @WebParam(name = "question") Question question) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            
            em.persist(question);
            
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
     * @return 
     */
    @WebMethod(operationName = "listQuestion")
    public List<Question> listQuestion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Question.class));
        return em.createQuery(cq).getResultList();
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchQuestion")
    public List<Question> searchQuestion(@WebParam(name = "keyword") String keyword) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
       
        return  em.createQuery("SELECT q FROM Question q WHERE q.topic LIKE '%"+keyword+"%' OR q.content LIKE '%"+keyword+"%'", Question.class).getResultList();
                
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestion")
    public Question getQuestion(@WebParam(name = "qid") int qid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        return (Question) em.createNamedQuery("Question.findByQid")
                .setParameter("qid", qid)
                .getResultList().get(0);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteQuestion")
    public Integer voteQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid, @WebParam(name = "value") String value) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        int uid = cRest.getUidByToken(token);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        Question q = em.find(Question.class, qid);
                
        em.getTransaction().begin();
        try {
            Questionvoter qvoter;
            if(hasVotedQuestion(qid, uid, 0)) {
                qvoter = (Questionvoter)em.createQuery("SELECT q FROM Questionvoter q WHERE q.qid=:qid AND q.uid=:uid", Questionvoter.class)
                        .setParameter("qid", qid).setParameter("uid", uid)
                        .getSingleResult();
                qvoter.setValue(value);
            }
            else {
                qvoter = new Questionvoter();
                qvoter.setQid(qid);
                qvoter.setUid(uid);
                qvoter.setValue(value);
                qvoter.setCreatedtime(new Date());
                em.persist(qvoter);
            }
            
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            int vote = getVotesQuestion(qid);
            q.setCountvotes(vote);
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
    @WebMethod(operationName = "deleteAllVoteQuestion")
    public Boolean deleteAllVoteQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return false;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        // validate user
        int uid = cRest.getUidByToken(token);
        Question question = em.find(Question.class, qid);
        if(question.getUid() != uid)
            return false;
       
        em.getTransaction().begin();
        try {
            int ex = em.createNativeQuery("DELETE FROM questionvoter WHERE qid = " + qid)
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
    @WebMethod(operationName = "getVotesQuestion")
    public Integer getVotesQuestion(@WebParam(name = "qid") int qid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        List< String > res = (List<String>)em.createQuery("SELECT q.value FROM Questionvoter q WHERE q.qid="+qid, String.class).getResultList();
        Integer sum = 0;
        for(String it : res) {
            sum += Integer.parseInt(it);
        }
        return sum;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "hasVotedQuestion")
    public Boolean hasVotedQuestion (@WebParam(name = "qid") int qid, @WebParam(name = "uid") int uid, @WebParam(name = "status") int status) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
       
        em.getTransaction().begin();
        int count = 0;
        try {
            
            if(status == 0) {
                count = ((Number)em.createQuery("SELECT COUNT(qv) FROM Questionvoter qv WHERE qv.qid=:qid AND qv.uid=:uid")
                        .setParameter("qid", qid).setParameter("uid", uid)
                        .getSingleResult()).intValue();
            } else {
                count = ((Number)em.createQuery("SELECT COUNT(qv) FROM Questionvoter qv WHERE qv.qid=:qid AND qv.uid=:uid AND qv.value=:value")
                        .setParameter("qid", qid)
                        .setParameter("uid", uid)
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
    @WebMethod(operationName = "updateQuestion")
    public Integer updateQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        // validate user
        int uid = cRest.getUidByToken(token);
        Question q = em.find(Question.class, qid);
        if(q.getUid() != uid)
            return 0;
        
        em.getTransaction().begin();
        try {
            
            q.setTopic(topic);
            q.setContent(content);
            q.setCreatedtime( new Date());
            
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
    @WebMethod(operationName = "deleteQuestion")
    public Integer deleteQuestion(@WebParam(name = "token") String token, @WebParam(name = "qid") int qid) {
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        // validate user
        int uid = cRest.getUidByToken(token);
        Question question = em.find(Question.class, qid);
        if(question.getUid() != uid)
            return 0;
        
        em.getTransaction().begin();
        try {
            
            em.remove(question);
            deleteAllVoteQuestion(token, qid);
            List< entity.Answer > ans = em.createQuery("SELECT a FROM Answer a WHERE a.qid=:qid").
                    setParameter("qid", qid).getResultList();
            webservice.Answer_WS answs = new webservice.Answer_WS();
            for(entity.Answer a : ans) {
                answs.deleteAllVoteAnswer(token, a.getAid());
                em.remove(a);
            }
            
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
}
