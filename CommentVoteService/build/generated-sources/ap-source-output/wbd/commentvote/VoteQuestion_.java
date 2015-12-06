package wbd.commentvote;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.commentvote.Question;
import wbd.commentvote.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(VoteQuestion.class)
public class VoteQuestion_ { 

    public static volatile SingularAttribute<VoteQuestion, User> uid;
    public static volatile SingularAttribute<VoteQuestion, Integer> id;
    public static volatile SingularAttribute<VoteQuestion, Integer> type;
    public static volatile SingularAttribute<VoteQuestion, Question> qid;

}