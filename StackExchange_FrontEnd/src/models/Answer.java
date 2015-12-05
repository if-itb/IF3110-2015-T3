package models;

import java.util.HashMap;

/**
 * Created by elvan_owen on 11/18/15.
 */
public class Answer {
    private Integer id;
    private String content;
    private String createDate;
    private Integer userId;
    private Integer vote;

    public Answer(HashMap<String, String> data){
        this.id = Integer.parseInt(data.get("answerId"));
        this.content = data.get("content");
        this.createDate = data.get("createDate");
        this.userId = Integer.parseInt(data.get("userId"));
        this.vote = Integer.parseInt(data.get("vote"));
    }

    public String getContent(){
        return content;
    }

    public String getCreateDate(){
        return createDate;
    }

    public Integer getVote(){
        return vote;
    }

    public Integer getUserId(){
        return userId;
    }

    public Integer getId(){
        return id;
    }
}
