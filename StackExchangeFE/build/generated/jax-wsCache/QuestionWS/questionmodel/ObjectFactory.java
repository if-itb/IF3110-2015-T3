
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

    private final static QName _Exception_QNAME = new QName("http://QuestionModel/", "Exception");
    private final static QName _IOException_QNAME = new QName("http://QuestionModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://QuestionModel/", "ParseException");
    private final static QName _DeleteQuestion_QNAME = new QName("http://QuestionModel/", "deleteQuestion");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://QuestionModel/", "deleteQuestionResponse");
    private final static QName _GetAnswerCount_QNAME = new QName("http://QuestionModel/", "getAnswerCount");
    private final static QName _GetAnswerCountResponse_QNAME = new QName("http://QuestionModel/", "getAnswerCountResponse");
    private final static QName _GetQVoteByQID_QNAME = new QName("http://QuestionModel/", "getQVoteByQID");
    private final static QName _GetQVoteByQIDResponse_QNAME = new QName("http://QuestionModel/", "getQVoteByQIDResponse");
    private final static QName _GetQuestion_QNAME = new QName("http://QuestionModel/", "getQuestion");
    private final static QName _GetQuestionByQID_QNAME = new QName("http://QuestionModel/", "getQuestionByQID");
    private final static QName _GetQuestionByQIDResponse_QNAME = new QName("http://QuestionModel/", "getQuestionByQIDResponse");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://QuestionModel/", "getQuestionResponse");
    private final static QName _GetUserEmail_QNAME = new QName("http://QuestionModel/", "getUserEmail");
    private final static QName _GetUserEmailResponse_QNAME = new QName("http://QuestionModel/", "getUserEmailResponse");
    private final static QName _GetUserID_QNAME = new QName("http://QuestionModel/", "getUserID");
    private final static QName _GetUserIDResponse_QNAME = new QName("http://QuestionModel/", "getUserIDResponse");
    private final static QName _GetUserName_QNAME = new QName("http://QuestionModel/", "getUserName");
    private final static QName _GetUserNameResponse_QNAME = new QName("http://QuestionModel/", "getUserNameResponse");
    private final static QName _InsertQuestion_QNAME = new QName("http://QuestionModel/", "insertQuestion");
    private final static QName _InsertQuestionResponse_QNAME = new QName("http://QuestionModel/", "insertQuestionResponse");
    private final static QName _UpdateQuestion_QNAME = new QName("http://QuestionModel/", "updateQuestion");
    private final static QName _UpdateQuestionResponse_QNAME = new QName("http://QuestionModel/", "updateQuestionResponse");
    private final static QName _VoteQuestion_QNAME = new QName("http://QuestionModel/", "voteQuestion");
    private final static QName _VoteQuestionResponse_QNAME = new QName("http://QuestionModel/", "voteQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: questionmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
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
     * Create an instance of {@link GetAnswerCount }
     * 
     */
    public GetAnswerCount createGetAnswerCount() {
        return new GetAnswerCount();
    }

    /**
     * Create an instance of {@link GetAnswerCountResponse }
     * 
     */
    public GetAnswerCountResponse createGetAnswerCountResponse() {
        return new GetAnswerCountResponse();
    }

    /**
     * Create an instance of {@link GetQVoteByQID }
     * 
     */
    public GetQVoteByQID createGetQVoteByQID() {
        return new GetQVoteByQID();
    }

    /**
     * Create an instance of {@link GetQVoteByQIDResponse }
     * 
     */
    public GetQVoteByQIDResponse createGetQVoteByQIDResponse() {
        return new GetQVoteByQIDResponse();
    }

    /**
     * Create an instance of {@link GetQuestion }
     * 
     */
    public GetQuestion createGetQuestion() {
        return new GetQuestion();
    }

    /**
     * Create an instance of {@link GetQuestionByQID }
     * 
     */
    public GetQuestionByQID createGetQuestionByQID() {
        return new GetQuestionByQID();
    }

    /**
     * Create an instance of {@link GetQuestionByQIDResponse }
     * 
     */
    public GetQuestionByQIDResponse createGetQuestionByQIDResponse() {
        return new GetQuestionByQIDResponse();
    }

    /**
     * Create an instance of {@link GetQuestionResponse }
     * 
     */
    public GetQuestionResponse createGetQuestionResponse() {
        return new GetQuestionResponse();
    }

    /**
     * Create an instance of {@link GetUserEmail }
     * 
     */
    public GetUserEmail createGetUserEmail() {
        return new GetUserEmail();
    }

    /**
     * Create an instance of {@link GetUserEmailResponse }
     * 
     */
    public GetUserEmailResponse createGetUserEmailResponse() {
        return new GetUserEmailResponse();
    }

    /**
     * Create an instance of {@link GetUserID }
     * 
     */
    public GetUserID createGetUserID() {
        return new GetUserID();
    }

    /**
     * Create an instance of {@link GetUserIDResponse }
     * 
     */
    public GetUserIDResponse createGetUserIDResponse() {
        return new GetUserIDResponse();
    }

    /**
     * Create an instance of {@link GetUserName }
     * 
     */
    public GetUserName createGetUserName() {
        return new GetUserName();
    }

    /**
     * Create an instance of {@link GetUserNameResponse }
     * 
     */
    public GetUserNameResponse createGetUserNameResponse() {
        return new GetUserNameResponse();
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
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getAnswerCount")
    public JAXBElement<GetAnswerCount> createGetAnswerCount(GetAnswerCount value) {
        return new JAXBElement<GetAnswerCount>(_GetAnswerCount_QNAME, GetAnswerCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getAnswerCountResponse")
    public JAXBElement<GetAnswerCountResponse> createGetAnswerCountResponse(GetAnswerCountResponse value) {
        return new JAXBElement<GetAnswerCountResponse>(_GetAnswerCountResponse_QNAME, GetAnswerCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQVoteByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQVoteByQID")
    public JAXBElement<GetQVoteByQID> createGetQVoteByQID(GetQVoteByQID value) {
        return new JAXBElement<GetQVoteByQID>(_GetQVoteByQID_QNAME, GetQVoteByQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQVoteByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQVoteByQIDResponse")
    public JAXBElement<GetQVoteByQIDResponse> createGetQVoteByQIDResponse(GetQVoteByQIDResponse value) {
        return new JAXBElement<GetQVoteByQIDResponse>(_GetQVoteByQIDResponse_QNAME, GetQVoteByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQuestion")
    public JAXBElement<GetQuestion> createGetQuestion(GetQuestion value) {
        return new JAXBElement<GetQuestion>(_GetQuestion_QNAME, GetQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQuestionByQID")
    public JAXBElement<GetQuestionByQID> createGetQuestionByQID(GetQuestionByQID value) {
        return new JAXBElement<GetQuestionByQID>(_GetQuestionByQID_QNAME, GetQuestionByQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQuestionByQIDResponse")
    public JAXBElement<GetQuestionByQIDResponse> createGetQuestionByQIDResponse(GetQuestionByQIDResponse value) {
        return new JAXBElement<GetQuestionByQIDResponse>(_GetQuestionByQIDResponse_QNAME, GetQuestionByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserEmail")
    public JAXBElement<GetUserEmail> createGetUserEmail(GetUserEmail value) {
        return new JAXBElement<GetUserEmail>(_GetUserEmail_QNAME, GetUserEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserEmailResponse")
    public JAXBElement<GetUserEmailResponse> createGetUserEmailResponse(GetUserEmailResponse value) {
        return new JAXBElement<GetUserEmailResponse>(_GetUserEmailResponse_QNAME, GetUserEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserID")
    public JAXBElement<GetUserID> createGetUserID(GetUserID value) {
        return new JAXBElement<GetUserID>(_GetUserID_QNAME, GetUserID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserIDResponse")
    public JAXBElement<GetUserIDResponse> createGetUserIDResponse(GetUserIDResponse value) {
        return new JAXBElement<GetUserIDResponse>(_GetUserIDResponse_QNAME, GetUserIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserName")
    public JAXBElement<GetUserName> createGetUserName(GetUserName value) {
        return new JAXBElement<GetUserName>(_GetUserName_QNAME, GetUserName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getUserNameResponse")
    public JAXBElement<GetUserNameResponse> createGetUserNameResponse(GetUserNameResponse value) {
        return new JAXBElement<GetUserNameResponse>(_GetUserNameResponse_QNAME, GetUserNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "insertQuestion")
    public JAXBElement<InsertQuestion> createInsertQuestion(InsertQuestion value) {
        return new JAXBElement<InsertQuestion>(_InsertQuestion_QNAME, InsertQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "insertQuestionResponse")
    public JAXBElement<InsertQuestionResponse> createInsertQuestionResponse(InsertQuestionResponse value) {
        return new JAXBElement<InsertQuestionResponse>(_InsertQuestionResponse_QNAME, InsertQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "updateQuestion")
    public JAXBElement<UpdateQuestion> createUpdateQuestion(UpdateQuestion value) {
        return new JAXBElement<UpdateQuestion>(_UpdateQuestion_QNAME, UpdateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "updateQuestionResponse")
    public JAXBElement<UpdateQuestionResponse> createUpdateQuestionResponse(UpdateQuestionResponse value) {
        return new JAXBElement<UpdateQuestionResponse>(_UpdateQuestionResponse_QNAME, UpdateQuestionResponse.class, null, value);
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

}
