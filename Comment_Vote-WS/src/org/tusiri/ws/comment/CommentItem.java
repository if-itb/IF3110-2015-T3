package org.tusiri.ws.comment;

public class CommentItem {
	private int id_comment;
	private int id_question=-1;
	private int id_user;
	private String comment="";
	private String comment_date="";
	private String username="";
	
	public int getIDCommet() {
		return id_comment;
	}

	public void setIDComment(int id_comment) {
		this.id_comment = id_comment;
	}
	
	public int getIDQuestion() {
		return id_question;
	}

	public void setIDQuestion(int id_question) {
		this.id_question = id_question;
	}
	
	public int getIDUser() {
		return id_user;
	}

	public void setIDUser(int id_user) {
		this.id_user = id_user;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getCommentDate(){
		return comment_date;
	}
	
	public void setCommentDate(String comment_date){
		this.comment_date = comment_date;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}