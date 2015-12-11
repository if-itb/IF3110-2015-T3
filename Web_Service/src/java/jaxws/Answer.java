/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;  

@XmlRootElement(name = "Answer") public class Answer {  
  @XmlElement(name="id", required=true)  
  private int id;  
  @XmlElement(name="qid", required=true)  
  private int qid;   
  @XmlElement(name="uid", required=true)   
  private int uid;   
  @XmlElement(name="name", required=true)   
  private String name; 
  @XmlElement(name="content", required=true)   
  private String content;   
  @XmlElement(name="date", required=true)  
  private String date;  
  @XmlElement(name="vote", required=true)  
  private int vote;
  public Answer() { 
      id = 0;     
      qid = 0; 
      uid = 0;
      name = "";
      content = "";
      date = "";
      vote = 0;
  }      
  public Answer(int id, int qid, int uid, String name, 
          String content, String date, int vote) {
      this.id = id;     
      this.qid = qid;     
      this.uid = uid; 
      this.name = name;
      this.content = content;     
      this.date = date;  
      this.vote = vote;
  }    
  public int getId(){
      return id;
  }
  public int getQId(){
      return qid;
  }
  public int getUid(){
      return uid;
  }
  public String getName(){
      return name;
  }
  public String getContent(){
      return content;
  }
  public String getDate(){
      return date;
  }
  public int getVote(){
      return vote;
  }

  public ArrayList<Answer> fetchAnswers(ResultSet rs) {
    ArrayList<Answer> ret = new ArrayList<>();
    try {
      UserWS userws = new UserWS();
      while(rs.next()) {
        String name = userws.getName(rs.getInt("uid"));
        ret.add(new Answer( rs.getInt("id"),                                  
                rs.getInt("qid"),                                  
                rs.getInt("uid"), 
                name,
                rs.getString("content"),                                  
                rs.getString("date"),
                rs.getInt("vote")
        ));  
      }
    } catch(Throwable e) {
    }
    return ret;
  }
}
