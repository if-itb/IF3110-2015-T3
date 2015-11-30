/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adz
 */

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Answer")
public class Answer {
    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "id_question", required = true)
    private int idQuestion;
    @XmlElement(name = "id_user", required = true)
    private int idUser;
    @XmlElement(name = "content", required = true)
    private String content;
    @XmlElement(name = "votes", required = true)
    private int votes;
    @XmlElement(name = "timestamp", required = true)
    private String timestamp;
    
    private Answer() {
        
    }

    public Answer(int id, int id_question, int id_user, String content,
            int votes, String timestamp) {
        this.id = id;
        this.idQuestion = id_question;
        this.idUser = id_user;
        this.content = content;
        this.votes = votes;
        this.timestamp = timestamp;
    }
}
