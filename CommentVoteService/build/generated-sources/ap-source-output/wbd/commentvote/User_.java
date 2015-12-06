package wbd.commentvote;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.commentvote.Answer;
import wbd.commentvote.Question;
import wbd.commentvote.VoteAnswer;
import wbd.commentvote.VoteQuestion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Question> questionCollection;
    public static volatile CollectionAttribute<User, VoteQuestion> voteQuestionCollection;
    public static volatile SingularAttribute<User, String> pass;
    public static volatile CollectionAttribute<User, Answer> answerCollection;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, VoteAnswer> voteAnswerCollection;

}