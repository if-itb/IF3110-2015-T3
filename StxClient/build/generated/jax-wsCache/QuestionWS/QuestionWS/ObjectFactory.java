
package QuestionWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the QuestionWS package. 
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

    private final static QName _Exception_QNAME = new QName("http://QuestionOperation/", "Exception");
    private final static QName _Question_QNAME = new QName("http://QuestionOperation/", "Question");
    private final static QName _Delete_QNAME = new QName("http://QuestionOperation/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://QuestionOperation/", "deleteResponse");
    private final static QName _GetAllQuestion_QNAME = new QName("http://QuestionOperation/", "getAllQuestion");
    private final static QName _GetAllQuestionResponse_QNAME = new QName("http://QuestionOperation/", "getAllQuestionResponse");
    private final static QName _GetNumAnswer_QNAME = new QName("http://QuestionOperation/", "getNumAnswer");
    private final static QName _GetNumAnswerResponse_QNAME = new QName("http://QuestionOperation/", "getNumAnswerResponse");
    private final static QName _GetQuestionById_QNAME = new QName("http://QuestionOperation/", "getQuestionById");
    private final static QName _GetQuestionByIdResponse_QNAME = new QName("http://QuestionOperation/", "getQuestionByIdResponse");
    private final static QName _GetVote_QNAME = new QName("http://QuestionOperation/", "getVote");
    private final static QName _GetVoteResponse_QNAME = new QName("http://QuestionOperation/", "getVoteResponse");
    private final static QName _Post_QNAME = new QName("http://QuestionOperation/", "post");
    private final static QName _PostResponse_QNAME = new QName("http://QuestionOperation/", "postResponse");
    private final static QName _Search_QNAME = new QName("http://QuestionOperation/", "search");
    private final static QName _SearchResponse_QNAME = new QName("http://QuestionOperation/", "searchResponse");
    private final static QName _Update_QNAME = new QName("http://QuestionOperation/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://QuestionOperation/", "updateResponse");
    private final static QName _Vote_QNAME = new QName("http://QuestionOperation/", "vote");
    private final static QName _VoteResponse_QNAME = new QName("http://QuestionOperation/", "voteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: QuestionWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link GetAllQuestion }
     * 
     */
    public GetAllQuestion createGetAllQuestion() {
        return new GetAllQuestion();
    }

    /**
     * Create an instance of {@link GetAllQuestionResponse }
     * 
     */
    public GetAllQuestionResponse createGetAllQuestionResponse() {
        return new GetAllQuestionResponse();
    }

    /**
     * Create an instance of {@link GetNumAnswer }
     * 
     */
    public GetNumAnswer createGetNumAnswer() {
        return new GetNumAnswer();
    }

    /**
     * Create an instance of {@link GetNumAnswerResponse }
     * 
     */
    public GetNumAnswerResponse createGetNumAnswerResponse() {
        return new GetNumAnswerResponse();
    }

    /**
     * Create an instance of {@link GetQuestionById }
     * 
     */
    public GetQuestionById createGetQuestionById() {
        return new GetQuestionById();
    }

    /**
     * Create an instance of {@link GetQuestionByIdResponse }
     * 
     */
    public GetQuestionByIdResponse createGetQuestionByIdResponse() {
        return new GetQuestionByIdResponse();
    }

    /**
     * Create an instance of {@link GetVote }
     * 
     */
    public GetVote createGetVote() {
        return new GetVote();
    }

    /**
     * Create an instance of {@link GetVoteResponse }
     * 
     */
    public GetVoteResponse createGetVoteResponse() {
        return new GetVoteResponse();
    }

    /**
     * Create an instance of {@link Post }
     * 
     */
    public Post createPost() {
        return new Post();
    }

    /**
     * Create an instance of {@link PostResponse }
     * 
     */
    public PostResponse createPostResponse() {
        return new PostResponse();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Vote }
     * 
     */
    public Vote createVote() {
        return new Vote();
    }

    /**
     * Create an instance of {@link VoteResponse }
     * 
     */
    public VoteResponse createVoteResponse() {
        return new VoteResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Question }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "Question")
    public JAXBElement<Question> createQuestion(Question value) {
        return new JAXBElement<Question>(_Question_QNAME, Question.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getAllQuestion")
    public JAXBElement<GetAllQuestion> createGetAllQuestion(GetAllQuestion value) {
        return new JAXBElement<GetAllQuestion>(_GetAllQuestion_QNAME, GetAllQuestion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllQuestionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getAllQuestionResponse")
    public JAXBElement<GetAllQuestionResponse> createGetAllQuestionResponse(GetAllQuestionResponse value) {
        return new JAXBElement<GetAllQuestionResponse>(_GetAllQuestionResponse_QNAME, GetAllQuestionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getNumAnswer")
    public JAXBElement<GetNumAnswer> createGetNumAnswer(GetNumAnswer value) {
        return new JAXBElement<GetNumAnswer>(_GetNumAnswer_QNAME, GetNumAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNumAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getNumAnswerResponse")
    public JAXBElement<GetNumAnswerResponse> createGetNumAnswerResponse(GetNumAnswerResponse value) {
        return new JAXBElement<GetNumAnswerResponse>(_GetNumAnswerResponse_QNAME, GetNumAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getQuestionById")
    public JAXBElement<GetQuestionById> createGetQuestionById(GetQuestionById value) {
        return new JAXBElement<GetQuestionById>(_GetQuestionById_QNAME, GetQuestionById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuestionByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getQuestionByIdResponse")
    public JAXBElement<GetQuestionByIdResponse> createGetQuestionByIdResponse(GetQuestionByIdResponse value) {
        return new JAXBElement<GetQuestionByIdResponse>(_GetQuestionByIdResponse_QNAME, GetQuestionByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getVote")
    public JAXBElement<GetVote> createGetVote(GetVote value) {
        return new JAXBElement<GetVote>(_GetVote_QNAME, GetVote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "getVoteResponse")
    public JAXBElement<GetVoteResponse> createGetVoteResponse(GetVoteResponse value) {
        return new JAXBElement<GetVoteResponse>(_GetVoteResponse_QNAME, GetVoteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Post }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "post")
    public JAXBElement<Post> createPost(Post value) {
        return new JAXBElement<Post>(_Post_QNAME, Post.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PostResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "postResponse")
    public JAXBElement<PostResponse> createPostResponse(PostResponse value) {
        return new JAXBElement<PostResponse>(_PostResponse_QNAME, PostResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Search }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "search")
    public JAXBElement<Search> createSearch(Search value) {
        return new JAXBElement<Search>(_Search_QNAME, Search.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "searchResponse")
    public JAXBElement<SearchResponse> createSearchResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "vote")
    public JAXBElement<Vote> createVote(Vote value) {
        return new JAXBElement<Vote>(_Vote_QNAME, Vote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://QuestionOperation/", name = "voteResponse")
    public JAXBElement<VoteResponse> createVoteResponse(VoteResponse value) {
        return new JAXBElement<VoteResponse>(_VoteResponse_QNAME, VoteResponse.class, null, value);
    }

}
