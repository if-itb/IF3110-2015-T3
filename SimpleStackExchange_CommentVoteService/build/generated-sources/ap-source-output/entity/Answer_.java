package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T19:35:19")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Integer> uid;
    public static volatile SingularAttribute<Answer, Integer> countvotes;
    public static volatile SingularAttribute<Answer, Integer> aid;
    public static volatile SingularAttribute<Answer, Integer> qid;
    public static volatile SingularAttribute<Answer, String> content;
    public static volatile SingularAttribute<Answer, Date> createdtime;

}