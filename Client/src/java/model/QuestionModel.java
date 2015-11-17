package model;

public class QuestionModel {

    private String qid, answer_count, votes, name, email, qtopic, qcontent, created_at;
    
    public QuestionModel(String qid, String name, String email, String qtopic, String qcontent, String votes, String answer_count, String created_at ) {
        this.qid = qid;
        this.name = name;
        this.email = email;
        this.qtopic = qtopic;
        this.qcontent = qcontent;
        this.votes = votes;
        this.answer_count = answer_count;
        this.created_at = created_at;       
    }
    
    public String getQid(){
	return this.qid;
    }
    public String getName(){
	return this.name;
    }
    public String getEmail(){
	return this.email;
    }
    public String getQtopic(){
	return this.qtopic;
    }
    public String getQcontent(){
	return this.qcontent;
    }
    public String getVotes(){
	return this.qtopic;
    }
    public String getCreatedAt(){
	return this.created_at;
    }
    public String getAnswerCount(){
	return this.answer_count;
    }
}
