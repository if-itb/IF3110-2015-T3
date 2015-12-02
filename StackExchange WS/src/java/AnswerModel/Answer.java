package AnswerModel;

import javax.xml.bind.annotation.*;

/**
 *
 * @author M. Fauzan Naufan
 */

@XmlRootElement(name="Answer")
public class Answer {
    @XmlElement(name="AID", required=true)
    private final int aid;
    @XmlElement(name="UserID", required=true)
    private final int userid;
    @XmlElement(name="Content", required=true)
    private String content;
    @XmlElement(name="Votes", required=true)
    private int votes;
    @XmlElement(name="QID", required=true)
    private final int qid;
    @XmlElement(name="DateTime", required=true)
    private String datetime;
    
    public Answer() {
        aid=0;
        userid=0;
        qid=0;
    }
    
    public Answer(int aid1, int userid1, String content1, int votes1, int qid1, String datetime1) {
        aid=aid1;
        userid=userid1;
        content=content1;
        votes=votes1;
        qid=qid1;
        datetime=datetime1;
    }
}
