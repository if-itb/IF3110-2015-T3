/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackx.module;

import java.sql.Date;

/**
 *
 * @author natanelia
 */
public class Question {
    private int questionId;
    private int userId;
    private String userName;
    private String title;
    private String content;
    private int vote;
    private String createDate;

    public Question(int questionId, int userId, String userName, String title, String content, int vote, String createDate) {
        this.questionId = questionId;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.vote = vote;
        this.createDate = createDate;
    }
    
    public int getQuestionId() {
        return questionId;
    }
    
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getVote() {
        return vote;
    }

    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    
    public void voteUp() {
        this.vote = vote + 1;
    }
    
    public void voteDown() {
        this.vote = vote - 1;
    }
}
