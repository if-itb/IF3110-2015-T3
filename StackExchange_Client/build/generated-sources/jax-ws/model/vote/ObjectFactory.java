
package model.vote;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.vote package. 
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

    private final static QName _HasVotedAnswerResponse_QNAME = new QName("http://vote.model/", "hasVotedAnswerResponse");
    private final static QName _HasVotedAnswer_QNAME = new QName("http://vote.model/", "hasVotedAnswer");
    private final static QName _VoteQuestion_QNAME = new QName("http://vote.model/", "voteQuestion");
    private final static QName _GetQuestionVotes_QNAME = new QName("http://vote.model/", "getQuestionVotes");
    private final static QName _VoteAnswer_QNAME = new QName("http://vote.model/", "voteAnswer");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://vote.model/", "voteAnswerResponse");
    private final static QName _GetQuestionVotesResponse_QNAME = new QName("http://vote.model/", "getQuestionVotesResponse");
    private final static QName _GetAnswerVotes_QNAME = new QName("http://vote.model/", "getAnswerVotes");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://vote.model/", "voteQuestionResponse");
    private final static QName _GetAnswerVotesResponse_QNAME = new QName("http://vote.model/", "getAnswerVotesResponse");
    private final static QName _HasVotedQuestion_QNAME = new QName("http://vote.model/", "hasVotedQuestion");
    private final static QName _HasVotedQuestionResponse_QNAME = new QName("http://vote.model/", "hasVotedQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.vote
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAnswerVotes }
     * 
     */
    public GetAnswerVotes createGetAnswerVotes() {
        return new GetAnswerVotes();
    }

    /**
     * Create an instance of {@link VoteQuestionResponse }
     * 
     */
    public VoteQuestionResponse createVoteQuestionResponse() {
        return new VoteQuestionResponse();
    }

    /**
     * Create an instance of {@link GetAnswerVotesResponse }
     * 
     */
    public GetAnswerVotesResponse createGetAnswerVotesResponse() {
        return new GetAnswerVotesResponse();
    }

    /**
     * Create an instance of {@link HasVotedQuestion }
     * 
     */
    public HasVotedQuestion createHasVotedQuestion() {
        return new HasVotedQuestion();
    }

    /**
     * Create an instance of {@link HasVotedQuestionResponse }
     * 
     */
    public HasVotedQuestionResponse createHasVotedQuestionResponse() {
        return new HasVotedQuestionResponse();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link GetQuestionVotesResponse }
     * 
     */
    public GetQuestionVotesResponse createGetQuestionVotesResponse() {
        return new GetQuestionVotesResponse();
    }

    /**
     * Create an instance of {@link GetQuestionVotes }
     * 
     */
    public GetQuestionVotes createGetQuestionVotes() {
        return new GetQuestionVotes();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link HasVotedAnswerResponse }
     * 
     */
    public HasVotedAnswerResponse createHasVotedAnswerResponse() {
        return new HasVotedAnswerResponse();
    }

    /**
     * Create an instance of {@link HasVotedAnswer }
     * 
     */
    public HasVotedAnswer createHasVotedAnswer() {
        return new HasVotedAnswer();
    }

    /**
     * Create an instance of {@link VoteQuestion }
     * 
     */
    public VoteQuestion createVoteQuestion() {
        return new VoteQuestion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "hasVotedAnswerResponse")
    public JAXBElement<HasVotedAnswerResponse> createHasVotedAnswerResponse(HasVotedAnswerResponse value) {
        return new JAXBElement<HasVotedAnswerResponse>(_HasVotedAnswerResponse_QNAME, HasVotedAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "hasVotedAnswer")
    public JAXBElement<HasVotedAnswer> createHasVotedAnswer(HasVotedAnswer value) {
        return new JAXBElement<HasVotedAnswer>(_HasVotedAnswer_QNAME, HasVotedAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionVotes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "getQuestionVotes")
    public JAXBElement<GetQuestionVotes> createGetQuestionVotes(GetQuestionVotes value) {
        return new JAXBElement<GetQuestionVotes>(_GetQuestionVotes_QNAME, GetQuestionVotes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionVotesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "getQuestionVotesResponse")
    public JAXBElement<GetQuestionVotesResponse> createGetQuestionVotesResponse(GetQuestionVotesResponse value) {
        return new JAXBElement<GetQuestionVotesResponse>(_GetQuestionVotesResponse_QNAME, GetQuestionVotesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerVotes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "getAnswerVotes")
    public JAXBElement<GetAnswerVotes> createGetAnswerVotes(GetAnswerVotes value) {
        return new JAXBElement<GetAnswerVotes>(_GetAnswerVotes_QNAME, GetAnswerVotes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerVotesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "getAnswerVotesResponse")
    public JAXBElement<GetAnswerVotesResponse> createGetAnswerVotesResponse(GetAnswerVotesResponse value) {
        return new JAXBElement<GetAnswerVotesResponse>(_GetAnswerVotesResponse_QNAME, GetAnswerVotesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "hasVotedQuestion")
    public JAXBElement<HasVotedQuestion> createHasVotedQuestion(HasVotedQuestion value) {
        return new JAXBElement<HasVotedQuestion>(_HasVotedQuestion_QNAME, HasVotedQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vote.model/", name = "hasVotedQuestionResponse")
    public JAXBElement<HasVotedQuestionResponse> createHasVotedQuestionResponse(HasVotedQuestionResponse value) {
        return new JAXBElement<HasVotedQuestionResponse>(_HasVotedQuestionResponse_QNAME, HasVotedQuestionResponse.class, null, value);
    }

}
