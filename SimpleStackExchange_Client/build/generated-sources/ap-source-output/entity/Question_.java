package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-30T12:06:47")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Integer> uid;
    public static volatile SingularAttribute<Question, Integer> countvotes;
    public static volatile SingularAttribute<Question, String> topic;
    public static volatile SingularAttribute<Question, Integer> countanswers;
    public static volatile SingularAttribute<Question, Integer> qid;
    public static volatile SingularAttribute<Question, String> content;
    public static volatile SingularAttribute<Question, Date> createdtime;

}