
package org.tusiri.ws.answer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tusiri.ws.answer package. 
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

    private final static QName _ParseException_QNAME = new QName("http://answer.ws.tusiri.org/", "ParseException");
    private final static QName _GetAnswerListResponse_QNAME = new QName("http://answer.ws.tusiri.org/", "getAnswerListResponse");
    private final static QName _CreateAnswerResponse_QNAME = new QName("http://answer.ws.tusiri.org/", "createAnswerResponse");
    private final static QName _ClientProtocolException_QNAME = new QName("http://answer.ws.tusiri.org/", "ClientProtocolException");
    private final static QName _GetAnswerList_QNAME = new QName("http://answer.ws.tusiri.org/", "getAnswerList");
    private final static QName _CreateAnswer_QNAME = new QName("http://answer.ws.tusiri.org/", "createAnswer");
    private final static QName _IOException_QNAME = new QName("http://answer.ws.tusiri.org/", "IOException");
    private final static QName _AnswerVoteUpResponse_QNAME = new QName("http://answer.ws.tusiri.org/", "AnswerVoteUpResponse");
    private final static QName _AnswerVoteDown_QNAME = new QName("http://answer.ws.tusiri.org/", "AnswerVoteDown");
    private final static QName _AnswerVoteDownResponse_QNAME = new QName("http://answer.ws.tusiri.org/", "AnswerVoteDownResponse");
    private final static QName _AnswerVoteUp_QNAME = new QName("http://answer.ws.tusiri.org/", "AnswerVoteUp");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tusiri.ws.answer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnswerVoteDownResponse }
     * 
     */
    public AnswerVoteDownResponse createAnswerVoteDownResponse() {
        return new AnswerVoteDownResponse();
    }

    /**
     * Create an instance of {@link AnswerVoteDown }
     * 
     */
    public AnswerVoteDown createAnswerVoteDown() {
        return new AnswerVoteDown();
    }

    /**
     * Create an instance of {@link AnswerVoteUp }
     * 
     */
    public AnswerVoteUp createAnswerVoteUp() {
        return new AnswerVoteUp();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link CreateAnswer }
     * 
     */
    public CreateAnswer createCreateAnswer() {
        return new CreateAnswer();
    }

    /**
     * Create an instance of {@link AnswerVoteUpResponse }
     * 
     */
    public AnswerVoteUpResponse createAnswerVoteUpResponse() {
        return new AnswerVoteUpResponse();
    }

    /**
     * Create an instance of {@link ClientProtocolException }
     * 
     */
    public ClientProtocolException createClientProtocolException() {
        return new ClientProtocolException();
    }

    /**
     * Create an instance of {@link CreateAnswerResponse }
     * 
     */
    public CreateAnswerResponse createCreateAnswerResponse() {
        return new CreateAnswerResponse();
    }

    /**
     * Create an instance of {@link GetAnswerList }
     * 
     */
    public GetAnswerList createGetAnswerList() {
        return new GetAnswerList();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link GetAnswerListResponse }
     * 
     */
    public GetAnswerListResponse createGetAnswerListResponse() {
        return new GetAnswerListResponse();
    }

    /**
     * Create an instance of {@link AnswerItem }
     * 
     */
    public AnswerItem createAnswerItem() {
        return new AnswerItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "getAnswerListResponse")
    public JAXBElement<GetAnswerListResponse> createGetAnswerListResponse(GetAnswerListResponse value) {
        return new JAXBElement<GetAnswerListResponse>(_GetAnswerListResponse_QNAME, GetAnswerListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "createAnswerResponse")
    public JAXBElement<CreateAnswerResponse> createCreateAnswerResponse(CreateAnswerResponse value) {
        return new JAXBElement<CreateAnswerResponse>(_CreateAnswerResponse_QNAME, CreateAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientProtocolException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "ClientProtocolException")
    public JAXBElement<ClientProtocolException> createClientProtocolException(ClientProtocolException value) {
        return new JAXBElement<ClientProtocolException>(_ClientProtocolException_QNAME, ClientProtocolException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "getAnswerList")
    public JAXBElement<GetAnswerList> createGetAnswerList(GetAnswerList value) {
        return new JAXBElement<GetAnswerList>(_GetAnswerList_QNAME, GetAnswerList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "createAnswer")
    public JAXBElement<CreateAnswer> createCreateAnswer(CreateAnswer value) {
        return new JAXBElement<CreateAnswer>(_CreateAnswer_QNAME, CreateAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnswerVoteUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "AnswerVoteUpResponse")
    public JAXBElement<AnswerVoteUpResponse> createAnswerVoteUpResponse(AnswerVoteUpResponse value) {
        return new JAXBElement<AnswerVoteUpResponse>(_AnswerVoteUpResponse_QNAME, AnswerVoteUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnswerVoteDown }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "AnswerVoteDown")
    public JAXBElement<AnswerVoteDown> createAnswerVoteDown(AnswerVoteDown value) {
        return new JAXBElement<AnswerVoteDown>(_AnswerVoteDown_QNAME, AnswerVoteDown.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnswerVoteDownResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "AnswerVoteDownResponse")
    public JAXBElement<AnswerVoteDownResponse> createAnswerVoteDownResponse(AnswerVoteDownResponse value) {
        return new JAXBElement<AnswerVoteDownResponse>(_AnswerVoteDownResponse_QNAME, AnswerVoteDownResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnswerVoteUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer.ws.tusiri.org/", name = "AnswerVoteUp")
    public JAXBElement<AnswerVoteUp> createAnswerVoteUp(AnswerVoteUp value) {
        return new JAXBElement<AnswerVoteUp>(_AnswerVoteUp_QNAME, AnswerVoteUp.class, null, value);
    }

}
