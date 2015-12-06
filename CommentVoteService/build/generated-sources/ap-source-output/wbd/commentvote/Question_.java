package wbd.commentvote;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.commentvote.Answer;
import wbd.commentvote.User;
import wbd.commentvote.VoteQuestion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Date> date;
    public static volatile SingularAttribute<Question, User> uid;
    public static volatile CollectionAttribute<Question, VoteQuestion> voteQuestionCollection;
    public static volatile CollectionAttribute<Question, Answer> answerCollection;
    public static volatile SingularAttribute<Question, String> topic;
    public static volatile SingularAttribute<Question, Integer> id;
    public static volatile SingularAttribute<Question, Integer> vote;
    public static volatile SingularAttribute<Question, String> content;

}