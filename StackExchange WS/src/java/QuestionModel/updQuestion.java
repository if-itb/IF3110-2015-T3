/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vanji
 */
@XmlRootElement (name = "updQuestion")
public class updQuestion {
    @XmlElement(name = "q_id", required = true)
    private int q_id;
    @XmlElement(name = "u_id", required = true)
    private int u_id;
    @XmlElement(name = "u_name", required = true)
    private String u_name;
    @XmlElement(name = "q_vote", required = true)
    private int q_vote;
    @XmlElement(name = "q_topic", required = true)
    private String q_topic;
    @XmlElement(name = "q_content", required = true)
    private String q_content;
    @XmlElement(name = "q_date", required = true)
    private String q_date;
    
    public updQuestion(){

    }
    
    public updQuestion(String topic, String content, String timestamp){
        this.q_topic = topic;
        this.q_content = content;
        this.q_date = timestamp;
    }
}
