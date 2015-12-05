package model;

import java.io.Serializable;



public class Answer implements Serializable {
    private long id;
    private long userId;
    private long questionId;
    private String content;
    private long vote;
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
