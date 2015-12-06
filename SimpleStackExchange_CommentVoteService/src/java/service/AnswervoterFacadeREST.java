/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Answer;
import entity.Answervoter;
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
@Path("answervoter")
public class AnswervoterFacadeREST extends AbstractFacade<Answervoter> {

    @PersistenceContext(unitName = "SimpleStackExchange_CommentVoteServicePU")
    private EntityManager em;

    public AnswervoterFacadeREST() {
        super(Answervoter.class);
    }

    @GET
    @Path("vote/{aid}/{value}/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer voteAnswer(@PathParam("aid") int aid, 
            @PathParam("value") String value, 
            @PathParam("token") String token) {
        ConsumerREST cRest = new ConsumerREST();
        // validate token
        Integer res = cRest.auth(token);
        if(res == 0 || res == -1)
            return res;
        
        int uid = cRest.getUidByToken(token);
        
        // increment countvotes in Question table
       Answer a = getEntityManager().find(Answer.class, aid);
                

        try {
            Answervoter avoter;
            if(hasVotedAnswer(aid, uid, "0")) {
                avoter = (Answervoter)getEntityManager().createQuery("SELECT a FROM Answervoter a WHERE a.aid=:aid AND a.uid=:uid", Answervoter.class)
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
                getEntityManager().persist(avoter);
            }
            
            int vote = getVotesAnswer(aid);
            a.setCountvotes(vote);
   
        } catch(Exception e){
        return 0;}
        
        return 1;
    }

    @GET
    @Path("getvotes/{aid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getVotesAnswer(@PathParam("aid") Integer aid) {
        List< String > res = (List<String>)getEntityManager().createQuery("SELECT a.value FROM Answervoter a WHERE a.aid="+aid, String.class).getResultList();
        Integer sum = 0;
        for(String it : res) {
            sum += Integer.parseInt(it);
        }
        return sum;
    }
    
    @GET
    @Path("voted/{aid}/{uid}/{status}")
    @Produces({MediaType.TEXT_PLAIN})
    public Boolean hasVotedAnswer(@PathParam("aid") int aid, @PathParam("uid") int uid, @PathParam("status") String status) {
        int count = 0;
            if(status.equals("0")) {
            count = ((Number)getEntityManager().createQuery("SELECT COUNT(av) FROM Answervoter av WHERE av.aid=:aid AND av.uid=:uid")
                    .setParameter("aid", aid).setParameter("uid", uid)
                    .getSingleResult()).intValue();
            }
            else {
                count = ((Number)getEntityManager().createQuery("SELECT COUNT(av) FROM Answervoter av WHERE av.aid=:aid AND av.uid=:uid AND av.value=:value")
                    .setParameter("aid", aid).setParameter("uid", uid)
                    .setParameter("value", status + "")
                    .getSingleResult()).intValue();
            }

        return count > 0;
    }
    
    @GET
    @Path("delete/{token}/{aid}")
    @Produces({MediaType.TEXT_PLAIN})
    public Boolean deleteAllVoteAnswer(@PathParam("token") String token,@PathParam("aid") int aid) {
        // validate token
        ConsumerREST cRest = new ConsumerREST();
        int res = cRest.auth(token);
        if(res == -1 || res == 0)
            return false;
       
        // validate user
        int uid = cRest.getUidByToken(token);
        Answer answer = getEntityManager().find(Answer.class, aid);
        Question question = getEntityManager().find(Question.class, answer.getQid());
        if(question.getUid() != uid)
            return false;
        

        try {
            int ex = getEntityManager().createNativeQuery("DELETE FROM answervoter WHERE aid = " + aid)
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
