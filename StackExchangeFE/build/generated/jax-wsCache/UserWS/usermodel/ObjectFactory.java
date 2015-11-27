
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

    private final static QName _User_QNAME = new QName("http://UserModel/", "User");
    private final static QName _GetUserByUID_QNAME = new QName("http://UserModel/", "getUserByUID");
    private final static QName _GetUserByUIDResponse_QNAME = new QName("http://UserModel/", "getUserByUIDResponse");
    private final static QName _Register_QNAME = new QName("http://UserModel/", "register");
    private final static QName _RegisterResponse_QNAME = new QName("http://UserModel/", "registerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: usermodel
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
     * Create an instance of {@link GetUserByUID }
     * 
     */
    public GetUserByUID createGetUserByUID() {
        return new GetUserByUID();
    }

    /**
     * Create an instance of {@link GetUserByUIDResponse }
     * 
     */
    public GetUserByUIDResponse createGetUserByUIDResponse() {
        return new GetUserByUIDResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByUID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getUserByUID")
    public JAXBElement<GetUserByUID> createGetUserByUID(GetUserByUID value) {
        return new JAXBElement<GetUserByUID>(_GetUserByUID_QNAME, GetUserByUID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByUIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "getUserByUIDResponse")
    public JAXBElement<GetUserByUIDResponse> createGetUserByUIDResponse(GetUserByUIDResponse value) {
        return new JAXBElement<GetUserByUIDResponse>(_GetUserByUIDResponse_QNAME, GetUserByUIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://UserModel/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

}
