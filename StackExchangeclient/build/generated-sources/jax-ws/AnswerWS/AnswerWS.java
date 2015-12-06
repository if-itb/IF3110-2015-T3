
package AnswerWS;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.1
 * 
 */
@WebService(name = "AnswerWS", targetNamespace = "http://jaxws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AnswerWS {


    /**
     * 
     * @param uid
     * @param qid
     * @param content
     * @param token
     */
    @WebMethod
    @RequestWrapper(localName = "insertAnswer", targetNamespace = "http://jaxws/", className = "AnswerWS.InsertAnswer")
    @ResponseWrapper(localName = "insertAnswerResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.InsertAnswerResponse")
    public void insertAnswer(
        @WebParam(name = "uid", targetNamespace = "")
        int uid,
        @WebParam(name = "qid", targetNamespace = "")
        int qid,
        @WebParam(name = "content", targetNamespace = "")
        String content,
        @WebParam(name = "token", targetNamespace = "")
        String token);

    /**
     * 
     * @param uid
     * @param aid
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "numVoteAnswer", targetNamespace = "")
    @RequestWrapper(localName = "numVoteAnswer", targetNamespace = "http://jaxws/", className = "AnswerWS.NumVoteAnswer")
    @ResponseWrapper(localName = "numVoteAnswerResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.NumVoteAnswerResponse")
    public int numVoteAnswer(
        @WebParam(name = "uid", targetNamespace = "")
        int uid,
        @WebParam(name = "aid", targetNamespace = "")
        int aid);

    /**
     * 
     * @param aid
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "getAnswerUID", targetNamespace = "")
    @RequestWrapper(localName = "getAnswerUID", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerUID")
    @ResponseWrapper(localName = "getAnswerUIDResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerUIDResponse")
    public int getAnswerUID(
        @WebParam(name = "aid", targetNamespace = "")
        int aid);

    /**
     * 
     * @param uid
     * @param type
     * @param aid
     */
    @WebMethod
    @RequestWrapper(localName = "voteAnswer", targetNamespace = "http://jaxws/", className = "AnswerWS.VoteAnswer")
    @ResponseWrapper(localName = "voteAnswerResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.VoteAnswerResponse")
    public void voteAnswer(
        @WebParam(name = "aid", targetNamespace = "")
        int aid,
        @WebParam(name = "uid", targetNamespace = "")
        int uid,
        @WebParam(name = "type", targetNamespace = "")
        int type);

    /**
     * 
     * @param id
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "gtVote", targetNamespace = "")
    @RequestWrapper(localName = "getAnswerVote", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerVote")
    @ResponseWrapper(localName = "getAnswerVoteResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerVoteResponse")
    public int getAnswerVote(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param val
     * @param id
     */
    @WebMethod
    @RequestWrapper(localName = "setAnswerVote", targetNamespace = "http://jaxws/", className = "AnswerWS.SetAnswerVote")
    @ResponseWrapper(localName = "setAnswerVoteResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.SetAnswerVoteResponse")
    public void setAnswerVote(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "val", targetNamespace = "")
        int val);

    /**
     * 
     * @param id
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "gtQID", targetNamespace = "")
    @RequestWrapper(localName = "getAnswerQID", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerQID")
    @ResponseWrapper(localName = "getAnswerQIDResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerQIDResponse")
    public int getAnswerQID(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param qid
     * @return
     *     returns java.util.List<AnswerWS.Answer>
     */
    @WebMethod
    @WebResult(name = "Answer", targetNamespace = "")
    @RequestWrapper(localName = "getAnswerByQID", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerByQID")
    @ResponseWrapper(localName = "getAnswerByQIDResponse", targetNamespace = "http://jaxws/", className = "AnswerWS.GetAnswerByQIDResponse")
    public List<Answer> getAnswerByQID(
        @WebParam(name = "qid", targetNamespace = "")
        int qid);

}
