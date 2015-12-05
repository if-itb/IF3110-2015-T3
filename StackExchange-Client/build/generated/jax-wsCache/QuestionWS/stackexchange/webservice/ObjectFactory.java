
package stackexchange.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stackexchange.webservice package. 
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

    private final static QName _Question_QNAME = new QName("http://webservice.stackexchange/", "Question");
    private final static QName _AddQuestion_QNAME = new QName("http://webservice.stackexchange/", "addQuestion");
    private final static QName _DeleteQuestion_QNAME = new QName("http://webservice.stackexchange/", "deleteQuestion");
    private final static QName _GetQuestions_QNAME = new QName("http://webservice.stackexchange/", "getQuestions");
    private final static QName _GetQuestionsResponse_QNAME = new QName("http://webservice.stackexchange/", "getQuestionsResponse");
    private final static QName _GetQuestions1_QNAME = new QName("http://webservice.stackexchange/", "getQuestions_1");
    private final static QName _GetQuestions1Response_QNAME = new QName("http://webservice.stackexchange/", "getQuestions_1Response");
    private final static QName _GetQuestions2_QNAME = new QName("http://webservice.stackexchange/", "getQuestions_2");
    private final static QName _GetQuestions2Response_QNAME = new QName("http://webservice.stackexchange/", "getQuestions_2Response");
    private final static QName _UpdateQuestion_QNAME = new QName("http://webservice.stackexchange/", "updateQuestion");
    private final static QName _UpdateQuestionResponse_QNAME = new QName("http://webservice.stackexchange/", "updateQuestionResponse");
    private final static QName _VoteQuestion_QNAME = new QName("http://webservice.stackexchange/", "voteQuestion");
    private final static QName _VoteQuestion1_QNAME = new QName("http://webservice.stackexchange/", "voteQuestion_1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stackexchange.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link AddQuestion }
     * 
     */
    public AddQuestion createAddQuestion() {
        return new AddQuestion();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link GetQuestions }
     * 
     */
    public GetQuestions createGetQuestions() {
        return new GetQuestions();
    }

    /**
     * Create an instance of {@link GetQuestionsResponse }
     * 
     */
    public GetQuestionsResponse createGetQuestionsResponse() {
        return new GetQuestionsResponse();
    }

    /**
     * Create an instance of {@link GetQuestions1 }
     * 
     */
    public GetQuestions1 createGetQuestions1() {
        return new GetQuestions1();
    }

    /**
     * Create an instance of {@link GetQuestions1Response }
     * 
     */
    public GetQuestions1Response createGetQuestions1Response() {
        return new GetQuestions1Response();
    }

    /**
     * Create an instance of {@link GetQuestions2 }
     * 
     */
    public GetQuestions2 createGetQuestions2() {
        return new GetQuestions2();
    }

    /**
     * Create an instance of {@link GetQuestions2Response }
     * 
     */
    public GetQuestions2Response createGetQuestions2Response() {
        return new GetQuestions2Response();
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
     * Create an instance of {@link VoteQuestion1 }
     * 
     */
    public VoteQuestion1 createVoteQuestion1() {
        return new VoteQuestion1();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "addQuestion")
    public JAXBElement<AddQuestion> createAddQuestion(AddQuestion value) {
        return new JAXBElement<AddQuestion>(_AddQuestion_QNAME, AddQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestions")
    public JAXBElement<GetQuestions> createGetQuestions(GetQuestions value) {
        return new JAXBElement<GetQuestions>(_GetQuestions_QNAME, GetQuestions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestionsResponse")
    public JAXBElement<GetQuestionsResponse> createGetQuestionsResponse(GetQuestionsResponse value) {
        return new JAXBElement<GetQuestionsResponse>(_GetQuestionsResponse_QNAME, GetQuestionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestions1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestions_1")
    public JAXBElement<GetQuestions1> createGetQuestions1(GetQuestions1 value) {
        return new JAXBElement<GetQuestions1>(_GetQuestions1_QNAME, GetQuestions1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestions1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestions_1Response")
    public JAXBElement<GetQuestions1Response> createGetQuestions1Response(GetQuestions1Response value) {
        return new JAXBElement<GetQuestions1Response>(_GetQuestions1Response_QNAME, GetQuestions1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestions2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestions_2")
    public JAXBElement<GetQuestions2> createGetQuestions2(GetQuestions2 value) {
        return new JAXBElement<GetQuestions2>(_GetQuestions2_QNAME, GetQuestions2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestions2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getQuestions_2Response")
    public JAXBElement<GetQuestions2Response> createGetQuestions2Response(GetQuestions2Response value) {
        return new JAXBElement<GetQuestions2Response>(_GetQuestions2Response_QNAME, GetQuestions2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "updateQuestion")
    public JAXBElement<UpdateQuestion> createUpdateQuestion(UpdateQuestion value) {
        return new JAXBElement<UpdateQuestion>(_UpdateQuestion_QNAME, UpdateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "updateQuestionResponse")
    public JAXBElement<UpdateQuestionResponse> createUpdateQuestionResponse(UpdateQuestionResponse value) {
        return new JAXBElement<UpdateQuestionResponse>(_UpdateQuestionResponse_QNAME, UpdateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "voteQuestion")
    public JAXBElement<VoteQuestion> createVoteQuestion(VoteQuestion value) {
        return new JAXBElement<VoteQuestion>(_VoteQuestion_QNAME, VoteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteQuestion1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "voteQuestion_1")
    public JAXBElement<VoteQuestion1> createVoteQuestion1(VoteQuestion1 value) {
        return new JAXBElement<VoteQuestion1>(_VoteQuestion1_QNAME, VoteQuestion1 .class, null, value);
    }

}
