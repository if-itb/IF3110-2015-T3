
package Answer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Answer package. 
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

    private final static QName _UpdateAnswerResponse_QNAME = new QName("http://AnswerModule/", "UpdateAnswerResponse");
    private final static QName _GetAllAnswer_QNAME = new QName("http://AnswerModule/", "GetAllAnswer");
    private final static QName _DeleteAnswer_QNAME = new QName("http://AnswerModule/", "DeleteAnswer");
    private final static QName _VoteAnswer_QNAME = new QName("http://AnswerModule/", "voteAnswer");
    private final static QName _DeleteAnswerResponse_QNAME = new QName("http://AnswerModule/", "DeleteAnswerResponse");
    private final static QName _GetAllAnswerResponse_QNAME = new QName("http://AnswerModule/", "GetAllAnswerResponse");
    private final static QName _InsertAnswer_QNAME = new QName("http://AnswerModule/", "InsertAnswer");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://AnswerModule/", "voteAnswerResponse");
    private final static QName _UpdateAnswer_QNAME = new QName("http://AnswerModule/", "UpdateAnswer");
    private final static QName _Answer_QNAME = new QName("http://AnswerModule/", "Answer");
    private final static QName _InsertAnswerResponse_QNAME = new QName("http://AnswerModule/", "InsertAnswerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Answer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertAnswerResponse }
     * 
     */
    public InsertAnswerResponse createInsertAnswerResponse() {
        return new InsertAnswerResponse();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link UpdateAnswer }
     * 
     */
    public UpdateAnswer createUpdateAnswer() {
        return new UpdateAnswer();
    }

    /**
     * Create an instance of {@link GetAllAnswerResponse }
     * 
     */
    public GetAllAnswerResponse createGetAllAnswerResponse() {
        return new GetAllAnswerResponse();
    }

    /**
     * Create an instance of {@link InsertAnswer }
     * 
     */
    public InsertAnswer createInsertAnswer() {
        return new InsertAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
    }

    /**
     * Create an instance of {@link DeleteAnswerResponse }
     * 
     */
    public DeleteAnswerResponse createDeleteAnswerResponse() {
        return new DeleteAnswerResponse();
    }

    /**
     * Create an instance of {@link DeleteAnswer }
     * 
     */
    public DeleteAnswer createDeleteAnswer() {
        return new DeleteAnswer();
    }

    /**
     * Create an instance of {@link VoteAnswer }
     * 
     */
    public VoteAnswer createVoteAnswer() {
        return new VoteAnswer();
    }

    /**
     * Create an instance of {@link GetAllAnswer }
     * 
     */
    public GetAllAnswer createGetAllAnswer() {
        return new GetAllAnswer();
    }

    /**
     * Create an instance of {@link UpdateAnswerResponse }
     * 
     */
    public UpdateAnswerResponse createUpdateAnswerResponse() {
        return new UpdateAnswerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "UpdateAnswerResponse")
    public JAXBElement<UpdateAnswerResponse> createUpdateAnswerResponse(UpdateAnswerResponse value) {
        return new JAXBElement<UpdateAnswerResponse>(_UpdateAnswerResponse_QNAME, UpdateAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "GetAllAnswer")
    public JAXBElement<GetAllAnswer> createGetAllAnswer(GetAllAnswer value) {
        return new JAXBElement<GetAllAnswer>(_GetAllAnswer_QNAME, GetAllAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "DeleteAnswer")
    public JAXBElement<DeleteAnswer> createDeleteAnswer(DeleteAnswer value) {
        return new JAXBElement<DeleteAnswer>(_DeleteAnswer_QNAME, DeleteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "DeleteAnswerResponse")
    public JAXBElement<DeleteAnswerResponse> createDeleteAnswerResponse(DeleteAnswerResponse value) {
        return new JAXBElement<DeleteAnswerResponse>(_DeleteAnswerResponse_QNAME, DeleteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "GetAllAnswerResponse")
    public JAXBElement<GetAllAnswerResponse> createGetAllAnswerResponse(GetAllAnswerResponse value) {
        return new JAXBElement<GetAllAnswerResponse>(_GetAllAnswerResponse_QNAME, GetAllAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "InsertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "UpdateAnswer")
    public JAXBElement<UpdateAnswer> createUpdateAnswer(UpdateAnswer value) {
        return new JAXBElement<UpdateAnswer>(_UpdateAnswer_QNAME, UpdateAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AnswerModule/", name = "InsertAnswerResponse")
    public JAXBElement<InsertAnswerResponse> createInsertAnswerResponse(InsertAnswerResponse value) {
        return new JAXBElement<InsertAnswerResponse>(_InsertAnswerResponse_QNAME, InsertAnswerResponse.class, null, value);
    }

}
