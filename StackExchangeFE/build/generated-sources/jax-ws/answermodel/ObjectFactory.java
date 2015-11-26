
package answermodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the answermodel package. 
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

    private final static QName _Answer_QNAME = new QName("http://AnswerModel/", "Answer");
    private final static QName _GetAVoteByAID_QNAME = new QName("http://AnswerModel/", "getAVoteByAID");
    private final static QName _GetAVoteByAIDResponse_QNAME = new QName("http://AnswerModel/", "getAVoteByAIDResponse");
    private final static QName _GetAnswerByQID_QNAME = new QName("http://AnswerModel/", "getAnswerByQID");
    private final static QName _GetAnswerByQIDResponse_QNAME = new QName("http://AnswerModel/", "getAnswerByQIDResponse");
    private final static QName _GetUserID_QNAME = new QName("http://AnswerModel/", "getUserID");
    private final static QName _GetUserIDResponse_QNAME = new QName("http://AnswerModel/", "getUserIDResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: answermodel
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
     * Create an instance of {@link GetAVoteByAID }
     * 
     */
    public GetAVoteByAID createGetAVoteByAID() {
        return new GetAVoteByAID();
    }

    /**
     * Create an instance of {@link GetAVoteByAIDResponse }
     * 
     */
    public GetAVoteByAIDResponse createGetAVoteByAIDResponse() {
        return new GetAVoteByAIDResponse();
    }

    /**
     * Create an instance of {@link GetAnswerByQID }
     * 
     */
    public GetAnswerByQID createGetAnswerByQID() {
        return new GetAnswerByQID();
    }

    /**
     * Create an instance of {@link GetAnswerByQIDResponse }
     * 
     */
    public GetAnswerByQIDResponse createGetAnswerByQIDResponse() {
        return new GetAnswerByQIDResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAVoteByAID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAVoteByAID")
    public JAXBElement<GetAVoteByAID> createGetAVoteByAID(GetAVoteByAID value) {
        return new JAXBElement<GetAVoteByAID>(_GetAVoteByAID_QNAME, GetAVoteByAID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAVoteByAIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAVoteByAIDResponse")
    public JAXBElement<GetAVoteByAIDResponse> createGetAVoteByAIDResponse(GetAVoteByAIDResponse value) {
        return new JAXBElement<GetAVoteByAIDResponse>(_GetAVoteByAIDResponse_QNAME, GetAVoteByAIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerByQID")
    public JAXBElement<GetAnswerByQID> createGetAnswerByQID(GetAnswerByQID value) {
        return new JAXBElement<GetAnswerByQID>(_GetAnswerByQID_QNAME, GetAnswerByQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerByQIDResponse")
    public JAXBElement<GetAnswerByQIDResponse> createGetAnswerByQIDResponse(GetAnswerByQIDResponse value) {
        return new JAXBElement<GetAnswerByQIDResponse>(_GetAnswerByQIDResponse_QNAME, GetAnswerByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserID")
    public JAXBElement<GetUserID> createGetUserID(GetUserID value) {
        return new JAXBElement<GetUserID>(_GetUserID_QNAME, GetUserID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserIDResponse")
    public JAXBElement<GetUserIDResponse> createGetUserIDResponse(GetUserIDResponse value) {
        return new JAXBElement<GetUserIDResponse>(_GetUserIDResponse_QNAME, GetUserIDResponse.class, null, value);
    }

}
