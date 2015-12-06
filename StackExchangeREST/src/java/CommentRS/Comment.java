/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommentRS;


import Auth.Auth;
import Database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Vanji
 */
public class Comment extends HttpServlet {
    DB db = new DB();
    Connection conn = db.connect();
    
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        try {
            
            String qid = request.getParameter("qid");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;

            sql = "SELECT *FROM "
                    + "comment NATURAL JOIN user WHERE "
                    + "q_id = ? AND comment.u_id = user.u_id";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, Integer.parseInt(qid));

            JSONArray ret = new JSONArray();
            JSONObject output = new JSONObject();
            ResultSet rs = dbStatement.executeQuery();
            while(rs.next()) {
              JSONObject obj = new JSONObject();
              obj.put("u_name", rs.getString("u_name"));
              obj.put("c_content", rs.getString("c_content"));
              obj.put("c_time", rs.getTimestamp("c_time").toString());
              ret.put(obj);

            } 
            
            output.put("commentList",ret);
            out.println(output); 

         } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        StringBuffer sb = new StringBuffer();
        String line= null;
        
        try {
            BufferedReader reader = request.getReader();
            while ((line=reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (Exception e) {
        out.println(e);}
        
        try {
            JSONParser parser = new JSONParser();
            Object temp = parser.parse(sb.toString());
            JSONObject input = (JSONObject) temp;
            String token = (String) input.get("token");
            int qid = Integer.parseInt(input.get("qid").toString());
            int uid = Integer.parseInt(input.get("uid").toString());
            String content = (String) input.get("content");
            String uname = (String) input.get("uname");
      
            Auth auth = new Auth();
            int ret = auth.validation(token);
            if ( ret == 1 ) {     
                    Statement stmt = conn.createStatement();
                    String sql;
                    sql = "INSERT INTO comment (q_id, u_name, u_id, c_content) VALUES (?, ?, ?, ?)";
                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.setString(2, uname);
                    dbStatement.setInt(3, uid);
                    dbStatement.setString(4, content);

                    dbStatement.executeUpdate();
                    
                    

                    JSONObject output = new JSONObject();
                    output.put("u_name",uname);
                    output.put("c_content",content);
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    output.put("c_time",time.toString());
                     stmt.close(); 
                    out.println(output);   
                    
                    
                }  
             else {
              obj.put("error", "validation");  
              out.print(obj);        
            }
            
        }
        catch (SQLException ex) {
                   
                } catch (ParseException ex) {     
            Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
