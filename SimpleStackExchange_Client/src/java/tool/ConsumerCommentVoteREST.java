/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:QuestionvoterFacadeREST
 * [questionvoter]<br>
 * USAGE:
 * <pre>
 *        ConsumerCommentVoteREST client = new ConsumerCommentVoteREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author mfikria
 */
public class ConsumerCommentVoteREST {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8083/SimpleStackExchange_CommentVoteService/api";

    public ConsumerCommentVoteREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("questionvoter");
    }

    public String deleteAllVoteQuestion(String token, String qid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("delete/{0}/{1}", new Object[]{token, qid}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String hasVotedQuestion(String qid, String uid, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("voted/{0}/{1}/{2}", new Object[]{qid, uid, status}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getVotesQuestion(String qid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getvotes/{0}", new Object[]{qid}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String voteQuestion(String qid, String value, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("vote/{0}/{1}/{2}", new Object[]{qid, value, token}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
    
    public String deleteAllVoteAnswer(String token, String aid) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("answervoter");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("delete/{0}/{1}", new Object[]{token, aid}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String voteAnswer( String aid, String value, String token) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("answervoter");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("vote/{0}/{1}/{2}", new Object[]{aid, value, token}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getVotesAnswer(String aid) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("answervoter");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getvotes/{0}", new Object[]{aid}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String hasVotedAnswer(String aid, String uid, String status) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("answervoter");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("voted/{0}/{1}/{2}", new Object[]{aid, uid, status}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
