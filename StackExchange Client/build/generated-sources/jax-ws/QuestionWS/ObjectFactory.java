
package QuestionWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the QuestionWS package. 
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

    private final static QName _DevoteQuestionResponse_QNAME = new QName("http://controllers/", "devoteQuestionResponse");
    private final static QName _SearchQuestion_QNAME = new QName("http://controllers/", "searchQuestion");
    private final static QName _GetQuestion_QNAME = new QName("http://controllers/", "getQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://controllers/", "getQuestionResponse");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://controllers/", "deleteQuestionResponse");
    private final static QName _GetAllQuestionsResponse_QNAME = new QName("http://controllers/", "getAllQuestionsResponse");
    private final static QName _UpdateQuestionResponse_QNAME = new QName("http://controllers/", "updateQuestionResponse");
    private final static QName _GetCountAnswerResponse_QNAME = new QName("http://controllers/", "getCountAnswerResponse");
    private final static QName _AddNewQuestion_QNAME = new QName("http://controllers/", "addNewQuestion");
    private final static QName _DeleteQuestion_QNAME = new QName("http://controllers/", "deleteQuestion");
    private final static QName _DevoteQuestion_QNAME = new QName("http://controllers/", "devoteQuestion");
    private final static QName _GetAllQuestions_QNAME = new QName("http://controllers/", "getAllQuestions");
    private final static QName _UpdateQuestion_QNAME = new QName("http://controllers/", "updateQuestion");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://controllers/", "voteQuestionResponse");
    private final static QName _GetCountAnswer_QNAME = new QName("http://controllers/", "getCountAnswer");
    private final static QName _VoteQuestion_QNAME = new QName("http://controllers/", "voteQuestion");
    private final static QName _AddNewQuestionResponse_QNAME = new QName("http://controllers/", "addNewQuestionResponse");
    private final static QName _SearchQuestionResponse_QNAME = new QName("http://controllers/", "searchQuestionResponse");
    private final static QName _Question_QNAME = new QName("http://controllers/", "Question");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: QuestionWS
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link DevoteQuestionResponse }
     * 
     */
    public DevoteQuestionResponse createDevoteQuestionResponse() {
        return new DevoteQuestionResponse();
    }

    /**
     * Create an instance of {@link SearchQuestion }
     * 
     */
    public SearchQuestion createSearchQuestion() {
        return new SearchQuestion();
    }

    /**
     * Create an instance of {@link UpdateQuestionResponse }
     * 
     */
    public UpdateQuestionResponse createUpdateQuestionResponse() {
        return new UpdateQuestionResponse();
    }

    /**
     * Create an instance of {@link GetAllQuestionsResponse }
     * 
     */
    public GetAllQuestionsResponse createGetAllQuestionsResponse() {
        return new GetAllQuestionsResponse();
    }

    /**
     * Create an instance of {@link DeleteQuestionResponse }
     * 
     */
    public DeleteQuestionResponse createDeleteQuestionResponse() {
        return new DeleteQuestionResponse();
    }

    /**
     * Create an instance of {@link UpdateQuestion }
     * 
     */
    public UpdateQuestion createUpdateQuestion() {
        return new UpdateQuestion();
    }

    /**
     * Create an instance of {@link VoteQuestionResponse }
     * 
     */
    public VoteQuestionResponse createVoteQuestionResponse() {
        return new VoteQuestionResponse();
    }

    /**
     * Create an instance of {@link DevoteQuestion }
     * 
     */
    public DevoteQuestion createDevoteQuestion() {
        return new DevoteQuestion();
    }

    /**
     * Create an instance of {@link GetAllQuestions }
     * 
     */
    public GetAllQuestions createGetAllQuestions() {
        return new GetAllQuestions();
    }

    /**
     * Create an instance of {@link AddNewQuestion }
     * 
     */
    public AddNewQuestion createAddNewQuestion() {
        return new AddNewQuestion();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link GetCountAnswerResponse }
     * 
     */
    public GetCountAnswerResponse createGetCountAnswerResponse() {
        return new GetCountAnswerResponse();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link AddNewQuestionResponse }
     * 
     */
    public AddNewQuestionResponse createAddNewQuestionResponse() {
        return new AddNewQuestionResponse();
    }

    /**
     * Create an instance of {@link SearchQuestionResponse }
     * 
     */
    public SearchQuestionResponse createSearchQuestionResponse() {
        return new SearchQuestionResponse();
    }

    /**
     * Create an instance of {@link GetCountAnswer }
     * 
     */
    public GetCountAnswer createGetCountAnswer() {
        return new GetCountAnswer();
    }

    /**
     * Create an instance of {@link VoteQuestion }
     * 
     */
    public VoteQuestion createVoteQuestion() {
        return new VoteQuestion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DevoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "devoteQuestionResponse")
    public JAXBElement<DevoteQuestionResponse> createDevoteQuestionResponse(DevoteQuestionResponse value) {
        return new JAXBElement<DevoteQuestionResponse>(_DevoteQuestionResponse_QNAME, DevoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "searchQuestion")
    public JAXBElement<SearchQuestion> createSearchQuestion(SearchQuestion value) {
        return new JAXBElement<SearchQuestion>(_SearchQuestion_QNAME, SearchQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getQuestion")
    public JAXBElement<GetQuestion> createGetQuestion(GetQuestion value) {
        return new JAXBElement<GetQuestion>(_GetQuestion_QNAME, GetQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getAllQuestionsResponse")
    public JAXBElement<GetAllQuestionsResponse> createGetAllQuestionsResponse(GetAllQuestionsResponse value) {
        return new JAXBElement<GetAllQuestionsResponse>(_GetAllQuestionsResponse_QNAME, GetAllQuestionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "updateQuestionResponse")
    public JAXBElement<UpdateQuestionResponse> createUpdateQuestionResponse(UpdateQuestionResponse value) {
        return new JAXBElement<UpdateQuestionResponse>(_UpdateQuestionResponse_QNAME, UpdateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getCountAnswerResponse")
    public JAXBElement<GetCountAnswerResponse> createGetCountAnswerResponse(GetCountAnswerResponse value) {
        return new JAXBElement<GetCountAnswerResponse>(_GetCountAnswerResponse_QNAME, GetCountAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "addNewQuestion")
    public JAXBElement<AddNewQuestion> createAddNewQuestion(AddNewQuestion value) {
        return new JAXBElement<AddNewQuestion>(_AddNewQuestion_QNAME, AddNewQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DevoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "devoteQuestion")
    public JAXBElement<DevoteQuestion> createDevoteQuestion(DevoteQuestion value) {
        return new JAXBElement<DevoteQuestion>(_DevoteQuestion_QNAME, DevoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getAllQuestions")
    public JAXBElement<GetAllQuestions> createGetAllQuestions(GetAllQuestions value) {
        return new JAXBElement<GetAllQuestions>(_GetAllQuestions_QNAME, GetAllQuestions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "updateQuestion")
    public JAXBElement<UpdateQuestion> createUpdateQuestion(UpdateQuestion value) {
        return new JAXBElement<UpdateQuestion>(_UpdateQuestion_QNAME, UpdateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getCountAnswer")
    public JAXBElement<GetCountAnswer> createGetCountAnswer(GetCountAnswer value) {
        return new JAXBElement<GetCountAnswer>(_GetCountAnswer_QNAME, GetCountAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "addNewQuestionResponse")
    public JAXBElement<AddNewQuestionResponse> createAddNewQuestionResponse(AddNewQuestionResponse value) {
        return new JAXBElement<AddNewQuestionResponse>(_AddNewQuestionResponse_QNAME, AddNewQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "searchQuestionResponse")
    public JAXBElement<SearchQuestionResponse> createSearchQuestionResponse(SearchQuestionResponse value) {
        return new JAXBElement<SearchQuestionResponse>(_SearchQuestionResponse_QNAME, SearchQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

}
