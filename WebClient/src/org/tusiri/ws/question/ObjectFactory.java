
package org.tusiri.ws.question;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tusiri.ws.question package. 
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

    private final static QName _GetQuestionInfo_QNAME = new QName("http://question.ws.tusiri.org/", "getQuestionInfo");
    private final static QName _Questionitem_QNAME = new QName("http://question.ws.tusiri.org/", "questionitem");
    private final static QName _QuestionVoteDownResponse_QNAME = new QName("http://question.ws.tusiri.org/", "QuestionVoteDownResponse");
    private final static QName _GetQuestionList_QNAME = new QName("http://question.ws.tusiri.org/", "getQuestionList");
    private final static QName _QuestionVoteDown_QNAME = new QName("http://question.ws.tusiri.org/", "QuestionVoteDown");
    private final static QName _CreateQuestionResponse_QNAME = new QName("http://question.ws.tusiri.org/", "createQuestionResponse");
    private final static QName _GetQuestionListResponse_QNAME = new QName("http://question.ws.tusiri.org/", "getQuestionListResponse");
    private final static QName _GetQuestionInfoResponse_QNAME = new QName("http://question.ws.tusiri.org/", "getQuestionInfoResponse");
    private final static QName _ParseException_QNAME = new QName("http://question.ws.tusiri.org/", "ParseException");
    private final static QName _EditQuestion_QNAME = new QName("http://question.ws.tusiri.org/", "editQuestion");
    private final static QName _EditQuestionResponse_QNAME = new QName("http://question.ws.tusiri.org/", "editQuestionResponse");
    private final static QName _ClientProtocolException_QNAME = new QName("http://question.ws.tusiri.org/", "ClientProtocolException");
    private final static QName _CreateQuestion_QNAME = new QName("http://question.ws.tusiri.org/", "createQuestion");
    private final static QName _IOException_QNAME = new QName("http://question.ws.tusiri.org/", "IOException");
    private final static QName _DeleteQuestionResponse_QNAME = new QName("http://question.ws.tusiri.org/", "deleteQuestionResponse");
    private final static QName _SearchQuestion_QNAME = new QName("http://question.ws.tusiri.org/", "searchQuestion");
    private final static QName _QuestionVoteUp_QNAME = new QName("http://question.ws.tusiri.org/", "QuestionVoteUp");
    private final static QName _SearchQuestionResponse_QNAME = new QName("http://question.ws.tusiri.org/", "searchQuestionResponse");
    private final static QName _DeleteQuestion_QNAME = new QName("http://question.ws.tusiri.org/", "deleteQuestion");
    private final static QName _QuestionVoteUpResponse_QNAME = new QName("http://question.ws.tusiri.org/", "QuestionVoteUpResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tusiri.ws.question
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchQuestionResponse }
     * 
     */
    public SearchQuestionResponse createSearchQuestionResponse() {
        return new SearchQuestionResponse();
    }

    /**
     * Create an instance of {@link DeleteQuestion }
     * 
     */
    public DeleteQuestion createDeleteQuestion() {
        return new DeleteQuestion();
    }

    /**
     * Create an instance of {@link QuestionVoteUpResponse }
     * 
     */
    public QuestionVoteUpResponse createQuestionVoteUpResponse() {
        return new QuestionVoteUpResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link DeleteQuestionResponse }
     * 
     */
    public DeleteQuestionResponse createDeleteQuestionResponse() {
        return new DeleteQuestionResponse();
    }

    /**
     * Create an instance of {@link SearchQuestion }
     * 
     */
    public SearchQuestion createSearchQuestion() {
        return new SearchQuestion();
    }

    /**
     * Create an instance of {@link QuestionVoteUp }
     * 
     */
    public QuestionVoteUp createQuestionVoteUp() {
        return new QuestionVoteUp();
    }

    /**
     * Create an instance of {@link ClientProtocolException }
     * 
     */
    public ClientProtocolException createClientProtocolException() {
        return new ClientProtocolException();
    }

    /**
     * Create an instance of {@link CreateQuestion }
     * 
     */
    public CreateQuestion createCreateQuestion() {
        return new CreateQuestion();
    }

    /**
     * Create an instance of {@link GetQuestionInfoResponse }
     * 
     */
    public GetQuestionInfoResponse createGetQuestionInfoResponse() {
        return new GetQuestionInfoResponse();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link EditQuestion }
     * 
     */
    public EditQuestion createEditQuestion() {
        return new EditQuestion();
    }

    /**
     * Create an instance of {@link EditQuestionResponse }
     * 
     */
    public EditQuestionResponse createEditQuestionResponse() {
        return new EditQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestionListResponse }
     * 
     */
    public GetQuestionListResponse createGetQuestionListResponse() {
        return new GetQuestionListResponse();
    }

    /**
     * Create an instance of {@link CreateQuestionResponse }
     * 
     */
    public CreateQuestionResponse createCreateQuestionResponse() {
        return new CreateQuestionResponse();
    }

    /**
     * Create an instance of {@link QuestionItem }
     * 
     */
    public QuestionItem createQuestionItem() {
        return new QuestionItem();
    }

    /**
     * Create an instance of {@link GetQuestionInfo }
     * 
     */
    public GetQuestionInfo createGetQuestionInfo() {
        return new GetQuestionInfo();
    }

    /**
     * Create an instance of {@link GetQuestionList }
     * 
     */
    public GetQuestionList createGetQuestionList() {
        return new GetQuestionList();
    }

    /**
     * Create an instance of {@link QuestionVoteDownResponse }
     * 
     */
    public QuestionVoteDownResponse createQuestionVoteDownResponse() {
        return new QuestionVoteDownResponse();
    }

    /**
     * Create an instance of {@link QuestionVoteDown }
     * 
     */
    public QuestionVoteDown createQuestionVoteDown() {
        return new QuestionVoteDown();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "getQuestionInfo")
    public JAXBElement<GetQuestionInfo> createGetQuestionInfo(GetQuestionInfo value) {
        return new JAXBElement<GetQuestionInfo>(_GetQuestionInfo_QNAME, GetQuestionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuestionItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "questionitem")
    public JAXBElement<QuestionItem> createQuestionitem(QuestionItem value) {
        return new JAXBElement<QuestionItem>(_Questionitem_QNAME, QuestionItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuestionVoteDownResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "QuestionVoteDownResponse")
    public JAXBElement<QuestionVoteDownResponse> createQuestionVoteDownResponse(QuestionVoteDownResponse value) {
        return new JAXBElement<QuestionVoteDownResponse>(_QuestionVoteDownResponse_QNAME, QuestionVoteDownResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "getQuestionList")
    public JAXBElement<GetQuestionList> createGetQuestionList(GetQuestionList value) {
        return new JAXBElement<GetQuestionList>(_GetQuestionList_QNAME, GetQuestionList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuestionVoteDown }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "QuestionVoteDown")
    public JAXBElement<QuestionVoteDown> createQuestionVoteDown(QuestionVoteDown value) {
        return new JAXBElement<QuestionVoteDown>(_QuestionVoteDown_QNAME, QuestionVoteDown.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "createQuestionResponse")
    public JAXBElement<CreateQuestionResponse> createCreateQuestionResponse(CreateQuestionResponse value) {
        return new JAXBElement<CreateQuestionResponse>(_CreateQuestionResponse_QNAME, CreateQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "getQuestionListResponse")
    public JAXBElement<GetQuestionListResponse> createGetQuestionListResponse(GetQuestionListResponse value) {
        return new JAXBElement<GetQuestionListResponse>(_GetQuestionListResponse_QNAME, GetQuestionListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "getQuestionInfoResponse")
    public JAXBElement<GetQuestionInfoResponse> createGetQuestionInfoResponse(GetQuestionInfoResponse value) {
        return new JAXBElement<GetQuestionInfoResponse>(_GetQuestionInfoResponse_QNAME, GetQuestionInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "editQuestion")
    public JAXBElement<EditQuestion> createEditQuestion(EditQuestion value) {
        return new JAXBElement<EditQuestion>(_EditQuestion_QNAME, EditQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "editQuestionResponse")
    public JAXBElement<EditQuestionResponse> createEditQuestionResponse(EditQuestionResponse value) {
        return new JAXBElement<EditQuestionResponse>(_EditQuestionResponse_QNAME, EditQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientProtocolException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "ClientProtocolException")
    public JAXBElement<ClientProtocolException> createClientProtocolException(ClientProtocolException value) {
        return new JAXBElement<ClientProtocolException>(_ClientProtocolException_QNAME, ClientProtocolException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "createQuestion")
    public JAXBElement<CreateQuestion> createCreateQuestion(CreateQuestion value) {
        return new JAXBElement<CreateQuestion>(_CreateQuestion_QNAME, CreateQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "deleteQuestionResponse")
    public JAXBElement<DeleteQuestionResponse> createDeleteQuestionResponse(DeleteQuestionResponse value) {
        return new JAXBElement<DeleteQuestionResponse>(_DeleteQuestionResponse_QNAME, DeleteQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "searchQuestion")
    public JAXBElement<SearchQuestion> createSearchQuestion(SearchQuestion value) {
        return new JAXBElement<SearchQuestion>(_SearchQuestion_QNAME, SearchQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuestionVoteUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "QuestionVoteUp")
    public JAXBElement<QuestionVoteUp> createQuestionVoteUp(QuestionVoteUp value) {
        return new JAXBElement<QuestionVoteUp>(_QuestionVoteUp_QNAME, QuestionVoteUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "searchQuestionResponse")
    public JAXBElement<SearchQuestionResponse> createSearchQuestionResponse(SearchQuestionResponse value) {
        return new JAXBElement<SearchQuestionResponse>(_SearchQuestionResponse_QNAME, SearchQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "deleteQuestion")
    public JAXBElement<DeleteQuestion> createDeleteQuestion(DeleteQuestion value) {
        return new JAXBElement<DeleteQuestion>(_DeleteQuestion_QNAME, DeleteQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuestionVoteUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://question.ws.tusiri.org/", name = "QuestionVoteUpResponse")
    public JAXBElement<QuestionVoteUpResponse> createQuestionVoteUpResponse(QuestionVoteUpResponse value) {
        return new JAXBElement<QuestionVoteUpResponse>(_QuestionVoteUpResponse_QNAME, QuestionVoteUpResponse.class, null, value);
    }

}
