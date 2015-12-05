
package stackexchange.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stackexchange.webservice package. 
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

    private final static QName _User_QNAME = new QName("http://webservice.stackexchange/", "User");
    private final static QName _AddUser_QNAME = new QName("http://webservice.stackexchange/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://webservice.stackexchange/", "addUserResponse");
    private final static QName _DeleteUser_QNAME = new QName("http://webservice.stackexchange/", "deleteUser");
    private final static QName _GetToken_QNAME = new QName("http://webservice.stackexchange/", "getToken");
    private final static QName _GetTokenResponse_QNAME = new QName("http://webservice.stackexchange/", "getTokenResponse");
    private final static QName _GetUser_QNAME = new QName("http://webservice.stackexchange/", "getUser");
    private final static QName _GetUserByEmail_QNAME = new QName("http://webservice.stackexchange/", "getUserByEmail");
    private final static QName _GetUserByEmailResponse_QNAME = new QName("http://webservice.stackexchange/", "getUserByEmailResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://webservice.stackexchange/", "getUserResponse");
    private final static QName _GetUsers_QNAME = new QName("http://webservice.stackexchange/", "getUsers");
    private final static QName _GetUsersResponse_QNAME = new QName("http://webservice.stackexchange/", "getUsersResponse");
    private final static QName _SignOut_QNAME = new QName("http://webservice.stackexchange/", "signOut");
    private final static QName _SignOutResponse_QNAME = new QName("http://webservice.stackexchange/", "signOutResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stackexchange.webservice
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
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link DeleteUser }
     * 
     */
    public DeleteUser createDeleteUser() {
        return new DeleteUser();
    }

    /**
     * Create an instance of {@link GetToken }
     * 
     */
    public GetToken createGetToken() {
        return new GetToken();
    }

    /**
     * Create an instance of {@link GetTokenResponse }
     * 
     */
    public GetTokenResponse createGetTokenResponse() {
        return new GetTokenResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetUserByEmail }
     * 
     */
    public GetUserByEmail createGetUserByEmail() {
        return new GetUserByEmail();
    }

    /**
     * Create an instance of {@link GetUserByEmailResponse }
     * 
     */
    public GetUserByEmailResponse createGetUserByEmailResponse() {
        return new GetUserByEmailResponse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link SignOut }
     * 
     */
    public SignOut createSignOut() {
        return new SignOut();
    }

    /**
     * Create an instance of {@link SignOutResponse }
     * 
     */
    public SignOutResponse createSignOutResponse() {
        return new SignOutResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "deleteUser")
    public JAXBElement<DeleteUser> createDeleteUser(DeleteUser value) {
        return new JAXBElement<DeleteUser>(_DeleteUser_QNAME, DeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getToken")
    public JAXBElement<GetToken> createGetToken(GetToken value) {
        return new JAXBElement<GetToken>(_GetToken_QNAME, GetToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getTokenResponse")
    public JAXBElement<GetTokenResponse> createGetTokenResponse(GetTokenResponse value) {
        return new JAXBElement<GetTokenResponse>(_GetTokenResponse_QNAME, GetTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUserByEmail")
    public JAXBElement<GetUserByEmail> createGetUserByEmail(GetUserByEmail value) {
        return new JAXBElement<GetUserByEmail>(_GetUserByEmail_QNAME, GetUserByEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUserByEmailResponse")
    public JAXBElement<GetUserByEmailResponse> createGetUserByEmailResponse(GetUserByEmailResponse value) {
        return new JAXBElement<GetUserByEmailResponse>(_GetUserByEmailResponse_QNAME, GetUserByEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "signOut")
    public JAXBElement<SignOut> createSignOut(SignOut value) {
        return new JAXBElement<SignOut>(_SignOut_QNAME, SignOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stackexchange/", name = "signOutResponse")
    public JAXBElement<SignOutResponse> createSignOutResponse(SignOutResponse value) {
        return new JAXBElement<SignOutResponse>(_SignOutResponse_QNAME, SignOutResponse.class, null, value);
    }

}
