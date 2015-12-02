
package stackexchange;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stackexchange package. 
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

    private final static QName _GetListQuestionResponse_QNAME = new QName("http://stackexchangews/", "getListQuestionResponse");
    private final static QName _GetQuestionByIDResponse_QNAME = new QName("http://stackexchangews/", "getQuestionByIDResponse");
    private final static QName _GetListQuestion_QNAME = new QName("http://stackexchangews/", "getListQuestion");
    private final static QName _GetQuestionByID_QNAME = new QName("http://stackexchangews/", "getQuestionByID");
    private final static QName _HelloResponse_QNAME = new QName("http://stackexchangews/", "helloResponse");
    private final static QName _Hello_QNAME = new QName("http://stackexchangews/", "hello");
    private final static QName _Question_QNAME = new QName("http://stackexchangews/", "Question");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stackexchange
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListQuestion }
     * 
     */
    public GetListQuestion createGetListQuestion() {
        return new GetListQuestion();
    }

    /**
     * Create an instance of {@link GetListQuestionResponse }
     * 
     */
    public GetListQuestionResponse createGetListQuestionResponse() {
        return new GetListQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestionByIDResponse }
     * 
     */
    public GetQuestionByIDResponse createGetQuestionByIDResponse() {
        return new GetQuestionByIDResponse();
    }

    /**
     * Create an instance of {@link GetQuestionByID }
     * 
     */
    public GetQuestionByID createGetQuestionByID() {
        return new GetQuestionByID();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "getListQuestionResponse")
    public JAXBElement<GetListQuestionResponse> createGetListQuestionResponse(GetListQuestionResponse value) {
        return new JAXBElement<GetListQuestionResponse>(_GetListQuestionResponse_QNAME, GetListQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "getQuestionByIDResponse")
    public JAXBElement<GetQuestionByIDResponse> createGetQuestionByIDResponse(GetQuestionByIDResponse value) {
        return new JAXBElement<GetQuestionByIDResponse>(_GetQuestionByIDResponse_QNAME, GetQuestionByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "getListQuestion")
    public JAXBElement<GetListQuestion> createGetListQuestion(GetListQuestion value) {
        return new JAXBElement<GetListQuestion>(_GetListQuestion_QNAME, GetListQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "getQuestionByID")
    public JAXBElement<GetQuestionByID> createGetQuestionByID(GetQuestionByID value) {
        return new JAXBElement<GetQuestionByID>(_GetQuestionByID_QNAME, GetQuestionByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stackexchangews/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

}
