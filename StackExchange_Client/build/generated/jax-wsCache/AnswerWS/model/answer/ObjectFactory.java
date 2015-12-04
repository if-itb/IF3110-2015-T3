
package model.answer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.answer package. 
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

    private final static QName _GetAnswerByIDResponse_QNAME = new QName("http://answer.model/", "getAnswerByIDResponse");
    private final static QName _GetAnswerCount_QNAME = new QName("http://answer.model/", "getAnswerCount");
    private final static QName _GetAnswersByQIDResponse_QNAME = new QName("http://answer.model/", "getAnswersByQIDResponse");
    private final static QName _GetAnswerCountResponse_QNAME = new QName("http://answer.model/", "getAnswerCountResponse");
    private final static QName _AddAnswerResponse_QNAME = new QName("http://answer.model/", "addAnswerResponse");
    private final static QName _AddAnswer_QNAME = new QName("http://answer.model/", "addAnswer");
    private final static QName _GetAnswerByID_QNAME = new QName("http://answer.model/", "getAnswerByID");
    private final static QName _GetAnswersByQID_QNAME = new QName("http://answer.model/", "getAnswersByQID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.answer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAnswerByIDResponse }
     * 
     */
    public GetAnswerByIDResponse createGetAnswerByIDResponse() {
        return new GetAnswerByIDResponse();
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
     * Create an instance of {@link GetAnswersByQIDResponse }
     * 
     */
    public GetAnswersByQIDResponse createGetAnswersByQIDResponse() {
        return new GetAnswersByQIDResponse();
    }

    /**
     * Create an instance of {@link AddAnswerResponse }
     * 
     */
    public AddAnswerResponse createAddAnswerResponse() {
        return new AddAnswerResponse();
    }

    /**
     * Create an instance of {@link AddAnswer }
     * 
     */
    public AddAnswer createAddAnswer() {
        return new AddAnswer();
    }

    /**
     * Create an instance of {@link GetAnswerByID }
     * 
     */
    public GetAnswerByID createGetAnswerByID() {
        return new GetAnswerByID();
    }

    /**
     * Create an instance of {@link GetAnswersByQID }
     * 
     */
    public GetAnswersByQID createGetAnswersByQID() {
        return new GetAnswersByQID();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswerByIDResponse")
    public JAXBElement<GetAnswerByIDResponse> createGetAnswerByIDResponse(GetAnswerByIDResponse value) {
        return new JAXBElement<GetAnswerByIDResponse>(_GetAnswerByIDResponse_QNAME, GetAnswerByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswerCount")
    public JAXBElement<GetAnswerCount> createGetAnswerCount(GetAnswerCount value) {
        return new JAXBElement<GetAnswerCount>(_GetAnswerCount_QNAME, GetAnswerCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswersByQIDResponse")
    public JAXBElement<GetAnswersByQIDResponse> createGetAnswersByQIDResponse(GetAnswersByQIDResponse value) {
        return new JAXBElement<GetAnswersByQIDResponse>(_GetAnswersByQIDResponse_QNAME, GetAnswersByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswerCountResponse")
    public JAXBElement<GetAnswerCountResponse> createGetAnswerCountResponse(GetAnswerCountResponse value) {
        return new JAXBElement<GetAnswerCountResponse>(_GetAnswerCountResponse_QNAME, GetAnswerCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "addAnswerResponse")
    public JAXBElement<AddAnswerResponse> createAddAnswerResponse(AddAnswerResponse value) {
        return new JAXBElement<AddAnswerResponse>(_AddAnswerResponse_QNAME, AddAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "addAnswer")
    public JAXBElement<AddAnswer> createAddAnswer(AddAnswer value) {
        return new JAXBElement<AddAnswer>(_AddAnswer_QNAME, AddAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswerByID")
    public JAXBElement<GetAnswerByID> createGetAnswerByID(GetAnswerByID value) {
        return new JAXBElement<GetAnswerByID>(_GetAnswerByID_QNAME, GetAnswerByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswersByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.model/", name = "getAnswersByQID")
    public JAXBElement<GetAnswersByQID> createGetAnswersByQID(GetAnswersByQID value) {
        return new JAXBElement<GetAnswersByQID>(_GetAnswersByQID_QNAME, GetAnswersByQID.class, null, value);
    }

}
