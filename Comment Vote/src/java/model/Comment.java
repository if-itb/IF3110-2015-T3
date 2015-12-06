/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author visat
 */
public class Comment {
    private int id;
    private int idQuestion;
    private int idUser;
    private String content;
    private String user;

    private Comment() {

    }

    public Comment(int id, int idQuestion, int idUser, String content, String user) {
        this.id = id;
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.content = content;
        this.user = user;
    }

    public int getId() { return this.id; }

    public int getIdQuestion() { return this.idQuestion; }

    public int getIdUser() { return this.idUser; }

    public String getContent() { return this.content; }

    public String getUser() { return this.user; }
}
