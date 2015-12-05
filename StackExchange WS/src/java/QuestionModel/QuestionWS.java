/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author adek
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createQuestion")
    public int createQuestion(@WebParam(name = "token") String token, @WebParam(name = "title") String title, @WebParam(name = "content") String content) throws ParseException {
        Form form = new Form();
        form.param("token",token);
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8082/Identity_Service/CheckToken"; 
        String result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jobj = (JSONObject) obj;
        String message = (String) jobj.get("message");
        System.out.println(message);
        int ret = 1;
        if(message.equals("valid")) {
            try {         
                System.out.println("success!!");
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                String sql = "INSERT INTO questions(id_user,title,content,vote) VALUES ((select userid from tokens where token='"+token+"'),'"+title+"','"+content+"',0)";
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                ret = 1;
            } catch(Exception e) {}   
        }
        else if(message.equals("expired")) {
                System.out.println("expred!");
            ret = 0;
        }
        else if(message.equals("invalid")) {
                System.out.println("invalid!");
            ret = -1;
        }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "GetAllQuestion")
    public java.util.ArrayList<Question> GetAllQuestion() {
        ArrayList<Question> Questions = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            Statement stmt = (Statement) conn.createStatement();
            String sql = "SELECT * FROM questions";
            PreparedStatement dbStatement = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet results = dbStatement.executeQuery();
            /* Get every data returned by SQL query */
            int i = 0;
            while(results.next()){
                Questions.add(new Question( results.getInt("id"),
                    results.getInt("id_user"),
                    results.getString("title"),
                    results.getString("content"),
                    results.getString("timestamp"),
                    results.getInt("vote")
                ));
                ++i;
            }
            results.close();
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "GetQuestionByID")
    @WebResult(name="QuestionByID")
    public Question GetQuestionByID(@WebParam(name = "id") int id) {
        Question q = new Question();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            Statement stmt = (Statement) conn.createStatement();
            String sql = "SELECT * FROM questions WHERE id=?";
            PreparedStatement dbStatement = (PreparedStatement) conn.prepareStatement(sql);
            dbStatement.setInt(1,id);
            ResultSet results = dbStatement.executeQuery();
            if(results.next()){
                q=(new Question( results.getInt("id"),
                    results.getInt("id_user"),
                    results.getString("title"),
                    results.getString("content"),
                    results.getString("timestamp"),
                    results.getInt("vote")
                ));
            }
            results.close();
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editQuestion")
    public int editQuestion(@WebParam(name = "title") String title, @WebParam(name = "content") String content, @WebParam(name = "qid") int qid, @WebParam(name = "token") String token) throws ParseException {
        Form form = new Form();
        form.param("token",token);
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8082/Identity_Service/CheckToken"; 
        String result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jobj = (JSONObject) obj;
        String message = (String) jobj.get("message");
        System.out.println(message);
        int ret = 1;
        if(message.equals("valid")) {
            try {         
                System.out.println("success edit!!");
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                String sql = "UPDATE questions SET title='"+title+"', content='"+content+"' WHERE id="+qid;
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                ret = 1;
            } catch(Exception e) {}   
        }
        else if(message.equals("false-agent")) {
                System.out.println("false-agent!!");
            ret = -2;
        }
        else if(message.equals("false-ipaddr")) {
                System.out.println("false-ipaddr!!");
            ret = -3;
        }
        else if(message.equals("expired")) {
                System.out.println("expired!");
            ret = 0;
        }
        else if(message.equals("invalid")) {
                System.out.println("invalid!");
            ret = -1;
        }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DeleteQuestion")
    public int DeleteQuestion(@WebParam(name = "qid") int qid, @WebParam(name = "token") String token) throws ParseException {
        Form form = new Form();
        form.param("token",token);
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8082/Identity_Service/CheckToken"; 
        String result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jobj = (JSONObject) obj;
        String message = (String) jobj.get("message");
        System.out.println(message);
        int ret = 1;
        if(message.equals("valid")) {
            try {         
                System.out.println("success edit!!");
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                String sql = "DELETE FROM questions WHERE id="+qid;
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                ret = 1;
            } catch(Exception e) {}   
        }
        else if(message.equals("expired")) {
                System.out.println("expred!");
            ret = 0;
        }
        else if(message.equals("invalid")) {
                System.out.println("invalid!");
            ret = -1;
        }
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "voteQuestion")
    public int voteQuestion(@WebParam(name = "userid") int userid, @WebParam(name = "qid") int qid, @WebParam(name = "stat") int stat, @WebParam(name = "token") String token) throws ParseException {
        Form form = new Form();
        form.param("token",token);
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8082/Identity_Service/CheckToken"; 
        String result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result);
        JSONObject jobj = (JSONObject) obj;
        String message = (String) jobj.get("message");
        System.out.println(message);
        int ret = 1;
        if(message.equals("valid")) {
            try {         
                System.out.println("success vote!!");
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                String sql = "SELECT id FROM voteQuestion WHERE id_user =" + userid + " && id_question = " + qid;
                PreparedStatement dbs = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = dbs.executeQuery();
                int id = 0,unicFlag=0;
                if(!rs.next()) {
                    unicFlag=1;
                    sql = "INSERT INTO voteQuestion(id_user,id_question,status) VALUES ("+userid+","+qid+","+stat+")";
                    java.sql.Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                }
                else {
                    id = rs.getInt("id");
                    sql = "SELECT id FROM voteQuestion WHERE id_user =" + userid + " && id_question = " + qid + "&& status = " + stat;
                    dbs = (PreparedStatement) conn.prepareStatement(sql);
                    rs = dbs.executeQuery();
                    if(!rs.isBeforeFirst()) {
                        unicFlag=1;
                        sql = "UPDATE voteQuestion SET status='"+stat+"' WHERE id="+id;
                        java.sql.Statement stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                    }
                }
                if(unicFlag==1) {
                    //get current vote value
                    sql = "SELECT vote FROM questions WHERE id =" + qid;
                    dbs = (PreparedStatement) conn.prepareStatement(sql);
                    rs = dbs.executeQuery();
                    int vote =0;
                    if(rs.next())
                        vote = rs.getInt("vote")+stat;
                    //update vote value
                    sql = "UPDATE questions SET vote='"+vote+"' WHERE id="+qid;
                    java.sql.Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                }
                ret = 1;
            } catch(Exception e) {}   
        }
        else if(message.equals("expired")) {
                System.out.println("expred!");
            ret = 0;
        }
        else if(message.equals("invalid")) {
                System.out.println("invalid!");
            ret = -1;
        }
        return ret; 
    }
}
