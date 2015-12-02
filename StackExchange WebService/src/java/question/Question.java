/*
 * Nama File : Question.java
 */
package question;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William Sentosa
 */
@XmlRootElement(name = "Question")
public class Question {
    @XmlElement(name="question_id", required=true)
    private int id;
    @XmlElement(name="asker_name", required=true)
    private String askerName;
    @XmlElement(name="email", required=true)
    private String email;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="user_id", required=true)
    private int userId;
    
    public Question() {
        id = 0;
        askerName = null;
        email = null;
        topic = null;
        content = null;
        vote = 0;
        userId = 0;
    }
    
    public Question(int id, String askerName, String email, String topic, String content, int vote, int userId) {
        this.id = id;
        this.askerName = askerName;
        this.email = email;
        this.topic = topic;
        this.content = content;
        this.vote = vote;
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getAskerName() {
        return askerName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public String getContent() {
        return content;
    } 
    
    public int getVote() {
        return vote;
    }
    
    public int getUserId() {
        return userId;
    }
    
}
