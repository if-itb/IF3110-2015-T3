
package question;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the question package. 
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

    private final static QName _CreateQuestion_QNAME = new QName("http://webservice/", "createQuestion");
    private final static QName _CreateQuestionResponse_QNAME = new QName("http://webservice/", "createQuestionResponse");
    private final static QName _DeleteAllVoteQuestion_QNAME = new QName("http://webservice/", "deleteAllVoteQuestion");
    private final static QName _DeleteAllVoteQuestionResponse_QNAME = new QName("http://webservice/", "deleteAllVoteQuestionResponse");
    private final static QName _DeleteQuestion_QNAME = new QName("http://webservice/", "deleteQuestion");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://webservice/", "deleteQuestionResponse");
    private final static QName _GetQuestion_QNAME = new QName("http://webservice/", "getQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://webservice/", "getQuestionResponse");
    private final static QName _GetVotesQuestion_QNAME = new QName("http://webservice/", "getVotesQuestion");
    private final static QName _GetVotesQuestionResponse_QNAME = new QName("http://webservice/", "getVotesQuestionResponse");
    private final static QName _HasVotedQuestion_QNAME = new QName("http://webservice/", "hasVotedQuestion");
    private final static QName _HasVotedQuestionResponse_QNAME = new QName("http://webservice/", "hasVotedQuestionResponse");
    private final static QName _ListQuestion_QNAME = new QName("http://webservice/", "listQuestion");
    private final static QName _ListQuestionResponse_QNAME = new QName("http://webservice/", "listQuestionResponse");
    private final static QName _Question_QNAME = new QName("http://webservice/", "question");
    private final static QName _SearchQuestion_QNAME = new QName("http://webservice/", "searchQuestion");
    private final static QName _SearchQuestionResponse_QNAME = new QName("http://webservice/", "searchQuestionResponse");
    private final static QName _UpdateQuestion_QNAME = new QName("http://webservice/", "updateQuestion");
    private final static QName _UpdateQuestionResponse_QNAME = new QName("http://webservice/", "updateQuestionResponse");
    private final static QName _VoteQuestion_QNAME = new QName("http://webservice/", "voteQuestion");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://webservice/", "voteQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: question
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateQuestion }
     * 
     */
    public CreateQuestion createCreateQuestion() {
        return new CreateQuestion();
    }

    /**
     * Create an instance of {@link CreateQuestionResponse }
     * 
     */
    public CreateQuestionResponse createCreateQuestionResponse() {
        return new CreateQuestionResponse();
    }

    /**
     * Create an instance of {@link DeleteAllVoteQuestion }
     * 
     */
    public DeleteAllVoteQuestion createDeleteAllVoteQuestion() {
        return new DeleteAllVoteQuestion();
    }

    /**
     * Create an instance of {@link DeleteAllVoteQuestionResponse }
     * 
     */
    public DeleteAllVoteQuestionResponse createDeleteAllVoteQuestionResponse() {
        return new DeleteAllVoteQuestionResponse();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link DeleteQuestionResponse }
     * 
     */
    public DeleteQuestionResponse createDeleteQuestionResponse() {
        return new DeleteQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestion }
     * 
     */
    public GetQuestion createGetQuestion() {
        return new GetQuestion();
    }

    /**
     * Create an instance of {@link GetQuestionResponse }
     * 
     */
    public GetQuestionResponse createGetQuestionResponse() {
        return new GetQuestionResponse();
    }

    /**
     * Create an instance of {@link GetVotesQuestion }
     * 
     */
    public GetVotesQuestion createGetVotesQuestion() {
        return new GetVotesQuestion();
    }

    /**
     * Create an instance of {@link GetVotesQuestionResponse }
     * 
     */
    public GetVotesQuestionResponse createGetVotesQuestionResponse() {
        return new GetVotesQuestionResponse();
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
     * Create an instance of {@link ListQuestion }
     * 
     */
    public ListQuestion createListQuestion() {
        return new ListQuestion();
    }

    /**
     * Create an instance of {@link ListQuestionResponse }
     * 
     */
    public ListQuestionResponse createListQuestionResponse() {
        return new ListQuestionResponse();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link SearchQuestion }
     * 
     */
    public SearchQuestion createSearchQuestion() {
        return new SearchQuestion();
    }

    /**
     * Create an instance of {@link SearchQuestionResponse }
     * 
     */
    public SearchQuestionResponse createSearchQuestionResponse() {
        return new SearchQuestionResponse();
    }

    /**
     * Create an instance of {@link UpdateQuestion }
     * 
     */
    public UpdateQuestion createUpdateQuestion() {
        return new UpdateQuestion();
    }

    /**
     * Create an instance of {@link UpdateQuestionResponse }
     * 
     */
    public UpdateQuestionResponse createUpdateQuestionResponse() {
        return new UpdateQuestionResponse();
    }

    /**
     * Create an instance of {@link VoteQuestion }
     * 
     */
    public VoteQuestion createVoteQuestion() {
        return new VoteQuestion();
    }

    /**
     * Create an instance of {@link VoteQuestionResponse }
     * 
     */
    public VoteQuestionResponse createVoteQuestionResponse() {
        return new VoteQuestionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createQuestion")
    public JAXBElement<CreateQuestion> createCreateQuestion(CreateQuestion value) {
        return new JAXBElement<CreateQuestion>(_CreateQuestion_QNAME, CreateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createQuestionResponse")
    public JAXBElement<CreateQuestionResponse> createCreateQuestionResponse(CreateQuestionResponse value) {
        return new JAXBElement<CreateQuestionResponse>(_CreateQuestionResponse_QNAME, CreateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllVoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteAllVoteQuestion")
    public JAXBElement<DeleteAllVoteQuestion> createDeleteAllVoteQuestion(DeleteAllVoteQuestion value) {
        return new JAXBElement<DeleteAllVoteQuestion>(_DeleteAllVoteQuestion_QNAME, DeleteAllVoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAllVoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteAllVoteQuestionResponse")
    public JAXBElement<DeleteAllVoteQuestionResponse> createDeleteAllVoteQuestionResponse(DeleteAllVoteQuestionResponse value) {
        return new JAXBElement<DeleteAllVoteQuestionResponse>(_DeleteAllVoteQuestionResponse_QNAME, DeleteAllVoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getQuestion")
    public JAXBElement<GetQuestion> createGetQuestion(GetQuestion value) {
        return new JAXBElement<GetQuestion>(_GetQuestion_QNAME, GetQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVotesQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getVotesQuestion")
    public JAXBElement<GetVotesQuestion> createGetVotesQuestion(GetVotesQuestion value) {
        return new JAXBElement<GetVotesQuestion>(_GetVotesQuestion_QNAME, GetVotesQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVotesQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getVotesQuestionResponse")
    public JAXBElement<GetVotesQuestionResponse> createGetVotesQuestionResponse(GetVotesQuestionResponse value) {
        return new JAXBElement<GetVotesQuestionResponse>(_GetVotesQuestionResponse_QNAME, GetVotesQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "hasVotedQuestion")
    public JAXBElement<HasVotedQuestion> createHasVotedQuestion(HasVotedQuestion value) {
        return new JAXBElement<HasVotedQuestion>(_HasVotedQuestion_QNAME, HasVotedQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasVotedQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "hasVotedQuestionResponse")
    public JAXBElement<HasVotedQuestionResponse> createHasVotedQuestionResponse(HasVotedQuestionResponse value) {
        return new JAXBElement<HasVotedQuestionResponse>(_HasVotedQuestionResponse_QNAME, HasVotedQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "listQuestion")
    public JAXBElement<ListQuestion> createListQuestion(ListQuestion value) {
        return new JAXBElement<ListQuestion>(_ListQuestion_QNAME, ListQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "listQuestionResponse")
    public JAXBElement<ListQuestionResponse> createListQuestionResponse(ListQuestionResponse value) {
        return new JAXBElement<ListQuestionResponse>(_ListQuestionResponse_QNAME, ListQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "searchQuestion")
    public JAXBElement<SearchQuestion> createSearchQuestion(SearchQuestion value) {
        return new JAXBElement<SearchQuestion>(_SearchQuestion_QNAME, SearchQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "searchQuestionResponse")
    public JAXBElement<SearchQuestionResponse> createSearchQuestionResponse(SearchQuestionResponse value) {
        return new JAXBElement<SearchQuestionResponse>(_SearchQuestionResponse_QNAME, SearchQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "updateQuestion")
    public JAXBElement<UpdateQuestion> createUpdateQuestion(UpdateQuestion value) {
        return new JAXBElement<UpdateQuestion>(_UpdateQuestion_QNAME, UpdateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "updateQuestionResponse")
    public JAXBElement<UpdateQuestionResponse> createUpdateQuestionResponse(UpdateQuestionResponse value) {
        return new JAXBElement<UpdateQuestionResponse>(_UpdateQuestionResponse_QNAME, UpdateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

}
