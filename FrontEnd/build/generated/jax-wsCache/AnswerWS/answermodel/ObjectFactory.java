
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

    private final static QName _DownAnswer_QNAME = new QName("http://AnswerModel/", "downAnswer");
    private final static QName _InsertAnswer_QNAME = new QName("http://AnswerModel/", "InsertAnswer");
    private final static QName _DownAnswerResponse_QNAME = new QName("http://AnswerModel/", "downAnswerResponse");
    private final static QName _InsertAnswerResponse_QNAME = new QName("http://AnswerModel/", "InsertAnswerResponse");
    private final static QName _GetAnswerbyQID_QNAME = new QName("http://AnswerModel/", "getAnswerbyQID");
    private final static QName _Answer_QNAME = new QName("http://AnswerModel/", "Answer");
    private final static QName _UpAnswerResponse_QNAME = new QName("http://AnswerModel/", "upAnswerResponse");
    private final static QName _GetAnswerbyQIDResponse_QNAME = new QName("http://AnswerModel/", "getAnswerbyQIDResponse");
    private final static QName _UpAnswer_QNAME = new QName("http://AnswerModel/", "upAnswer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: answermodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertAnswerResponse }
     * 
     */
    public InsertAnswerResponse createInsertAnswerResponse() {
        return new InsertAnswerResponse();
    }

    /**
     * Create an instance of {@link GetAnswerbyQID }
     * 
     */
    public GetAnswerbyQID createGetAnswerbyQID() {
        return new GetAnswerbyQID();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link DownAnswer }
     * 
     */
    public DownAnswer createDownAnswer() {
        return new DownAnswer();
    }

    /**
     * Create an instance of {@link InsertAnswer }
     * 
     */
    public InsertAnswer createInsertAnswer() {
        return new InsertAnswer();
    }

    /**
     * Create an instance of {@link DownAnswerResponse }
     * 
     */
    public DownAnswerResponse createDownAnswerResponse() {
        return new DownAnswerResponse();
    }

    /**
     * Create an instance of {@link UpAnswer }
     * 
     */
    public UpAnswer createUpAnswer() {
        return new UpAnswer();
    }

    /**
     * Create an instance of {@link UpAnswerResponse }
     * 
     */
    public UpAnswerResponse createUpAnswerResponse() {
        return new UpAnswerResponse();
    }

    /**
     * Create an instance of {@link GetAnswerbyQIDResponse }
     * 
     */
    public GetAnswerbyQIDResponse createGetAnswerbyQIDResponse() {
        return new GetAnswerbyQIDResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "downAnswer")
    public JAXBElement<DownAnswer> createDownAnswer(DownAnswer value) {
        return new JAXBElement<DownAnswer>(_DownAnswer_QNAME, DownAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "InsertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "downAnswerResponse")
    public JAXBElement<DownAnswerResponse> createDownAnswerResponse(DownAnswerResponse value) {
        return new JAXBElement<DownAnswerResponse>(_DownAnswerResponse_QNAME, DownAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "InsertAnswerResponse")
    public JAXBElement<InsertAnswerResponse> createInsertAnswerResponse(InsertAnswerResponse value) {
        return new JAXBElement<InsertAnswerResponse>(_InsertAnswerResponse_QNAME, InsertAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerbyQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerbyQID")
    public JAXBElement<GetAnswerbyQID> createGetAnswerbyQID(GetAnswerbyQID value) {
        return new JAXBElement<GetAnswerbyQID>(_GetAnswerbyQID_QNAME, GetAnswerbyQID.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UpAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "upAnswerResponse")
    public JAXBElement<UpAnswerResponse> createUpAnswerResponse(UpAnswerResponse value) {
        return new JAXBElement<UpAnswerResponse>(_UpAnswerResponse_QNAME, UpAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerbyQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerbyQIDResponse")
    public JAXBElement<GetAnswerbyQIDResponse> createGetAnswerbyQIDResponse(GetAnswerbyQIDResponse value) {
        return new JAXBElement<GetAnswerbyQIDResponse>(_GetAnswerbyQIDResponse_QNAME, GetAnswerbyQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "upAnswer")
    public JAXBElement<UpAnswer> createUpAnswer(UpAnswer value) {
        return new JAXBElement<UpAnswer>(_UpAnswer_QNAME, UpAnswer.class, null, value);
    }

}
