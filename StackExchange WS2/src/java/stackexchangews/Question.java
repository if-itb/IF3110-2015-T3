/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchangews;

import javax.xml.bind.annotation.*;

/**
 *
 * @author adar
 */
@XmlRootElement(name="Question")
public class Question {
    @XmlElement(name="id", required=true)
    private int id;
    @XmlElement(name="id_user", required=true)
    private int idUser;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="timepost", required=true)
    private String timepost;

    
    
    Question(int _id, int _id_user, String _topic, String _content, String _timepost) { //To change body of generated methods, choose Tools | Templates.
        id = _id;
        idUser = _id_user;
        topic = _topic;
        content = _content;
        timepost = _timepost;
    }

    Question() {
        id = 0;
        idUser = 0;
        topic = "";
        content = "";
        timepost = "";
    }
    
}
