/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jessica
 */
public class AnswerClass {
    private int answer_id;
    private int question_id;
    private String content;
    private int vote;
    private String date;
    private int userID;

    public AnswerClass(int answer_id, int question_id, String content, int vote, String date, int userID) {
        this.answer_id = answer_id;
        this.question_id = question_id;
        this.content = content;
        this.vote = vote;
        this.date = date;
        this.userID = userID;
    }
	
    public int getQuestion_id() {

        return question_id;
    }

    public int getAnswer_id() {

        return answer_id;
    }

    public String getContent() {
        return content;
    }

    public int getAnswerVote() {

        return vote;
    }

    public String getDate() {

        return date;
    }

    public int getUserID() {

        return userID;
    }    
}
