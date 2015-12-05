
package usermodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the usermodel package. 
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

    private final static QName _RemoveTokenResponse_QNAME = new QName("http://UserModel/", "removeTokenResponse");
    private final static QName _GetIDUserbyToken_QNAME = new QName("http://UserModel/", "getIDUserbyToken");
    private final static QName _RemoveToken_QNAME = new QName("http://UserModel/", "removeToken");
    private final static QName _GetUserbyIDResponse_QNAME = new QName("http://UserModel/", "getUserbyIDResponse");
    private final static QName _GetUserbyID_QNAME = new QName("http://UserModel/", "getUserbyID");
    private final static QName _GetIDUserbyTokenResponse_QNAME = new QName("http://UserModel/", "getIDUserbyTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: usermodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserbyIDResponse }
     * 
     */
    public GetUserbyIDResponse createGetUserbyIDResponse() {
        return new GetUserbyIDResponse();
    }

    /**
     * Create an instance of {@link RemoveTokenResponse }
     * 
     */
    public RemoveTokenResponse createRemoveTokenResponse() {
        return new RemoveTokenResponse();
    }

    /**
     * Create an instance of {@link RemoveToken }
     * 
     */
    public RemoveToken createRemoveToken() {
        return new RemoveToken();
    }

    /**
     * Create an instance of {@link GetIDUserbyToken }
     * 
     */
    public GetIDUserbyToken createGetIDUserbyToken() {
        return new GetIDUserbyToken();
    }

    /**
     * Create an instance of {@link GetIDUserbyTokenResponse }
     * 
     */
    public GetIDUserbyTokenResponse createGetIDUserbyTokenResponse() {
        return new GetIDUserbyTokenResponse();
    }

    /**
     * Create an instance of {@link GetUserbyID }
     * 
     */
    public GetUserbyID createGetUserbyID() {
        return new GetUserbyID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "removeTokenResponse")
    public JAXBElement<RemoveTokenResponse> createRemoveTokenResponse(RemoveTokenResponse value) {
        return new JAXBElement<RemoveTokenResponse>(_RemoveTokenResponse_QNAME, RemoveTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDUserbyToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getIDUserbyToken")
    public JAXBElement<GetIDUserbyToken> createGetIDUserbyToken(GetIDUserbyToken value) {
        return new JAXBElement<GetIDUserbyToken>(_GetIDUserbyToken_QNAME, GetIDUserbyToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "removeToken")
    public JAXBElement<RemoveToken> createRemoveToken(RemoveToken value) {
        return new JAXBElement<RemoveToken>(_RemoveToken_QNAME, RemoveToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserbyIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getUserbyIDResponse")
    public JAXBElement<GetUserbyIDResponse> createGetUserbyIDResponse(GetUserbyIDResponse value) {
        return new JAXBElement<GetUserbyIDResponse>(_GetUserbyIDResponse_QNAME, GetUserbyIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserbyID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getUserbyID")
    public JAXBElement<GetUserbyID> createGetUserbyID(GetUserbyID value) {
        return new JAXBElement<GetUserbyID>(_GetUserbyID_QNAME, GetUserbyID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDUserbyTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getIDUserbyTokenResponse")
    public JAXBElement<GetIDUserbyTokenResponse> createGetIDUserbyTokenResponse(GetIDUserbyTokenResponse value) {
        return new JAXBElement<GetIDUserbyTokenResponse>(_GetIDUserbyTokenResponse_QNAME, GetIDUserbyTokenResponse.class, null, value);
    }

}
