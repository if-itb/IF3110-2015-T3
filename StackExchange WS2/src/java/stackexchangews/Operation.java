/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchangews;
import java.io.IOException;
import java.io.PrintWriter;
import database.DB;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.servlet.http.Cookie;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Raihan
 */
@WebService(serviceName = "Operation")
@Stateless()
public class Operation {
    Connection conn = DB.connect();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "name") String name, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO USER (username, email, password) value (?,?,?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, name);
            dbStatement.setString(2, email);
            dbStatement.setString(3, password);
            
            dbStatement.executeUpdate();
            
            stmt.close();
            return true;
        }        
        catch (SQLException ex) {
            return false;    
        }
        
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createQuestion")
    public boolean createQuestion(@WebParam(name = "token") String token, @WebParam(name = "title") String title, @WebParam(name = "content") String content) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        try {
            if (Auth(token)>0) {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO QUESTION (id_user, topic, content) value (?,?,?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, getUIDbyToken(token));
            dbStatement.setString(2, title);
            dbStatement.setString(3, content);
            
            dbStatement.executeUpdate();
            
            stmt.close();
            return true;
            } else return false;
        }        
        catch (SQLException ex) {
            return false;    
        }
       
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAnswer")
    public boolean createAnswer(@WebParam(name = "token") String token, @WebParam(name = "idq") String idq, @WebParam(name = "content") String content) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        try {
            if (Auth(token)>0) {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO ANSWER (id_user, content, id_question) value (?,?,?)";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, getUIDbyToken(token));            
            dbStatement.setString(2, content);
            dbStatement.setInt(3, Integer.parseInt(idq.trim()));
            
            dbStatement.executeUpdate();
            
            stmt.close();
            return true;
            } else return false;
        }        
        catch (SQLException ex) {
            return false;    
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserbyID")
    public String getUserbyID(@WebParam(name = "id") int id) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT username FROM USER where id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);            
            
            ResultSet rs = dbStatement.executeQuery();
            rs.next();
            String name = rs.getString("username");
            
            stmt.close();
            return name;
        }        
        catch (SQLException ex) {
            return null;   
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "vote")
    public void vote(@WebParam(name = "token") String token, @WebParam(name = "mode") boolean mode, @WebParam(name = "idq") String idq, @WebParam(name = "val") int val) throws IOException, UnsupportedEncodingException, MalformedURLException, ParseException {
        try {
            if (Auth(token)>0 && canVote(Integer.toString(getUIDbyToken(token)),mode,idq)) {
            Statement stmt = conn.createStatement();
            String sql;
            if (mode) {
                sql = "INSERT INTO VOTE_QUESTION (id_user, id_question, type) value (?,?,?)";                         
            } else {
                sql = "INSERT INTO VOTE_ANSWER (id_user, id_answer, type) value (?,?,?)";
            }
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, getUIDbyToken(token));            
            dbStatement.setInt(2, Integer.parseInt(idq.trim()));
            dbStatement.setInt(3, val);

            dbStatement.executeUpdate();

            stmt.close();                  
            }
        }        
        catch (SQLException ex) {
             
        }        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "canVote")
    public boolean canVote(@WebParam(name = "idus") String idus, @WebParam(name = "mode") boolean mode, @WebParam(name = "idqa") String idqa) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            if (mode) {
                sql = "SELECT * FROM VOTE_QUESTION WHERE ID_USER = ? AND ID_QUESTION = ?";                         
            } else {
                sql = "SELECT * FROM VOTE_ANSWER WHERE ID_USER = ? AND ID_ANSWER = ?";
            }
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            getUIDbyToken(idus);
            dbStatement.setInt(1, Integer.parseInt(idus.trim()));            
            dbStatement.setInt(2, Integer.parseInt(idqa.trim()));


            ResultSet rs = dbStatement.executeQuery();
            
            boolean temp = !rs.next();            
            
            stmt.close();                  
            
            return temp;
        }        
        catch (SQLException ex) {
             return false;
        }    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "countVote")
    public int countVote(@WebParam(name = "mode") boolean mode, @WebParam(name = "id") String id) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            if (mode) {
                sql = "SELECT SUM(TYPE) AS SUM FROM VOTE_QUESTION WHERE ID_QUESTION = ?";                         
            } else {
                sql = "SELECT SUM(TYPE) AS SUM FROM VOTE_ANSWER WHERE ID_ANSWER = ?";
            }
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, Integer.parseInt(id.trim()));                        


            ResultSet rs = dbStatement.executeQuery();
            
            int temp;
            if (rs.next()) {
                temp = rs.getInt("SUM");
            } else {
                temp = 0;
            }
            
            stmt.close();                  
            
            return temp;
        }        
        catch (SQLException ex) {
             return 0;
        }    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "countAnswer")
    public int countAnswer(@WebParam(name = "idq") String idq) {
        try {
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT COUNT(*) AS COUNT FROM ANSWER WHERE ID_QUESTION = ?";                         

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, Integer.parseInt(idq.trim()));                        


            ResultSet rs = dbStatement.executeQuery();
            
            int temp;
            if (rs.next()) {
                temp = rs.getInt("COUNT");
            } else {
                temp = 0;
            }
            
            stmt.close();                  
            
            return temp;
        }        
        catch (SQLException ex) {
             return 0;
        }    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserbyToken")
    public String getUserbyToken(@WebParam(name = "token") String token) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_user FROM USER_TOKEN where token = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);            
            
            ResultSet rs = dbStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id_user");            
            
            sql = "SELECT username FROM USER where id = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, id);             
            
            rs = dbStatement.executeQuery();
            rs.next();
            String name = rs.getString("username");              
            
            stmt.close();
            
            return name;
        }        
        catch (SQLException ex) {
            return null;   
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUIDbyToken")
    public int getUIDbyToken(@WebParam(name = "token") String token) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_user FROM USER_TOKEN where token = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);            
            
            ResultSet rs = dbStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id_user");                                               
            stmt.close();
            
            return id;
        }        
        catch (SQLException ex) {
            return -1;   
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUIDbyQID")
    public int getUIDbyQID(@WebParam(name = "qid") int qid) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_user FROM question where id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);            
            
            ResultSet rs = dbStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id_user");                                               
            stmt.close();
            
            return id;
        }        
        catch (SQLException ex) {
            return -1;   
        }
    }    
    @WebMethod(operationName = "getUIDbyAID")
    public int getUIDbyAID(@WebParam(name = "Aid") int aid) {
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_user FROM answer where id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, aid);            
            
            ResultSet rs = dbStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id_user");                                               
            stmt.close();
            
            return id;
        }        
        catch (SQLException ex) {
            return -1;   
        }
    }        
    
    
    @WebMethod(operationName = "deleteQ")
    public boolean deleteQ(@WebParam(name = "token") String token, @WebParam(name = "idq") int idq) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        try {
            if (Auth(token)>0 &&  getUIDbyToken(token)==getUIDbyQID(idq)) {
            Statement stmt = conn.createStatement();
            String sql;            
            sql = "DELETE FROM ANSWER WHERE id_question = ?";                         

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, idq);                        

            dbStatement.executeUpdate();

            sql = "DELETE FROM question WHERE id = ?";                         

            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, idq);                        

            dbStatement.executeUpdate();
                                    
            stmt.close();                  
            return true;
            } else 
                return true;
        }        
        catch (SQLException ex) {
             return false;
        }      
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteA")
    public boolean deleteA(@WebParam(name = "token") String token, @WebParam(name = "ida") int ida) throws MalformedURLException, ParseException, IOException {
        if (Auth(token)>0 && getUIDbyToken(token)==getUIDbyAID(ida)) {
        try {
            Statement stmt = conn.createStatement();
            String sql;            
            sql = "DELETE FROM ANSWER WHERE id = ?";                         

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, ida);                        

            dbStatement.executeUpdate();

            stmt.close();                  
            return true;
        }        
        catch (SQLException ex) {
             return false;
        }   
        } else
            return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editQ")
    public void editQ(@WebParam(name = "token") String token, @WebParam(name = "id") int id, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) throws IOException, UnsupportedEncodingException, MalformedURLException, ParseException {
        if (Auth(token)>0) {
            try {
                Statement stmt = conn.createStatement();
                String sql;                     
                sql = "UPDATE QUESTION SET topic = ?, content = ? where id = ?";                         

                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, topic);                        
                dbStatement.setString(2, content);                        
                dbStatement.setInt(3, id);                        

                dbStatement.executeUpdate();

                stmt.close();                              
            }        
            catch (SQLException ex) {

            }       
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Auth")
    public int Auth(@WebParam(name = "token") String token) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
            
            String url = "http://localhost:8080/StackExchange_IS/Validation";
            String charset = "UTF-8";            ;                        
            String query = String.format("token=%s", 
                                    URLEncoder.encode(token, charset));
            
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream res = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));            
            JSONParser parser = new JSONParser();             
            
            String output;      
            Object obj;
            JSONObject jobj = null;
            String acctoken = null ;
            while ((output = br.readLine()) != null) {                              
                obj = parser.parse(output);
                jobj = (JSONObject) obj;                    
                acctoken = (String) jobj.get("message");                       
                  
            }        
                        

            
            connection.disconnect();     
            if (acctoken.equals("valid"))
                return 1;
            else if (acctoken.equals("expired"))
                return 0;
            else
                return -1;
    }

 

}
