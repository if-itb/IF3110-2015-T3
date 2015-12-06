

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author Edwin
 */
public class Request extends HttpServlet {
    
     //attr
    private String random_string;
    private String user_agent;
    private String ip_address;
    private String create_time;
    private String is_valid;
    
    private String token;
    
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/stackx";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
    private Object yourString;
   
   public void createNewToken(String email, String password){
        Connection conn = null;
        Statement stmt = null;
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           stmt = conn.createStatement();
           
           String sql;
           sql = "SELECT user_id FROM user WHERE email ='" + email + "' AND password='" + password + "'";
           ResultSet rs = stmt.executeQuery(sql);
           if(rs.next()){
                int user_id = rs.getInt("user_id");
                random_string = getRandomString();
                
                java.util.Date temp = new Date(); 
                create_time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(temp);
                
                is_valid ="1";
                
                // append user_agent and ip_address to random_string
                String original = user_agent+ip_address;
                random_string += this.getMD5Hash(original);
                
                //check if current user_id has invalid token
                sql = "SELECT * FROM user_token WHERE user_id=" + user_id +" AND user_agent='" + user_agent +"' AND ip_address='" + ip_address +"'";
                rs = stmt.executeQuery(sql);
                 
                if(!rs.next()){ //current user_id have no token for current user agent and IP address
                    
                    sql = "INSERT INTO user_token VALUES(" + user_id +",'" + random_string + "','" + user_agent + "','" + ip_address + "','" + create_time + "' )";
                    int res = stmt.executeUpdate(sql);
                    System.out.println(res);
                }
                else{ // current user_id have invalid token
                
                    sql = "UPDATE user_token SET random_string='" + random_string + "',user_agent='" + user_agent + "', ip_address='" + ip_address + "', create_time='" + create_time + "' WHERE user_id=" + user_id + " AND user_agent='" + user_agent +"' AND ip_address='" + ip_address +"'";;
                    int res = stmt.executeUpdate(sql);
                    System.out.println(res);
                }
                token = random_string;
                
           }
           else{ //INVALID email or password
               token = "";
               create_time ="";
               is_valid = "0";
           }
           
           //closing database
           rs.close();
           stmt.close();
           conn.close();
        }
         catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
    }
    
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // get input
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
              jb.append(line);

              //parsing json input format
            JSONParser parser = new JSONParser();
            String email, password;
            try {
                Object obj = parser.parse(jb.toString());
                JSONObject input = (JSONObject) obj;
                email = (String) input.get("email");
                password = (String) input.get("password");
                user_agent = (String) input.get("user_agent");
                ip_address = (String) input.get("ip_address");
                
                // For technical reasons
                ip_address = "127.0.0.1";
                
                createNewToken(email, password);
                
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {

                    //JSON format          
                    JSONObject output = new JSONObject();
                    output.put("token", token);
                    output.put("create_time", create_time);
                    output.put("is_valid", is_valid);
                    out.println(output);
                }
                
            } catch (ParseException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) { /*report an error*/ }
        
    }
    
    // get random string
    public String getRandomString(){
        
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        
        return randomUUIDString;
    }
    
    public String getMD5Hash(String original) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString(); // return hashed string
    }
}
