package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Question")
public class Question implements Serializable {

    @XmlElement(name = "id", required = true)
    private long id;

    @XmlElement(name = "user_id", required = true)
    private long userId;

    @XmlElement(name = "topic", required = true)
    private String topic;

    @XmlElement(name = "content", required = true)
    private String content;

    @XmlElement(name = "created_at", required = true)
    private String createdAt;

    @XmlElement(name = "vote", required = true)
    private long vote;

    @XmlElement(name = "name", required = true)
    private String name = null;
    
    public Question() {
        
    }

    public Question(long id, long userId, String topic, String content, String createdAt, long vote) {
        this.id = id;
        this.userId = userId;
        this.topic = topic;
        this.content = content;
        this.createdAt = createdAt;
        this.vote = vote;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getVote() {
        return vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
