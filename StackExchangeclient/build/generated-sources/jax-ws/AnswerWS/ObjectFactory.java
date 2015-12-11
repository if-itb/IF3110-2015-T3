
package AnswerWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the AnswerWS package. 
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

    private final static QName _GetAnswerByQID_QNAME = new QName("http://jaxws/", "getAnswerByQID");
    private final static QName _GetAnswerVote_QNAME = new QName("http://jaxws/", "getAnswerVote");
    private final static QName _NumVoteAnswer_QNAME = new QName("http://jaxws/", "numVoteAnswer");
    private final static QName _GetAnswerUIDResponse_QNAME = new QName("http://jaxws/", "getAnswerUIDResponse");
    private final static QName _GetAnswerByQIDResponse_QNAME = new QName("http://jaxws/", "getAnswerByQIDResponse");
    private final static QName _GetAnswerQID_QNAME = new QName("http://jaxws/", "getAnswerQID");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://jaxws/", "voteAnswerResponse");
    private final static QName _GetAnswerUID_QNAME = new QName("http://jaxws/", "getAnswerUID");
    private final static QName _NumVoteAnswerResponse_QNAME = new QName("http://jaxws/", "numVoteAnswerResponse");
    private final static QName _SetAnswerVote_QNAME = new QName("http://jaxws/", "setAnswerVote");
    private final static QName _InsertAnswerResponse_QNAME = new QName("http://jaxws/", "insertAnswerResponse");
    private final static QName _GetAnswerQIDResponse_QNAME = new QName("http://jaxws/", "getAnswerQIDResponse");
    private final static QName _VoteAnswer_QNAME = new QName("http://jaxws/", "voteAnswer");
    private final static QName _InsertAnswer_QNAME = new QName("http://jaxws/", "insertAnswer");
    private final static QName _Answer_QNAME = new QName("http://jaxws/", "Answer");
    private final static QName _GetAnswerVoteResponse_QNAME = new QName("http://jaxws/", "getAnswerVoteResponse");
    private final static QName _SetAnswerVoteResponse_QNAME = new QName("http://jaxws/", "setAnswerVoteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: AnswerWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAnswerUID }
     * 
     */
    public GetAnswerUID createGetAnswerUID() {
        return new GetAnswerUID();
    }

    /**
     * Create an instance of {@link NumVoteAnswerResponse }
     * 
     */
    public NumVoteAnswerResponse createNumVoteAnswerResponse() {
        return new NumVoteAnswerResponse();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link GetAnswerQID }
     * 
     */
    public GetAnswerQID createGetAnswerQID() {
        return new GetAnswerQID();
    }

    /**
     * Create an instance of {@link GetAnswerUIDResponse }
     * 
     */
    public GetAnswerUIDResponse createGetAnswerUIDResponse() {
        return new GetAnswerUIDResponse();
    }

    /**
     * Create an instance of {@link GetAnswerByQIDResponse }
     * 
     */
    public GetAnswerByQIDResponse createGetAnswerByQIDResponse() {
        return new GetAnswerByQIDResponse();
    }

    /**
     * Create an instance of {@link NumVoteAnswer }
     * 
     */
    public NumVoteAnswer createNumVoteAnswer() {
        return new NumVoteAnswer();
    }

    /**
     * Create an instance of {@link GetAnswerByQID }
     * 
     */
    public GetAnswerByQID createGetAnswerByQID() {
        return new GetAnswerByQID();
    }

    /**
     * Create an instance of {@link GetAnswerVote }
     * 
     */
    public GetAnswerVote createGetAnswerVote() {
        return new GetAnswerVote();
    }

    /**
     * Create an instance of {@link InsertAnswer }
     * 
     */
    public InsertAnswer createInsertAnswer() {
        return new InsertAnswer();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link GetAnswerVoteResponse }
     * 
     */
    public GetAnswerVoteResponse createGetAnswerVoteResponse() {
        return new GetAnswerVoteResponse();
    }

    /**
     * Create an instance of {@link SetAnswerVoteResponse }
     * 
     */
    public SetAnswerVoteResponse createSetAnswerVoteResponse() {
        return new SetAnswerVoteResponse();
    }

    /**
     * Create an instance of {@link InsertAnswerResponse }
     * 
     */
    public InsertAnswerResponse createInsertAnswerResponse() {
        return new InsertAnswerResponse();
    }

    /**
     * Create an instance of {@link GetAnswerQIDResponse }
     * 
     */
    public GetAnswerQIDResponse createGetAnswerQIDResponse() {
        return new GetAnswerQIDResponse();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link SetAnswerVote }
     * 
     */
    public SetAnswerVote createSetAnswerVote() {
        return new SetAnswerVote();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerByQID")
    public JAXBElement<GetAnswerByQID> createGetAnswerByQID(GetAnswerByQID value) {
        return new JAXBElement<GetAnswerByQID>(_GetAnswerByQID_QNAME, GetAnswerByQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerVote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerVote")
    public JAXBElement<GetAnswerVote> createGetAnswerVote(GetAnswerVote value) {
        return new JAXBElement<GetAnswerVote>(_GetAnswerVote_QNAME, GetAnswerVote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NumVoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "numVoteAnswer")
    public JAXBElement<NumVoteAnswer> createNumVoteAnswer(NumVoteAnswer value) {
        return new JAXBElement<NumVoteAnswer>(_NumVoteAnswer_QNAME, NumVoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerUIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerUIDResponse")
    public JAXBElement<GetAnswerUIDResponse> createGetAnswerUIDResponse(GetAnswerUIDResponse value) {
        return new JAXBElement<GetAnswerUIDResponse>(_GetAnswerUIDResponse_QNAME, GetAnswerUIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerByQIDResponse")
    public JAXBElement<GetAnswerByQIDResponse> createGetAnswerByQIDResponse(GetAnswerByQIDResponse value) {
        return new JAXBElement<GetAnswerByQIDResponse>(_GetAnswerByQIDResponse_QNAME, GetAnswerByQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerQID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerQID")
    public JAXBElement<GetAnswerQID> createGetAnswerQID(GetAnswerQID value) {
        return new JAXBElement<GetAnswerQID>(_GetAnswerQID_QNAME, GetAnswerQID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerUID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerUID")
    public JAXBElement<GetAnswerUID> createGetAnswerUID(GetAnswerUID value) {
        return new JAXBElement<GetAnswerUID>(_GetAnswerUID_QNAME, GetAnswerUID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NumVoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "numVoteAnswerResponse")
    public JAXBElement<NumVoteAnswerResponse> createNumVoteAnswerResponse(NumVoteAnswerResponse value) {
        return new JAXBElement<NumVoteAnswerResponse>(_NumVoteAnswerResponse_QNAME, NumVoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAnswerVote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "setAnswerVote")
    public JAXBElement<SetAnswerVote> createSetAnswerVote(SetAnswerVote value) {
        return new JAXBElement<SetAnswerVote>(_SetAnswerVote_QNAME, SetAnswerVote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "insertAnswerResponse")
    public JAXBElement<InsertAnswerResponse> createInsertAnswerResponse(InsertAnswerResponse value) {
        return new JAXBElement<InsertAnswerResponse>(_InsertAnswerResponse_QNAME, InsertAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerQIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerQIDResponse")
    public JAXBElement<GetAnswerQIDResponse> createGetAnswerQIDResponse(GetAnswerQIDResponse value) {
        return new JAXBElement<GetAnswerQIDResponse>(_GetAnswerQIDResponse_QNAME, GetAnswerQIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "insertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerVoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "getAnswerVoteResponse")
    public JAXBElement<GetAnswerVoteResponse> createGetAnswerVoteResponse(GetAnswerVoteResponse value) {
        return new JAXBElement<GetAnswerVoteResponse>(_GetAnswerVoteResponse_QNAME, GetAnswerVoteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetAnswerVoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws/", name = "setAnswerVoteResponse")
    public JAXBElement<SetAnswerVoteResponse> createSetAnswerVoteResponse(SetAnswerVoteResponse value) {
        return new JAXBElement<SetAnswerVoteResponse>(_SetAnswerVoteResponse_QNAME, SetAnswerVoteResponse.class, null, value);
    }

}
