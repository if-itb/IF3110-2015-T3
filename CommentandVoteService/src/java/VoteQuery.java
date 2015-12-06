
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nim_13512501
 */
public class VoteQuery {
    public static int voteQuestion(int qid, boolean up, String Email){
        int Status = 0;
        Database DB = new Database();
        Connection con = DB.connect();
        PreparedStatement ps=null;
        PreparedStatement checkvote=null;
        PreparedStatement checkps=null;
        int plus  = 0;
        
        System.out.println("check");
        try{
            
                checkvote = con.prepareStatement("SELECT up from HasVotedQuestion WHERE qid = ? AND Email = ?");
                checkvote.setInt(1,qid);
                checkvote.setString(2,Email);
                ResultSet rs2 = checkvote.executeQuery();
                if(!rs2.next()){
                    if(up==false) plus = -1;
                    else plus=1;
                    String query = "UPDATE Question SET vote = (vote+?) where qid = ?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, plus);
                    ps.setInt(2, qid);
                    ps.executeUpdate();
                    query = "INSERT INTO HasVotedQuestion (Email, qid, up) VALUES ( ? , ? , ? )";
                    ps = con.prepareStatement(query);
                    ps.setString(1, Email);
                    ps.setInt(2, qid);
                    ps.setBoolean(3, up);
                    ps.executeUpdate();
                    Status = HttpServletResponse.SC_CREATED;
                }else{
                    Status = HttpServletResponse.SC_CONFLICT;
                }
                rs2.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
            try{
                if(checkps!=null) checkps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
            try{
                if(con!=null)    con.close();     
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
        }
        return Status;
    }
    
    public static int voteAnswer(int qid, int aid, boolean up, String Email){
        int Status = 0;
        PreparedStatement ps = null;
        Connection con = Database.connect();
        try{                
            String q = "SELECT * FROM HasVotedAnswer WHERE qid = ? AND aid = ? AND Email=?";
            ps = con.prepareStatement(q);
            ps.setInt(1, qid);
            ps.setInt(2, aid);
            ps.setString(3, Email);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){ 
                int plus;
                if(up==false) plus = -1; 
                else plus = 1;
                String query = "UPDATE Answer SET vote = (vote + ?) where qid = ? AND aid = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1,plus);
                ps.setInt(2,qid);
                ps.setInt(3, aid);
                ps.executeUpdate();
                query = "INSERT INTO HasVotedAnswer (Email, qid, aid, up) VALUES (? , ? , ? , ?)";
                ps = con.prepareStatement(query);
                ps.setString(1, Email);
                ps.setInt(2,qid);
                ps.setInt(3,aid);
                ps.setBoolean(4, up);
                ps.executeUpdate();
                ps.close();
                Status = HttpServletResponse.SC_CREATED;
            }
            else{
                Status = HttpServletResponse.SC_CONFLICT;
            }
        }catch(SQLException e){
            e.printStackTrace();;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                if(ps!=null)    ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
            try{
                if(con!=null)    con.close();     
            }catch(SQLException ex){
                ex.printStackTrace();
                Status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
        }
        return Status;
    }
    
}
