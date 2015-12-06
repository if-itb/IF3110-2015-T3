package wbd.commentvote;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.commentvote.Question;
import wbd.commentvote.User;
import wbd.commentvote.VoteAnswer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Date> date;
    public static volatile SingularAttribute<Answer, User> uid;
    public static volatile SingularAttribute<Answer, Integer> id;
    public static volatile SingularAttribute<Answer, Integer> vote;
    public static volatile SingularAttribute<Answer, Question> qid;
    public static volatile SingularAttribute<Answer, String> content;
    public static volatile CollectionAttribute<Answer, VoteAnswer> voteAnswerCollection;

}