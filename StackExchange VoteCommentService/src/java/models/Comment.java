/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Tifani
 */
public class Comment {
    private int cId;
    private int qId;
    private int uId;
    private String email;
    private String content;
    private String dateCreated;
    
    public Comment() {
        
    }

    public Comment(int cId, int qId, int uId, String email, String content, String dateCreated) {
        this.cId = cId;
        this.qId = qId;
        this.uId = uId;
        this.email = email;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    public int getcId() {
        return cId;
    }

    public int getqId() {
        return qId;
    }

    public int getuId() {
        return uId;
    }

    public String getContent() {
        return content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getUsername() {
        return email;
    }
    
}
