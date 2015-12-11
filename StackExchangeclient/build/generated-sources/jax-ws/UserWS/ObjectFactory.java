
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

    private final static QName _CreateToken_QNAME = new QName("http://jaxws/", "createToken");
    private final static QName _Match_QNAME = new QName("http://jaxws/", "match");
    private final static QName _GetNameResponse_QNAME = new QName("http://jaxws/", "getNameResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://jaxws/", "createUserResponse");
    private final static QName _EmailDoneResponse_QNAME = new QName("http://jaxws/", "emailDoneResponse");
    private final static QName _EmailDone_QNAME = new QName("http://jaxws/", "emailDone");
    private final static QName _GetUIDByEmailResponse_QNAME = new QName("http://jaxws/", "getUIDByEmailResponse");
    private final static QName _GetName_QNAME = new QName("http://jaxws/", "getName");
    private final static QName _CreateTokenResponse_QNAME = new QName("http://jaxws/", "createTokenResponse");
    private final static QName _CreateUser_QNAME = new QName("http://jaxws/", "createUser");
    private final static QName _MatchResponse_QNAME = new QName("http://jaxws/", "matchResponse");
    private final static QName _GetUIDResponse_QNAME = new QName("http://jaxws/", "getUIDResponse");
    private final static QName _GetUID_QNAME = new QName("http://jaxws/", "getUID");
    private final static QName _GetUIDByEmail_QNAME = new QName("http://jaxws/", "getUIDByEmail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: UserWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateTokenResponse }
     * 
     */
    public CreateTokenResponse createCreateTokenResponse() {
        return new CreateTokenResponse();
    }

    /**
     * Create an instance of {@link GetName }
     * 
     */
    public GetName createGetName() {
        return new GetName();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link EmailDoneResponse }
     * 
     */
    public EmailDoneResponse createEmailDoneResponse() {
        return new EmailDoneResponse();
    }

    /**
     * Create an instance of {@link EmailDone }
     * 
     */
    public EmailDone createEmailDone() {
        return new EmailDone();
    }

    /**
     * Create an instance of {@link GetUIDByEmailResponse }
     * 
     */
    public GetUIDByEmailResponse createGetUIDByEmailResponse() {
        return new GetUIDByEmailResponse();
    }

    /**
     * Create an instance of {@link GetNameResponse }
     * 
     */
    public GetNameResponse createGetNameResponse() {
        return new GetNameResponse();
    }

    /**
     * Create an instance of {@link Match }
     * 
     */
    public Match createMatch() {
        return new Match();
    }

    /**
     * Create an instance of {@link CreateToken }
     * 
     */
    public CreateToken createCreateToken() {
        return new CreateToken();
    }

    /**
     * Create an instance of {@link GetUIDByEmail }
     * 
     */
    public GetUIDByEmail createGetUIDByEmail() {
        return new GetUIDByEmail();
    }

    /**
     * Create an instance of {@link GetUID }
     * 
     */
    public GetUID createGetUID() {
        return new GetUID();
    }

    /**
     * Create an instance of {@link GetUIDResponse }
     * 
     */
    public GetUIDResponse createGetUIDResponse() {
        return new GetUIDResponse();
    }

    /**
     * Create an instance of {@link MatchResponse }
     * 
     */
    public MatchResponse createMatchResponse() {
        return new MatchResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "createToken")
    public JAXBElement<CreateToken> createCreateToken(CreateToken value) {
        return new JAXBElement<CreateToken>(_CreateToken_QNAME, CreateToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Match }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "match")
    public JAXBElement<Match> createMatch(Match value) {
        return new JAXBElement<Match>(_Match_QNAME, Match.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getNameResponse")
    public JAXBElement<GetNameResponse> createGetNameResponse(GetNameResponse value) {
        return new JAXBElement<GetNameResponse>(_GetNameResponse_QNAME, GetNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailDoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "emailDoneResponse")
    public JAXBElement<EmailDoneResponse> createEmailDoneResponse(EmailDoneResponse value) {
        return new JAXBElement<EmailDoneResponse>(_EmailDoneResponse_QNAME, EmailDoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailDone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "emailDone")
    public JAXBElement<EmailDone> createEmailDone(EmailDone value) {
        return new JAXBElement<EmailDone>(_EmailDone_QNAME, EmailDone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUIDByEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getUIDByEmailResponse")
    public JAXBElement<GetUIDByEmailResponse> createGetUIDByEmailResponse(GetUIDByEmailResponse value) {
        return new JAXBElement<GetUIDByEmailResponse>(_GetUIDByEmailResponse_QNAME, GetUIDByEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getName")
    public JAXBElement<GetName> createGetName(GetName value) {
        return new JAXBElement<GetName>(_GetName_QNAME, GetName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "createTokenResponse")
    public JAXBElement<CreateTokenResponse> createCreateTokenResponse(CreateTokenResponse value) {
        return new JAXBElement<CreateTokenResponse>(_CreateTokenResponse_QNAME, CreateTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MatchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "matchResponse")
    public JAXBElement<MatchResponse> createMatchResponse(MatchResponse value) {
        return new JAXBElement<MatchResponse>(_MatchResponse_QNAME, MatchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getUIDResponse")
    public JAXBElement<GetUIDResponse> createGetUIDResponse(GetUIDResponse value) {
        return new JAXBElement<GetUIDResponse>(_GetUIDResponse_QNAME, GetUIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getUID")
    public JAXBElement<GetUID> createGetUID(GetUID value) {
        return new JAXBElement<GetUID>(_GetUID_QNAME, GetUID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUIDByEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getUIDByEmail")
    public JAXBElement<GetUIDByEmail> createGetUIDByEmail(GetUIDByEmail value) {
        return new JAXBElement<GetUIDByEmail>(_GetUIDByEmail_QNAME, GetUIDByEmail.class, null, value);
    }

}
