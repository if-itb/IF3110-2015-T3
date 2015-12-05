/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import Class.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tama
 */
@WebService(serviceName = "NewWebService")
public class StackWebSevice {
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost/stack";
    
        static final String USER = "root";
        static final String PASS = "" ;
        
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    @WebMethod(operationName = "countAnswer")
        public int countAnswer(@WebParam(name="qid") int qid) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            int ret = 0 ;
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT COUNT(id_a) as total FROM answer WHERE q_id="+qid;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);                
                rs.next();
                ret = rs.getInt("total");
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
    
        @WebMethod(operationName="getAllQuestions")
        public ArrayList<Question> getAllQuestions() {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            ArrayList<Question> ret = new ArrayList<Question>() ;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM question";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Question Q = new Question();
                    int aa =rs.getInt("id_q");
                    Q.id_q = aa;
                    Q.title = rs.getString("title");
                    Q.content = rs.getString("content");
                    Q.vote = rs.getInt("vote");
                    Q.date = rs.getString("date");
                    Q.username = rs.getString("username");
                    Q.email = rs.getString("email"); 
                    Q.tanswer = countAnswer(aa);
                    ret.add(Q);
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
               
        @WebMethod(operationName="getQuestions")
        public Question getQuestions(@WebParam(name="qid") int qid) {
            Question Q = new Question() ;
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM question WHERE id_q="+qid;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                ResultSetMetaData rmsd = rs.getMetaData();
                rs.next();    
                    int aa =rs.getInt("id_q");
                    Q.id_q = aa;
                    Q.title = rs.getString("title");
                    Q.content = rs.getString("content");
                    Q.vote = rs.getInt("vote");
                    Q.date = rs.getString("date");
                    Q.username = rs.getString("username");
                    Q.email = rs.getString("email"); 
                    Q.tanswer = countAnswer(aa);             
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return Q ;
        }
        
        
        @WebMethod(operationName="getAllAnswers")
        public ArrayList<Answer> getAllAnswers(@WebParam(name="id_a") int id_a) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            ArrayList<Answer> ret = new ArrayList<Answer>() ;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM answer WHERE q_id="+id_a;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                    Answer A = new Answer();
                    int aa = rs.getInt("id_a");
                    int bb = rs.getInt("q_id");
                    A.id_a = aa;
                    A.q_id = bb;
                    A.content = rs.getString("content");
                    A.vote = rs.getInt("vote");
                    A.date = rs.getString("date");
                    A.username = rs.getString("username");
                    A.email = rs.getString("email"); 
                    ret.add(A);
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
        
         @WebMethod(operationName="getAllComment")
        public ArrayList<Comment> getAllComment(@WebParam(name="id_a") int id_a) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            ArrayList<Comment> ret = new ArrayList<Comment>() ;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM comment WHERE q_id="+id_a;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                    Comment C = new Comment();
                    int aa = rs.getInt("id_c");
                    int bb = rs.getInt("q_id");
                    C.id_c = aa;
                    C.q_id = bb;
                    C.content = rs.getString("content");
                    C.date = rs.getString("date");
                    C.username = rs.getString("username");
                    ret.add(C);
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
        
        @WebMethod(operationName="register")
        public int register(String n,String e,String p) {
            int status = 0 ;
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            String list_u[][] = null ;            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM registered WHERE username='"+n+"' or email='"+e+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last();
                int size = rs.getRow();
                rs.beforeFirst();
                if (size == 0) {
                   String sql2 = "INSERT INTO registered (username,email,password) VALUES ('"+n+"','"+e+"','"+p+"')";
                   stmt = conn.createStatement();
                   stmt.executeUpdate(sql2);
                }
                else {
                    rs.next();
                    status = rs.getInt("user_id");
                }
                stmt.close();
                conn.close();
            }
            catch (Exception ee) {
                ee.printStackTrace() ;
            }
            return status ;
        }
        
        @WebMethod(operationName="addQuestion")
        public int addQuestion(String at,String t,String c,String ipa,String browser) {                
            
            int r=0;
            System.out.println("adQuesiton--------");
            try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            int status=r;
            if (r>0) {                
                Connection conn = null ; 
                Statement stmt = null ;
                ResultSet rs =null;     
                String username = getTokenOwner(at);
                try {
                    
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                    String sql1 = "SELECT MAX(id_q) as max FROM question" ;
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql1);
                    rs.next();
                    int new_id = rs.getInt("max");                    
                    new_id++;
                    status=new_id;
                    System.out.println("ID New  Question "+new_id);
                    String sql2 = "INSERT INTO question (id_q,username,email,title,content,date,vote) VALUES ('"+new_id+"','"+username+"','','"+t+"','"+c+"', CURRENT_TIMESTAMP,0)";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql2);
                    stmt.close();
                    conn.close();
                }
                catch (Exception ee) {
                    ee.printStackTrace() ;
                }
            }
            return status ;
        }
        
         @WebMethod(operationName="updateQuestion")
        public int updateQuestion(String at,int id,String t,String c,String ipa,String browser) {
           int r=0;
            System.out.println("updateQuesiton--------");
            try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            int status=r;
            if (r>0) {                
                String username = getTokenOwner(at);
                status = 1 ;
                Connection conn = null ; 
                Statement stmt = null ;
                ResultSet rs =null;           
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                    String sql = "UPDATE question SET title='"+t+"',content='"+c+"' WHERE id_q="+id;
                    stmt = conn.createStatement();                                    
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                }
                catch (Exception ee) {
                    ee.printStackTrace() ;
                }
            }
            return status ;
        }
        
         @WebMethod(operationName="deleteQuestion")
        public int deleteQuestion(String at,int id,String ipa,String browser) {
            int r=0;
            System.out.println("deleteQuesiton--------");
            try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            int status=r;
            if (r>0) {
                status = 1 ;
                Connection conn = null ; 
                Statement stmt = null ;
                ResultSet rs =null;           
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                    String sql = "DELETE FROM question WHERE id_q="+id;
                    stmt = conn.createStatement();                                    
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                }
                catch (Exception ee) {
                    ee.printStackTrace() ;
                }
            }
            return status ;
        }    
        
         @WebMethod(operationName="addAnswer")
        public int addAnswer(String at,int idq,int ida,String c,String ipa,String browser) {
            int status = 0;
            int r=0;
             try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            status=r;
            if (r>0) {
                Connection conn = null ; 
                Statement stmt = null ;
                ResultSet rs =null; 
                String username = getTokenOwner(at) ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                    String sql = "INSERT INTO `answer` (`id_a`, `q_id`, `content`, `date`, `vote`, `username`, `email`) VALUES ('"+ida+"','"+idq+"','"+c+"',CURRENT_TIMESTAMP,0,'"+username+"','')";
                    stmt = conn.createStatement();                                    
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                }
                catch (Exception ee) {
                    ee.printStackTrace() ;
                }
            }            
            return status ;
        }
        
         @WebMethod(operationName="searchQuestion")
        public ArrayList<Question> searchQuestion(String s) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            ArrayList<Question> ret= new ArrayList<Question>();
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM question WHERE title LIKE '%"+s+"%'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
               
                while (rs.next()) {
                   Question Q = new Question();
                    int aa =rs.getInt("id_q");
                    Q.id_q = aa;
                    Q.title = rs.getString("title");
                    Q.content = rs.getString("content");
                    Q.vote = rs.getInt("vote");
                    Q.date = rs.getString("date");
                    Q.username = rs.getString("username");
                    Q.email = rs.getString("email"); 
                    Q.tanswer = countAnswer(aa);
                    ret.add(Q);
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
        
        
        @WebMethod(operationName="getVote")
        public int getVote(String t,int id) {
           Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            int ret = 0 ;
            String sql;
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);  
                if (t.equals("q")) sql = "SELECT vote FROM question WHERE id_q="+id;
                else sql = "SELECT vote FROM answer WHERE id_a="+id;
                stmt= conn.createStatement();
                rs = stmt.executeQuery(sql);                
                rs.next();
                ret = rs.getInt("vote");
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
        
        @WebMethod(operationName="changeVote")
        public int changeVote(String at,String t,int id,int num) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            int status = 0;
            int r=0;
             try {
                r = statusToken(at,at,at);
            } catch (ParseException ex) {
               r=0;
            }
            status=r;
            String sql;
            if (r>0) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);  
                    sql = "SELECT * FROM voted WHERE id_v="+id+" AND id_u="+r;
                    stmt= conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    rs.last();
                    int size = rs.getRow();
                    if (size==1) status=-999;
                    else {                    
                        if (t.equals("q")) sql = "UPDATE question SET vote=(vote+"+num+") WHERE id_q="+id;
                        else sql = "UPDATE answer SET vote=(vote+"+num+") WHERE id_a="+id;
                        stmt= conn.createStatement();
                        stmt.executeUpdate(sql);
                        String kode = String.valueOf(id)+"-"+String.valueOf(r);
                        String sql2 = "INSERT INTO voted (kode,id_v,id_u) VALUES ('"+kode+"',"+id+","+r+")";
                        stmt= conn.createStatement();
                        stmt.executeUpdate(sql2);
                        stmt.close();
                        conn.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace() ;
                }
            }
            return status ;
        }
        
        @WebMethod(operationName="statusToken")
        public int statusToken(String t,String ipa,String browser) throws ParseException {
        int ret = 0;
        JSONParser parser = new JSONParser();  
        String msg="";
        try { 
            URL url = new URL("http://localhost:39854/IdentityService/ISValid");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset","UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String query = String.format("token=%s&ipa=%s&browser=%s",URLEncoder.encode(t, "UTF-8"),URLEncoder.encode(ipa, "UTF-8"),URLEncoder.encode(browser, "UTF-8"));
          
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes("UTF-8"));
            }
            InputStream istream = conn.getInputStream();
            System.out.println(istream);
      
            BufferedReader bufread = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            Object obj;
            JSONObject jsonobj;
      
            while ((output = bufread.readLine()) != null) {
              obj = parser.parse(output);
              jsonobj = (JSONObject) obj;
              msg = String.valueOf(jsonobj.get("status"));
              System.out.println("Status token (message) : |"+msg+"|");
                }      
                conn.disconnect();
                } catch (MalformedURLException e) {
                      e.printStackTrace();
                } catch (IOException e) {
                      e.printStackTrace();
               }
            ret = Integer.valueOf(msg);
            System.out.println("Status token (return) : |"+ret+"|");
            return ret;
        }
        
        @WebMethod (operationName="deleteToken") 
        public int deleteToken(String t) {
            int ret = 0;
             Connection conn = null ; 
            Statement stmt = null ;
            String sql="";
            try {                
                Class.forName("com.mysql.jdbc.Driver");
                 conn = DriverManager.getConnection(DB_URL, USER,PASS); 
                sql = "DELETE FROM token WHERE token='"+t+"'";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("deleteToken : |"+t+"|");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StackWebSevice.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StackWebSevice.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("deleteToken return : "+ret);
            return ret;
        }
        
        public String getTokenOwner(String t) {
             Connection conn =null;
            String ret ="";
            String sql ="";
            Statement stmt = null;
            ResultSet rs = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                sql = "SELECT username FROM token WHERE token='"+t+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last() ;
                int size =rs.getRow();
                if (size!=0) {
                    ret = rs.getString("username");
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
        System.out.println("getTokenOwner return :  |"+ret+"|");
        return ret ;
        }
        
}
