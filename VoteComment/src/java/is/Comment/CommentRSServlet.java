/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.Comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import vc.auth.Auth;

/**
 *
 * @author Asus
 */
public class CommentRSServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String Comment = null;
        String Name = null;
        try {
            int qid = Integer.parseInt(request.getParameter("qid"));
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            // getting the commenter's name
            sql = "SELECT * FROM comments WHERE QuestionID = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            ResultSet rsComments = dbStatement.executeQuery();
            JSONObject objOut = new JSONObject();
            JSONArray jarr = new JSONArray();
            
            while(rsComments.next()) {
                JSONObject jobj = new JSONObject(); 
                jobj.put("name", rsComments.getString("Name"));
                jobj.put("comment", rsComments.getString("Content"));
                jobj.put("time", rsComments.getTimestamp("Datetime").toString());
                jarr.put(jobj);
            }
            objOut.put("comments", jarr);
            out.println(objOut);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String currentEmail = null;
        String Name = null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        System.out.println(jb.toString());
        try {
            JSONParser parser = new JSONParser();
            
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            String comment = (String) obj.get("comment");
            int qid = Integer.parseInt(obj.get("qid").toString());
            String token = (String) obj.get("access_token");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            // getting the commenter's name
            /*sql = "SELECT Email FROM sessions WHERE AccessToken = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            System.out.println(dbStatement.toString());
            ResultSet rsEmail = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan get email
            if(rsEmail.next()) {
                //returnExecution = returnExecution + 1;
                currentEmail = rsEmail.getString("Email");
            }*/
            
            Auth auth = new Auth();
            currentEmail = auth.getEmail(token);
            
            sql = "SELECT Name FROM users WHERE Email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, currentEmail);
            ResultSet rsName = dbStatement.executeQuery();
            if(rsName.next()) {
                Name = rsName.getString("Name");
            }
            
            
            sql = "INSERT INTO comments (QuestionID, Name, Content, Datetime) VALUES (?,?,?,NOW())";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, Name);
            dbStatement.setString(3, comment);
            
            int res = dbStatement.executeUpdate();
            if (res == 1) {
                JSONObject objOut = new JSONObject();
                objOut.put("name", Name);
                objOut.put("comment", comment);
                objOut.put("qid", qid);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                objOut.put("time", time.toString());
                out.println(objOut);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommentRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
