
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _CreateQuestionResponse_QNAME = new QName("http://service/", "createQuestionResponse");
    private final static QName _CreateAnswer_QNAME = new QName("http://service/", "createAnswer");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://service/", "voteAnswerResponse");
    private final static QName _GetAllQuestion_QNAME = new QName("http://service/", "getAllQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://service/", "getQuestionResponse");
    private final static QName _GetQuestion_QNAME = new QName("http://service/", "getQuestion");
    private final static QName _EditQuestionResponse_QNAME = new QName("http://service/", "editQuestionResponse");
    private final static QName _Exception_QNAME = new QName("http://service/", "Exception");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://service/", "deleteQuestionResponse");
    private final static QName _GetAllQuestionResponse_QNAME = new QName("http://service/", "getAllQuestionResponse");
    private final static QName _DeleteQuestion_QNAME = new QName("http://service/", "deleteQuestion");
    private final static QName _GetAnswers_QNAME = new QName("http://service/", "getAnswers");
    private final static QName _CreateAnswerResponse_QNAME = new QName("http://service/", "createAnswerResponse");
    private final static QName _Answer_QNAME = new QName("http://service/", "Answer");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://service/", "voteQuestionResponse");
    private final static QName _GetAnswersResponse_QNAME = new QName("http://service/", "getAnswersResponse");
    private final static QName _VoteQuestion_QNAME = new QName("http://service/", "voteQuestion");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://service/", "registerUserResponse");
    private final static QName _CreateQuestion_QNAME = new QName("http://service/", "createQuestion");
    private final static QName _VoteAnswer_QNAME = new QName("http://service/", "voteAnswer");
    private final static QName _Question_QNAME = new QName("http://service/", "Question");
    private final static QName _EditQuestion_QNAME = new QName("http://service/", "editQuestion");
    private final static QName _RegisterUser_QNAME = new QName("http://service/", "registerUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
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
     * Create an instance of {@link GetAllQuestion }
     * 
     */
    public GetAllQuestion createGetAllQuestion() {
        return new GetAllQuestion();
    }

    /**
     * Create an instance of {@link CreateAnswer }
     * 
     */
    public CreateAnswer createCreateAnswer() {
        return new CreateAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link CreateQuestionResponse }
     * 
     */
    public CreateQuestionResponse createCreateQuestionResponse() {
        return new CreateQuestionResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link DeleteQuestionResponse }
     * 
     */
    public DeleteQuestionResponse createDeleteQuestionResponse() {
        return new DeleteQuestionResponse();
    }

    /**
     * Create an instance of {@link GetAllQuestionResponse }
     * 
     */
    public GetAllQuestionResponse createGetAllQuestionResponse() {
        return new GetAllQuestionResponse();
    }

    /**
     * Create an instance of {@link EditQuestionResponse }
     * 
     */
    public EditQuestionResponse createEditQuestionResponse() {
        return new EditQuestionResponse();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link VoteQuestionResponse }
     * 
     */
    public VoteQuestionResponse createVoteQuestionResponse() {
        return new VoteQuestionResponse();
    }

    /**
     * Create an instance of {@link CreateAnswerResponse }
     * 
     */
    public CreateAnswerResponse createCreateAnswerResponse() {
        return new CreateAnswerResponse();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link GetAnswers }
     * 
     */
    public GetAnswers createGetAnswers() {
        return new GetAnswers();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link EditQuestion }
     * 
     */
    public EditQuestion createEditQuestion() {
        return new EditQuestion();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link CreateQuestion }
     * 
     */
    public CreateQuestion createCreateQuestion() {
        return new CreateQuestion();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link GetAnswersResponse }
     * 
     */
    public GetAnswersResponse createGetAnswersResponse() {
        return new GetAnswersResponse();
    }

    /**
     * Create an instance of {@link VoteQuestion }
     * 
     */
    public VoteQuestion createVoteQuestion() {
        return new VoteQuestion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createQuestionResponse")
    public JAXBElement<CreateQuestionResponse> createCreateQuestionResponse(CreateQuestionResponse value) {
        return new JAXBElement<CreateQuestionResponse>(_CreateQuestionResponse_QNAME, CreateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createAnswer")
    public JAXBElement<CreateAnswer> createCreateAnswer(CreateAnswer value) {
        return new JAXBElement<CreateAnswer>(_CreateAnswer_QNAME, CreateAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllQuestion")
    public JAXBElement<GetAllQuestion> createGetAllQuestion(GetAllQuestion value) {
        return new JAXBElement<GetAllQuestion>(_GetAllQuestion_QNAME, GetAllQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getQuestion")
    public JAXBElement<GetQuestion> createGetQuestion(GetQuestion value) {
        return new JAXBElement<GetQuestion>(_GetQuestion_QNAME, GetQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "editQuestionResponse")
    public JAXBElement<EditQuestionResponse> createEditQuestionResponse(EditQuestionResponse value) {
        return new JAXBElement<EditQuestionResponse>(_EditQuestionResponse_QNAME, EditQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAllQuestionResponse")
    public JAXBElement<GetAllQuestionResponse> createGetAllQuestionResponse(GetAllQuestionResponse value) {
        return new JAXBElement<GetAllQuestionResponse>(_GetAllQuestionResponse_QNAME, GetAllQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAnswers")
    public JAXBElement<GetAnswers> createGetAnswers(GetAnswers value) {
        return new JAXBElement<GetAnswers>(_GetAnswers_QNAME, GetAnswers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createAnswerResponse")
    public JAXBElement<CreateAnswerResponse> createCreateAnswerResponse(CreateAnswerResponse value) {
        return new JAXBElement<CreateAnswerResponse>(_CreateAnswerResponse_QNAME, CreateAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getAnswersResponse")
    public JAXBElement<GetAnswersResponse> createGetAnswersResponse(GetAnswersResponse value) {
        return new JAXBElement<GetAnswersResponse>(_GetAnswersResponse_QNAME, GetAnswersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "createQuestion")
    public JAXBElement<CreateQuestion> createCreateQuestion(CreateQuestion value) {
        return new JAXBElement<CreateQuestion>(_CreateQuestion_QNAME, CreateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "editQuestion")
    public JAXBElement<EditQuestion> createEditQuestion(EditQuestion value) {
        return new JAXBElement<EditQuestion>(_EditQuestion_QNAME, EditQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

}
