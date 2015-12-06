package org.stackexchange;

public class Question {

    private long id;

    private long userId;

    private String topic;

    private String content;

    private String createdAt;

    private long vote;

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
}
