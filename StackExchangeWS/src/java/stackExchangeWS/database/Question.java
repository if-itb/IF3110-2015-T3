/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackExchangeWS.database;

/**
 *
 * @author davidkwan
 */
public class Question {
    private int questionId;
    private int askerId;
    private int answers;
    private int votes;
    private String askerEmail;
    private String topic;
    private String content;
    private String time;    
    private int countAnswers;

    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }
    
    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getAskerEmail() {
        return askerEmail;
    }

    public void setAskerEmail(String askerEmail) {
        this.askerEmail = askerEmail;
    }
    
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAskerId() {
        return askerId;
    }

    public void setAskerId(int askerId) {
        this.askerId = askerId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
