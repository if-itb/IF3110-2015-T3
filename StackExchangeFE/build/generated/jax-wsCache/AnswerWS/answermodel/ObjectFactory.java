
package answermodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the answermodel package. 
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

    private final static QName _Answer_QNAME = new QName("http://AnswerModel/", "Answer");
    private final static QName _Exception_QNAME = new QName("http://AnswerModel/", "Exception");
    private final static QName _IOException_QNAME = new QName("http://AnswerModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://AnswerModel/", "ParseException");
    private final static QName _GetAVoteByAID_QNAME = new QName("http://AnswerModel/", "getAVoteByAID");
    private final static QName _GetAVoteByAIDResponse_QNAME = new QName("http://AnswerModel/", "getAVoteByAIDResponse");
    private final static QName _GetAnswerByQID_QNAME = new QName("http://AnswerModel/", "getAnswerByQID");
    private final static QName _GetAnswerByQIDResponse_QNAME = new QName("http://AnswerModel/", "getAnswerByQIDResponse");
    private final static QName _GetUserEmail_QNAME = new QName("http://AnswerModel/", "getUserEmail");
    private final static QName _GetUserEmailResponse_QNAME = new QName("http://AnswerModel/", "getUserEmailResponse");
    private final static QName _GetUserID_QNAME = new QName("http://AnswerModel/", "getUserID");
    private final static QName _GetUserIDResponse_QNAME = new QName("http://AnswerModel/", "getUserIDResponse");
    private final static QName _GetUserName_QNAME = new QName("http://AnswerModel/", "getUserName");
    private final static QName _GetUserNameResponse_QNAME = new QName("http://AnswerModel/", "getUserNameResponse");
    private final static QName _InsertAnswer_QNAME = new QName("http://AnswerModel/", "insertAnswer");
    private final static QName _InsertAnswerResponse_QNAME = new QName("http://AnswerModel/", "insertAnswerResponse");
    private final static QName _VoteAnswer_QNAME = new QName("http://AnswerModel/", "voteAnswer");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://AnswerModel/", "voteAnswerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: answermodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link GetAVoteByAID }
     * 
     */
    public GetAVoteByAID createGetAVoteByAID() {
        return new GetAVoteByAID();
    }

    /**
     * Create an instance of {@link GetAVoteByAIDResponse }
     * 
     */
    public GetAVoteByAIDResponse createGetAVoteByAIDResponse() {
        return new GetAVoteByAIDResponse();
    }

    /**
     * Create an instance of {@link GetAnswerByQID }
     * 
     */
    public GetAnswerByQID createGetAnswerByQID() {
        return new GetAnswerByQID();
    }

    /**
     * Create an instance of {@link GetAnswerByQIDResponse }
     * 
     */
    public GetAnswerByQIDResponse createGetAnswerByQIDResponse() {
        return new GetAnswerByQIDResponse();
    }

    /**
     * Create an instance of {@link GetUserEmail }
     * 
     */
    public GetUserEmail createGetUserEmail() {
        return new GetUserEmail();
    }

    /**
     * Create an instance of {@link GetUserEmailResponse }
     * 
     */
    public GetUserEmailResponse createGetUserEmailResponse() {
        return new GetUserEmailResponse();
    }

    /**
     * Create an instance of {@link GetUserID }
     * 
     */
    public GetUserID createGetUserID() {
        return new GetUserID();
    }

    /**
     * Create an instance of {@link GetUserIDResponse }
     * 
     */
    public GetUserIDResponse createGetUserIDResponse() {
        return new GetUserIDResponse();
    }

    /**
     * Create an instance of {@link GetUserName }
     * 
     */
    public GetUserName createGetUserName() {
        return new GetUserName();
    }

    /**
     * Create an instance of {@link GetUserNameResponse }
     * 
     */
    public GetUserNameResponse createGetUserNameResponse() {
        return new GetUserNameResponse();
    }

    /**
     * Create an instance of {@link InsertAnswer }
     * 
     */
    public InsertAnswer createInsertAnswer() {
        return new InsertAnswer();
    }

    /**
     * Create an instance of {@link InsertAnswerResponse }
     * 
     */
    public InsertAnswerResponse createInsertAnswerResponse() {
        return new InsertAnswerResponse();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAVoteByAID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAVoteByAID")
    public JAXBElement<GetAVoteByAID> createGetAVoteByAID(GetAVoteByAID value) {
        return new JAXBElement<GetAVoteByAID>(_GetAVoteByAID_QNAME, GetAVoteByAID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAVoteByAIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAVoteByAIDResponse")
    public JAXBElement<GetAVoteByAIDResponse> createGetAVoteByAIDResponse(GetAVoteByAIDResponse value) {
        return new JAXBElement<GetAVoteByAIDResponse>(_GetAVoteByAIDResponse_QNAME, GetAVoteByAIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerByQID")
    public JAXBElement<GetAnswerByQID> createGetAnswerByQID(GetAnswerByQID value) {
        return new JAXBElement<GetAnswerByQID>(_GetAnswerByQID_QNAME, GetAnswerByQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getAnswerByQIDResponse")
    public JAXBElement<GetAnswerByQIDResponse> createGetAnswerByQIDResponse(GetAnswerByQIDResponse value) {
        return new JAXBElement<GetAnswerByQIDResponse>(_GetAnswerByQIDResponse_QNAME, GetAnswerByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserEmail")
    public JAXBElement<GetUserEmail> createGetUserEmail(GetUserEmail value) {
        return new JAXBElement<GetUserEmail>(_GetUserEmail_QNAME, GetUserEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserEmailResponse")
    public JAXBElement<GetUserEmailResponse> createGetUserEmailResponse(GetUserEmailResponse value) {
        return new JAXBElement<GetUserEmailResponse>(_GetUserEmailResponse_QNAME, GetUserEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserID")
    public JAXBElement<GetUserID> createGetUserID(GetUserID value) {
        return new JAXBElement<GetUserID>(_GetUserID_QNAME, GetUserID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserIDResponse")
    public JAXBElement<GetUserIDResponse> createGetUserIDResponse(GetUserIDResponse value) {
        return new JAXBElement<GetUserIDResponse>(_GetUserIDResponse_QNAME, GetUserIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserName")
    public JAXBElement<GetUserName> createGetUserName(GetUserName value) {
        return new JAXBElement<GetUserName>(_GetUserName_QNAME, GetUserName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "getUserNameResponse")
    public JAXBElement<GetUserNameResponse> createGetUserNameResponse(GetUserNameResponse value) {
        return new JAXBElement<GetUserNameResponse>(_GetUserNameResponse_QNAME, GetUserNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "insertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "insertAnswerResponse")
    public JAXBElement<InsertAnswerResponse> createInsertAnswerResponse(InsertAnswerResponse value) {
        return new JAXBElement<InsertAnswerResponse>(_InsertAnswerResponse_QNAME, InsertAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModel/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

}
