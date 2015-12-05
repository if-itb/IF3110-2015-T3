
package answer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the answer package. 
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

    private final static QName _Answer_QNAME = new QName("http://webservice/", "answer");
    private final static QName _CreateAnswer_QNAME = new QName("http://webservice/", "createAnswer");
    private final static QName _CreateAnswerResponse_QNAME = new QName("http://webservice/", "createAnswerResponse");
    private final static QName _DeleteAllVoteAnswer_QNAME = new QName("http://webservice/", "deleteAllVoteAnswer");
    private final static QName _DeleteAllVoteAnswerResponse_QNAME = new QName("http://webservice/", "deleteAllVoteAnswerResponse");
    private final static QName _GetAnswers_QNAME = new QName("http://webservice/", "getAnswers");
    private final static QName _GetAnswersResponse_QNAME = new QName("http://webservice/", "getAnswersResponse");
    private final static QName _GetCountAnswer_QNAME = new QName("http://webservice/", "getCountAnswer");
    private final static QName _GetCountAnswerResponse_QNAME = new QName("http://webservice/", "getCountAnswerResponse");
    private final static QName _GetVotesAnswer_QNAME = new QName("http://webservice/", "getVotesAnswer");
    private final static QName _GetVotesAnswerResponse_QNAME = new QName("http://webservice/", "getVotesAnswerResponse");
    private final static QName _HasVotedAnswer_QNAME = new QName("http://webservice/", "hasVotedAnswer");
    private final static QName _HasVotedAnswerResponse_QNAME = new QName("http://webservice/", "hasVotedAnswerResponse");
    private final static QName _VoteAnswer_QNAME = new QName("http://webservice/", "voteAnswer");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://webservice/", "voteAnswerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: answer
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
     * Create an instance of {@link CreateAnswer }
     * 
     */
    public CreateAnswer createCreateAnswer() {
        return new CreateAnswer();
    }

    /**
     * Create an instance of {@link CreateAnswerResponse }
     * 
     */
    public CreateAnswerResponse createCreateAnswerResponse() {
        return new CreateAnswerResponse();
    }

    /**
     * Create an instance of {@link DeleteAllVoteAnswer }
     * 
     */
    public DeleteAllVoteAnswer createDeleteAllVoteAnswer() {
        return new DeleteAllVoteAnswer();
    }

    /**
     * Create an instance of {@link DeleteAllVoteAnswerResponse }
     * 
     */
    public DeleteAllVoteAnswerResponse createDeleteAllVoteAnswerResponse() {
        return new DeleteAllVoteAnswerResponse();
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
     * Create an instance of {@link GetCountAnswer }
     * 
     */
    public GetCountAnswer createGetCountAnswer() {
        return new GetCountAnswer();
    }

    /**
     * Create an instance of {@link GetCountAnswerResponse }
     * 
     */
    public GetCountAnswerResponse createGetCountAnswerResponse() {
        return new GetCountAnswerResponse();
    }

    /**
     * Create an instance of {@link GetVotesAnswer }
     * 
     */
    public GetVotesAnswer createGetVotesAnswer() {
        return new GetVotesAnswer();
    }

    /**
     * Create an instance of {@link GetVotesAnswerResponse }
     * 
     */
    public GetVotesAnswerResponse createGetVotesAnswerResponse() {
        return new GetVotesAnswerResponse();
    }

    /**
     * Create an instance of {@link HasVotedAnswer }
     * 
     */
    public HasVotedAnswer createHasVotedAnswer() {
        return new HasVotedAnswer();
    }

    /**
     * Create an instance of {@link HasVotedAnswerResponse }
     * 
     */
    public HasVotedAnswerResponse createHasVotedAnswerResponse() {
        return new HasVotedAnswerResponse();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createAnswer")
    public JAXBElement<CreateAnswer> createCreateAnswer(CreateAnswer value) {
        return new JAXBElement<CreateAnswer>(_CreateAnswer_QNAME, CreateAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createAnswerResponse")
    public JAXBElement<CreateAnswerResponse> createCreateAnswerResponse(CreateAnswerResponse value) {
        return new JAXBElement<CreateAnswerResponse>(_CreateAnswerResponse_QNAME, CreateAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllVoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteAllVoteAnswer")
    public JAXBElement<DeleteAllVoteAnswer> createDeleteAllVoteAnswer(DeleteAllVoteAnswer value) {
        return new JAXBElement<DeleteAllVoteAnswer>(_DeleteAllVoteAnswer_QNAME, DeleteAllVoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllVoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteAllVoteAnswerResponse")
    public JAXBElement<DeleteAllVoteAnswerResponse> createDeleteAllVoteAnswerResponse(DeleteAllVoteAnswerResponse value) {
        return new JAXBElement<DeleteAllVoteAnswerResponse>(_DeleteAllVoteAnswerResponse_QNAME, DeleteAllVoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getAnswers")
    public JAXBElement<GetAnswers> createGetAnswers(GetAnswers value) {
        return new JAXBElement<GetAnswers>(_GetAnswers_QNAME, GetAnswers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getAnswersResponse")
    public JAXBElement<GetAnswersResponse> createGetAnswersResponse(GetAnswersResponse value) {
        return new JAXBElement<GetAnswersResponse>(_GetAnswersResponse_QNAME, GetAnswersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getCountAnswer")
    public JAXBElement<GetCountAnswer> createGetCountAnswer(GetCountAnswer value) {
        return new JAXBElement<GetCountAnswer>(_GetCountAnswer_QNAME, GetCountAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getCountAnswerResponse")
    public JAXBElement<GetCountAnswerResponse> createGetCountAnswerResponse(GetCountAnswerResponse value) {
        return new JAXBElement<GetCountAnswerResponse>(_GetCountAnswerResponse_QNAME, GetCountAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVotesAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getVotesAnswer")
    public JAXBElement<GetVotesAnswer> createGetVotesAnswer(GetVotesAnswer value) {
        return new JAXBElement<GetVotesAnswer>(_GetVotesAnswer_QNAME, GetVotesAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVotesAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getVotesAnswerResponse")
    public JAXBElement<GetVotesAnswerResponse> createGetVotesAnswerResponse(GetVotesAnswerResponse value) {
        return new JAXBElement<GetVotesAnswerResponse>(_GetVotesAnswerResponse_QNAME, GetVotesAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "hasVotedAnswer")
    public JAXBElement<HasVotedAnswer> createHasVotedAnswer(HasVotedAnswer value) {
        return new JAXBElement<HasVotedAnswer>(_HasVotedAnswer_QNAME, HasVotedAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "hasVotedAnswerResponse")
    public JAXBElement<HasVotedAnswerResponse> createHasVotedAnswerResponse(HasVotedAnswerResponse value) {
        return new JAXBElement<HasVotedAnswerResponse>(_HasVotedAnswerResponse_QNAME, HasVotedAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

}
