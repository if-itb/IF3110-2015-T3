
package stackexchangews;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stackexchangews package. 
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

    private final static QName _InsertAnswer_QNAME = new QName("http://StackExchangeWS/", "insertAnswer");
    private final static QName _UpdateQuestion_QNAME = new QName("http://StackExchangeWS/", "updateQuestion");
    private final static QName _VoteDownAnswer_QNAME = new QName("http://StackExchangeWS/", "voteDownAnswer");
    private final static QName _Answer_QNAME = new QName("http://StackExchangeWS/", "Answer");
    private final static QName _RegisterResponse_QNAME = new QName("http://StackExchangeWS/", "registerResponse");
    private final static QName _GetNameById_QNAME = new QName("http://StackExchangeWS/", "getNameById");
    private final static QName _GetRecentQuestionsResponse_QNAME = new QName("http://StackExchangeWS/", "getRecentQuestionsResponse");
    private final static QName _DeleteQuestion_QNAME = new QName("http://StackExchangeWS/", "deleteQuestion");
    private final static QName _GetAnswer_QNAME = new QName("http://StackExchangeWS/", "getAnswer");
    private final static QName _Question_QNAME = new QName("http://StackExchangeWS/", "Question");
    private final static QName _GetAnswerResponse_QNAME = new QName("http://StackExchangeWS/", "getAnswerResponse");
    private final static QName _GetNameByIdResponse_QNAME = new QName("http://StackExchangeWS/", "getNameByIdResponse");
    private final static QName _VoteUpQuestion_QNAME = new QName("http://StackExchangeWS/", "voteUpQuestion");
    private final static QName _Register_QNAME = new QName("http://StackExchangeWS/", "register");
    private final static QName _VoteUpAnswer_QNAME = new QName("http://StackExchangeWS/", "voteUpAnswer");
    private final static QName _GetIdByTokenResponse_QNAME = new QName("http://StackExchangeWS/", "getIdByTokenResponse");
    private final static QName _VoteDownQuestion_QNAME = new QName("http://StackExchangeWS/", "voteDownQuestion");
    private final static QName _GetQuestion_QNAME = new QName("http://StackExchangeWS/", "getQuestion");
    private final static QName _GetRecentQuestions_QNAME = new QName("http://StackExchangeWS/", "getRecentQuestions");
    private final static QName _InsertQuestion_QNAME = new QName("http://StackExchangeWS/", "insertQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://StackExchangeWS/", "getQuestionResponse");
    private final static QName _CheckTokenResponse_QNAME = new QName("http://StackExchangeWS/", "checkTokenResponse");
    private final static QName _CheckToken_QNAME = new QName("http://StackExchangeWS/", "checkToken");
    private final static QName _GetIdByToken_QNAME = new QName("http://StackExchangeWS/", "getIdByToken");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stackexchangews
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
     * Create an instance of {@link GetRecentQuestions }
     * 
     */
    public GetRecentQuestions createGetRecentQuestions() {
        return new GetRecentQuestions();
    }

    /**
     * Create an instance of {@link InsertQuestion }
     * 
     */
    public InsertQuestion createInsertQuestion() {
        return new InsertQuestion();
    }

    /**
     * Create an instance of {@link GetQuestionResponse }
     * 
     */
    public GetQuestionResponse createGetQuestionResponse() {
        return new GetQuestionResponse();
    }

    /**
     * Create an instance of {@link VoteDownQuestion }
     * 
     */
    public VoteDownQuestion createVoteDownQuestion() {
        return new VoteDownQuestion();
    }

    /**
     * Create an instance of {@link CheckTokenResponse }
     * 
     */
    public CheckTokenResponse createCheckTokenResponse() {
        return new CheckTokenResponse();
    }

    /**
     * Create an instance of {@link CheckToken }
     * 
     */
    public CheckToken createCheckToken() {
        return new CheckToken();
    }

    /**
     * Create an instance of {@link GetIdByToken }
     * 
     */
    public GetIdByToken createGetIdByToken() {
        return new GetIdByToken();
    }

    /**
     * Create an instance of {@link InsertAnswer }
     * 
     */
    public InsertAnswer createInsertAnswer() {
        return new InsertAnswer();
    }

    /**
     * Create an instance of {@link UpdateQuestion }
     * 
     */
    public UpdateQuestion createUpdateQuestion() {
        return new UpdateQuestion();
    }

    /**
     * Create an instance of {@link VoteDownAnswer }
     * 
     */
    public VoteDownAnswer createVoteDownAnswer() {
        return new VoteDownAnswer();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link GetNameById }
     * 
     */
    public GetNameById createGetNameById() {
        return new GetNameById();
    }

    /**
     * Create an instance of {@link GetRecentQuestionsResponse }
     * 
     */
    public GetRecentQuestionsResponse createGetRecentQuestionsResponse() {
        return new GetRecentQuestionsResponse();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link GetAnswer }
     * 
     */
    public GetAnswer createGetAnswer() {
        return new GetAnswer();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link GetNameByIdResponse }
     * 
     */
    public GetNameByIdResponse createGetNameByIdResponse() {
        return new GetNameByIdResponse();
    }

    /**
     * Create an instance of {@link VoteUpQuestion }
     * 
     */
    public VoteUpQuestion createVoteUpQuestion() {
        return new VoteUpQuestion();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link GetAnswerResponse }
     * 
     */
    public GetAnswerResponse createGetAnswerResponse() {
        return new GetAnswerResponse();
    }

    /**
     * Create an instance of {@link VoteUpAnswer }
     * 
     */
    public VoteUpAnswer createVoteUpAnswer() {
        return new VoteUpAnswer();
    }

    /**
     * Create an instance of {@link GetIdByTokenResponse }
     * 
     */
    public GetIdByTokenResponse createGetIdByTokenResponse() {
        return new GetIdByTokenResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "insertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "updateQuestion")
    public JAXBElement<UpdateQuestion> createUpdateQuestion(UpdateQuestion value) {
        return new JAXBElement<UpdateQuestion>(_UpdateQuestion_QNAME, UpdateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteDownAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "voteDownAnswer")
    public JAXBElement<VoteDownAnswer> createVoteDownAnswer(VoteDownAnswer value) {
        return new JAXBElement<VoteDownAnswer>(_VoteDownAnswer_QNAME, VoteDownAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getNameById")
    public JAXBElement<GetNameById> createGetNameById(GetNameById value) {
        return new JAXBElement<GetNameById>(_GetNameById_QNAME, GetNameById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecentQuestionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getRecentQuestionsResponse")
    public JAXBElement<GetRecentQuestionsResponse> createGetRecentQuestionsResponse(GetRecentQuestionsResponse value) {
        return new JAXBElement<GetRecentQuestionsResponse>(_GetRecentQuestionsResponse_QNAME, GetRecentQuestionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getAnswer")
    public JAXBElement<GetAnswer> createGetAnswer(GetAnswer value) {
        return new JAXBElement<GetAnswer>(_GetAnswer_QNAME, GetAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getAnswerResponse")
    public JAXBElement<GetAnswerResponse> createGetAnswerResponse(GetAnswerResponse value) {
        return new JAXBElement<GetAnswerResponse>(_GetAnswerResponse_QNAME, GetAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getNameByIdResponse")
    public JAXBElement<GetNameByIdResponse> createGetNameByIdResponse(GetNameByIdResponse value) {
        return new JAXBElement<GetNameByIdResponse>(_GetNameByIdResponse_QNAME, GetNameByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteUpQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "voteUpQuestion")
    public JAXBElement<VoteUpQuestion> createVoteUpQuestion(VoteUpQuestion value) {
        return new JAXBElement<VoteUpQuestion>(_VoteUpQuestion_QNAME, VoteUpQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteUpAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "voteUpAnswer")
    public JAXBElement<VoteUpAnswer> createVoteUpAnswer(VoteUpAnswer value) {
        return new JAXBElement<VoteUpAnswer>(_VoteUpAnswer_QNAME, VoteUpAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIdByTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getIdByTokenResponse")
    public JAXBElement<GetIdByTokenResponse> createGetIdByTokenResponse(GetIdByTokenResponse value) {
        return new JAXBElement<GetIdByTokenResponse>(_GetIdByTokenResponse_QNAME, GetIdByTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteDownQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "voteDownQuestion")
    public JAXBElement<VoteDownQuestion> createVoteDownQuestion(VoteDownQuestion value) {
        return new JAXBElement<VoteDownQuestion>(_VoteDownQuestion_QNAME, VoteDownQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getQuestion")
    public JAXBElement<GetQuestion> createGetQuestion(GetQuestion value) {
        return new JAXBElement<GetQuestion>(_GetQuestion_QNAME, GetQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecentQuestions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getRecentQuestions")
    public JAXBElement<GetRecentQuestions> createGetRecentQuestions(GetRecentQuestions value) {
        return new JAXBElement<GetRecentQuestions>(_GetRecentQuestions_QNAME, GetRecentQuestions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "insertQuestion")
    public JAXBElement<InsertQuestion> createInsertQuestion(InsertQuestion value) {
        return new JAXBElement<InsertQuestion>(_InsertQuestion_QNAME, InsertQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "checkTokenResponse")
    public JAXBElement<CheckTokenResponse> createCheckTokenResponse(CheckTokenResponse value) {
        return new JAXBElement<CheckTokenResponse>(_CheckTokenResponse_QNAME, CheckTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "checkToken")
    public JAXBElement<CheckToken> createCheckToken(CheckToken value) {
        return new JAXBElement<CheckToken>(_CheckToken_QNAME, CheckToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIdByToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://StackExchangeWS/", name = "getIdByToken")
    public JAXBElement<GetIdByToken> createGetIdByToken(GetIdByToken value) {
        return new JAXBElement<GetIdByToken>(_GetIdByToken_QNAME, GetIdByToken.class, null, value);
    }

}
