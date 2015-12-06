/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comment;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Gerry
 */
@XmlRootElement(name="Comment")
public class Comment {
  @XmlElement(name="id", required=true)
    public int id;
    
    @XmlElement(name="questionId", required=true)
    public int questionId;
    
    @XmlElement(name="userId", required=true)
    public int userId;
    
    @XmlElement(name="content", required=true)
    public String content;
    
    public Comment() {
        id = 0;
        questionId = 0;
        userId = 0;
    }
    
    public Comment(int id, int questionId, int userId, String content) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
    }
}
