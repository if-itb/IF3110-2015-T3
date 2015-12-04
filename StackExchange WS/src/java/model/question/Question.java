package model.question;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Asanilta
 */

@XmlRootElement(name = "Question")
public class Question {
    
    
    private int question_id;
    private String topic;
    private String content;
    private int user_id;
    private String create_time;
    private int vote;
    
    public Question() {
        question_id = 0;
        user_id = 0;
        vote = 0;
    }
    public Question(int question_id, String topic, String content, int user_id, String create_time, int vote) {
        this.question_id = question_id;
        this.topic = topic;
        this.content = content;
        this.user_id = user_id;
        this.create_time = create_time;
        this.vote = vote;
    }
    
    @XmlElement(name="question_id", required=true)
    public int getQuestionID() {
        return question_id;
    }
    
    @XmlElement(name="topic", required=true)
    public String getTopic() {
        return topic;
    }
    
    @XmlElement(name="content", required=true)
    public String getContent() {
        return content;
    }
    
    
    @XmlElement(name="user_id", required=true)
    public int getUserID() {
        return user_id;
    }
    
    
    @XmlElement(name="create_time", required=true)
    public String getCreateTime() {
        return create_time;
    }
    
    
    @XmlElement(name="vote", required=true)
    public int getVote() {
        return vote;
    }
    
    public void setQuestionID(int question_id) {
        this.question_id = question_id;
    }
    public void setUserID(int uid) {
        user_id = uid;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setVote(int vote) {
        this.vote = vote;
    }
}
