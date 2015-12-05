/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import javax.xml.bind.annotation.*;

/**
 *
 * @author user
 */
@XmlRootElement(name = "Question")
public class Question {
    
    @XmlElement(name="questionid", required=true)
    private int questionID;
    @XmlElement(name="username", required=true)
    private String username;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="timestamp", required=true)
    private String timestamp;
    @XmlElement(name="answer_count", required=true)
    private int answer_count;
    
    public Question() {
        questionID = 0;
        vote = 0;
        answer_count = 0;
    }
    
    public Question(int qid, String uname, String topic, String content, int vote, String timestamp, int ans_count) {
        questionID = qid;
        username = uname;
        this.topic = topic;
        this.content = content;
        this.vote = vote;
        this.timestamp = timestamp;
        answer_count = ans_count;
    }
}
