package AnswerModel;

import Authentication.Auth;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import Config.DB;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    
    private final DB db = new DB();
    private Connection conn;
    private final Auth auth= new Auth();
    
    private static String getCurrentTimeStamp() {
        Calendar cal = Calendar.getInstance();  
	Timestamp now = new Timestamp(cal.getTimeInMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now); 
    }


    /**
     *
     * @param qid
     * @return
     */
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name = "Answer")
    public List<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        
        List<Answer> answers = new ArrayList<Answer>();
        
        conn = db.connect();
        
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM answer where id_question = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                answers.add( new Answer(
                    rs.getInt("id_answer"),
                    rs.getInt("id_question"),
                    rs.getInt("vote"),
                    rs.getString("content"),
                    rs.getString("date"),
                    rs.getString("username")));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return answers;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAnswer")
    public Boolean createAnswer(@WebParam(name = "id_question") int id_question, 
            @WebParam(name = "content") String content,
            @WebParam(name = "token") String token,
            
            @WebParam(name = "userAgent") String userAgent,
            @WebParam(name = "ip") String ip) {
        
        conn = db.connect();
        Boolean status = true;
        String username = auth.checkToken(token,userAgent, ip);
        if(!username.equals("-999")){
            if(!username.equals("-999")){
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                        try {
                        Statement stmt;
                            stmt = conn.createStatement();

                            String sql;
                            sql = "INSERT INTO answer(id_question, username, content, vote, date)"
                                    + " VALUES (?, ?, ?, 0, ?)";

                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            dbStatement.setInt(1, id_question);
                            dbStatement.setString(2, username);
                            dbStatement.setString(3, content);
                            dbStatement.setString(4, getCurrentTimeStamp());

                            status = dbStatement.execute();
                            stmt.close();
                            conn.close();
                        } 
                        catch(SQLException ex) {
                           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
                        }
                    }
                }
            }
        }
        return status;
    }
    @WebMethod(operationName = "voteUp")
    public int voteUp(@WebParam(name = "qid") int qid,@WebParam(name = "token") String token,
            
            @WebParam(name = "userAgent") String userAgent,
            @WebParam(name = "ip") String ip){
        int count;
        count=getVoteById(qid);
        String username= auth.checkToken(token, userAgent, ip);
        if((!username.equals("-999"))&&(!isVoteUp(qid, username))){
            if(!username.equals("-999")){
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                        try {
                            conn = db.connect();
                            Statement stmt;
                            stmt = conn.createStatement();
                            //pernah vote down
                            if(isVoteDown(qid,username)){
                                count+=2;
                                String sql;
                                sql="DELETE FROM vote_answer WHERE id_answer = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setInt(1, qid);
                                dbStatement.execute();
                            }
                            else{count+=1;}
                            String sql, sql_select;
                            sql="UPDATE answer SET vote = ? WHERE id_answer = ? ";

                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            dbStatement.setInt(1, count);
                            dbStatement.setInt(2, qid);
                            dbStatement.execute();
                            sql="INSERT INTO vote_answer (id_answer, username,value) VALUES (?,?,1)";
                            PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                            dbStatement2.setInt(1, qid);
                            dbStatement2.setString(2, username);
                            dbStatement2.executeUpdate();

                            stmt.close();
                            conn.close();
                        }
                        catch(SQLException ex) {
                           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
                        }
                    }
                }
            }
        }
        return count;
    }
    @WebMethod(operationName = "voteDown")
    public int voteDown(@WebParam(name = "aid") int aid,@WebParam(name = "token") String token,
            @WebParam(name = "userAgent") String userAgent,
            @WebParam(name = "ip") String ip){
        int count;
        count=getVoteById(aid);
        String username= auth.checkToken(token, userAgent, ip);       
        if((!username.equals("-999"))&&(!isVoteDown(aid, username))){
            if(!username.equals("-999")){
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                        try {
                            conn = db.connect();
                            Statement stmt;
                            stmt = conn.createStatement();
                            if(isVoteUp(aid,username)){
                                count-=2;
                                String sql;
                                sql="DELETE FROM vote_answer WHERE id_answer = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setInt(1, aid);
                                dbStatement.execute();
                            }else{count-=1;}
                            String sql, sql_select;
                            sql="UPDATE answer SET vote = ? WHERE id_answer = ? ";

                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            dbStatement.setInt(1, count);
                            dbStatement.setInt(2, aid);
                            dbStatement.execute();

                            sql="INSERT INTO vote_answer (id_answer, username,value) VALUES (?,?,-1)";
                            PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                            dbStatement2.setInt(1, aid);
                            dbStatement2.setString(2, username);
                            dbStatement2.execute();

                            stmt.close();
                            conn.close();
                        }
                        catch(SQLException ex) {
                           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
                        }
                    }
                }
            }
        }
        return count;
    }
    @WebMethod(operationName = "isVoted")
    public Boolean isVoted(@WebParam(name = "aid") int aid,@WebParam(name = "token") String token,
            
            @WebParam(name = "userAgent") String userAgent,
            @WebParam(name = "ip") String ip){
        Boolean status = false;
        conn = db.connect();
        
        String username = auth.checkToken(token,userAgent,ip);
        if(!username.equals("-999")){
            if(!username.equals("-999")){
                if(!username.equals("-998")){//user agent
                    if(!username.equals("-997")){//ip
                        try {
                           Statement stmt;
                           stmt = conn.createStatement();

                           String sql;
                           sql="Select * from vote_answer where id_answer = ? and username = ?";

                           PreparedStatement dbStatement = conn.prepareStatement(sql);
                           dbStatement.setInt(1, aid);
                           dbStatement.setString(2, username);

                           ResultSet rs;
                           rs = dbStatement.executeQuery();

                           /* Get every data returned by SQLquery */
                           while(rs.next()) {
                               status= true;
                           }

                           stmt.close();
                           conn.close();
                        }
                        catch(SQLException ex) {
                           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
                        }
                    }
                }
            }
        }
        return status;
    }
     public Boolean isVoteUp(@WebParam(name = "aid") int aid,@WebParam(name = "username") String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_answer where id_answer = ? and username = ? and value = 1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            dbStatement.setString(2, username);

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                status= true;
            }
            
            stmt.close();
            //conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    public Boolean isVoteDown(@WebParam(name = "aid") int aid,@WebParam(name = "username") String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_answer where id_answer = ? and username = ? and value = -1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            dbStatement.setString(2, username);

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                status= true;
            }
            
            stmt.close();
            //conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    @WebMethod(operationName = "getVoteById")
    @WebResult(name = "vote")
    public int getVoteById(@WebParam(name = "aid") int aid) {
        int count= 0;
        conn = db.connect();
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT vote FROM answer where id_answer = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                count = rs.getInt("vote");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(AnswerWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return count;
    }
    
    public Boolean isValidToken(@WebParam(name = "token") String token,
            
            @WebParam(name = "userAgent") String userAgent,
            @WebParam(name = "ip") String ip){
        String username= auth.checkToken(token,userAgent,ip);
        if(username.equals("-999"))
              return false; 
        else return true;
    }
}
