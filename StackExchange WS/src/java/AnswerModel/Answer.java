/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="Answer")
public class Answer {
    
        @XmlElement(name="id", required=true)
        private int id;
        @XmlElement(name="id_question", required=true)
        private int idQuestion;
        @XmlElement(name="id_user", required=true)
        private int idUser;
        @XmlElement(name="content", required=true)
        private String content;
        @XmlElement(name="timestamp", required=true)
        private String timestamp;
        @XmlElement(name="vote", required=true)
        private int vote;
        
    public Answer() {
        id = 0;
        idQuestion = 0;
        idUser = 0;
        vote=0;
    }       
    public Answer(int id, int qid, int uid, String content, String timestamp,int vote) {
        this.id = id;
        idQuestion = qid;
        idUser = uid;
        this.content = content;
        this.timestamp = timestamp;
        this.vote = vote;
    }
}
