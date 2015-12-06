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
 @XmlRootElement(name = "questionitem")
public class QuestionClass {
    private int questionId;
    private String title;
    private String content;
    private int vote;
    private String date;
    private int userID;

	
	public QuestionClass(){
    
    }
	
    public QuestionClass (int questionId, String title, String content, int vote, String date, int userID ) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.vote = vote;
        this.date = date;
        this.userID = userID;
    }
	
	
	@XmlElement(name = "questionId")
    public int getQuestionId() {
        return questionId;
    }

	
	@XmlElement(name = "questionTitle")
    public String getTitle() {

        return title;
    }

	@XmlElement(name = "questionContent")
    public String getContent() {

        return content;
    }

	@XmlElement(name = "questionVote")
    public int getVoteQuestion() {

        return vote;
    }

	@XmlElement(name = "questionDate")
    public String getDate() {

        return date;
    }

	 @XmlElement(name = "questionUserId")
    public int getUserID() {

        return userID;
    }
}

