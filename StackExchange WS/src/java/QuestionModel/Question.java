package QuestionModel;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author M. Fauzan Naufan
 */
public class Question {
    @XmlElement(name="QID", required=true)
    private final int qid;
    @XmlElement(name="UserID", required=true)
    private final int userid;
    @XmlElement(name="Topic", required=true)
    private String topic;
    @XmlElement(name="Content", required=true)
    private String content;
    @XmlElement(name="Votes", required=true)
    private int votes;
    @XmlElement(name="DateTime", required=true)
    private String datetime;
    @XmlElement(name="Answers", required=true)
    private int answers;
    
    public Question() {
        qid = 0;
        userid = 0;
    }
    
    public Question(int qid1, int userid1, String topic1, String content1, int votes1, String datetime1, int answers1) {
        qid = qid1;
        userid=userid1;
        topic=topic1;
        content=content1;
        votes=votes1;
        datetime=datetime1;
        answers=answers1;
    }
}
