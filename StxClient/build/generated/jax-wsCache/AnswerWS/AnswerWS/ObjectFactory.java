
package AnswerWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the AnswerWS package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Answer_QNAME = new QName("http://AnsOperation/", "Answer");
    private final static QName _Exception_QNAME = new QName("http://AnsOperation/", "Exception");
    private final static QName _DeleteAns_QNAME = new QName("http://AnsOperation/", "deleteAns");
    private final static QName _DeleteAnsResponse_QNAME = new QName("http://AnsOperation/", "deleteAnsResponse");
    private final static QName _GetAnsVote_QNAME = new QName("http://AnsOperation/", "getAnsVote");
    private final static QName _GetAnsVoteResponse_QNAME = new QName("http://AnsOperation/", "getAnsVoteResponse");
    private final static QName _GetAnswers_QNAME = new QName("http://AnsOperation/", "getAnswers");
    private final static QName _GetAnswersResponse_QNAME = new QName("http://AnsOperation/", "getAnswersResponse");
    private final static QName _PostAns_QNAME = new QName("http://AnsOperation/", "postAns");
    private final static QName _PostAnsResponse_QNAME = new QName("http://AnsOperation/", "postAnsResponse");
    private final static QName _VoteAns_QNAME = new QName("http://AnsOperation/", "voteAns");
    private final static QName _VoteAnsResponse_QNAME = new QName("http://AnsOperation/", "voteAnsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: AnswerWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link DeleteAns }
     * 
     */
    public DeleteAns createDeleteAns() {
        return new DeleteAns();
    }

    /**
     * Create an instance of {@link DeleteAnsResponse }
     * 
     */
    public DeleteAnsResponse createDeleteAnsResponse() {
        return new DeleteAnsResponse();
    }

    /**
     * Create an instance of {@link GetAnsVote }
     * 
     */
    public GetAnsVote createGetAnsVote() {
        return new GetAnsVote();
    }

    /**
     * Create an instance of {@link GetAnsVoteResponse }
     * 
     */
    public GetAnsVoteResponse createGetAnsVoteResponse() {
        return new GetAnsVoteResponse();
    }

    /**
     * Create an instance of {@link GetAnswers }
     * 
     */
    public GetAnswers createGetAnswers() {
        return new GetAnswers();
    }

    /**
     * Create an instance of {@link GetAnswersResponse }
     * 
     */
    public GetAnswersResponse createGetAnswersResponse() {
        return new GetAnswersResponse();
    }

    /**
     * Create an instance of {@link PostAns }
     * 
     */
    public PostAns createPostAns() {
        return new PostAns();
    }

    /**
     * Create an instance of {@link PostAnsResponse }
     * 
     */
    public PostAnsResponse createPostAnsResponse() {
        return new PostAnsResponse();
    }

    /**
     * Create an instance of {@link VoteAns }
     * 
     */
    public VoteAns createVoteAns() {
        return new VoteAns();
    }

    /**
     * Create an instance of {@link VoteAnsResponse }
     * 
     */
    public VoteAnsResponse createVoteAnsResponse() {
        return new VoteAnsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAns }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "deleteAns")
    public JAXBElement<DeleteAns> createDeleteAns(DeleteAns value) {
        return new JAXBElement<DeleteAns>(_DeleteAns_QNAME, DeleteAns.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "deleteAnsResponse")
    public JAXBElement<DeleteAnsResponse> createDeleteAnsResponse(DeleteAnsResponse value) {
        return new JAXBElement<DeleteAnsResponse>(_DeleteAnsResponse_QNAME, DeleteAnsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnsVote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "getAnsVote")
    public JAXBElement<GetAnsVote> createGetAnsVote(GetAnsVote value) {
        return new JAXBElement<GetAnsVote>(_GetAnsVote_QNAME, GetAnsVote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnsVoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "getAnsVoteResponse")
    public JAXBElement<GetAnsVoteResponse> createGetAnsVoteResponse(GetAnsVoteResponse value) {
        return new JAXBElement<GetAnsVoteResponse>(_GetAnsVoteResponse_QNAME, GetAnsVoteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "getAnswers")
    public JAXBElement<GetAnswers> createGetAnswers(GetAnswers value) {
        return new JAXBElement<GetAnswers>(_GetAnswers_QNAME, GetAnswers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "getAnswersResponse")
    public JAXBElement<GetAnswersResponse> createGetAnswersResponse(GetAnswersResponse value) {
        return new JAXBElement<GetAnswersResponse>(_GetAnswersResponse_QNAME, GetAnswersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAns }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "postAns")
    public JAXBElement<PostAns> createPostAns(PostAns value) {
        return new JAXBElement<PostAns>(_PostAns_QNAME, PostAns.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostAnsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "postAnsResponse")
    public JAXBElement<PostAnsResponse> createPostAnsResponse(PostAnsResponse value) {
        return new JAXBElement<PostAnsResponse>(_PostAnsResponse_QNAME, PostAnsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAns }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "voteAns")
    public JAXBElement<VoteAns> createVoteAns(VoteAns value) {
        return new JAXBElement<VoteAns>(_VoteAns_QNAME, VoteAns.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnsOperation/", name = "voteAnsResponse")
    public JAXBElement<VoteAnsResponse> createVoteAnsResponse(VoteAnsResponse value) {
        return new JAXBElement<VoteAnsResponse>(_VoteAnsResponse_QNAME, VoteAnsResponse.class, null, value);
    }

}
