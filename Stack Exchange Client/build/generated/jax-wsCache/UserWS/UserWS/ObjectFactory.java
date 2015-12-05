
package UserWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the UserWS package. 
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

    private final static QName _GetUserByToken_QNAME = new QName("http://user_package.dazzlesquad.com/", "getUserByToken");
    private final static QName _RegisterUser_QNAME = new QName("http://user_package.dazzlesquad.com/", "registerUser");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://user_package.dazzlesquad.com/", "registerUserResponse");
    private final static QName _User_QNAME = new QName("http://user_package.dazzlesquad.com/", "User");
    private final static QName _GetUserById_QNAME = new QName("http://user_package.dazzlesquad.com/", "getUserById");
    private final static QName _GetUserByTokenResponse_QNAME = new QName("http://user_package.dazzlesquad.com/", "getUserByTokenResponse");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://user_package.dazzlesquad.com/", "getUserByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: UserWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetUserById }
     * 
     */
    public GetUserById createGetUserById() {
        return new GetUserById();
    }

    /**
     * Create an instance of {@link GetUserByIdResponse }
     * 
     */
    public GetUserByIdResponse createGetUserByIdResponse() {
        return new GetUserByIdResponse();
    }

    /**
     * Create an instance of {@link GetUserByTokenResponse }
     * 
     */
    public GetUserByTokenResponse createGetUserByTokenResponse() {
        return new GetUserByTokenResponse();
    }

    /**
     * Create an instance of {@link GetUserByToken }
     * 
     */
    public GetUserByToken createGetUserByToken() {
        return new GetUserByToken();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "getUserByToken")
    public JAXBElement<GetUserByToken> createGetUserByToken(GetUserByToken value) {
        return new JAXBElement<GetUserByToken>(_GetUserByToken_QNAME, GetUserByToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "getUserByTokenResponse")
    public JAXBElement<GetUserByTokenResponse> createGetUserByTokenResponse(GetUserByTokenResponse value) {
        return new JAXBElement<GetUserByTokenResponse>(_GetUserByTokenResponse_QNAME, GetUserByTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user_package.dazzlesquad.com/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

}
