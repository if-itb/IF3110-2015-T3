/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tama
 */
public class CComment {
     public int id_c;
   public int q_id;
    public String content;
    public String date;
    public String username; 
    
    public CComment() {}
    
    public CComment(int i,int q,String c,String d,String u) {
        this.id_c=i;
        this.q_id=q;
        this.content=c;
        this.date=d;
        this.username=u;
    }   
}
