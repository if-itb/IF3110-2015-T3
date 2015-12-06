package wbd.commentvote;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.commentvote.Answer;
import wbd.commentvote.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(VoteAnswer.class)
public class VoteAnswer_ { 

    public static volatile SingularAttribute<VoteAnswer, User> uid;
    public static volatile SingularAttribute<VoteAnswer, Integer> id;
    public static volatile SingularAttribute<VoteAnswer, Integer> type;
    public static volatile SingularAttribute<VoteAnswer, Answer> aid;

}