
package QuestionWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the QuestionWS package. 
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

    private final static QName _GetQuestion_QNAME = new QName("http://QuestionModel/", "getQuestion");
    private final static QName _GetQuestionResponse_QNAME = new QName("http://QuestionModel/", "getQuestionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: QuestionWS
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
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

}
