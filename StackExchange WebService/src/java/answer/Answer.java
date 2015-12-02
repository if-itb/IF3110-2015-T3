/*
 * Nama File : Answer.java
 */
package answer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William Sentosa
 */

@XmlRootElement(name = "Answer")
public class Answer {
    
    @XmlElement(name="answer_id", required=true)
    private int id;
    @XmlElement(name="answerer_name", required=true)
    private String answererName;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="question_id", required=true)
    private int questionId;
    @XmlElement(name="user_id", required=true)
    private int userId;
    
    public Answer() {
        id = 0;
        answererName = null;
        email = null;
        content = null;
        vote = 0;
        questionId = 0;
    }
    
    public Answer(int id, String answererName, String email, String content, int vote, int questionId, int userId) {
        this.id = id;
        this.answererName = answererName;
        this.email = email;
        this.content = content;
        this.vote = vote;
        this.questionId = questionId;
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getAnswererName() {
        return answererName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getContent() {
        return content;
    }
    
    public int getVote() {
        return vote;
    }
    
    public int getQuestionId() {
        return questionId;
    }
    
    public int getUserId() {
        return userId;
    }
    
}
