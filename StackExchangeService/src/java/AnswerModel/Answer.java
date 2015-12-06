package AnswerModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Answer")  
public class Answer {
    
    @XmlElement(name= "id_answer", required = true)
    private int id_answer;
    @XmlElement(name= "id_question", required = true)
    private int id_question;
    @XmlElement(name= "vote", required = true)
    private int vote;
    @XmlElement(name = "content", required = true)
    private String content;
    @XmlElement(name = "date", required = true)
    private String date;
    @XmlElement(name = "username", required = true)
    private String username;
    
    public Answer() {
        id_answer = 0;
        id_question = 0;
        vote = 0;
    }
    
    public Answer(int id_answer, int id_question, int vote, String content, String date, String username) {
        this.id_answer = id_answer;
        this.id_question = id_question;
        this.vote = vote;
        this.content = content;
        this.date = date;
        this.username = username;
    }
    
    public String getContent() {
        return content;
    }
    
    public int getVote() {
        return vote;
    }
    
    public String getUsername() {
        return username;
    }
}
