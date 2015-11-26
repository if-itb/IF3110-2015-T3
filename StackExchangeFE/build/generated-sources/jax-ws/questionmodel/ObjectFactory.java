
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
    private final static QName _DeleteQuesetion_QNAME = new QName("http://QuestionModel/", "deleteQuesetion");
    private final static QName _DeleteQuesetionResponse_QNAME = new QName("http://QuestionModel/", "deleteQuesetionResponse");
    private final static QName _GetAnswerCount_QNAME = new QName("http://QuestionModel/", "getAnswerCount");
    private final static QName _GetAnswerCountResponse_QNAME = new QName("http://QuestionModel/", "getAnswerCountResponse");
    private final static QName _GetQVoteByQID_QNAME = new QName("http://QuestionModel/", "getQVoteByQID");
    private final static QName _GetQVoteByQIDResponse_QNAME = new QName("http://QuestionModel/", "getQVoteByQIDResponse");
    private final static QName _GetQuestion_QNAME = new QName("http://QuestionModel/", "getQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://QuestionModel/", "getQuestionResponse");
    private final static QName _GetUserID_QNAME = new QName("http://QuestionModel/", "getUserID");
    private final static QName _GetUserIDResponse_QNAME = new QName("http://QuestionModel/", "getUserIDResponse");
    private final static QName _InsertQuestion_QNAME = new QName("http://QuestionModel/", "insertQuestion");
    private final static QName _InsertQuestionResponse_QNAME = new QName("http://QuestionModel/", "insertQuestionResponse");
    private final static QName _UpdateQuestion_QNAME = new QName("http://QuestionModel/", "updateQuestion");
    private final static QName _UpdateQuestionResponse_QNAME = new QName("http://QuestionModel/", "updateQuestionResponse");

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
     * Create an instance of {@link DeleteQuesetion }
     * 
     */
    public DeleteQuesetion createDeleteQuesetion() {
        return new DeleteQuesetion();
    }

    /**
     * Create an instance of {@link DeleteQuesetionResponse }
     * 
     */
    public DeleteQuesetionResponse createDeleteQuesetionResponse() {
        return new DeleteQuesetionResponse();
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
     * Create an instance of {@link GetQuestionResponse }
     * 
     */
    public GetQuestionResponse createGetQuestionResponse() {
        return new GetQuestionResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuesetion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "deleteQuesetion")
    public JAXBElement<DeleteQuesetion> createDeleteQuesetion(DeleteQuesetion value) {
        return new JAXBElement<DeleteQuesetion>(_DeleteQuesetion_QNAME, DeleteQuesetion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuesetionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "deleteQuesetionResponse")
    public JAXBElement<DeleteQuesetionResponse> createDeleteQuesetionResponse(DeleteQuesetionResponse value) {
        return new JAXBElement<DeleteQuesetionResponse>(_DeleteQuesetionResponse_QNAME, DeleteQuesetionResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionModel/", name = "getQuestionResponse")
    public JAXBElement<GetQuestionResponse> createGetQuestionResponse(GetQuestionResponse value) {
        return new JAXBElement<GetQuestionResponse>(_GetQuestionResponse_QNAME, GetQuestionResponse.class, null, value);
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

}
