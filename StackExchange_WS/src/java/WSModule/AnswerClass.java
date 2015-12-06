/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSModule;


import javax.xml.bind.annotation.*;
import javax.xml.bind.*;
/**
 *
 * @author Jessica
 */
 @XmlRootElement(name = "answeritem")
public class AnswerClass {
    private int answer_id;
    private int question_id;
    private String content;
    private int vote;
    private String date;
    private int userID;

	
	
	public AnswerClass(){
    
    }
	
    public AnswerClass(int answer_id, int question_id, String content, int vote, String date, int userID) {
        this.answer_id = answer_id;
        this.question_id = question_id;
        this.content = content;
        this.vote = vote;
        this.date = date;
        this.userID = userID;
    }
	
	
	@XmlElement(name = "questionId2")
    public int getQuestion_id() {

        return question_id;
    }

	@XmlElement(name = "answerId")
    public int getAnswer_id() {

        return answer_id;
    }

	@XmlElement(name = "answerContent")
    public String getContent() {
        return content;
    }

	@XmlElement(name = "answerVote")
    public int getAnswerVote() {

        return vote;
    }

	@XmlElement(name = "answerDate")
    public String getDate() {

        return date;
    }

	@XmlElement(name = "answerUserId")
    public int getUserID() {

        return userID;
    }
}
