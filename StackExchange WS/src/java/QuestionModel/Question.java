/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Question")
public class Question {

  @XmlElement(name="id", required=true)
  private int id;
  @XmlElement(name="id_user", required=true)
  private int idUser;
  @XmlElement (name="topic", required=true)
  private String topic;
  @XmlElement(name="content", required=true)
  private String content;
  @XmlElement(name="timestamp", required=true)
  private String timestamp;
    
  public Question() {
    id = 0;
    idUser = 0;
    topic = null;
    content = null;
    timestamp = null;
  }
  
  public Question(int id, int uid, String topic, String content, String timestamp) {
    this.id = id;
    this.idUser = uid;
    this.topic = topic;
    this.content = content;
    this.timestamp = timestamp;
  }
  
  /* Getter */
  public int getId() {
    return id;
  }  
  public int getIdUser() {
    return idUser;
  }  
  public String getTopic() {
      return topic;
  }
  public String getContent() {
    return content;
  }
  public String getTimestamp() {
      return timestamp;
  }
}
