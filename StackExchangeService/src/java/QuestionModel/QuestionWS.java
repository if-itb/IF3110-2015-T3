/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;


import Authentication.Auth;
import Config.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author FiqieUlya
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    private final DB db = new DB();
    private final Auth auth= new Auth();
    private Connection conn;
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
    @WebMethod(operationName = "getQuestionByQID")
    @WebResult(name = "Question")
    public List<Question> getQuestionByQID(@WebParam(name = "qid") int qid) {
        
        List<Question> question = new ArrayList<Question>();
        
        conn = db.connect();
        
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM question where id_question = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);

            ResultSet rs;
            rs = dbStatement.executeQuery();            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                question.add( new Question(
                    rs.getInt("id_question"),
                    rs.getInt("vote"),
                    rs.getString("topic"),    
                    rs.getString("content"),
                    rs.getString("date"),
                    rs.getString("username")));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return question;
    }
    
    @WebMethod(operationName = "createQuestion")
    public  Boolean createQuestion(
            @WebParam(name = "topic") String topic,
            @WebParam(name = "token") String token,
            @WebParam(name = "content") String content){
   
        Boolean status = true;
        String uname = auth.checkToken(token);
        status = false;
        if(!uname.equals("-999")){
            try {
                conn = db.connect();
                Statement stmt;
                stmt = conn.createStatement();
                String sql;
                sql = "INSERT INTO question (topic, username,content,vote,date)VALUES (?, ?, ?, 0, ?)";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, topic);
                dbStatement.setString(2, uname);
                dbStatement.setString(3, content);
                dbStatement.setString(4, getCurrentTimeStamp());

                status= dbStatement.execute();

                stmt.close();
                conn.close();
            }
            catch(SQLException ex) {
               Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return status;
    }
    
    @WebMethod(operationName = "getQuestions")
    @WebResult(name = "Questions")
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<Question>();
        conn = db.connect();
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM question ORDER BY date DESC";
            PreparedStatement dbStatement = conn.prepareStatement(sql);

            ResultSet rs;
            rs = dbStatement.executeQuery();            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
             int temp = getAnswerById(rs.getInt("id_question"));
                questions.add( new Question(
                    temp,
                    rs.getInt("id_question"),
                    rs.getInt("vote"),
                    rs.getString("topic"),    
                    rs.getString("content"),
                    rs.getString("date"),
                    rs.getString("username")));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return questions;
    }
    
    @WebMethod(operationName = "getVoteById")
    @WebResult(name = "vote")
    public int getVoteById(@WebParam(name = "qid") int qid) {
        int count= 0;
        conn = db.connect();
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT vote FROM question where id_question = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
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
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return count;
    }
    @WebMethod(operationName = "getAnswerById")
    public int getAnswerById(@WebParam(name = "qid") int qid) {
        int count= 0;
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
                count++;
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return count;
    }
    @WebMethod(operationName = "voteUp")
    public int voteUp(@WebParam(name = "qid") int qid,@WebParam(name = "token") String token ){
        int count;
        count=getVoteById(qid);
        String username= auth.checkToken(token);
        if((!username.equals("-999"))&&(!isVoteUp(qid, username))){
            try {
                conn = db.connect();
                Statement stmt;
                stmt = conn.createStatement();
                //pernah vote down
                if(isVoteDown(qid,username)){
                    count+=2;
                    String sql;
                    sql="DELETE FROM vote_question WHERE id_question = ?";
                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.execute();
                }
                else{count+=1;}
                String sql, sql_select;
                sql="UPDATE question SET vote = ? WHERE id_question = ? ";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, count);
                dbStatement.setInt(2, qid);
                dbStatement.execute();
                sql="INSERT INTO vote_question (id_question, username,value) VALUES (?,?,1)";
                PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                dbStatement2.setInt(1, qid);
                dbStatement2.setString(2, username);
                dbStatement2.executeUpdate();

                stmt.close();
                conn.close();
            }
            catch(SQLException ex) {
               Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return count;
    }
    @WebMethod(operationName = "voteDown")
    public int voteDown(@WebParam(name = "qid") int qid,@WebParam(name = "token") String token ){
        int count;
        count=getVoteById(qid);
        String username= auth.checkToken(token);       
        if((!username.equals("-999"))&&(!isVoteDown(qid, username))){
            try {
                conn = db.connect();
                Statement stmt;
                stmt = conn.createStatement();
                if(isVoteUp(qid,username)){
                    count-=2;
                    String sql;
                    sql="DELETE FROM vote_question WHERE id_question = ?";
                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.execute();
                }else{count-=1;}
                String sql, sql_select;
                sql="UPDATE question SET vote = ? WHERE id_question = ? ";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, count);
                dbStatement.setInt(2, qid);
                dbStatement.execute();

                sql="INSERT INTO vote_question (id_question, username,value) VALUES (?,?,-1)";
                PreparedStatement dbStatement2 = conn.prepareStatement(sql);
                dbStatement2.setInt(1, qid);
                dbStatement2.setString(2, username);
                dbStatement2.execute();

                stmt.close();
                conn.close();
            }
            catch(SQLException ex) {
               Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return count;
    }
    
    @WebMethod(operationName = "deleteQuestionById")
    public Boolean deleteQuestionById(@WebParam(name = "qid") int qid,@WebParam(name = "token") String token){
        int count=0;
        
        Boolean status= true;
        String username= auth.checkToken(token);
        if((!username.equals("-999"))&&(validateUsername(qid, username))){           
            try {
               Statement statement;
               conn = db.connect();
               statement = conn.createStatement();
               
               String sql;
               
               sql = "DELETE FROM question where id_question = ?";
               
               PreparedStatement dbStatement = conn.prepareStatement(sql);
               dbStatement.setInt(1, qid);
               status=dbStatement.execute();
               
               statement.close();
               conn.close();
            }
            catch(SQLException ex) {
               Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return status;
    }
    @WebMethod(operationName = "isVoted")
    public Boolean isVoted(@WebParam(name = "qid") int qid,@WebParam(name = "username") String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_question where id_question = ? and username = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
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
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
     @WebMethod(operationName = "searchQuestion")
    public List<Question> searchQuestion(@WebParam(name = "keyword") String keyword){
        List<Question> questions = new ArrayList<Question>();
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="SELECT * FROM question WHERE topic LIKE ? OR content LIKE ? ORDER BY Date DESC";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, "%" + keyword + "%");
            dbStatement.setString(2, "%" + keyword + "%");

            ResultSet rs;
            rs = dbStatement.executeQuery();
            
             while(rs.next()) {
             int temp = getAnswerById(rs.getInt("id_question"));
                questions.add( new Question(
                    temp,
                    rs.getInt("id_question"),
                    rs.getInt("vote"),
                    rs.getString("topic"),    
                    rs.getString("content"),
                    rs.getString("date"),
                    rs.getString("username")));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return questions;
    }
    @WebMethod(operationName = "getUsernameByQid")
     public String getUsernameByQid(@WebParam(name = "qid") int qid) {
        conn = db.connect();
        String uname="";
        try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT username FROM question where id_question = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs;
            rs = dbStatement.executeQuery();
            
            /* Get every data returned by SQLquery */
            while(rs.next()) {
                uname=rs.getString("username");
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        return uname;
    }
    public Boolean isVoteUp(@WebParam(name = "qid") int qid,@WebParam(name = "username") String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_question where id_question = ? and username = ? and value = 1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
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
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    public Boolean isVoteDown(@WebParam(name = "qid") int qid,@WebParam(name = "username") String username){
        Boolean status = false;
        conn = db.connect();
        
         try {
            Statement stmt;
            stmt = conn.createStatement();
            
            String sql;
            sql="Select * from vote_question where id_question = ? and username = ? and value = -1";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
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
           Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
        }
        return status;
    }
    public Boolean validateUsername(@WebParam(name = "qid") int qid
             ,@WebParam(name = "username") String uname){
         Boolean status= false;
         
         String username = getUsernameByQid(qid);
         if(username.equals(uname)){
             status= true;
         }
         
         return status;
    }
     
    @WebMethod(operationName = "editQuestion")
    public  Boolean editQuestion(
            @WebParam(name = "qid") int qid,
            @WebParam(name = "topic") String topic,
            @WebParam(name = "token") String token,
            @WebParam(name = "content") String content){
        
        Boolean status = true;
        String username = auth.checkToken(token);
        if((!username.equals("-999"))&&(validateUsername(qid, username))){
            try {
                Statement stmt;
                conn = db.connect();
                stmt = conn.createStatement();
                String sql;
                sql = "UPDATE question SET topic = ? , content= ?, date = ? WHERE id_question = ?";

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, topic);
                dbStatement.setString(2, content);
                dbStatement.setString(3, getCurrentTimeStamp());
                dbStatement.setInt(4, qid);

                dbStatement.execute();

                stmt.close();
                conn.close();
            }
            catch(SQLException ex) {
               Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }
        return status;
    }
    
    public Boolean isValidToken(@WebParam(name = "token") String token){
        String username= auth.checkToken(token);
        if(username.equals("-999"))
              return false; 
        else return true;
    }
}

