
package Token;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Token package. 
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

    private final static QName _GetUserIDByToken_QNAME = new QName("http://TokenModel/", "getUserIDByToken");
    private final static QName _GetUserIDByTokenResponse_QNAME = new QName("http://TokenModel/", "getUserIDByTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Token
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserIDByToken }
     * 
     */
    public GetUserIDByToken createGetUserIDByToken() {
        return new GetUserIDByToken();
    }

    /**
     * Create an instance of {@link GetUserIDByTokenResponse }
     * 
     */
    public GetUserIDByTokenResponse createGetUserIDByTokenResponse() {
        return new GetUserIDByTokenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIDByToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TokenModel/", name = "getUserIDByToken")
    public JAXBElement<GetUserIDByToken> createGetUserIDByToken(GetUserIDByToken value) {
        return new JAXBElement<GetUserIDByToken>(_GetUserIDByToken_QNAME, GetUserIDByToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIDByTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://TokenModel/", name = "getUserIDByTokenResponse")
    public JAXBElement<GetUserIDByTokenResponse> createGetUserIDByTokenResponse(GetUserIDByTokenResponse value) {
        return new JAXBElement<GetUserIDByTokenResponse>(_GetUserIDByTokenResponse_QNAME, GetUserIDByTokenResponse.class, null, value);
    }

}
