/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.webservice.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import stackexchange.webservice.model.Answer;
import stackexchange.webservice.model.Question;

/**
 *
 * @author fauzanrifqy
 */
public class CVote {
    public boolean voting(int id,String email, boolean inc){
        String query = "select * from questionVotes where questionId="+id+" and email='"+ email +"'";
        Database db = new Database();
        Connection conn = db.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return false;
            }else{
                if(inc){
                    int temp = 1;
                    query = "insert into questionVotes (questionId, email, value) values ("+id+",'"+email+"',"+temp+")";
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }else{
                    int temp = -1;
                    query = "insert into questionVotes (questionId, email, value) values ("+id+",'"+email+"',"+temp+")";
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CVote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean voting(int id, int questionid,String email, boolean inc){
        String query = "select * from answerVotes where answerId="+id+" and questionId="+questionid+" and email='"+ email +"'";
        Database db = new Database();
        Connection conn = db.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return false;
            }else{
                if(inc){
                    int temp = 1;
                    query = "insert into answerVotes (questionId, answerId, email, value) values ("+questionid+","+id+",'"+email+"',"+temp+")";
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }else{
                    int temp = -1;
                    query = "insert into answerVotes (questionId, answerId, email, value) values ("+questionid+","+id+",'"+email+"',"+temp+")";
                    ps = conn.prepareStatement(query);
                    ps.executeUpdate();
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CVote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
