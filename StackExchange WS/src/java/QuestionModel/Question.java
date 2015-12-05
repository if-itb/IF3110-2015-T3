/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import javax.xml.bind.annotation.*;
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="id_user", required=true)
    private int idUser;
    @XmlElement(name="title", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="timestamp", required=true)
    private String timestamp;
    @XmlElement(name="votes", required=true)
    private int votes;
        public Question() {
        id = 0;
        idUser = 0;
        votes=0;
    }    
    public Question(int id, int uid, String topic, String content, String timestamp,int votes) {
        this.id = id;
        idUser = uid;
        this.topic = topic;
        this.content = content;
        this.timestamp = timestamp;
        this.votes = votes;
    }
}
