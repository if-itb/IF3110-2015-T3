package CommentModel;

import javax.xml.bind.annotation.*;

/**
 *
 * @author M. Fauzan Naufan
 */
@XmlRootElement(name = "Comment")
public class Comment {

    @XmlElement(name = "CID", required = true)
    private int cid;
    @XmlElement(name = "Content", required = true)
    private String content;
    @XmlElement(name = "DateTime", required = true)
    private String datetime;
    @XmlElement(name = "QID", required = true)
    private int qid;
    @XmlElement(name = "AID", required = true)
    private int aid;
    @XmlElement(name = "UserID", required = true)
    private int userid;

    public Comment() {

    }

    public Comment(int cid1, String content1, String datetime1, int qid1, int aid1, int userid1) {
        cid = cid1;
        content = content1;
        datetime = datetime1;
        qid = qid1;
        aid = aid1;
        userid = userid1;
    }
}
