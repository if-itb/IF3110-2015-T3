package model;

import java.io.Serializable;



public class Question implements Serializable {
    private long id;
    private long userId;
    private String topic;
    private String content;
    private String createdAt;
    private long vote;
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
