
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

    private final static QName _Answer_QNAME = new QName("http://question_package.dazzlesquad.com/", "Answer");
    private final static QName _Question_QNAME = new QName("http://question_package.dazzlesquad.com/", "Question");
    private final static QName _CountAnswer_QNAME = new QName("http://question_package.dazzlesquad.com/", "countAnswer");
    private final static QName _CountAnswerResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "countAnswerResponse");
    private final static QName _DeleteQuestion_QNAME = new QName("http://question_package.dazzlesquad.com/", "deleteQuestion");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "deleteQuestionResponse");
    private final static QName _EditQuestion_QNAME = new QName("http://question_package.dazzlesquad.com/", "editQuestion");
    private final static QName _EditQuestionResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "editQuestionResponse");
    private final static QName _GetAnswerByQuestionId_QNAME = new QName("http://question_package.dazzlesquad.com/", "getAnswerByQuestionId");
    private final static QName _GetAnswerByQuestionIdResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "getAnswerByQuestionIdResponse");
    private final static QName _GetQuestionById_QNAME = new QName("http://question_package.dazzlesquad.com/", "getQuestionById");
    private final static QName _GetQuestionByIdResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "getQuestionByIdResponse");
    private final static QName _Hello_QNAME = new QName("http://question_package.dazzlesquad.com/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "helloResponse");
    private final static QName _InsertQuestion_QNAME = new QName("http://question_package.dazzlesquad.com/", "insertQuestion");
    private final static QName _InsertQuestionResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "insertQuestionResponse");
    private final static QName _ShowAllQuestion_QNAME = new QName("http://question_package.dazzlesquad.com/", "showAllQuestion");
    private final static QName _ShowAllQuestionResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "showAllQuestionResponse");
    private final static QName _VoteQuestion_QNAME = new QName("http://question_package.dazzlesquad.com/", "voteQuestion");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://question_package.dazzlesquad.com/", "voteQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: QuestionWS
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
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link CountAnswer }
     * 
     */
    public CountAnswer createCountAnswer() {
        return new CountAnswer();
    }

    /**
     * Create an instance of {@link CountAnswerResponse }
     * 
     */
    public CountAnswerResponse createCountAnswerResponse() {
        return new CountAnswerResponse();
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
     * Create an instance of {@link EditQuestion }
     * 
     */
    public EditQuestion createEditQuestion() {
        return new EditQuestion();
    }

    /**
     * Create an instance of {@link EditQuestionResponse }
     * 
     */
    public EditQuestionResponse createEditQuestionResponse() {
        return new EditQuestionResponse();
    }

    /**
     * Create an instance of {@link GetAnswerByQuestionId }
     * 
     */
    public GetAnswerByQuestionId createGetAnswerByQuestionId() {
        return new GetAnswerByQuestionId();
    }

    /**
     * Create an instance of {@link GetAnswerByQuestionIdResponse }
     * 
     */
    public GetAnswerByQuestionIdResponse createGetAnswerByQuestionIdResponse() {
        return new GetAnswerByQuestionIdResponse();
    }

    /**
     * Create an instance of {@link GetQuestionById }
     * 
     */
    public GetQuestionById createGetQuestionById() {
        return new GetQuestionById();
    }

    /**
     * Create an instance of {@link GetQuestionByIdResponse }
     * 
     */
    public GetQuestionByIdResponse createGetQuestionByIdResponse() {
        return new GetQuestionByIdResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link InsertQuestion }
     * 
     */
    public InsertQuestion createInsertQuestion() {
        return new InsertQuestion();
    }

    /**
     * Create an instance of {@link InsertQuestionResponse }
     * 
     */
    public InsertQuestionResponse createInsertQuestionResponse() {
        return new InsertQuestionResponse();
    }

    /**
     * Create an instance of {@link ShowAllQuestion }
     * 
     */
    public ShowAllQuestion createShowAllQuestion() {
        return new ShowAllQuestion();
    }

    /**
     * Create an instance of {@link ShowAllQuestionResponse }
     * 
     */
    public ShowAllQuestionResponse createShowAllQuestionResponse() {
        return new ShowAllQuestionResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "countAnswer")
    public JAXBElement<CountAnswer> createCountAnswer(CountAnswer value) {
        return new JAXBElement<CountAnswer>(_CountAnswer_QNAME, CountAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "countAnswerResponse")
    public JAXBElement<CountAnswerResponse> createCountAnswerResponse(CountAnswerResponse value) {
        return new JAXBElement<CountAnswerResponse>(_CountAnswerResponse_QNAME, CountAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "editQuestion")
    public JAXBElement<EditQuestion> createEditQuestion(EditQuestion value) {
        return new JAXBElement<EditQuestion>(_EditQuestion_QNAME, EditQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "editQuestionResponse")
    public JAXBElement<EditQuestionResponse> createEditQuestionResponse(EditQuestionResponse value) {
        return new JAXBElement<EditQuestionResponse>(_EditQuestionResponse_QNAME, EditQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQuestionId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "getAnswerByQuestionId")
    public JAXBElement<GetAnswerByQuestionId> createGetAnswerByQuestionId(GetAnswerByQuestionId value) {
        return new JAXBElement<GetAnswerByQuestionId>(_GetAnswerByQuestionId_QNAME, GetAnswerByQuestionId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQuestionIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "getAnswerByQuestionIdResponse")
    public JAXBElement<GetAnswerByQuestionIdResponse> createGetAnswerByQuestionIdResponse(GetAnswerByQuestionIdResponse value) {
        return new JAXBElement<GetAnswerByQuestionIdResponse>(_GetAnswerByQuestionIdResponse_QNAME, GetAnswerByQuestionIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "getQuestionById")
    public JAXBElement<GetQuestionById> createGetQuestionById(GetQuestionById value) {
        return new JAXBElement<GetQuestionById>(_GetQuestionById_QNAME, GetQuestionById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "getQuestionByIdResponse")
    public JAXBElement<GetQuestionByIdResponse> createGetQuestionByIdResponse(GetQuestionByIdResponse value) {
        return new JAXBElement<GetQuestionByIdResponse>(_GetQuestionByIdResponse_QNAME, GetQuestionByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "insertQuestion")
    public JAXBElement<InsertQuestion> createInsertQuestion(InsertQuestion value) {
        return new JAXBElement<InsertQuestion>(_InsertQuestion_QNAME, InsertQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "insertQuestionResponse")
    public JAXBElement<InsertQuestionResponse> createInsertQuestionResponse(InsertQuestionResponse value) {
        return new JAXBElement<InsertQuestionResponse>(_InsertQuestionResponse_QNAME, InsertQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowAllQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "showAllQuestion")
    public JAXBElement<ShowAllQuestion> createShowAllQuestion(ShowAllQuestion value) {
        return new JAXBElement<ShowAllQuestion>(_ShowAllQuestion_QNAME, ShowAllQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowAllQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "showAllQuestionResponse")
    public JAXBElement<ShowAllQuestionResponse> createShowAllQuestionResponse(ShowAllQuestionResponse value) {
        return new JAXBElement<ShowAllQuestionResponse>(_ShowAllQuestionResponse_QNAME, ShowAllQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question_package.dazzlesquad.com/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

}
