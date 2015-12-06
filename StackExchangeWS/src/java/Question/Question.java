/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmad Naufal Farhan
 */
@XmlRootElement(name="Question")
public class Question {
    
    @XmlElement(name="question-id", required=true)
    private int question_id;
    @XmlElement(name="question-uid", required=true)
    private int question_uid;
    @XmlElement(name="question-topic", required=true)
    private String question_topic;
    @XmlElement(name="question-content", required=true)
    private String question_content;
    @XmlElement(name="question-timestamp", required=true)
    private String question_timestamp;
    
    
    /**
     * Constructor for Question
     */
    public Question() {
       question_id = 0;
    }
    
    /**
     * Constructor with parameters for Question
     * @param _id
     * @param _uid
     * @param _topic
     * @param _content
     * @param _timestamp
     */
    public Question(int _id, int _uid, String _topic, String _content, String _timestamp) {
        question_id = _id;
        question_uid = _uid;
        question_topic = _topic;
        question_content = _content;
        question_timestamp = _timestamp;
    }

    /**
     * @return the question_id
     */
    public int getQuestion_id() {
        return question_id;
    }


    /**
     * @return the question_uid
     */
    public int getQuestion_uid() {
        return question_uid;
    }


    /**
     * @return the question_topic
     */
    public String getQuestion_topic() {
        return question_topic;
    }


    /**
     * @return the question_content
     */
    public String getQuestion_content() {
        return question_content;
    }


    /**
     * @return the question_timestamp
     */
    public String getQuestion_timestamp() {
        return question_timestamp;
    }

    
}
