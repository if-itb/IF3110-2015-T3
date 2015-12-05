package org.tusiri.ws.answer;

import javax.xml.bind.annotation.XmlElement;

public class AnswerItem {
	
	private int num_answer;
	private int id_question;
	private int id_user;
	private String content;
	private String answer_date;
	private int num_votes;
	private String username;
	private int status;
	
	public AnswerItem(int num_answer,int id_question,int id_user,String content,String answer_date,int num_votes,String username,int stat){
		this.num_answer = num_answer;
		this.id_question = id_question;
		this.id_user = id_user;
		this.content = content;
		this.answer_date = answer_date;
		this.num_votes = num_votes;
		this.username = username;
		this.status = stat;
	}
	
	@XmlElement(name = "num_answer")
	public int getNumAnswer() {
		return num_answer;
	}

	public void setNumAnswer(int num_answer) {
		this.num_answer = num_answer;
	}
	
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
	
	@XmlElement(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlElement(name = "question_date")
	public String getAnswerDate() {
		return answer_date.toString();
	}

	public void setAnswerDate(String answer_date) {
		this.answer_date = answer_date;
	}
	
	@XmlElement(name = "num_votes")
	public int getNumVotes() {
		return num_votes;
	}

	public void setNumVotes(int num_votes) {
		this.num_votes = num_votes;
	}
	
	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int s) {
		this.status = s;
	}
	
}
