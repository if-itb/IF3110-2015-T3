package model;


public class Answer {
    public int qid, aid, votes;
    public String name, email, content, created_at;
    
    public Answer() {}
        
    public Answer(int qid,int aid, String name, String email, String content, int votes, String created_at ) {
        this.aid = aid;
        this.qid = qid;
        this.name = name;
        this.email = email;
        this.content = content;
        this.votes = votes;
        this.created_at = created_at;        
    }    
}
