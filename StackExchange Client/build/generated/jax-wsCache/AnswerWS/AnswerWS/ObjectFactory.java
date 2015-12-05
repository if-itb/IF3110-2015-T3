
package AnswerWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the AnswerWS package. 
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

    private final static QName _AddNewAnswer_QNAME = new QName("http://controllers/", "addNewAnswer");
    private final static QName _DevoteAnswerResponse_QNAME = new QName("http://controllers/", "devoteAnswerResponse");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://controllers/", "voteAnswerResponse");
    private final static QName _Answer_QNAME = new QName("http://controllers/", "Answer");
    private final static QName _GetAnswerByQId_QNAME = new QName("http://controllers/", "getAnswerByQId");
    private final static QName _DevoteAnswer_QNAME = new QName("http://controllers/", "devoteAnswer");
    private final static QName _GetAnswerByQIdResponse_QNAME = new QName("http://controllers/", "getAnswerByQIdResponse");
    private final static QName _VoteAnswer_QNAME = new QName("http://controllers/", "voteAnswer");
    private final static QName _AddNewAnswerResponse_QNAME = new QName("http://controllers/", "addNewAnswerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: AnswerWS
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
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link AddNewAnswer }
     * 
     */
    public AddNewAnswer createAddNewAnswer() {
        return new AddNewAnswer();
    }

    /**
     * Create an instance of {@link DevoteAnswerResponse }
     * 
     */
    public DevoteAnswerResponse createDevoteAnswerResponse() {
        return new DevoteAnswerResponse();
    }

    /**
     * Create an instance of {@link AddNewAnswerResponse }
     * 
     */
    public AddNewAnswerResponse createAddNewAnswerResponse() {
        return new AddNewAnswerResponse();
    }

    /**
     * Create an instance of {@link DevoteAnswer }
     * 
     */
    public DevoteAnswer createDevoteAnswer() {
        return new DevoteAnswer();
    }

    /**
     * Create an instance of {@link GetAnswerByQIdResponse }
     * 
     */
    public GetAnswerByQIdResponse createGetAnswerByQIdResponse() {
        return new GetAnswerByQIdResponse();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link GetAnswerByQId }
     * 
     */
    public GetAnswerByQId createGetAnswerByQId() {
        return new GetAnswerByQId();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "addNewAnswer")
    public JAXBElement<AddNewAnswer> createAddNewAnswer(AddNewAnswer value) {
        return new JAXBElement<AddNewAnswer>(_AddNewAnswer_QNAME, AddNewAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DevoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "devoteAnswerResponse")
    public JAXBElement<DevoteAnswerResponse> createDevoteAnswerResponse(DevoteAnswerResponse value) {
        return new JAXBElement<DevoteAnswerResponse>(_DevoteAnswerResponse_QNAME, DevoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getAnswerByQId")
    public JAXBElement<GetAnswerByQId> createGetAnswerByQId(GetAnswerByQId value) {
        return new JAXBElement<GetAnswerByQId>(_GetAnswerByQId_QNAME, GetAnswerByQId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DevoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "devoteAnswer")
    public JAXBElement<DevoteAnswer> createDevoteAnswer(DevoteAnswer value) {
        return new JAXBElement<DevoteAnswer>(_DevoteAnswer_QNAME, DevoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "getAnswerByQIdResponse")
    public JAXBElement<GetAnswerByQIdResponse> createGetAnswerByQIdResponse(GetAnswerByQIdResponse value) {
        return new JAXBElement<GetAnswerByQIdResponse>(_GetAnswerByQIdResponse_QNAME, GetAnswerByQIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://controllers/", name = "addNewAnswerResponse")
    public JAXBElement<AddNewAnswerResponse> createAddNewAnswerResponse(AddNewAnswerResponse value) {
        return new JAXBElement<AddNewAnswerResponse>(_AddNewAnswerResponse_QNAME, AddNewAnswerResponse.class, null, value);
    }

}
