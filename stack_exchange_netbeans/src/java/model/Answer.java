package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Answer")
public class Answer implements Serializable {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "user_id", required = true)
    private long userId;

    @XmlElement(name = "question_id", required = true)
    private long questionId;

    @XmlElement(name = "content", required = true)
    private String content;

    @XmlElement(name = "vote", required = true)
    private long vote;

    @XmlElement(name = "created_at", required = true)
    private String createdAt;
    
    public Answer() {
        
    }

    public Answer(long id, long userId, long questionId, String content, long vote, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.content = content;
        this.vote = vote;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public long getVote() {
        return vote;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
