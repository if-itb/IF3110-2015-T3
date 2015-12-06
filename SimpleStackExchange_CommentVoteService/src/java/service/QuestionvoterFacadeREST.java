/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Question;
import entity.Questionvoter;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mfikria
 */
@Stateless
@Path("questionvoter")
public class QuestionvoterFacadeREST extends AbstractFacade<Questionvoter> {

    @PersistenceContext(unitName = "SimpleStackExchange_CommentVoteServicePU")
    private EntityManager em;

    public QuestionvoterFacadeREST() {
        super(Questionvoter.class);
    }

    @GET
    @Path("vote/{qid}/{value}/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer voteQuestion(@PathParam("qid") int qid, 
            @PathParam("value") String value, 
            @PathParam("token") String token) {
        ConsumerREST cRest = new ConsumerREST();
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        int uid = cRest.getUidByToken(token);
        
        Question q = getEntityManager().find(Question.class, qid);
        
        try {
            Questionvoter qvoter;
            if(hasVotedQuestion(qid, uid, 0)) {
                qvoter = (Questionvoter)getEntityManager().createQuery("SELECT q FROM Questionvoter q WHERE q.qid=:qid AND q.uid=:uid", Questionvoter.class)
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
                getEntityManager().persist(qvoter);
            }
            
         
            int vote = getVotesQuestion(qid);
            q.setCountvotes(vote);
          
        } catch (Exception e) {
         
            return 0;
        } 
        
        return 1;
    }
    
    @GET
    @Path("getvotes/{qid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getVotesQuestion(@PathParam("qid") int qid) {
        List< String > res = (List<String>)getEntityManager().createQuery("SELECT q.value FROM Questionvoter q WHERE q.qid="+qid, String.class).getResultList();
        Integer sum = 0;
        for(String it : res) {
            sum += Integer.parseInt(it);
        }
        return sum;
    }
    
    @GET
    @Path("voted/{qid}/{uid}/{status}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean hasVotedQuestion (@PathParam("qid") int qid, @PathParam("uid") int uid, @PathParam("status") int status) {
       
        int count = 0;
        try {
            
            if(status == 0) {
                count = ((Number)getEntityManager().createQuery("SELECT COUNT(qv) FROM Questionvoter qv WHERE qv.qid=:qid AND qv.uid=:uid")
                        .setParameter("qid", qid).setParameter("uid", uid)
                        .getSingleResult()).intValue();
            } else {
                count = ((Number)getEntityManager().createQuery("SELECT COUNT(qv) FROM Questionvoter qv WHERE qv.qid=:qid AND qv.uid=:uid AND qv.value=:value")
                        .setParameter("qid", qid)
                        .setParameter("uid", uid)
                        .setParameter("value", status + "")
                        .getSingleResult()).intValue();
            }
      
        } catch (Exception e) {
          
        }
        
        return count > 0;
    }
    
    @GET
    @Path("delete/{token}/{qid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean deleteAllVoteQuestion(@PathParam("token") String token, @PathParam("qid") int qid) {
        ConsumerREST cRest = new ConsumerREST();
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return false;
        
        // validate user
        int uid = cRest.getUidByToken(token);
        Question question = getEntityManager().find(Question.class, qid);
        if(question.getUid() != uid)
            return false;
       
        try {
            int ex = getEntityManager().createNativeQuery("DELETE FROM questionvoter WHERE qid = " + qid)
                    .executeUpdate();

        } catch (Exception e) {
            return false;
        } 
        return true;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
