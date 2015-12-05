package org.tusiri.ws.question;


import java.sql.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "questionitem")
public class QuestionItem {
	
	private int id_question=-1;
	private int id_user;
	private String content="";
	private String question_date="";
	private String topic="";
	private int num_vote;
	private String username="";
	private int num_answer;
	private int status;
	
	
	@XmlElement(name = "id_question")
	public int getIDQuestion() {
		return id_question;
	}

	public void setIDQuestion(int id_question) {
		this.id_question = id_question;
	}
	
	@XmlElement(name = "id_user")
	public int getIDUser() {
		return id_user;
	}

	public void setIDUser(int id_user) {
		this.id_user = id_user;
	}
	
	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlElement(name = "question_date")
	public String getQuestionDate() {
		return question_date.toString();
	}

	public void setQuestionDate(String question_date) {
		this.question_date = question_date;
	}
	
	@XmlElement(name = "topic")
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@XmlElement(name = "num_vote")
	public int getNumVote() {
		return num_vote;
	}

	public void setNumVote(int vote) {
		this.num_vote = vote;
	}
	
	@XmlElement(name = "num_answer")
	public int getNumAnswer() {
		return num_answer;
	}

	public void setNumAnswer(int n_answer) {
		this.num_answer = n_answer;
	}
	
	@XmlElement(name = "status")
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int s){
		this.status = s;
	}
} 