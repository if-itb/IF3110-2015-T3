/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Comment implements Serializable {
    private long id;
    private long userId;
    private long questionId;
    private String content;
    private String createdAt;
    private String name = null;
    
     public Comment(long id, String content, long userId, long questionId, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.questionId = userId;
        this.content = content;
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

    public String getCreatedAt() {
        return createdAt;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
