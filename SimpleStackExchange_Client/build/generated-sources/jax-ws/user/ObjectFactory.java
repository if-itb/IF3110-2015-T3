
package user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the user package. 
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

    private final static QName _CheckEmailUser_QNAME = new QName("http://webservice/", "checkEmailUser");
    private final static QName _CheckEmailUserResponse_QNAME = new QName("http://webservice/", "checkEmailUserResponse");
    private final static QName _CreateUser_QNAME = new QName("http://webservice/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://webservice/", "createUserResponse");
    private final static QName _GetNameByUid_QNAME = new QName("http://webservice/", "getNameByUid");
    private final static QName _GetNameByUidResponse_QNAME = new QName("http://webservice/", "getNameByUidResponse");
    private final static QName _GetUserById_QNAME = new QName("http://webservice/", "getUserById");
    private final static QName _GetUserByIdResponse_QNAME = new QName("http://webservice/", "getUserByIdResponse");
    private final static QName _Registereduser_QNAME = new QName("http://webservice/", "registereduser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckEmailUser }
     * 
     */
    public CheckEmailUser createCheckEmailUser() {
        return new CheckEmailUser();
    }

    /**
     * Create an instance of {@link CheckEmailUserResponse }
     * 
     */
    public CheckEmailUserResponse createCheckEmailUserResponse() {
        return new CheckEmailUserResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link GetNameByUid }
     * 
     */
    public GetNameByUid createGetNameByUid() {
        return new GetNameByUid();
    }

    /**
     * Create an instance of {@link GetNameByUidResponse }
     * 
     */
    public GetNameByUidResponse createGetNameByUidResponse() {
        return new GetNameByUidResponse();
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
     * Create an instance of {@link Registereduser }
     * 
     */
    public Registereduser createRegistereduser() {
        return new Registereduser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckEmailUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "checkEmailUser")
    public JAXBElement<CheckEmailUser> createCheckEmailUser(CheckEmailUser value) {
        return new JAXBElement<CheckEmailUser>(_CheckEmailUser_QNAME, CheckEmailUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckEmailUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "checkEmailUserResponse")
    public JAXBElement<CheckEmailUserResponse> createCheckEmailUserResponse(CheckEmailUserResponse value) {
        return new JAXBElement<CheckEmailUserResponse>(_CheckEmailUserResponse_QNAME, CheckEmailUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameByUid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getNameByUid")
    public JAXBElement<GetNameByUid> createGetNameByUid(GetNameByUid value) {
        return new JAXBElement<GetNameByUid>(_GetNameByUid_QNAME, GetNameByUid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameByUidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getNameByUidResponse")
    public JAXBElement<GetNameByUidResponse> createGetNameByUidResponse(GetNameByUidResponse value) {
        return new JAXBElement<GetNameByUidResponse>(_GetNameByUidResponse_QNAME, GetNameByUidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getUserById")
    public JAXBElement<GetUserById> createGetUserById(GetUserById value) {
        return new JAXBElement<GetUserById>(_GetUserById_QNAME, GetUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getUserByIdResponse")
    public JAXBElement<GetUserByIdResponse> createGetUserByIdResponse(GetUserByIdResponse value) {
        return new JAXBElement<GetUserByIdResponse>(_GetUserByIdResponse_QNAME, GetUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Registereduser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "registereduser")
    public JAXBElement<Registereduser> createRegistereduser(Registereduser value) {
        return new JAXBElement<Registereduser>(_Registereduser_QNAME, Registereduser.class, null, value);
    }

}
