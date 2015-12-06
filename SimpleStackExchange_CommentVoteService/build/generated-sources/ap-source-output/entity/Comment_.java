package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T19:35:19")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Integer> uid;
    public static volatile SingularAttribute<Comment, Integer> qid;
    public static volatile SingularAttribute<Comment, String> content;
    public static volatile SingularAttribute<Comment, Date> createdtime;
    public static volatile SingularAttribute<Comment, Integer> cid;

}