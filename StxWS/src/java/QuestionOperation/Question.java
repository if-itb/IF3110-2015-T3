/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionOperation;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Asus
 */
@XmlRootElement(name = "Question")
public class Question {
    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "email", required = true)
    private String email;
    @XmlElement(name = "topic", required = true)
    private String topic;
    @XmlElement(name = "content", required = true)
    private String content;
    @XmlElement(name = "vote", required = true)
    private int vote;
    public Question() {
        id = 0;
        name = "";
        email = "empty@empty";
        topic = "";
        content = "";
        vote = 0;
    }
    public Question(int _id, String _name, String _email, String _topic, String _content, int _vote) {
        id  = _id;
        name = _name;
        email = _email;
        topic = _topic;
        content = _content;
        vote = _vote;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
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
}
