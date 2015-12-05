package QuestionModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Question") 
public class Question {
    @XmlElement(name="id_question", required=true)
    private int id_question;
    @XmlElement(name="topic", required=true)
    private String topic;
    @XmlElement(name="content", required=true)
    private String content;
    @XmlElement(name="date", required=true)
    private String date;
    @XmlElement(name="username", required=true)
    private String username;
    @XmlElement(name="vote", required=true)
    private int vote;
    @XmlElement(name="answer", required=true)
    private int answer;
    
    public Question() {
        id_question = 0;
        vote = 0;
}
    
    public Question(int id_question, int vote, String topic, String content, String date, String username) {
        this.id_question = id_question;
        this.vote = vote;
        this.content = content;
        this.date = date;
        this.username = username;
        this.topic=topic;
    }
    
    public String getContent() {
        return content;
    }
    
    public Question(int Ans, int id_question, int vote, String topic, String content, String date, String username) {
        this.id_question = id_question;
        this.vote = vote;
        this.content = content;
        this.date = date;
        this.username = username;
        this.topic=topic;
        this.answer=Ans;
    }
}
