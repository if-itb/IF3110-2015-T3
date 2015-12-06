/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jessica
 */
public class CommentClass {
    private int commentID;
    private int questionID;
    private int userID;
    private String content;
    
    public CommentClass(int commentID, int questionID,int userID, String content) {
        this.commentID = commentID;
        this.questionID = questionID;
        this.userID = userID;
        this.content = content;        
    }
    
    public int getCommentID() {
        return commentID;
    }
    
    public int getQuestiontID() {
        return questionID;
    }
    
    public int getUsertID() {
        return  userID;
    }
    
    public String getContentComment() {
        return content;
    }
}
