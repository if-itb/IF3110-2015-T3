/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Vanji
 */
@XmlRootElement(name = "Answer")
public class Answer {
    @XmlElement(name = "id", required=true)
    private int id;
    @XmlElement(name = "q_id", required=true)
    private int idQuestion;
    @XmlElement(name = "u_id", required=true)
    private int idUser;
    @XmlElement(name = "content", required=true)
    private String content;
    @XmlElement(name = "timestamp", required=true)
    private String timestamp;

    public Answer() {
        id = 0;
        idQuestion = 0;
        idUser = 0;
    }    

    public Answer(int id, int qid, int uid, String content, String timestamp){
        this.id = id;
        idQuestion = qid;
        idUser = uid;
        this.content = content;
        this.timestamp = timestamp;
    }
    
}
