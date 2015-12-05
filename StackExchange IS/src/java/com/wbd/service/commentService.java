/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.service;

import com.wbd.tokenChecker.TokenChecker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
@WebServlet(name = "commentService", urlPatterns = {"/commentService"})
public class commentService extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet comment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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

        System.out.println("GET Started");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        
        System.out.println("Hello World");
        
        System.out.println(jb.toString());
        try {
               System.out.println("Hello World2");
 
            JSONParser parser = new JSONParser();
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            int qid = Integer.parseInt(obj.get("question_id").toString());
            
        
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull","root","");
                Statement stmt = conn.createStatement();
                String sql;
                sql = "SELECT user.Nama, comment_question.Comment FROM comment_question NATURAL JOIN user WHERE IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,qid);
                ResultSet rs = dbStatement.executeQuery();
                int i = 0;

                JSONArray listOfComment = new JSONArray();

                while (rs.next()){
                    JSONObject comment = new JSONObject();
                    comment.put("firstName", "John");
                    comment.put("lastName", "Doe");
                    listOfComment.add(comment);        
                }

                JSONObject mainObj = new JSONObject();
                mainObj.put("comments", listOfComment);
                out.println(mainObj);

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex){
                //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
   
    }   catch (ParseException ex) {
            Logger.getLogger(comment.class.getName()).log(Level.SEVERE, null, ex);
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
        //Connection conn = null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        
        System.out.println(jb.toString());
        try {
            System.out.println("Hello World");
            JSONParser parser = new JSONParser();
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            String access_token = (String) obj.get("access_token");
            int qid = Integer.parseInt(obj.get("question_id").toString());
            int userid = Integer.parseInt(obj.get("user_id").toString());
            String comment = (String) obj.get("comment");
            int message = 0;
            TokenChecker token_check = new TokenChecker();
            token_check.check(access_token);
            if (token_check.getExpired() == 1){
                message = -2; //Expired
            }
            try {
                if (token_check.getValid() == 1){
                    //Can access. Right Identity
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                    Statement stmt = conn.createStatement();
                    String sql = "INSERT INTO comment_question(IDQ, IDUser, Comment) VALUES (?,?,?)";
                    PreparedStatement dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.setInt(2, userid);
                    dbStatement.setString(3, comment);
                    dbStatement.executeUpdate();
                    stmt.close();
                    message = 1;
                    conn.close();
                }else{
                    //Wrong identity. Something wrong
                    message = -1;                
                } 

            } catch (Exception ex){
                ex.printStackTrace();

            }
            
            JSONObject objOut = new JSONObject();
            objOut.put("message", message);
            out.println(objOut);
        } catch (ParseException ex) {
            Logger.getLogger(comment.class.getName()).log(Level.SEVERE, null, ex);
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
