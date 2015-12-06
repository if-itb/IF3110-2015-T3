package models;

import java.util.HashMap;
import org.json.JSONObject;

/**
 * Created by elvan_owen on 11/18/15.
 */
public class Answer {
    private Integer id;
    private String content;
    private String createDate;
    private String userName;
    private Integer userId;
    private Integer vote;

    public Answer(HashMap<String, String> data){
        this.id = Integer.parseInt(data.get("answerId"));
        this.content = data.get("content");
        this.createDate = data.get("createDate");
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
            jsonObject.put("author_id", this.userId);
            jsonObject.put("vote", this.vote);
            jsonObject.put("create_time", this.createDate);
            jsonObject.put("author_name", this.userName);
        } catch (Exception e){

        }

        return jsonObject.toString();
    }
}
