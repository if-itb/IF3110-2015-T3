/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackx.module;

/**
 *
 * @author natanelia
 */
public class Answer {
    private int answerId;
    private int questionId;
    private int userId;
    private String content;
    private int vote;
    private String createDate;

    public Answer(int answerId, int questionId, int userId, String content, int vote, String createDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.vote = vote;
        this.createDate = createDate;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
