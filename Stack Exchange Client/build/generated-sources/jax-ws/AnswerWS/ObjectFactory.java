
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

    private final static QName _VoteAnswer_QNAME = new QName("http://answer_package.dazzlesquad.com/", "voteAnswer");
    private final static QName _InsertAnswerResponse_QNAME = new QName("http://answer_package.dazzlesquad.com/", "insertAnswerResponse");
    private final static QName _DeleteAnswerResponse_QNAME = new QName("http://answer_package.dazzlesquad.com/", "deleteAnswerResponse");
    private final static QName _DeleteAnswer_QNAME = new QName("http://answer_package.dazzlesquad.com/", "deleteAnswer");
    private final static QName _GetAnswerById_QNAME = new QName("http://answer_package.dazzlesquad.com/", "getAnswerById");
    private final static QName _Answer_QNAME = new QName("http://answer_package.dazzlesquad.com/", "Answer");
    private final static QName _InsertAnswer_QNAME = new QName("http://answer_package.dazzlesquad.com/", "insertAnswer");
    private final static QName _GetAnswerByIdResponse_QNAME = new QName("http://answer_package.dazzlesquad.com/", "getAnswerByIdResponse");
    private final static QName _VoteAnswerResponse_QNAME = new QName("http://answer_package.dazzlesquad.com/", "voteAnswerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: AnswerWS
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link GetAnswerByIdResponse }
     * 
     */
    public GetAnswerByIdResponse createGetAnswerByIdResponse() {
        return new GetAnswerByIdResponse();
    }

    /**
     * Create an instance of {@link VoteAnswerResponse }
     * 
     */
    public VoteAnswerResponse createVoteAnswerResponse() {
        return new VoteAnswerResponse();
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
     * Create an instance of {@link GetAnswerById }
     * 
     */
    public GetAnswerById createGetAnswerById() {
        return new GetAnswerById();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "voteAnswer")
    public JAXBElement<VoteAnswer> createVoteAnswer(VoteAnswer value) {
        return new JAXBElement<VoteAnswer>(_VoteAnswer_QNAME, VoteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "insertAnswerResponse")
    public JAXBElement<InsertAnswerResponse> createInsertAnswerResponse(InsertAnswerResponse value) {
        return new JAXBElement<InsertAnswerResponse>(_InsertAnswerResponse_QNAME, InsertAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "deleteAnswerResponse")
    public JAXBElement<DeleteAnswerResponse> createDeleteAnswerResponse(DeleteAnswerResponse value) {
        return new JAXBElement<DeleteAnswerResponse>(_DeleteAnswerResponse_QNAME, DeleteAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "deleteAnswer")
    public JAXBElement<DeleteAnswer> createDeleteAnswer(DeleteAnswer value) {
        return new JAXBElement<DeleteAnswer>(_DeleteAnswer_QNAME, DeleteAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "getAnswerById")
    public JAXBElement<GetAnswerById> createGetAnswerById(GetAnswerById value) {
        return new JAXBElement<GetAnswerById>(_GetAnswerById_QNAME, GetAnswerById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Answer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "Answer")
    public JAXBElement<Answer> createAnswer(Answer value) {
        return new JAXBElement<Answer>(_Answer_QNAME, Answer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "insertAnswer")
    public JAXBElement<InsertAnswer> createInsertAnswer(InsertAnswer value) {
        return new JAXBElement<InsertAnswer>(_InsertAnswer_QNAME, InsertAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAnswerByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "getAnswerByIdResponse")
    public JAXBElement<GetAnswerByIdResponse> createGetAnswerByIdResponse(GetAnswerByIdResponse value) {
        return new JAXBElement<GetAnswerByIdResponse>(_GetAnswerByIdResponse_QNAME, GetAnswerByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://answer_package.dazzlesquad.com/", name = "voteAnswerResponse")
    public JAXBElement<VoteAnswerResponse> createVoteAnswerResponse(VoteAnswerResponse value) {
        return new JAXBElement<VoteAnswerResponse>(_VoteAnswerResponse_QNAME, VoteAnswerResponse.class, null, value);
    }

}
