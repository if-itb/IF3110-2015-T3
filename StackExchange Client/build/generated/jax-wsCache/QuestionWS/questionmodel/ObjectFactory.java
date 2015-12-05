
package questionmodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the questionmodel package. 
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

    private final static QName _DeleteQuestion_QNAME = new QName("http://QuestionModel/", "DeleteQuestion");
    private final static QName _Question_QNAME = new QName("http://QuestionModel/", "Question");
    private final static QName _EditQuestion_QNAME = new QName("http://QuestionModel/", "editQuestion");
    private final static QName _ParseException_QNAME = new QName("http://QuestionModel/", "ParseException");
    private final static QName _CreateQuestion_QNAME = new QName("http://QuestionModel/", "createQuestion");
    private final static QName _VoteQuestion_QNAME = new QName("http://QuestionModel/", "voteQuestion");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://QuestionModel/", "voteQuestionResponse");
    private final static QName _GetQuestionByID_QNAME = new QName("http://QuestionModel/", "GetQuestionByID");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://QuestionModel/", "DeleteQuestionResponse");
    private final static QName _GetAllQuestionResponse_QNAME = new QName("http://QuestionModel/", "GetAllQuestionResponse");
    private final static QName _GetQuestionByIDResponse_QNAME = new QName("http://QuestionModel/", "GetQuestionByIDResponse");
    private final static QName _GetAllQuestion_QNAME = new QName("http://QuestionModel/", "GetAllQuestion");
    private final static QName _EditQuestionResponse_QNAME = new QName("http://QuestionModel/", "editQuestionResponse");
    private final static QName _CreateQuestionResponse_QNAME = new QName("http://QuestionModel/", "createQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: questionmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateQuestionResponse }
     * 
     */
    public CreateQuestionResponse createCreateQuestionResponse() {
        return new CreateQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestionByIDResponse }
     * 
     */
    public GetQuestionByIDResponse createGetQuestionByIDResponse() {
        return new GetQuestionByIDResponse();
    }

    /**
     * Create an instance of {@link GetAllQuestion }
     * 
     */
    public GetAllQuestion createGetAllQuestion() {
        return new GetAllQuestion();
    }

    /**
     * Create an instance of {@link EditQuestionResponse }
     * 
     */
    public EditQuestionResponse createEditQuestionResponse() {
        return new EditQuestionResponse();
    }

    /**
     * Create an instance of {@link VoteQuestionResponse }
     * 
     */
    public VoteQuestionResponse createVoteQuestionResponse() {
        return new VoteQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestionByID }
     * 
     */
    public GetQuestionByID createGetQuestionByID() {
        return new GetQuestionByID();
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
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
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
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link CreateQuestion }
     * 
     */
    public CreateQuestion createCreateQuestion() {
        return new CreateQuestion();
    }

    /**
     * Create an instance of {@link VoteQuestion }
     * 
     */
    public VoteQuestion createVoteQuestion() {
        return new VoteQuestion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "DeleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "editQuestion")
    public JAXBElement<EditQuestion> createEditQuestion(EditQuestion value) {
        return new JAXBElement<EditQuestion>(_EditQuestion_QNAME, EditQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "createQuestion")
    public JAXBElement<CreateQuestion> createCreateQuestion(CreateQuestion value) {
        return new JAXBElement<CreateQuestion>(_CreateQuestion_QNAME, CreateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "voteQuestionResponse")
    public JAXBElement<VoteQuestionResponse> createVoteQuestionResponse(VoteQuestionResponse value) {
        return new JAXBElement<VoteQuestionResponse>(_VoteQuestionResponse_QNAME, VoteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "GetQuestionByID")
    public JAXBElement<GetQuestionByID> createGetQuestionByID(GetQuestionByID value) {
        return new JAXBElement<GetQuestionByID>(_GetQuestionByID_QNAME, GetQuestionByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "DeleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "GetAllQuestionResponse")
    public JAXBElement<GetAllQuestionResponse> createGetAllQuestionResponse(GetAllQuestionResponse value) {
        return new JAXBElement<GetAllQuestionResponse>(_GetAllQuestionResponse_QNAME, GetAllQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "GetQuestionByIDResponse")
    public JAXBElement<GetQuestionByIDResponse> createGetQuestionByIDResponse(GetQuestionByIDResponse value) {
        return new JAXBElement<GetQuestionByIDResponse>(_GetQuestionByIDResponse_QNAME, GetQuestionByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "GetAllQuestion")
    public JAXBElement<GetAllQuestion> createGetAllQuestion(GetAllQuestion value) {
        return new JAXBElement<GetAllQuestion>(_GetAllQuestion_QNAME, GetAllQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "editQuestionResponse")
    public JAXBElement<EditQuestionResponse> createEditQuestionResponse(EditQuestionResponse value) {
        return new JAXBElement<EditQuestionResponse>(_EditQuestionResponse_QNAME, EditQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "createQuestionResponse")
    public JAXBElement<CreateQuestionResponse> createCreateQuestionResponse(CreateQuestionResponse value) {
        return new JAXBElement<CreateQuestionResponse>(_CreateQuestionResponse_QNAME, CreateQuestionResponse.class, null, value);
    }

}
