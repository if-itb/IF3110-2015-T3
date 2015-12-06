

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.*;
/**
 *
 * @author Edwin
 */
public class Authentication extends HttpServlet {
   
    //attr
    private String user_id;
    private String name; 
    private String email;
    private String create_time;
    private String is_valid;
    
    // element of token
    private String random_string;
    private String user_agent;
    private String ip_address;
    
    private String token;
    
    //info of user agent and ip address from sender
    private String realUserAgent;
    private String realIpAddress;
    
    //timeout constant
    static final int timeout = 60; // timeout in seconds
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/stackx";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
   
    public void getUser(){
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
            sql = "SELECT * FROM user_token INNER JOIN user ON user.user_id=user_token.user_id WHERE random_string='" + random_string + "' AND user_agent='" + user_agent +"' AND ip_address='" + ip_address + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            // check token is on DB or not
            if(!rs.next()){ //INVALID TOKEN, not found in DB
                
                name = "";
                email = "";
                user_id = "";
                create_time= "";
                is_valid ="-2"; // token rejected, invalid token
            }
            else{ //token valid, next checking...
                
                // check validity of user agent and IP address
                user_agent = rs.getString("user_agent");
                ip_address = rs.getString("ip_address");
                
                if(!isUserAgentValid(realUserAgent, user_agent)){
                    name = "";
                    email = "";
                    user_id = "";
                    is_valid ="-3"; // diff user agent
                }
                else if(!isIpAddressValid(realIpAddress, ip_address)){
                    name = "";
                    email = "";
                    user_id = "";
                    is_valid ="-4"; // diff IP address
                }
                else{
                    create_time = rs.getString("create_time");

                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    java.util.Date last_active = formatter.parse(create_time);
                    java.util.Date now = new java.util.Date();

                    //count duration in seconds
                    long duration = (now.getTime() - last_active.getTime())/1000;

                    if(duration < timeout){
                        name = rs.getString("name");
                        email = rs.getString("email");
                        user_id = rs.getInt("user_id") + "";
                        is_valid = "0"; // token accepted

                        //update create_time
                        java.util.Date temp = new java.util.Date(); 
                        create_time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(temp);
                        sql = "UPDATE user_token SET create_time='" + create_time + "' WHERE user_id=" + Integer.parseInt(user_id) + " AND random_string='" + random_string + "'";
                        int res = stmt.executeUpdate(sql);
                    }
                    else{ // token expired, new login required
                        name = "";
                        email = "";
                        user_id = "";
                        is_valid ="-1"; // token expired
                    }
                }
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
        
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
          
            //parsing json input format
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(jb.toString());
                JSONObject input = (JSONObject) obj;
                token = (String) input.get("token"); // full token
                
                //decode token
                token = URLDecoder.decode(token, "UTF-8");
                
                realUserAgent = (String) input.get("user_agent"); // real user agent
                realIpAddress = (String) input.get("ip_address"); // real ip address
                
                String temp[] = token.split("#");
                random_string = temp[0];
                user_agent = temp[1];
                ip_address = temp[2];
                
                // check user agent and ip address in token is valid
                
                getUser();
                
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    //out.println(date);
                    //JSON output format          
                    JSONObject output = new JSONObject();
                    output.put("user_id", user_id);
                    output.put("name", name);
                    output.put("email", email);
                    //output.put("create_time", create_time);
                    output.put("is_valid", is_valid);
                    out.println(output);

                }
            
            } catch (ParseException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          
        } catch (Exception e) { /*report an error*/ }
        
    }
    
    public boolean isUserAgentValid(String realUserAgent, String user_agent){
        return (realUserAgent.equals(user_agent));
    }
    
    public boolean isIpAddressValid(String realIpAddress, String ip_address){
        return(realIpAddress.equals(ip_address));
    }
}
