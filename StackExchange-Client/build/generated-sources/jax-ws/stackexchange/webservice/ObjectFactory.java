
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

    private final static QName _Answer_QNAME = new QName("http://webservice.stackexchange/", "Answer");
    private final static QName _AddAnswer_QNAME = new QName("http://webservice.stackexchange/", "addAnswer");
    private final static QName _DeleteAnswer_QNAME = new QName("http://webservice.stackexchange/", "deleteAnswer");
    private final static QName _GetAnswers_QNAME = new QName("http://webservice.stackexchange/", "getAnswers");
    private final static QName _GetAnswersResponse_QNAME = new QName("http://webservice.stackexchange/", "getAnswersResponse");
    private final static QName _GetAnswers1_QNAME = new QName("http://webservice.stackexchange/", "getAnswers_1");
    private final static QName _GetAnswers1Response_QNAME = new QName("http://webservice.stackexchange/", "getAnswers_1Response");
    private final static QName _UpdateAnswer_QNAME = new QName("http://webservice.stackexchange/", "updateAnswer");
    private final static QName _VoteAnswer_QNAME = new QName("http://webservice.stackexchange/", "voteAnswer");
    private final static QName _VoteAnswer1_QNAME = new QName("http://webservice.stackexchange/", "voteAnswer_1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stackexchange.webservice
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
     * Create an instance of {@link AddAnswer }
     * 
     */
    public AddAnswer createAddAnswer() {
        return new AddAnswer();
    }

    /**
     * Create an instance of {@link DeleteAnswer }
     * 
     */
    public DeleteAnswer createDeleteAnswer() {
        return new DeleteAnswer();
    }

    /**
     * Create an instance of {@link GetAnswers }
     * 
     */
    public GetAnswers createGetAnswers() {
        return new GetAnswers();
    }

    /**
     * Create an instance of {@link GetAnswersResponse }
     * 
     */
    public GetAnswersResponse createGetAnswersResponse() {
        return new GetAnswersResponse();
    }

    /**
     * Create an instance of {@link GetAnswers1 }
     * 
     */
    public GetAnswers1 createGetAnswers1() {
        return new GetAnswers1();
    }

    /**
     * Create an instance of {@link GetAnswers1Response }
     * 
     */
    public GetAnswers1Response createGetAnswers1Response() {
        return new GetAnswers1Response();
    }

    /**
     * Create an instance of {@link UpdateAnswer }
     * 
     */
    public UpdateAnswer createUpdateAnswer() {
        return new UpdateAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswer1 }
     * 
     */
    public VoteAnswer1 createVoteAnswer1() {
        return new VoteAnswer1();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "addAnswer")
    public JAXBElement<AddAnswer> createAddAnswer(AddAnswer value) {
        return new JAXBElement<AddAnswer>(_AddAnswer_QNAME, AddAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "deleteAnswer")
    public JAXBElement<DeleteAnswer> createDeleteAnswer(DeleteAnswer value) {
        return new JAXBElement<DeleteAnswer>(_DeleteAnswer_QNAME, DeleteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getAnswers")
    public JAXBElement<GetAnswers> createGetAnswers(GetAnswers value) {
        return new JAXBElement<GetAnswers>(_GetAnswers_QNAME, GetAnswers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getAnswersResponse")
    public JAXBElement<GetAnswersResponse> createGetAnswersResponse(GetAnswersResponse value) {
        return new JAXBElement<GetAnswersResponse>(_GetAnswersResponse_QNAME, GetAnswersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getAnswers_1")
    public JAXBElement<GetAnswers1> createGetAnswers1(GetAnswers1 value) {
        return new JAXBElement<GetAnswers1>(_GetAnswers1_QNAME, GetAnswers1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswers1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getAnswers_1Response")
    public JAXBElement<GetAnswers1Response> createGetAnswers1Response(GetAnswers1Response value) {
        return new JAXBElement<GetAnswers1Response>(_GetAnswers1Response_QNAME, GetAnswers1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "updateAnswer")
    public JAXBElement<UpdateAnswer> createUpdateAnswer(UpdateAnswer value) {
        return new JAXBElement<UpdateAnswer>(_UpdateAnswer_QNAME, UpdateAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "voteAnswer_1")
    public JAXBElement<VoteAnswer1> createVoteAnswer1(VoteAnswer1 value) {
        return new JAXBElement<VoteAnswer1>(_VoteAnswer1_QNAME, VoteAnswer1 .class, null, value);
    }

}
