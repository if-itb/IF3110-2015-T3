package models;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by elvan_owen on 11/17/15.
 */
public class Question {

    private Integer id;
    private String content;
    private String createDate;
    private String title;
    private String userName;
    private Integer userId;
    private Integer vote;

    public Question(HashMap<String, String> data){
        this.id= Integer.parseInt(data.get("questionId"));
        this.content = data.get("content");
        this.createDate = data.get("createDate");
        this.title = data.get("title");
        this.userName = data.get("userName");
        this.userId = Integer.parseInt(data.get("userId"));
        this.vote = Integer.parseInt(data.get("vote"));
    }

    public String getContent(){
        return content;
    }

    public String getCreateDate(){
        return createDate;
    }

    public String getTitle(){
        return title;
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

    @Override
    public String toString() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", this.id);
            jsonObject.put("content", this.content);
            jsonObject.put("title", this.title);
            jsonObject.put("author_id", this.userId);
            jsonObject.put("vote", this.vote);
            jsonObject.put("create_time", this.createDate);
            jsonObject.put("author_name", this.userName);
        } catch (Exception e){

        }

        return jsonObject.toString();
    }
}
