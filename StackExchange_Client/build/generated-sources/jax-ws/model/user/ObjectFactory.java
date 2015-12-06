
package model.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.user package. 
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

    private final static QName _AddUser_QNAME = new QName("http://user.model/", "addUser");
    private final static QName _GetUserByIDResponse_QNAME = new QName("http://user.model/", "getUserByIDResponse");
    private final static QName _GetUserByTokenResponse_QNAME = new QName("http://user.model/", "getUserByTokenResponse");
    private final static QName _User_QNAME = new QName("http://user.model/", "User");
    private final static QName _GetUserByID_QNAME = new QName("http://user.model/", "getUserByID");
    private final static QName _GetIDbyEmail_QNAME = new QName("http://user.model/", "getIDbyEmail");
    private final static QName _GetUserByToken_QNAME = new QName("http://user.model/", "getUserByToken");
    private final static QName _GetIDbyEmailResponse_QNAME = new QName("http://user.model/", "getIDbyEmailResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.user
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
     * Create an instance of {@link GetUserByID }
     * 
     */
    public GetUserByID createGetUserByID() {
        return new GetUserByID();
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link GetUserByIDResponse }
     * 
     */
    public GetUserByIDResponse createGetUserByIDResponse() {
        return new GetUserByIDResponse();
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
     * Create an instance of {@link GetIDbyEmailResponse }
     * 
     */
    public GetIDbyEmailResponse createGetIDbyEmailResponse() {
        return new GetIDbyEmailResponse();
    }

    /**
     * Create an instance of {@link GetIDbyEmail }
     * 
     */
    public GetIDbyEmail createGetIDbyEmail() {
        return new GetIDbyEmail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getUserByIDResponse")
    public JAXBElement<GetUserByIDResponse> createGetUserByIDResponse(GetUserByIDResponse value) {
        return new JAXBElement<GetUserByIDResponse>(_GetUserByIDResponse_QNAME, GetUserByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getUserByTokenResponse")
    public JAXBElement<GetUserByTokenResponse> createGetUserByTokenResponse(GetUserByTokenResponse value) {
        return new JAXBElement<GetUserByTokenResponse>(_GetUserByTokenResponse_QNAME, GetUserByTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getUserByID")
    public JAXBElement<GetUserByID> createGetUserByID(GetUserByID value) {
        return new JAXBElement<GetUserByID>(_GetUserByID_QNAME, GetUserByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDbyEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getIDbyEmail")
    public JAXBElement<GetIDbyEmail> createGetIDbyEmail(GetIDbyEmail value) {
        return new JAXBElement<GetIDbyEmail>(_GetIDbyEmail_QNAME, GetIDbyEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getUserByToken")
    public JAXBElement<GetUserByToken> createGetUserByToken(GetUserByToken value) {
        return new JAXBElement<GetUserByToken>(_GetUserByToken_QNAME, GetUserByToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIDbyEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://user.model/", name = "getIDbyEmailResponse")
    public JAXBElement<GetIDbyEmailResponse> createGetIDbyEmailResponse(GetIDbyEmailResponse value) {
        return new JAXBElement<GetIDbyEmailResponse>(_GetIDbyEmailResponse_QNAME, GetIDbyEmailResponse.class, null, value);
    }

}
