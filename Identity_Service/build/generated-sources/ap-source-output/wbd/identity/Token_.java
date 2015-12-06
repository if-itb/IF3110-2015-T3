package wbd.identity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import wbd.identity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T11:29:36")
@StaticMetamodel(Token.class)
public class Token_ { 

    public static volatile SingularAttribute<Token, String> val;
    public static volatile SingularAttribute<Token, User> uid;
    public static volatile SingularAttribute<Token, Integer> expires;

}